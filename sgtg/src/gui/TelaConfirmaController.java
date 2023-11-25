package gui;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;



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
		if(txtNome.getText() == null) {
			return txtNome.getText();
		}else {
			return txtNome.getText().trim();
		}
	}

	public void setTxtNome(String text) {
		txtNome.setEditable(true);
		txtNome.setText(text);
	}
	
	public String getTxtdEmailPessoal() {
		if(txtEmailPessoal.getText() == null) {
			return txtEmailPessoal.getText();
		}else {
			return txtEmailPessoal.getText().trim();
		}
	}
	
	public void setTxtdEmailPessoal(String text) {
		txtEmailPessoal.setEditable(true);
		txtEmailPessoal.setText(text);
	}
	
	public String getTxtEmailInstitucional() {
		if(txtEmailInstitucional.getText() == null) {
			return txtEmailInstitucional.getText();
		}else {
			return txtEmailInstitucional.getText().trim();
		}
	}
	
	public void setTxtEmailInstitucional(String text) {
		txtNomeOrientador.setEditable(true);
		txtEmailInstitucional.setText(text);
	}
	
	public String getTxtNomeOrientador() {
		if(txtNomeOrientador.getText() == null) {
			return txtNomeOrientador.getText();
		}else {
			return txtNomeOrientador.getText().trim();
		}
	}
	
	public void setTxtNomeOrientador(String text) {
		txtNomeOrientador.setEditable(true);
		txtNomeOrientador.setText(text);
	}
	
	public String getTxtEmailInstitucionalOrientador() {
		if(txtEmailInstitucionalOrientador.getText() == null) {
			return txtEmailInstitucionalOrientador.getText();
		}else {
			return txtEmailInstitucionalOrientador.getText().trim();
		}
	}
	
	public void setTxtEmailInstitucionalOrientador(String text) {
		txtEmailInstitucionalOrientador.setEditable(true);
		txtEmailInstitucionalOrientador.setText(text);
	}
	
	public String getTxtTgMatriculado() {
		if (txtTgMatriculado.getText() == null) {
			return txtTgMatriculado.getText();
		}else {
			return txtTgMatriculado.getText().trim();
		}
	}
	
	public void setTxtTgMatriculado(String text) {
		txtTgMatriculado.setEditable(true);
		txtTgMatriculado.setText(text);
	}
	
	public String getTxtTipoTg() {
		if(txtTipoTg.getText() == null) {
			return txtTipoTg.getText();
		}else {
		return txtTipoTg.getText().trim();
		}
	}
	
	public void setTxtTipoTg(String text) {
		txtTipoTg.setEditable(true);
		txtTipoTg.setText(text);
	}
	
	public String getTxtTituloTg() {
		if(txtTituloTg.getText() == null) {
			return txtTituloTg.getText();
		}else {
			return txtTituloTg.getText().trim();
		}
	}
	
	public void setTxtTituloTg(String text) {
		txtTituloTg.setEditable(true);
		txtTituloTg.setText(text);
	}
	
	public String getTxtEmpresa() {
		if(txtEmpresa.getText() == null) {
			return txtEmpresa.getText();
		}else {
			return txtEmpresa.getText().trim();
		}
	}
	
	public void setTxtEmpresa(String text) {
		txtEmpresa.setEditable(true);
		txtEmpresa.setText(text);
	}
	
	public String getTxtDisciplina() {
		if(txtDisciplina.getText() == null) {
			return txtDisciplina.getText();
		}else {
			return txtDisciplina.getText().trim();
		}
	}
	
	public void setTxtDisciplina(String text) {
		txtDisciplina.setEditable(true);
		txtDisciplina.setText(text);
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	

}
