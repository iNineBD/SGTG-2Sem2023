package application;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Main.fxml"));
			ScrollPane scrollPane = loader.load();
			
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			
			this.mainScene = new Scene(scrollPane);
			
			primaryStage.setScene(mainScene);
			
			
			Image iconeInine = new Image(getClass().getResourceAsStream("/imgs/inine_logo.png"));
			
			primaryStage.getIcons().add(iconeInine);
			primaryStage.setTitle("Sistema Gerenciador de TG's");
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public static Scene getMainScene() {
		return mainScene;
	}


	public static void main(String[] args) {
		launch(args);
	}
}
