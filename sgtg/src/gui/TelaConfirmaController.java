package gui;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;



public class TelaConfirmaController implements Initializable{
	
	
	@FXML
	private TextField txtNome = new TextField();
	
	@FXML
	private TextField txtEmailPessoal = new TextField();
	
	@FXML
	private TextField txtEmailInstitucional = new TextField();
	
	@FXML
	private TextField txtNomeOrientador = new TextField();
	
	@FXML
	private TextField txtEmailInstitucionalOrientador = new TextField();
	
	@FXML
	private TextField txtTgMatriculado = new TextField();
	
	@FXML
	private TextField txtTipoTg = new TextField();
	
	@FXML
	private TextField txtTituloTg = new TextField();
	
	@FXML
	private TextField txtEmpresa = new TextField();
	
	@FXML
	private TextField txtDisciplina = new TextField();
	
	
	@FXML
	public Button btConfirma;
	
	@FXML
	public Button btConfirmaTodos;
	
	public String getTxtNome() {
		return txtNome.getText();
	}

	public void setTxtNome(String text) {
		txtNome.setText(text);
	}
	
	public String getTxtdEmailPessoal() {
		return txtEmailPessoal.getText();
	}
	
	public void setTxtdEmailPessoal(String text) {
		txtEmailPessoal.setText(text);
	}
	
	public String getTxtEmailInstitucional() {
		return txtEmailInstitucional.getText();
	}
	
	public void setTxtEmailInstitucional(String text) {
		txtEmailInstitucional.setText(text);
	}
	
	public String getTxtNomeOrientador() {
		return txtNomeOrientador.getText();
	}
	
	public void setTxtNomeOrientador(String text) {
		txtNomeOrientador.setText(text);
	}
	
	public String getTxtEmailInstitucionalOrientador() {
		return txtEmailInstitucionalOrientador.getText();
	}
	
	public void setTxtEmailInstitucionalOrientador(String text) {
		txtEmailInstitucionalOrientador.setText(text);
	}
	
	public String getTxtTgMatriculado() {
		return txtTgMatriculado.getText();
	}
	
	public void setTxtTgMatriculado(String text) {
		txtTgMatriculado.setText(text);
	}
	
	public String getTxtTipoTg() {
		return txtTipoTg.getText();
	}
	
	public void setTxtTipoTg(String text) {
		txtTipoTg.setText(text);
	}
	
	public String getTxtTituloTg() {
		return txtTituloTg.getText();
	}
	
	public void setTxtTituloTg(String text) {
		txtTituloTg.setText(text);
	}
	
	public String getTxtEmpresa() {
		return txtEmpresa.getText();
	}
	
	public void setTxtEmpresa(String text) {
		txtEmpresa.setText(text);
	}
	
	public String getTxtDisciplina() {
		return txtDisciplina.getText();
	}
	
	public void setTxtDisciplina(String text) {
		txtDisciplina.setText(text);
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	

}
