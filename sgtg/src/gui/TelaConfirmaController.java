package gui;

import java.awt.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import gui.util.Telas;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;

import entidades.Aluno;

public class TelaConfirmaController implements Initializable{
	
	private Telas loadTelas = new Telas();
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtEmailPessoal;
	
	
	public void confirmaDadosAlunos(ArrayList<Aluno> listAlunos) {
			
		Aluno alunoSelecionado = listAlunos.get(0);
		System.out.println(alunoSelecionado.getNome());
		setTextFieldNome(alunoSelecionado.getNome());
		setTextFieldEmailPessoal(alunoSelecionado.getEmailPessoal());
		
		loadTelas.loadView("/gui/TelaConfirmarCsv.fxml");
	}
	
	public void setTextFieldNome(String text) {
		txtNome.setText(text);
	}
	
	public void setTextFieldEmailPessoal(String text) {
		txtEmailPessoal.setText(text);
	}
		

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	

}
