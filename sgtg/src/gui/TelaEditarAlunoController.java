package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaEditarAlunoController {
	
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
	public Button btCancelar;
	
	@FXML
	public Button btSalvar;
	
	
	public String getTxtNome() {
		return txtNome.getText();
	}

	public void setTxtNome(String text) {
		txtNome.setEditable(true);
		txtNome.setText(text);
	}
	
	public String getTxtdEmailPessoal() {
		return txtEmailPessoal.getText();
	}
	
	public void setTxtdEmailPessoal(String text) {
		txtEmailPessoal.setEditable(true);
		txtEmailPessoal.setText(text);
	}
	
	public String getTxtEmailInstitucional() {
		return txtEmailInstitucional.getText();
	}
	
	public void setTxtEmailInstitucional(String text) {
		txtNomeOrientador.setEditable(true);
		txtEmailInstitucional.setText(text);
	}
	
	public String getTxtNomeOrientador() {
		return txtNomeOrientador.getText();
	}
	
	public void setTxtNomeOrientador(String text) {
		txtNomeOrientador.setEditable(true);
		txtNomeOrientador.setText(text);
	}
	
	public String getTxtEmailInstitucionalOrientador() {
		return txtEmailInstitucionalOrientador.getText();
	}
	
	public void setTxtEmailInstitucionalOrientador(String text) {
		txtEmailInstitucionalOrientador.setEditable(true);
		txtEmailInstitucionalOrientador.setText(text);
	}
	
	public String getTxtTgMatriculado() {
		return txtTgMatriculado.getText();
	}
	
	public void setTxtTgMatriculado(String text) {
		txtTgMatriculado.setEditable(true);
		txtTgMatriculado.setText(text);
	}
	
	public String getTxtTipoTg() {
		return txtTipoTg.getText();
	}
	
	public void setTxtTipoTg(String text) {
		txtTipoTg.setEditable(true);
		txtTipoTg.setText(text);
	}
	
	public String getTxtTituloTg() {
		return txtTituloTg.getText();
	}
	
	public void setTxtTituloTg(String text) {
		txtTituloTg.setEditable(true);
		txtTituloTg.setText(text);
	}
	
	public String getTxtEmpresa() {
		return txtEmpresa.getText();
	}
	
	public void setTxtEmpresa(String text) {
		txtEmpresa.setEditable(true);
		txtEmpresa.setText(text);
	}
	
	public String getTxtDisciplina() {
		return txtDisciplina.getText();
	}
	
	public void setTxtDisciplina(String text) {
		txtDisciplina.setEditable(true);
		txtDisciplina.setText(text);
	}
	


}
