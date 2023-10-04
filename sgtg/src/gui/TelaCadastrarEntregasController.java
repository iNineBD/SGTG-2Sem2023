package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TelaCadastrarEntregasController {
	
	@FXML
	private Label lbSemestreAno;
	
	@FXML
	private ChoiceBox<String> choiceBoxTurma;
	
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
	
	
	public void onBtnSalvarAction(){
		System.out.println();
	}

}
