package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Telas;
import javafx.fxml.Initializable;

public class TelaConfirmaAluno implements Initializable{
	
	private Telas loadTelas = new Telas();
	
	
	public void confirmaDadosAlunos() {
		loadTelas.loadView("/gui/TelaConfirmarCsv.fxml");
		
		
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	

}
