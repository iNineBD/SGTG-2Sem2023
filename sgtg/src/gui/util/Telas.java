package gui.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;

import application.Main;
import conexao.DB;
import gui.TelaGerenciarAlunosController;

import java.util.ArrayList;
import java.util.Calendar;

import entidades.Aluno;
import gui.TelaConfirmaController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
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
                        try {
							loadView2("/gui/TelaGerenciarAlunos.fxml");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                });
                
                //Configura o botão confirmar todos
                controller.btConfirmaTodos.setOnAction(event ->{
                	while(currentAlunoIndex < alunos.size()) {
                    	alunos.get(currentAlunoIndex).setConfirmado();
                    	
                    	// Vai incluir o aluno no banco.
                    	insertBd(alunos.get(currentAlunoIndex));
                    	currentAlunoIndex++;
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
    
    public void insertBd(Aluno aluno){
    	
    	int ano = LocalDate.now().getYear();
    	
    	int mes = LocalDate.now().getMonthValue();
    	
    	int semestre;
    	
    	if(mes > 6) {
    		semestre = 2;
    	}else {
    		semestre = 1;
    	}
    	
    	
    	Connection conecta = null;
    	
    	PreparedStatement stBuscaEmailOrientador = null;
    	 
    	PreparedStatement stBuscaIdTurma = null;
    	
    	PreparedStatement stBuscaIdAluno = null;
    	
    	PreparedStatement stBuscaIdTipo = null;
    	
    	PreparedStatement stBuscaSemestreEAno = null;
    	
    	PreparedStatement stAnoESemestre = null;
    	
    	PreparedStatement stAluno = null;
    	
    	PreparedStatement stOrientador = null;
    	
    	PreparedStatement stTurma = null;
    	
    	PreparedStatement stTg = null;
    	
    	PreparedStatement stTipo = null;
    	
    	PreparedStatement stMatricula = null;
    	
    	
    	try	 {
    	conecta = DB.getConnection();
    	
    	stBuscaEmailOrientador = conecta.prepareStatement("select orientador.id, orientador.email_fatec from orientador where email_fatec like ?");
    	
    	stBuscaIdTurma = conecta.prepareStatement("select turma.id, turma.nome from turma where turma.nome = ?");
    	
    	stBuscaIdAluno = conecta.prepareStatement("select aluno.id,aluno.email_institucional from aluno where aluno.email_institucional = ?");
    	
    	stBuscaIdTipo = conecta.prepareStatement("select tipo.id,tipo.tipo from tipo where tipo.tipo = ?");
    	
    	stBuscaSemestreEAno = conecta.prepareStatement("select semestre.semestralizacao, semestre.ano from semestre where semestre.semestralizacao = ? and semestre.ano = ?");
    	
    	stAnoESemestre = conecta.prepareStatement("insert into semestre(semestralizacao,ano) values(?,?)");
    	
    	stAluno = conecta.prepareStatement("insert into aluno (nome,email_institucional,email_pessoal,id_orientador) values(?,?,?,?)");
    	
    	stOrientador = conecta.prepareStatement("insert into orientador (nome,email_fatec) values(?,?)");
    	
    	stTurma = conecta.prepareStatement("insert into turma(nome,semestralizacao,ano) values(?,?,?)");
    	
    	stTg = conecta.prepareStatement("insert into tg(problema_a_resolver,empresa,disciplina,id_aluno,id_tipo) values(?,?,?,?,?)");
    	
    	stTipo = conecta.prepareStatement("insert into tipo(tipo,regra) values(?,?)");
    	
    	stMatricula = conecta.prepareStatement("insert into matricula(id_aluno,id_turma) values(?,?)");
    	
    	stOrientador.setString(1,aluno.getOrientador());
    	stOrientador.setString(2, aluno.getEmailFatecOrientador());
    	stOrientador.executeUpdate();
    	
    	// Para fazer a consulta e achar o ID do orientador
    	stBuscaEmailOrientador.setString(1, "%" + aluno.getEmailFatecOrientador() + "%" );
    	ResultSet result1 = stBuscaEmailOrientador.executeQuery();
    	result1.next();
    	int idOrientador = result1.getInt("id");
    	
    	stAluno.setString(1, aluno.getNome());
    	stAluno.setString(2, aluno.getEmailFatecAluno());
    	stAluno.setString(3, aluno.getEmailPessoal());
    	stAluno.setInt(4, idOrientador);
    	stAluno.executeUpdate();
    	
    	// Para fazer a consulta e localizar o ID do aluno
    	stBuscaIdAluno.setString(1, aluno.getEmailFatecAluno());
    	ResultSet result3 = stBuscaIdAluno.executeQuery();
    	result3.next();
    	int idAluno = result3.getInt("id");
    	
    	// Para fazer a consulta e localizar o ID da turma
    	stBuscaIdTurma.setString(1, aluno.getNomeTurma());
    	ResultSet result2 = stBuscaIdTurma.executeQuery();
        	
    	if(!result2.next()) {
    	// Verificação se no banco já existe ano e semestre
    	try {
    		stBuscaSemestreEAno.setInt(1, semestre);
    		stBuscaSemestreEAno.setInt(2,ano);
    		ResultSet result5 = stBuscaSemestreEAno.executeQuery();
    		result5.next();
    		int sem = result5.getInt("semestralizacao");
    		int year = result5.getInt("ano");
        	stTurma.setString(1,aluno.getNomeTurma());
        	stTurma.setInt(2, sem);
        	stTurma.setInt(3, year);
        	stTurma.executeUpdate();
        	
        	stBuscaIdTurma.setString(1, aluno.getNomeTurma());
        	result2 = stBuscaIdTurma.executeQuery();
        	result2.next();
        	int idTurma = result2.getInt("id");
        	
        	stMatricula.setInt(1, idAluno);
        	stMatricula.setInt(2, idTurma);
        	stMatricula.executeUpdate();
        	       	
        	stTipo.setString(1, aluno.getTipoTG());
        	stTipo.setString(2, "123");
        	stTipo.executeUpdate();
        	
        	// Para fazer a consulta e localizar o ID do tipo
        	stBuscaIdTipo.setString(1, aluno.getTipoTG());
        	ResultSet result4 = stBuscaIdTurma.executeQuery();
        	result4.next();
        	int idTipo = result4.getInt("id");
        	
        	stTg.setString(1, aluno.getProblemaResolvidoOuEstudoArtigo());
        	stTg.setString(2, aluno.getEmpresa());
        	stTg.setString(3, aluno.getDisciplina());
        	stTg.setInt(4, idAluno);
        	stTg.setInt(5, idTipo);
        	stTg.executeUpdate();
    	}catch(SQLException e) {
//    		e.printStackTrace();
    		stAnoESemestre.setInt(1,semestre);
    		stAnoESemestre.setInt(2, ano);
    		stAnoESemestre.executeUpdate();
    		
    		stBuscaSemestreEAno.setInt(1, semestre);
    		stBuscaSemestreEAno.setInt(2,ano);
    		ResultSet result5 = stBuscaSemestreEAno.executeQuery();
    		result5.next();
    		int sem = result5.getInt("semestralizacao");
    		int year = result5.getInt("ano");
        	stTurma.setString(1,aluno.getNomeTurma());
        	stTurma.setInt(2, sem);
        	stTurma.setInt(3,year);
        	stTurma.executeUpdate();
        	
        	stBuscaIdTurma.setString(1, aluno.getNomeTurma());
        	result2 = stBuscaIdTurma.executeQuery();
        	result2.next();
        	int idTurma = result2.getInt("id");
        	stMatricula.setInt(1, idAluno);
        	stMatricula.setInt(2, idTurma);
        	stMatricula.executeUpdate();
        	
        	
        	stTipo.setString(1, aluno.getTipoTG());
        	stTipo.setString(2, "123");
        	stTipo.executeUpdate();
        	
        	// Para fazer a consulta e localizar o ID do tipo
        	stBuscaIdTipo.setString(1, aluno.getTipoTG());
        	ResultSet result4 = stBuscaIdTurma.executeQuery();
        	result4.next();
        	int idTipo = result4.getInt("id");
        	
        	stTg.setString(1, aluno.getProblemaResolvidoOuEstudoArtigo());
        	stTg.setString(2, aluno.getEmpresa());
        	stTg.setString(3, aluno.getDisciplina());
        	stTg.setInt(4, idAluno);
        	stTg.setInt(5, idTipo);
        	stTg.executeUpdate();
    	}}
    	else {
    	int idTurma = result2.getInt("id");
    	
    	stMatricula.setInt(1, idAluno);
    	stMatricula.setInt(2, idTurma);
    	stMatricula.executeUpdate();
    	
    	
    	stTipo.setString(1, aluno.getTipoTG());
    	stTipo.setString(2, "123");
    	stTipo.executeUpdate();
    	
    	// Para fazer a consulta e localizar o ID do tipo
    	stBuscaIdTipo.setString(1, aluno.getTipoTG());
    	ResultSet result4 = stBuscaIdTurma.executeQuery();
    	result4.next();
    	int idTipo = result4.getInt("id");
    	
    	stTg.setString(1, aluno.getProblemaResolvidoOuEstudoArtigo());
    	stTg.setString(2, aluno.getEmpresa());
    	stTg.setString(3, aluno.getDisciplina());
    	stTg.setInt(4, idAluno);
    	stTg.setInt(5, idTipo);
    	stTg.executeUpdate();
    	}

    	} catch (SQLException e) {
			// TODO Auto-generated catch block
    		e.printStackTrace();
//    		Alerts.showAlert("SQL Exception","Erro","Este aluno já está cadastrado", AlertType.ERROR);
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
