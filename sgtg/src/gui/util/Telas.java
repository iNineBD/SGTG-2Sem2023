package gui.util;

import java.io.IOException;
import java.sql.SQLException;

import application.Main;
import gui.TelaEntregaTurmaController;
import gui.TelaGerenciarAlunosController;
import gui.TelaMostrarAlunoController;

import java.util.ArrayList;

import entidades.Aluno;
import gui.TelaConfirmaController;
import gui.TelaEditarAlunoController;
import gui.util.InsertBd;
import gui.util.ShowAndEditAluno;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Telas {
	
	private InsertBd insertBd = new InsertBd();
	private ShowAndEditAluno aluno = new ShowAndEditAluno();


	public synchronized void loadView(String absoluteName) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));

			VBox newVbox = loader.load();
			

			Scene mainScene = Main.getMainScene();

			VBox mainVbox = (VBox) (((ScrollPane) mainScene.getRoot()).getContent());

			Node mainMenu = mainVbox.getChildren().get(0);

			mainVbox.getChildren().clear();

			mainVbox.getChildren().add(mainMenu);
			
			mainVbox.prefHeightProperty().bind(mainScene.heightProperty());
			mainVbox.prefWidthProperty().bind(mainScene.widthProperty());


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

			mainVbox.getChildren().addAll((newVbox.getChildren()));
			
			mainVbox.prefHeightProperty().bind(mainScene.heightProperty());
			mainVbox.prefWidthProperty().bind(mainScene.widthProperty());
			
			TelaGerenciarAlunosController controller = loader.getController();
			controller.setLoadAluno(new LoadGerenciarAlunos());
			
			controller.updateTableView();

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
		}

	}
	
	public synchronized void loadView10(String absoluteName,Aluno alunos, int id_aluno) throws SQLException {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));

			VBox newVbox = loader.load();

			Scene mainScene = Main.getMainScene();

			VBox mainVbox = (VBox) (((ScrollPane) mainScene.getRoot()).getContent());

			Node mainMenu = mainVbox.getChildren().get(0);

			mainVbox.getChildren().clear();

			mainVbox.getChildren().add(mainMenu);

			mainVbox.getChildren().addAll((newVbox.getChildren()));
			
			mainVbox.prefHeightProperty().bind(mainScene.heightProperty());
			mainVbox.prefWidthProperty().bind(mainScene.widthProperty());
			
			TelaMostrarAlunoController controller1 = loader.getController();
					
			aluno.mostraAluno2(controller1, alunos);
			aluno.mostraAlunoTravado(controller1);
			Aluno alunoEdit = alunos;
			
			controller1.btVoltar.setOnAction( event1 ->{
				try {
					loadView2("/gui/TelaGerenciarAlunos.fxml");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			controller1.btEditar.setOnAction( event2 ->{
				try {
					loadView11("/gui/TelaEditarAluno.fxml",alunoEdit, id_aluno);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
		}

	}
	
	public synchronized void loadView11(String absoluteName,Aluno alunos, int id_aluno) throws SQLException {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));

			VBox newVbox = loader.load();

			Scene mainScene = Main.getMainScene();

			VBox mainVbox = (VBox) (((ScrollPane) mainScene.getRoot()).getContent());

			Node mainMenu = mainVbox.getChildren().get(0);

			mainVbox.getChildren().clear();

			mainVbox.getChildren().add(mainMenu);

			mainVbox.getChildren().addAll((newVbox.getChildren()));
			
			mainVbox.prefHeightProperty().bind(mainScene.heightProperty());
			mainVbox.prefWidthProperty().bind(mainScene.widthProperty());
			
			TelaEditarAlunoController controller = loader.getController();
			
			
			Aluno alunoMostrar = alunos;
			aluno.mostraAluno3(controller, alunos);
			aluno.editaInformacao2(controller,alunos);
			
			controller.btCancelar.setOnAction( event1 ->{
				try {
					loadView10("/gui/TelaMostrarAluno.fxml",alunoMostrar,id_aluno);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			controller.btSalvar.setOnAction( event1 ->{
				try {
					insertBd.atualizaAluno(id_aluno, controller);
					Aluno alunoMostrarNovo;
					loadView10("/gui/TelaMostrarAluno.fxml",alunoMostrar,id_aluno);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

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
                aluno.mostraAluno(controller, alunos.get(currentAlunoIndex));

                // Configura o botão Confirma.  
                controller.btConfirma.setOnAction(event -> {
                	// Caso tenha alterações nos campos, caso não tenha segue normal.
                	aluno.editaInformacao(controller, alunos.get(currentAlunoIndex));
                	
                	if(aluno.confirmaDados(controller, alunos.get(currentAlunoIndex))) {
                		      	
	                	// Quando clicar no botão confirma ele vai acusar como True.
	                	alunos.get(currentAlunoIndex).setConfirmado();
	                	
	                	// Vai incluir o aluno no banco.
	                	insertBd.insertBd(alunos.get(currentAlunoIndex));
	
						// Avança para o próximo aluno.
	                    currentAlunoIndex++;
	                    if (currentAlunoIndex < alunos.size()) {
	                        
	                    	aluno.mostraAluno(controller, alunos.get(currentAlunoIndex));
	                        
	                    // Caso todos os alunos tenham sido exibidos
	                    } else {
	                        try {
								loadView2("/gui/TelaGerenciarAlunos.fxml");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                    }
                	}else {
                		Alerts.showAlert("IO Exception","Erro","Dados inválidos.", AlertType.ERROR);
                	}
                });
                
                //Configura o botão confirmar todos
                controller.btConfirmaTodos.setOnAction(event ->{
                	// Caso tenha alterações nos campos, caso não tenha segue normal.
                	aluno.editaInformacao(controller, alunos.get(currentAlunoIndex));
                	while(currentAlunoIndex < alunos.size()) {
                    	alunos.get(currentAlunoIndex).setConfirmado();
                    	if(aluno.confirmaDados(controller, alunos.get(currentAlunoIndex))) {
                    	// Vai incluir o aluno no banco.
                    	insertBd.insertBd(alunos.get(currentAlunoIndex));
                    	currentAlunoIndex++;
                    	}else {
                    		aluno.mostraAluno(controller, alunos.get(currentAlunoIndex));
                    		Alerts.showAlert("IO Exception","Erro","Dados inválidos.", AlertType.ERROR);
                    		return;
                      	}
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
    
	public synchronized void clearView() {

		Scene mainScene = Main.getMainScene();

		VBox mainVbox = (VBox) (((ScrollPane) mainScene.getRoot()).getContent());

		Node mainMenu = mainVbox.getChildren().get(0);

		mainVbox.getChildren().clear();

		mainVbox.getChildren().add(mainMenu);

	}
}
