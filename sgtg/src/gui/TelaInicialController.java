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

import excecoes.CSVException;
import java.util.Scanner;

public class TelaInicialController implements Initializable{
	
	private Telas loadTelas = new Telas();

	@FXML
	private Button btCarregarCsv;
	
	@FXML
	private Button btGerenciarAlunos;
	
	@FXML
	private Button btNovaEntrega;
	
	@FXML
	public void onBtCarrgarCsvAction() {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione um arquivo!");
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        String caminhoAbsoluto = selectedFile.getAbsolutePath();
        File file = new File(caminhoAbsoluto);
		String nomeArquivo = selectedFile.getName();
		String extensaoArquivo = nomeArquivo.substring(nomeArquivo.lastIndexOf(".") + 1);
		Scanner leitor = null;
		
		try {
			if(extensaoArquivo.trim().equalsIgnoreCase("csv")) {
				System.out.println("Arquivo selecionado: " + nomeArquivo + " corresponde a um CSV");
//				try {
//				leitor = new Scanner(file);
//				
//				}catch(IOException e){
//					
//				}
			}else {
				throw new CSVException("Arquivo selecionado: " + nomeArquivo + " não corresponde a um CSV");
			}
		}catch(CSVException e) {
				System.out.println(e.getMessage());
                }        
	}
     
	
	@FXML
	public void onBtGerenciarAlunosAction() {
		loadTelas.loadView("/gui/TelaGerenciarAlunos.fxml");
	}
	
	
	@FXML
	public void onBtNovaEntregaAction() {
		loadTelas.loadView("/gui/TelaCadastrarEntregas.fxml");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	
}
