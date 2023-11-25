package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dto.AptosADefenderDTO;
import gui.util.LoadAptos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaAptosADefenderController implements Initializable {

	
	@FXML
	private TableView<AptosADefenderDTO> table;
	@FXML
	private TableColumn<AptosADefenderDTO, String> tableColumnNome;
	@FXML
	private TableColumn<AptosADefenderDTO, String> tableColumnEmailAluno;
	@FXML
	private TableColumn<AptosADefenderDTO, String> tableColumnEmailOrientador;
	@FXML
	private TableColumn<AptosADefenderDTO, String> tableColumnTipoTG;
	@FXML
	private TableColumn<AptosADefenderDTO, String> tableColumnTituloTG;
	
	private ObservableList<AptosADefenderDTO> obsList; 	

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initializeNodes();
		try {
			updateTableView();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void initializeNodes() {
		// TODO Auto-generated mehod stub
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnEmailAluno.setCellValueFactory(new PropertyValueFactory<>("email_aluno"));
		tableColumnEmailOrientador.setCellValueFactory(new PropertyValueFactory<>("email_orientador"));
		tableColumnTipoTG.setCellValueFactory(new PropertyValueFactory<>("tipo_tg"));
		tableColumnTituloTG.setCellValueFactory(new PropertyValueFactory<>("titulo_tg"));
	}
	
	
	public void updateTableView() throws SQLException {
		
		List<AptosADefenderDTO> listAptos = LoadAptos.selectDados();

		obsList = FXCollections.observableArrayList(listAptos);
        // listAptos.add(aptosADefenderDTO);
        
        table.setItems(obsList);
		
	}

}