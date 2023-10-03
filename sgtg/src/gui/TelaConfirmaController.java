package gui;


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

	
	
	public void setTxtNome(String text) {
		txtNome.setText(text);
	}
	
	public void setTxtdEmailPessoal(String text) {
		txtEmailPessoal.setText(text);
	}
	
	public void settxtEmailInstitucional(String text) {
		txtEmailInstitucional.setText(text);
	}
	
	public void settxtNomeOrientador(String text) {
		txtNomeOrientador.setText(text);
	}
	
	public void settxtEmailInstitucionalOrientador(String text) {
		txtEmailInstitucionalOrientador.setText(text);
	}
	
	public void settxtTgMatriculado(String text) {
		txtTgMatriculado.setText(text);
	}
	
	public void settxtTipoTg(String text) {
		txtTipoTg.setText(text);
	}
	
	public void settxtTituloTg(String text) {
		txtTituloTg.setText(text);
	}
	
	public void settxtEmpresa(String text) {
		txtEmpresa.setText(text);
	}
		

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	

}
