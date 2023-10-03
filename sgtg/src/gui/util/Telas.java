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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;

public class Telas {
	
	@FXML
	private Button confirmaButton;

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

	
	public synchronized void loadView3(String absoluteName,ArrayList<Aluno> alunos) {
		

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
			
			int i = 0;
			while(i < alunos.size()) {
			
				Aluno aluno = alunos.get(i);
				
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
			

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
		}

	}
	
	public int onBtConfirma(int i) {
		return i++;
	}

	
	
	public synchronized void clearView() {

		Scene mainScene = Main.getMainScene();

		VBox mainVbox = (VBox) (((ScrollPane) mainScene.getRoot()).getContent());

		Node mainMenu = mainVbox.getChildren().get(0);

		mainVbox.getChildren().clear();

		mainVbox.getChildren().add(mainMenu);

	}

}
