package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import conexao.DB;
import dto.RelatorioOrientadorAlunoDTO;
import dto.TurmasDTO;
import gui.util.Alerts;
import gui.util.LoadOrientadorAluno;
import gui.util.LoadTurmas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaOrientadorAlunoController implements Initializable {
	public LoadOrientadorAluno loadOrientadorAluno = new LoadOrientadorAluno();

	private RelatorioOrientadorAlunoDTO orientadorAluno;

	Connection conecta = DB.getConnection();

	@FXML
	private TableColumn<RelatorioOrientadorAlunoDTO, String> TableColumnAluno;

	@FXML
	private TableColumn<RelatorioOrientadorAlunoDTO, String> TableColumnOrientador;

	@FXML
	private TableView<RelatorioOrientadorAlunoDTO> TableViewAlunoOrientador;

	@FXML
	private ChoiceBox<TurmasDTO> choiceBoxTurma;

	@FXML
	private Label labelSelecioneTurma;

	private ObservableList<RelatorioOrientadorAlunoDTO> obsList;

	public void setOrientadorAluno(RelatorioOrientadorAlunoDTO orientadorAluno) {
		this.orientadorAluno = orientadorAluno;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
		choiceBoxTurma.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				try {

					updateTableView(newValue.getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Alerts.showAlert("SQLException", "Erro ao buscar entregas",
							"Ocorreu um erro ao buscar as entregas para turma selcionada.", AlertType.WARNING);
				}
			}
		});
		List<TurmasDTO> listaTurmas = new ArrayList<TurmasDTO>();

		try {
			listaTurmas = LoadTurmas.carregaTurmas(2, LocalDate.now().getYear());

		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			Alerts.showAlert("SQLException", "Erro ao buscar turmas",
					"Ocorreu um erro ao buscar as turmas para o semestre atual.", AlertType.ERROR);
		}

		if (listaTurmas != null) {
			ObservableList<TurmasDTO> turmas = FXCollections.observableArrayList(listaTurmas);
			choiceBoxTurma.setItems(turmas);
		}
	}

	 private void initializeNodes() {
		 	// TODO Auto-generated method stub
			TableColumnAluno.setCellValueFactory(new PropertyValueFactory<>("nome_aluno"));
			TableColumnOrientador.setCellValueFactory(new PropertyValueFactory<>("nomeOrientador"));

		}

	public void updateTableView(int id_turma) throws SQLException { 
		if (loadOrientadorAluno == null) {
			throw new IllegalStateException("Serviço gerenciar aluno fora do ar");
		}
		List<RelatorioOrientadorAlunoDTO> listaAlunos = loadOrientadorAluno.carregaOrientadoresAlunos(id_turma); 
																								
		obsList = FXCollections.observableArrayList(listaAlunos);
		TableViewAlunoOrientador.setItems(obsList); 

	}

}
