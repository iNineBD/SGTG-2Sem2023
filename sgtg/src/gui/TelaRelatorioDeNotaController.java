package gui;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import dto.NotasDTO;
import gui.util.Alerts;
import gui.util.LoadNotas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaRelatorioDeNotaController implements Initializable{
		
	@FXML
	private Label lbSemestreAno; 
	
	@FXML
	private TableView<NotasDTO> tableViewRelatorioDeNotas;
	
	@FXML
	private TableColumn<NotasDTO, String> tableViewNome;
	
	@FXML
	private TableColumn<NotasDTO, String> tabeViewTurma;
	
	@FXML
	private TableColumn<NotasDTO, String> tabeViewTipo;
	
	@FXML
	private TableColumn<NotasDTO, String> tableViewEntregas;
	
	@FXML
	private TableColumn <NotasDTO, Double> tableViewNotas;
	
	private ObservableList<NotasDTO> obsList;
	
	private LoadNotas loadNotas = new LoadNotas();
	
	@Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        initializeNodes();
        try {
			updateTableView();
			// Semestre e ano
			int semestralizacao = (LocalDate.now().getMonthValue() <= 6) ? 1 : 2;

			int anoAtual = LocalDate.now().getYear();

			lbSemestreAno.setText(String.format("%d° / %d", semestralizacao, anoAtual));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alerts.showAlert("Campo nulo", "Cuidado", "Todos os campos devem ser preenchidos", AlertType.WARNING);
		}
    }

    public void initializeNodes() {
        // TODO Auto-generated method stub
    	tableViewNome.setCellValueFactory(new PropertyValueFactory<>("nome_aluno"));
    	tabeViewTipo.setCellValueFactory(new PropertyValueFactory<>("tipo_tg"));
    	tabeViewTurma.setCellValueFactory(new PropertyValueFactory<>("nome_turma"));
    	tableViewEntregas.setCellValueFactory(new PropertyValueFactory<>("entregas_format"));
    	tableViewNotas.setCellValueFactory(new PropertyValueFactory<>("nota"));
    } 
    public void updateTableView() throws SQLException {
//			throw new IllegalStateException("Serviço gerenciar aluno fora do ar");
		
		List<NotasDTO> listNota = loadNotas.informacoes();
			
		obsList = FXCollections.observableArrayList(listNota);
		tableViewRelatorioDeNotas.setItems(obsList);
	
    }
}
