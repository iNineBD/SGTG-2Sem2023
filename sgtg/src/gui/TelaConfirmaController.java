package gui;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import gui.util.Telas;
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
		return txtNome.getText().trim();
	}

	public void setTxtNome(String text) {
		txtNome.setEditable(true);
		txtNome.setText(text.trim());
	}
	
	public String getTxtdEmailPessoal() {
		return txtEmailPessoal.getText().trim();
	}
	
	public void setTxtdEmailPessoal(String text) {
		txtEmailPessoal.setEditable(true);
		txtEmailPessoal.setText(text.trim());
	}
	
	public String getTxtEmailInstitucional() {
		return txtEmailInstitucional.getText().trim();
	}
	
	public void setTxtEmailInstitucional(String text) {
		txtNomeOrientador.setEditable(true);
		txtEmailInstitucional.setText(text.trim());
	}
	
	public String getTxtNomeOrientador() {
		return txtNomeOrientador.getText().trim();
	}
	
	public void setTxtNomeOrientador(String text) {
		txtNomeOrientador.setEditable(true);
		txtNomeOrientador.setText(text.trim());
	}
	
	public String getTxtEmailInstitucionalOrientador() {
		return txtEmailInstitucionalOrientador.getText().trim();
	}
	
	public void setTxtEmailInstitucionalOrientador(String text) {
		txtEmailInstitucionalOrientador.setEditable(true);
		txtEmailInstitucionalOrientador.setText(text.trim());
	}
	
	public String getTxtTgMatriculado() {
		return txtTgMatriculado.getText().trim();
	}
	
	public void setTxtTgMatriculado(String text) {
		txtTgMatriculado.setEditable(true);
		txtTgMatriculado.setText(text.trim());
	}
	
	public String getTxtTipoTg() {
		return txtTipoTg.getText().trim();
	}
	
	public void setTxtTipoTg(String text) {
		txtTipoTg.setEditable(true);
		txtTipoTg.setText(text.trim());
	}
	
	public String getTxtTituloTg() {
		return txtTituloTg.getText().trim();
	}
	
	public void setTxtTituloTg(String text) {
		txtTituloTg.setEditable(true);
		txtTituloTg.setText(text.trim());
	}
	
	public String getTxtEmpresa() {
		return txtEmpresa.getText().trim();
	}
	
	public void setTxtEmpresa(String text) {
		txtEmpresa.setEditable(true);
		txtEmpresa.setText(text.trim());
	}
	
	public String getTxtDisciplina() {
		return txtDisciplina.getText().trim();
	}
	
	public void setTxtDisciplina(String text) {
		txtDisciplina.setEditable(true);
		txtDisciplina.setText(text.trim());
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	

}
