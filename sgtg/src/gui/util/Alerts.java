package gui.util;

import java.util.concurrent.atomic.AtomicBoolean;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Alerts {

	public static void showAlert(String title, String header, String content, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}
	
	public static boolean showAlertConfirmation(String title, String header, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		
		ButtonType simButton = new ButtonType("Sim");
        ButtonType naoButton = new ButtonType("Não");
		
        alert.getButtonTypes().setAll(simButton, naoButton);
        
        AtomicBoolean status = new AtomicBoolean(false);
     // Mostra o diálogo e aguarda a escolha do usuário
        alert.showAndWait().ifPresent(response -> {
            if (response == simButton) {
                status.set(true);
            } else if (response == naoButton) {
            	status.set(false);
            }
        });
        
        boolean resultado = status.get();
        
        return resultado;
	}
}