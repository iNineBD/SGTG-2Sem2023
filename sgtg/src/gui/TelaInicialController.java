package gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Telas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TelaInicialController implements Initializable{
	
	private Telas loadTelas = new Telas();

	@FXML
	private Button btCarregarCsv;
	
	@FXML
	public void onBtCarrgarCsvAction() {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione um arquivo!");
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            // Faça algo com o arquivo selecionado
            System.out.println("Arquivo selecionado: " + selectedFile.getAbsolutePath());
        }
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	
}
