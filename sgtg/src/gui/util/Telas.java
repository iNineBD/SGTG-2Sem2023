package gui.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Main;
import conexao.DB;
import gui.TelaGerenciarAlunosController;

import java.util.ArrayList;

import application.Main;
import entidades.Aluno;
import gui.TelaConfirmaController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Telas {


	public synchronized void loadView(String absoluteName) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));

			VBox newVbox = loader.load();

			Scene mainScene = Main.getMainScene();

			VBox mainVbox = (VBox) (((ScrollPane) mainScene.getRoot()).getContent());

			Node mainMenu = mainVbox.getChildren().get(0);

			mainVbox.getChildren().clear();

			mainVbox.getChildren().add(mainMenu);

			mainVbox.getChildren().addAll(newVbox.getChildren());

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
		}

	}
	
	public synchronized void loadView2(String absoluteName) throws SQLException {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));

			VBox newVbox = loader.load();

			Scene mainScene = Main.getMainScene();

			VBox mainVbox = (VBox) (((ScrollPane) mainScene.getRoot()).getContent());

			Node mainMenu = mainVbox.getChildren().get(0);

			mainVbox.getChildren().clear();

			mainVbox.getChildren().add(mainMenu);

			mainVbox.getChildren().addAll(newVbox.getChildren());
			
			TelaGerenciarAlunosController controller = loader.getController();
			controller.setLoadAluno(new LoadGerenciarAlunos());
			
			controller.updateTableView();

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
		}

	}


    private int currentAlunoIndex = 0;

    public synchronized void loadView3(String absoluteName, ArrayList<Aluno> alunos) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVbox = loader.load();

            Scene mainScene = Main.getMainScene();
            VBox mainVbox = (VBox) (((ScrollPane) mainScene.getRoot()).getContent());
            Node mainMenu = mainVbox.getChildren().get(0);

            mainVbox.getChildren().clear();
            mainVbox.getChildren().add(mainMenu);
            mainVbox.getChildren().addAll(newVbox.getChildren());

            TelaConfirmaController controller = loader.getController();
            
            // Caso não haja alunos na lista, vai exibir um alerta.
            if (alunos.isEmpty()) {
            	Alerts.showAlert("IO Exception","Erro","Este CSV está vazio.", AlertType.ERROR);
            	loadView("/gui/TelaInicial.fxml");
            	
            } else {
            	
                // Exibe o primeiro aluno ao carregar a tela.
                showAluno(controller, alunos.get(currentAlunoIndex));
                

                // Configura o botão Confirma.  
                controller.btConfirma.setOnAction(event -> {
                	
                	// Caso tenha alterações nos campos, caso não tenha segue normal.
                	editInformation(controller, alunos.get(currentAlunoIndex));
                	                	
                	// Quando clicar no botão confirma ele vai acusar como True.
                	alunos.get(currentAlunoIndex).setConfirmado();
                	
                	// Vai incluir o aluno no banco.
					insertBd(alunos.get(currentAlunoIndex));

                	
                    // Avança para o próximo aluno.
                    currentAlunoIndex++;
                    if (currentAlunoIndex < alunos.size()) {
                        showAluno(controller, alunos.get(currentAlunoIndex));
                        
                    // Caso todos os alunos tenham sido exibidos
                    } else {
                        loadView("/gui/TelaGerenciarAlunos.fxml");
                    }
                });
                
                //Configura o botão confirmar todos
                controller.btConfirmaTodos.setOnAction(event ->{
                	while(currentAlunoIndex < alunos.size()) {
                    	alunos.get(currentAlunoIndex).setConfirmado();
                    	currentAlunoIndex++;
                    	
                    	// Vai incluir o aluno no banco.
                    	insertBd(alunos.get(currentAlunoIndex));
                	}
                	
                	try {
						loadView2("/gui/TelaGerenciarAlunos.fxml");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                });
            }
        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
        }
    }
    
    // Método somente para exibir os alunos na tela
    private void showAluno(TelaConfirmaController controller, Aluno aluno) {
        controller.setTxtNome(aluno.getNome());
        controller.setTxtEmailInstitucional(aluno.getEmailFatecAluno());
        controller.setTxtdEmailPessoal(aluno.getEmailPessoal());
        controller.setTxtNomeOrientador(aluno.getOrientador());
        controller.setTxtEmailInstitucionalOrientador(aluno.getEmailFatecOrientador());
        controller.setTxtTgMatriculado(aluno.getNomeTurma());
        controller.setTxtTipoTg(aluno.getTipoTG());
        controller.setTxtTituloTg(aluno.getProblemaResolvidoOuEstudoArtigo());
        controller.setTxtEmpresa(aluno.getEmpresa());
        controller.setTxtDisciplina(aluno.getDisciplina());
    }
    
    //Método para editar informações
    public void editInformation(TelaConfirmaController controller,Aluno aluno) {
    	String novoNome = controller.getTxtNome();
    	aluno.setNome(novoNome);
    	
    	String novoEmailPessoal = controller.getTxtdEmailPessoal();
    	aluno.setEmailPessoal(novoEmailPessoal);
    	
    	String novoEmailInstitucional = controller.getTxtEmailInstitucional();
    	aluno.setEmailFatecAluno(novoEmailInstitucional);
    	
    	String novoOrientador = controller.getTxtNomeOrientador();
    	aluno.setOrientador(novoOrientador);
    	
    	String novoEmailOrientador = controller.getTxtEmailInstitucionalOrientador();
    	aluno.setEmailFatec(novoEmailOrientador);
    	
    	String novaTurmaTg = controller.getTxtTgMatriculado();
    	aluno.setNomeTurma(novaTurmaTg);
    	
    	String novoTipoTg = controller.getTxtTipoTg();
    	aluno.setTipoTG(novoTipoTg);
    	
    	String novoTituloTg = controller.getTxtTituloTg();
    	aluno.setProblemaResolvidoOuEstudoArtigo(novoTituloTg);
    	
    	String novaEmpresa = controller.getTxtEmpresa();
    	aluno.setEmpresa(novaEmpresa);
    	
    	String novaDisciplina = controller.getTxtDisciplina();
    	aluno.setDisciplina(novaDisciplina);
    	
    }
    
    //Método para jogar os dados no banco de dados
    
    public void insertBd(Aluno aluno) {
    	Connection conecta = null;
    	
    	try {
    	conecta = DB.getConnection();
    	
    	PreparedStatement stBuscaEmailOrientador = conecta.prepareStatement("select orientador.id, orientador.email_fatec from orientador where email_fatec like %?%");
    	
    	PreparedStatement stAluno = conecta.prepareStatement("insert into aluno (nome,email_institucional,email_pessoal,id_orientador) values(?,?,?,?)");
    	
    	PreparedStatement stOrientador = conecta.prepareStatement("insert into orientador (nome,email_fatec) values(?,?)");
    	
    	PreparedStatement stTurma = conecta.prepareStatement("insert into turma(nome) values(?)");
    	
    	PreparedStatement stTg = conecta.prepareStatement("insert into tg(problema_a_resolver,empresa,disciplina) values(?,?,?)");
    	
    	PreparedStatement stTipo = conecta.prepareStatement("insert into tipo(tipo) values(?)");
    	
    	stOrientador.setString(1,aluno.getOrientador());
    	stOrientador.setString(2, aluno.getEmailFatecOrientador());
    	stOrientador.executeUpdate();
    	
    	stTurma.setString(1,aluno.getNomeTurma());
    	stTurma.executeUpdate();
    	
    	stAluno.setString(1, aluno.getNome());
    	stAluno.setString(2, aluno.getEmailFatecAluno());
    	stAluno.setString(3, aluno.getEmailPessoal());
    	stAluno.setInt(4, 5);
    	stAluno.executeUpdate();
    	
    	stTipo.setString(1, aluno.getTipoTG());
    	stTipo.executeUpdate();
    	
    	stTg.setString(1, aluno.getProblemaResolvidoOuEstudoArtigo());
    	stTg.setString(2, aluno.getEmpresa());
    	stTg.setString(3, aluno.getDisciplina());
    	stTg.executeUpdate();
    	

    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} finally {
			try {
				if(conecta != null) {
				conecta.close();
				}
			}catch(SQLException e) {
				throw new RuntimeException("Erro ao fechar conexão", e);
			}
		}
    	
    	}
	
	

	public synchronized void clearView() {

		Scene mainScene = Main.getMainScene();

		VBox mainVbox = (VBox) (((ScrollPane) mainScene.getRoot()).getContent());

		Node mainMenu = mainVbox.getChildren().get(0);

		mainVbox.getChildren().clear();

		mainVbox.getChildren().add(mainMenu);

	}

}
