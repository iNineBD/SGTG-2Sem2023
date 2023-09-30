package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
import java.util.ArrayList;
import entidades.CsvAluno;

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
				
				ArrayList<CsvAluno> listAlunos = new ArrayList<CsvAluno>();
				
				try(BufferedReader br = new BufferedReader(new FileReader(caminhoAbsoluto))){
					
					String line = br.readLine();
					line = br.readLine();
					while (line!= null) {
						String[] vetor = line.split(",");
						
						// Adicionando os alunos na lista de alunos com seus atributos
						String emailPessoal = vetor[1];
						String emailFatecAluno = vetor[2];
						String nomeAluno = vetor[3];
						
						// Adicionando os alunos na lista de alunos com seus atributos
						String nomeOrientador = vetor[4];
						String emailFatecOrientador = vetor[5];
						
						// Adicionando os 
						
						
					}
					
					
				}catch(IOException e) {
					System.out.println(e.getMessage());
				}
				
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
