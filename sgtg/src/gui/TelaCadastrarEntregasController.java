package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
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

public class TelaCadastrarEntregasController implements Initializable{
	
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
	
	
	public void onBtnSalvarAction() throws SQLException{
		
		TurmasDTO turma_selecionada = choiceBoxTurma.getValue();
		String titulo = txtFieldTituloEntrega.getText();
		String descricao = txtAreaDescricao.getText();
		LocalDate data = datePickerDataFinal.getValue();
		

		new DB();
		Connection conn = DB.getConnection();
		
			
			PreparedStatement st = conn.prepareStatement("insert into entrega (titulo_entrega, data_entrega, descricao, id_turma) values (?, ?, ?, ?)");
			st.setString(1, titulo);
			st.setObject(2, data);
			st.setString(3, descricao);
			st.setInt(4, turma_selecionada.getId());
			
			st.executeUpdate();
	}
	
	
	public void onBtnCancelarAction() {
		Telas tela = new Telas();
		
		tela.loadView("/gui/TelaInicial.fxml");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		
		//Semestre e ano
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
