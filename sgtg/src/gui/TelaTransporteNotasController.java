package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import conexao.DB;
import dto.GerenciarAlunoDTO;
import dto.TurmasDTO;
import gui.util.LoadGerenciarAlunos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaTransporteNotasController {

	private LoadGerenciarAlunos loadAluno;

	Connection conecta = DB.getConnection();

	@FXML
	private TableView<GerenciarAlunoDTO> tableViewTransporteNotas;
	@FXML
	private ChoiceBox<TurmasDTO> choiceBoxTurma;
	@FXML
	private TableColumn<GerenciarAlunoDTO, String> tableColumnNome;
	@FXML
	private TableColumn<GerenciarAlunoDTO, String> tableColumnTipoTG;
	@FXML
	private TableColumn<GerenciarAlunoDTO, Integer> tableColumnMedia;
	
	private ObservableList<GerenciarAlunoDTO> obsList;

	public void setLoadAluno(LoadGerenciarAlunos loadAluno) {
		this.loadAluno = loadAluno;
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initializeNodes();

	}
	private void initializeNodes() {
		// TODO Auto-generated method stub
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome_aluno"));
		tableColumnTipoTG.setCellValueFactory(new PropertyValueFactory<>("tipo_tg"));
		tableColumnMedia.setCellValueFactory(new PropertyValueFactory<>("media"));
	}
	
	public void updateTableView() throws SQLException {
		if (loadAluno == null) {
			throw new IllegalStateException("Serviço gerenciar aluno fora do ar");
		}
		List<GerenciarAlunoDTO> listaAlunos = loadAluno.atualizarDados();
		obsList = FXCollections.observableArrayList(listaAlunos);

		tableViewTransporteNotas.setItems(obsList);

	}

}
