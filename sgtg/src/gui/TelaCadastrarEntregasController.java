package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import conexao.DB;
import dto.TurmasDTO;
import gui.util.Alerts;
import gui.util.LoadTurmas;
import gui.util.Telas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TelaCadastrarEntregasController implements Initializable {

	@FXML
	private Label lbSemestreAno;

	@FXML
	private ChoiceBox<TurmasDTO> choiceBoxTurma;

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

		TurmasDTO turma_selecionada = choiceBoxTurma.getValue();
		String titulo = txtFieldTituloEntrega.getText();
		String descricao = txtAreaDescricao.getText();
		LocalDate data = datePickerDataFinal.getValue();
		List<String> nome_entrega = new ArrayList<String>();

		if (turma_selecionada == null || titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null) {
			Alerts.showAlert("Campo nulo", "Cuidado", "Todos os campos devem ser preenchidos", AlertType.WARNING);
		} else {
			LocalDate dataAtual = LocalDate.now();

			// Verifica se localData é menor do que a data atual
			if (data.isBefore(dataAtual)) {
				Alerts.showAlert("Data Anterior a Atual", "Atenção", "A data selecionada é anterior a data atual", AlertType.WARNING);
			} else {
				new DB();
				Connection conn = DB.getConnection();

				PreparedStatement st2 = conn.prepareStatement("select titulo_entrega from entrega");
				
				ResultSet result = st2.executeQuery();

				while (result.next()) {
					nome_entrega.add(result.getString("titulo_entrega"));
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
					PreparedStatement st = conn.prepareStatement(
							"insert into entrega (titulo_entrega, data_entrega, descricao, id_turma) values (?, ?, ?, ?)");
					st.setString(1, titulo);
					st.setObject(2, data);
					st.setString(3, descricao);
					st.setInt(4, turma_selecionada.getId());

					st.executeUpdate();
					
					Alerts.showAlert("Sucesso", "Entrega cadastrada!!!",
							"A entrega foi cadastrada com sucesso", AlertType.INFORMATION);
					
					txtAreaDescricao.setText("");
					
					txtFieldTituloEntrega.setText("");
					
					datePickerDataFinal.setValue(null);
					
					choiceBoxTurma.setValue(null);
					
				}

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

		// preenchimento do ChoiceBox Turma

		List<TurmasDTO> listaTurmas = new ArrayList<TurmasDTO>();

		try {

			listaTurmas = LoadTurmas.carregaTurmas(semestralizacao, anoAtual);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alerts.showAlert("SQLException", "Erro ao buscar turmas", "Ocorreu um erro ao buscar as turmas para o semestre atual.", AlertType.ERROR);
		}

		if (listaTurmas != null) {
			ObservableList<TurmasDTO> turmas = FXCollections.observableArrayList(listaTurmas);
			choiceBoxTurma.getItems().addAll(turmas);
		}

	}

}
