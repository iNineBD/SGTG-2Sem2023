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
import entidades.Aluno;

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
		ArrayList<Aluno> listAlunos = new ArrayList<Aluno>();
		
		try {
			if(extensaoArquivo.trim().equalsIgnoreCase("csv")) {
				System.out.println("Arquivo selecionado: " + nomeArquivo + " corresponde a um CSV");
				
				
				
				try(BufferedReader br = new BufferedReader(new FileReader(caminhoAbsoluto))){
					
					String line = br.readLine();
					line = br.readLine();
					while (line!= null) {
						String[] vetor = line.split(",", -1);
						
						// Listando os atributos do CSV
						String emailPessoal = vetor[1].trim();
						String emailFatecAluno = vetor[2].trim();
						String nome = vetor[3].trim();
						String orientador = vetor[4].trim();
						String emailFatecOrientador = vetor[5].trim();
						String turma = vetor[6].trim();
						String tipoTg = vetor[7].trim();
						String problemaResolvidoOuEstudoArtigo = vetor[8].trim();
						String empresa = vetor[9].trim();
						String disciplina = vetor[10].trim();
						
				        // Verifique campos em branco e atribua valores padrão
				        if (emailPessoal.isEmpty()) {
				            emailPessoal = null;
				        }
				        if (emailFatecAluno.isEmpty()) {
				            emailFatecAluno = null;
				        }
				        if (nome.isEmpty()) {
				            nome = null;
				        }
				        if (orientador.isEmpty()) {
				            orientador = null;
				        }
				        if (emailFatecOrientador.isEmpty()) {
				            emailFatecOrientador = null;
				        }
				        if (turma.isEmpty()) {
				            turma = null;
				        }
				        if (tipoTg.isEmpty()) {
				            tipoTg = null;
				        }
				        if (problemaResolvidoOuEstudoArtigo.isEmpty()) {
				            problemaResolvidoOuEstudoArtigo = null;
				        }
				        if (empresa.isEmpty()) {
				            empresa = null;
				        }
				        if (disciplina.isEmpty()) {
				            disciplina = null;
				        }
						
						// Adicionando os atributos na classe aluno
						
						Aluno aluno = new Aluno(nome,emailPessoal,emailFatecAluno,orientador,emailFatecOrientador,turma,tipoTg,problemaResolvidoOuEstudoArtigo,empresa,disciplina);
						listAlunos.add(aluno);
						
						line = br.readLine();
					}
					
				}catch(IOException e) {
					System.out.println("erro =" + e.getMessage());
				}
				
			}else {
				throw new CSVException("Arquivo selecionado: " + nomeArquivo + " não corresponde a um CSV");
			}
		}catch(CSVException e) {
				System.out.println(e.getMessage());
                }
		
		TelaConfirmaController confirma = new TelaConfirmaController();
		confirma.confirmaDadosAlunos(listAlunos);
			
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
