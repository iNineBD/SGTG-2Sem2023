package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dto.OrientadorDto;
import dto.TurmasDTO;
import entidades.Aluno;
import gui.util.Alerts;
import gui.util.LoadOrientadores;

public class TelaEditarAlunoController implements Initializable{
	
	@FXML
	private TextField txtNome = new TextField();
	
	@FXML
	private TextField txtEmailPessoal = new TextField();
	
	@FXML
	private TextField txtEmailInstitucional = new TextField();
	
	@FXML
	private ChoiceBox<OrientadorDto> comboxNomeOrientador;
	
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
		txtEmailInstitucional.setEditable(true);
		txtEmailInstitucional.setText(text);
	}
	
	public ObservableList<OrientadorDto> getComboxNomeOrientador() {
		return comboxNomeOrientador.getItems();
	}
	
	public String getNomeOrientador() {
		return comboxNomeOrientador.getValue().toString();
	}
	
	public void setComboxNomeOrientador(OrientadorDto nome) {
		comboxNomeOrientador.setValue(nome);
	}
	
	public String getTxtEmailInstitucionalOrientador() {
		return txtEmailInstitucionalOrientador.getText();
	}
	
	public void setTxtEmailInstitucionalOrientador(String text) {
		txtEmailInstitucionalOrientador.setEditable(false);
		txtEmailInstitucionalOrientador.setText(text);
	}
	
	public String getTxtTgMatriculado() {
		return txtTgMatriculado.getText();
	}
	
	public void setTxtTgMatriculado(String text) {
		txtTgMatriculado.setEditable(false);
		txtTgMatriculado.setText(text);
	}
	
	public String getTxtTipoTg() {
		return txtTipoTg.getText();
	}
	
	public void setTxtTipoTg(String text) {
		txtTipoTg.setEditable(false);
		txtTipoTg.setText(text);
	}
	
	public String getTxtTituloTg() {
		return txtTituloTg.getText();
	}
	
	public void setTxtTituloTg(String text) {
		txtTituloTg.setEditable(false);
		txtTituloTg.setText(text);
	}
	
	public String getTxtEmpresa() {
		return txtEmpresa.getText();
	}
	
	public void setTxtEmpresa(String text) {
		txtEmpresa.setEditable(false);
		txtEmpresa.setText(text);
	}
	
	public String getTxtDisciplina() {
		return txtDisciplina.getText();
	}
	
	public void setTxtDisciplina(String text) {
		txtDisciplina.setEditable(false);
		txtDisciplina.setText(text);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		List<OrientadorDto> listaOrientadores = new ArrayList<OrientadorDto>();
		
		try {
			listaOrientadores = LoadOrientadores.carregaOrientadores();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alerts.showAlert("SQLException", "Erro ao buscar os orientadores", "Ocorreu um erro ao buscar os orientador do semestre atual.", AlertType.ERROR);
		}
		if(listaOrientadores != null) {
		ObservableList<OrientadorDto> orientador = FXCollections.observableArrayList(listaOrientadores);
		comboxNomeOrientador.getItems().addAll(orientador);
		}
		
		comboxNomeOrientador.setOnAction(event ->{
			OrientadorDto selectdItem = comboxNomeOrientador.getValue();
			
			txtEmailInstitucionalOrientador.setText(selectdItem.getEmailOrientador());
		});
	}


}
