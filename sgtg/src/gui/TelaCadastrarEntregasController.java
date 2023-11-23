package gui;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import conexao.DB;
import dto.TipoDTO;
import gui.util.Alerts;
import gui.util.LoadTipo;
import gui.util.Telas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TelaCadastrarEntregasController implements Initializable {

	@FXML
	private Label lbSemestreAno;

	@FXML
	private ChoiceBox<TipoDTO> choiceBoxTipo = new ChoiceBox<TipoDTO>();

	@FXML
	private CheckBox check1;
	@FXML
	private CheckBox check2;

	@FXML
	private TextField txtFieldTituloEntrega;

	@FXML
	private TextArea txtAreaDescricao;

	@FXML
	private DatePicker datePickerDataFinal;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	public void onBtnSalvarAction() throws SQLException {

		DB db = new DB();
		Connection con = db.getConnection();

		// cadastro entrega

		int id_entrega = 0;
		TipoDTO tipo = choiceBoxTipo.getValue();
		String titulo = txtFieldTituloEntrega.getText();
		String descricao = txtAreaDescricao.getText();
		LocalDate data = datePickerDataFinal.getValue();
		List<String> nome_entrega = new ArrayList<String>();

		LocalDate dataAtual = LocalDate.now();

		if (tipo == null || titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null) {
			Alerts.showAlert("Campo nulo", "Cuidado", "Todos os campos devem ser preenchidos", AlertType.WARNING);
		} else if (data.isBefore(dataAtual)) {
			Alerts.showAlert("Data Anterior a Atual", "Atenção", "A data selecionada é anterior a data atual",
					AlertType.WARNING);
		} else if (check1.isSelected() && check2.isSelected()) {
			Alerts.showAlert("Case Portfólio", "Muitas turmas selecionadas",
					"Para portfólio, as entregas devem ser individuais por turma. Selecione apenas uma turma!",
					AlertType.WARNING);
		} else if (choiceBoxTipo.getValue().getTipo().equals("Portfólio") && !check1.isSelected()
				&& !check2.isSelected()) {
			Alerts.showAlert("Case Portfólio", "Selecione uma turma",
					"Para portfólio, as entregas devem ser individuais por turma. Selecione uma turma!",
					AlertType.WARNING);
		} else {

			PreparedStatement st2 = con.prepareStatement(
					"select titulo_entrega from entrega, entrega_tipo, tipo where entrega.id = entrega_tipo.id_entrega and entrega_tipo.id_tipo = tipo.id and tipo.id = ?");
			st2.setInt(1, tipo.getId());

			ResultSet result2 = st2.executeQuery();

			while (result2.next()) {
				nome_entrega.add(result2.getString("titulo_entrega"));
			}
			boolean nome_repetido = false;
			for (String nome : nome_entrega) {
				if (nome.equals(titulo)) {
					nome_repetido = true;
					break;
				}

			}
			if (nome_repetido) {
				Alerts.showAlert("Titulo de entrega", "Titulo de entrega ja cadastrado",
						"O titulo inserido já foi ultilizado", AlertType.WARNING);
			} else {

				// cadastro entrega

				CallableStatement cs = con.prepareCall("{CALL pr_insert_entrega(?, ?, ?)}");

				cs.setString(1, titulo);
				cs.setObject(2, data);
				cs.setString(3, descricao);
				ResultSet result = cs.executeQuery();
				while (result.next()) {
					id_entrega = result.getInt("id");
				}
				cs.close();

				ResultSet list_id_tipos = null;
				// relacao entrega_tipo
				if (tipo.getTipo().equals("Relatórios (disciplina e estágio)")) {
					PreparedStatement st = con.prepareStatement(
							"select id from tipo where tipo != 'Portfólio' and tipo != 'Artigo tecnológico ou cientifico'");
					list_id_tipos = st.executeQuery();
				} else {
					PreparedStatement st = con.prepareStatement("select id from tipo where tipo = ?");
					st.setString(1, tipo.getTipo());
					list_id_tipos = st.executeQuery();
				}

				while (list_id_tipos.next()) {
					int id = list_id_tipos.getInt("id");
					PreparedStatement st3 = con
							.prepareStatement("insert into entrega_tipo (id_entrega, id_tipo) values (?, ?)");
					st3.setInt(1, id_entrega);
					st3.setInt(2, id);
					st3.executeUpdate();
				}

				// relacao entrega_turma

				int semestralizacao = (LocalDate.now().getMonthValue() <= 6) ? 1 : 2;

				int anoAtual = LocalDate.now().getYear();

				PreparedStatement st7 = con
						.prepareStatement("select id, nome from turma where semestralizacao = ? and ano = ?");
				st7.setInt(1, semestralizacao);
				st7.setInt(2, anoAtual);
				ResultSet turmas = st7.executeQuery();

				if (tipo.getTipo().equals("Portfólio")) {
					
					String turma_sel;
					if (check1.isSelected()) {
						turma_sel = "TG1";
					} else {
						turma_sel = "TG2";
					}
					
					

					while (turmas.next()) {
						String turma_atual_pont = turmas.getString("nome");
						int id_turma_atual_pont = turmas.getInt("id");

						if (turma_atual_pont.equals(turma_sel)) {
							PreparedStatement st10 = con
									.prepareStatement("insert into entrega_turma(id_turma, id_entrega) values (?,?)");
							st10.setInt(1, id_turma_atual_pont);
							st10.setInt(2, id_entrega);
							st10.executeUpdate();
						}
					}

				} else {
					// ambas as turmas (tipo != portifolio
					while (turmas.next()) {
						int id_turma_atual_pont = turmas.getInt("id");
						PreparedStatement st10 = con
								.prepareStatement("insert into entrega_turma(id_turma, id_entrega) values (?,?)");
						st10.setInt(1, id_turma_atual_pont);
						st10.setInt(2, id_entrega);
						st10.executeUpdate();

					}
				}
				Alerts.showAlert("Sucesso", "Entrega cadastrada!!!", "A entrega foi cadastrada com sucesso",
						AlertType.INFORMATION);

				txtAreaDescricao.setText("");

				txtFieldTituloEntrega.setText("");

				datePickerDataFinal.setValue(null);

				choiceBoxTipo.setValue(null);

			}
		}
	}

	public void onBtnCancelarAction() {
		Telas tela = new Telas();

		tela.loadView("/gui/TelaInicial.fxml");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// Semestre e ano
		int semestralizacao = (LocalDate.now().getMonthValue() <= 6) ? 1 : 2;

		int anoAtual = LocalDate.now().getYear();

		lbSemestreAno.setText(String.format("%d° / %d", semestralizacao, anoAtual));

		// preenchimento do combobox TIPO

		List<TipoDTO> listaTipo = new ArrayList<TipoDTO>();

		try {

			// lista tipos
			listaTipo = LoadTipo.carregaTipos();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alerts.showAlert("SQLException", "Erro ao buscar turmas",
					"Ocorreu um erro ao buscar as turmas para o semestre atual.", AlertType.ERROR);
		}

		if (listaTipo != null) {
			ObservableList<TipoDTO> tipos = FXCollections.observableArrayList(listaTipo);
			choiceBoxTipo.setItems(tipos);
		}

		choiceBoxTipo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				if (newValue.getTipo().equals("Portfólio")) {
					check1.setVisible(true);
					check2.setVisible(true);
				} else {
					check1.setVisible(false);
					check2.setVisible(false);
				}
			}
		});

	}

}