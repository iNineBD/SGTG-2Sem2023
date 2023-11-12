package gui;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.cj.protocol.Resultset;

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

		TipoDTO tipo = choiceBoxTipo.getValue();
		String titulo = txtFieldTituloEntrega.getText();
		String descricao = txtAreaDescricao.getText();
		LocalDate data = datePickerDataFinal.getValue();
		
		if (tipo == null || titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null) {
			Alerts.showAlert("Campo nulo", "Cuidado", "Todos os campos devem ser preenchidos", AlertType.WARNING);
		} else {
			DB db = new DB();
			Connection con = db.getConnection();
			CallableStatement cs = con.prepareCall("{CALL pr_insert_entrega(?, ?, ?)}");
			
			cs.setString(1, titulo);
			cs.setObject(2, data);
			cs.setString(3, descricao);
			ResultSet result = cs.executeQuery();
			while (result.next()) {
				int id_entrega = result.getInt("id");
				System.out.println(id_entrega);
			}
			
			cs.close();
			
			
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
				if (newValue.getTipo().equals("Portifólio")) {
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
