package gui.util;

import java.io.IOException;
import java.util.ArrayList;

import application.Main;
import entidades.Aluno;
import gui.TelaConfirmaController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class Telas {

    private int currentAlunoIndex = 0;
    

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
                	alunos.get(currentAlunoIndex).setConfirmado();
                	
                	System.out.println(alunos.get(currentAlunoIndex));
                	
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
                	int i = 0;
                	while(i < alunos.size()) {
                    	alunos.get(i).setConfirmado();
                    	System.out.println(alunos.get(i));
                		i++;
                	}
                	loadView("/gui/TelaGerenciarAlunos.fxml");
                });
            }
        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
        }
    }
    
    // Método somente para exibir os alunos na tela
    private void showAluno(TelaConfirmaController controller, Aluno aluno) {
        controller.setTxtNome(aluno.getNome());
        controller.settxtEmailInstitucional(aluno.getEmailFatecAluno());
        controller.setTxtdEmailPessoal(aluno.getEmailPessoal());
        controller.settxtNomeOrientador(aluno.getOrientador());
        controller.settxtEmailInstitucionalOrientador(aluno.getEmailFatecOrientador());
        controller.settxtTgMatriculado(aluno.getNomeTurma());
        controller.settxtTipoTg(aluno.getTipoTG());
        controller.settxtTituloTg(aluno.getProblemaResolvidoOuEstudoArtigo());
        controller.settxtEmpresa(aluno.getEmpresa());
    }

	
	
	public synchronized void clearView() {

		Scene mainScene = Main.getMainScene();

		VBox mainVbox = (VBox) (((ScrollPane) mainScene.getRoot()).getContent());

		Node mainMenu = mainVbox.getChildren().get(0);

		mainVbox.getChildren().clear();

		mainVbox.getChildren().add(mainMenu);

	}

}
