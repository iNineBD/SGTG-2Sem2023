package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import conexao.DB;
import dto.CBTransporteNotaDTO;
import dto.TransporteNotaDTO;
import gui.util.Alerts;
import gui.util.LoadTransporteNota;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaTransporteNotasController implements Initializable {

	public LoadTransporteNota loadTransporteNota = new LoadTransporteNota();
	@FXML
	private ChoiceBox<CBTransporteNotaDTO> choiceBoxTurma = new ChoiceBox<CBTransporteNotaDTO>();
	@FXML
	private TableView<TransporteNotaDTO> tableViewNotas;
	@FXML
	private TableColumn<TransporteNotaDTO, String> tableColumnNome;
	@FXML
	private TableColumn<TransporteNotaDTO, String> tableColumnTipoTG;
	@FXML
	private TableColumn<TransporteNotaDTO, String> tableColumnMedia;

	public ChoiceBox<CBTransporteNotaDTO> getChoiceBoxTurma() {
		return choiceBoxTurma;
	}

	public void setChoiceBoxTurma(ChoiceBox<CBTransporteNotaDTO> choiceBoxTurma) {
		this.choiceBoxTurma = choiceBoxTurma;
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
		atualizarDadosChoiceBox();
		choiceBoxTurma.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                	this.loadTransporteNota = new LoadTransporteNota();
                    updateTableView(newValue.getIdTurma());
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                	e.printStackTrace();
                    Alerts.showAlert("SQLException", "Erro ao buscar entregas",
                            "Ocorreu um erro ao buscar as entregas para turma selcionada.", AlertType.WARNING);
                }
            }
        });
	}

	public void initializeNodes() {
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnTipoTG.setCellValueFactory(new PropertyValueFactory<>("tipo_tg"));
		tableColumnMedia.setCellValueFactory(new PropertyValueFactory<>("media"));
	}

	public void atualizarDadosChoiceBox() {

		List<CBTransporteNotaDTO> listTurma = new ArrayList<>();
		Connection conn = DB.getConnection();
		PreparedStatement st;
		try {
			st = conn.prepareStatement("select id, nome,semestralizacao,ano from turma");
			ResultSet result = st.executeQuery();
			while (result.next()) {
				int id_turma_selecionada = result.getInt("id");
				String nome_turma_selecionada = result.getString("nome");
				int semestre_turma_selecionada = result.getInt("semestralizacao");
				int ano_turma_selecionada = result.getInt("ano");
				CBTransporteNotaDTO cbTransporteNotaDTO = new CBTransporteNotaDTO();
				cbTransporteNotaDTO.setIdTurma(id_turma_selecionada);
				cbTransporteNotaDTO.setNome(nome_turma_selecionada);
				cbTransporteNotaDTO.setSemestralizacao(semestre_turma_selecionada);
				cbTransporteNotaDTO.setAno(ano_turma_selecionada);
				listTurma.add(cbTransporteNotaDTO);
			}
			
			ObservableList<CBTransporteNotaDTO> turmas = FXCollections.observableArrayList(listTurma);
			choiceBoxTurma.setItems(turmas);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alerts.showAlert("SQLException", "Erro ao buscar turmas", "Ocorreu um erro ao buscar as turmas",
					AlertType.ERROR);
		}

	}

	public void updateTableView(int id_turma) throws SQLException {
		if (loadTransporteNota == null) {
			throw new IllegalStateException("Transporte de notas indisponivel");
		}
		List<TransporteNotaDTO> listNotas = LoadTransporteNota.dados(id_turma);
		ObservableList<TransporteNotaDTO>obsList = FXCollections.observableArrayList(listNotas);
		tableViewNotas.setItems(obsList);

	}

}
