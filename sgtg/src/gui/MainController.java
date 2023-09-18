package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainController implements Initializable{

	@FXML
	private MenuItem menuItemNovaEntrega;

	@FXML
	private MenuItem menuItemSobre;
	
	
	@FXML
	public void onMenuItemNovaEntregaAction() {
		clearView();
	}
	
	public void onMenuItemSobreAction() {
		loadView("/gui/Sobre.fxml");
	}
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
	}
	
	private synchronized void loadView(String absoluteName) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			
			VBox newVbox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			
			VBox mainVbox = (VBox)(((ScrollPane)mainScene.getRoot()).getContent());
			
			Node mainMenu = mainVbox.getChildren().get(0);
			
			mainVbox.getChildren().clear();
			
			mainVbox.getChildren().add(mainMenu);
			
			mainVbox.getChildren().addAll(newVbox.getChildren());
			
			
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
		}
		
	}
	
	private synchronized void clearView() {
				
		Scene mainScene = Main.getMainScene();
		
		VBox mainVbox = (VBox)(((ScrollPane)mainScene.getRoot()).getContent());
		
		Node mainMenu = mainVbox.getChildren().get(0);
		
		mainVbox.getChildren().clear();
		
		mainVbox.getChildren().add(mainMenu);
		
	}

}
