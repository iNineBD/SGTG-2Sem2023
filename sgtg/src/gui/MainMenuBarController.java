package gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Telas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainMenuBarController implements Initializable{
	
	private Telas loadTelas = new Telas();

	@FXML
	private MenuItem menuItemCarregarCsv;
	
	@FXML
	private MenuItem menuItemGerenciarAluno;
	
	@FXML
	private MenuItem menuItemVisualizarEntregas;

	@FXML
	private MenuItem menuItemNovaEntrega;
	
	@FXML
	private MenuItem menuItemSobre;
	
	
	@FXML
	public void onMenuItemCarregarCsvAction() {
		TelaInicialController carregaCsv = new TelaInicialController();
		carregaCsv.onBtCarrgarCsvAction();
        }
	
	@FXML
	public void onMenuItemGerenciarAlunoAction() {
		loadTelas.loadView("/gui/TelaGerenciarAlunos.fxml");
	}
	
	@FXML
	public void onMenuItemNovaEntrega() {
		loadTelas.loadView("/gui/TelaCadastrarEntregas.fxml");
	}
	
	@FXML
	public void onMenuItemVisualizarEntregas() {
		loadTelas.loadView("/gui/TelaEntregaTurma.fxml");
	}
	
	@FXML
	public void onMenuItemSobreAction() {
		loadTelas.loadView("/gui/TelaSobre.fxml");
	}
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {

	}

}
