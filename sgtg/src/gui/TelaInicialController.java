package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entidades.Aluno;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Telas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TelaInicialController implements Initializable{
	
	private Telas loadTelas = new Telas();

	@FXML
	private Button btCarregarCsv;
	
	@FXML
	private Button btGerenciarAlunos;
	
	@FXML
	private Button btNovaEntrega;
	
	@FXML
	public void onBtCarregarCsvAction() {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione um arquivo!");
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        String caminhoAbsoluto = selectedFile.getAbsolutePath();
       
		String nomeArquivo = selectedFile.getName();
		String extensaoArquivo = nomeArquivo.substring(nomeArquivo.lastIndexOf(".") + 1);
		
		
		ArrayList<Aluno> listAlunos = new ArrayList<Aluno>();
		
		
			if(extensaoArquivo.trim().equalsIgnoreCase("csv")) {
							
				try(BufferedReader br = new BufferedReader(new FileReader(caminhoAbsoluto))){
					
					String line = br.readLine();
					line = br.readLine();
					while (line!= null) {
						String[] vetor = line.split(",", -1);
						
						// Listando os atributos do CSV
						String emailPessoal = vetor[1].trim();
						String emailFatecAluno = Constraints.verificarEmail(vetor[2].trim());
						String nome = Constraints.verificarNome(vetor[3].trim());
						String orientador = Constraints.verificarNome(vetor[4].trim());
						String emailFatecOrientador = Constraints.verificarEmail(vetor[5].trim());
						String turma = vetor[6].trim().toUpperCase();
						//Pegando o tipo TG e sua regra
						String tipo = vetor[7].trim();
						String[] separador = tipo.split("\\(");
						String tipoTg = separador[0].trim();
						String regra;
						try {
						regra = separador[1].replaceAll("\\)", " ").trim();
						}
						catch(ArrayIndexOutOfBoundsException a) {
							regra = null;
						}
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
						
						Aluno aluno = new Aluno(nome,emailPessoal,emailFatecAluno,orientador,emailFatecOrientador,turma,tipoTg,regra,problemaResolvidoOuEstudoArtigo,empresa,disciplina);
						listAlunos.add(aluno);
						
						line = br.readLine();
					}
					
					loadTelas.loadView3("/gui/TelaConfirmarCsv.fxml",listAlunos);
					
				}catch(IOException e) {
//					e.printStackTrace();
					System.out.println("erro =" + e.getMessage());
				}
				
			}else {
				Alerts.showAlert("IO Exception","Erro","Este arquivo não corresponde a um CSV.", AlertType.ERROR);
            	loadTelas.loadView("/gui/TelaInicial.fxml");
			}   

       
        
		
	}
     
	
	@FXML
	public void onBtGerenciarAlunosAction() throws SQLException {
		loadTelas.loadView2("/gui/TelaGerenciarAlunos.fxml");
	}
	
	
	@FXML
	public void onBtNovaEntregaAction() {
		loadTelas.loadView("/gui/TelaCadastrarEntregas.fxml");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	
}
