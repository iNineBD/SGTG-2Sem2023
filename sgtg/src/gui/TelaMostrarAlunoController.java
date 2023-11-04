package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaMostrarAlunoController {
	
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
	public Button btVoltar;
	
	@FXML
	public Button btEditar;
	

	public void setTxtNome(String text) {
		txtNome.setText(text);
	}
	
	
	public void setTxtdEmailPessoal(String text) {
		txtEmailPessoal.setText(text);
	}
	
	public void setTxtEmailInstitucional(String text) {
		txtEmailInstitucional.setText(text);
	}
	
	
	public void setTxtNomeOrientador(String text) {
		txtNomeOrientador.setText(text);
	}
	
	
	public void setTxtEmailInstitucionalOrientador(String text) {
		txtEmailInstitucionalOrientador.setText(text);
	}
	
	
	public void setTxtTgMatriculado(String text) {
		txtTgMatriculado.setText(text);
	}
	
	
	public void setTxtTipoTg(String text) {
		txtTipoTg.setText(text);
	}
		
	public void setTxtTituloTg(String text) {
		txtTituloTg.setText(text);
	}
		
	public void setTxtEmpresa(String text) {
		txtEmpresa.setText(text);
	}
	
	
	public void setTxtDisciplina(String text) {
		txtDisciplina.setText(text);
	}

	public void setTxtNomeTravado() {
		txtNome.setEditable(false);
	}

	public void setTxtEmailPessoalTravado() {
		txtEmailPessoal.setEditable(false);
	}

	public void setTxtEmailInstitucionalTravado() {
		txtEmailInstitucional.setEditable(false);
	}

	public void setTxtNomeOrientadorTravado() {
		txtNomeOrientador.setEditable(false);
	}

	public void setTxtEmailInstitucionalOrientadorTravado() {
		txtEmailInstitucionalOrientador.setEditable(false);
	}

	public void setTxtTgMatriculadoTravado() {
		txtTgMatriculado.setEditable(false);
	}

	public void setTxtTipoTgTravado() {
		txtTipoTg.setEditable(false);
	}

	public void setTxtTituloTgTravado() {
		txtTituloTg.setEditable(false);
	}

	public void setTxtEmpresaTravado() {
		txtEmpresa.setEditable(false);
	}

	public void setTxtDisciplinaTravado() {
		txtDisciplina.setEditable(false);
	}

}
