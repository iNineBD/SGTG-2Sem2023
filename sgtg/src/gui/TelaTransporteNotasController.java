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
import dto.TransporteNotaDTO;
import gui.util.Alerts;
import gui.util.LoadTransporteNota;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaTransporteNotasController {

	public LoadTransporteNota loadTransporteNota = new LoadTransporteNota();

	private TransporteNotaDTO transporteNota;

	Connection conecta = DB.getConnection();

	@FXML
	private TableView<TransporteNotaDTO> tableViewTransporteNotas;
	@FXML
	private ChoiceBox<TransporteNotaDTO> choiceBoxTurma;
	@FXML
	private TableColumn<TransporteNotaDTO, String> tableColumnNome;
	@FXML
	private TableColumn<TransporteNotaDTO, String> tableColumnTipoTG;
	@FXML
	private TableColumn<TransporteNotaDTO, Integer> tableColumnMedia;

	private ObservableList<TransporteNotaDTO> obsList;

	public void atualizarDadosChoiceBox() {

		List<TransporteNotaDTO> listTurma = new ArrayList<TransporteNotaDTO>();

		Connection conn = DB.getConnection();

		PreparedStatement st2;
		try {
			st2 = conn.prepareStatement("select id, nome,semestralizacao,ano from turma ");
			ResultSet result2 = st2.executeQuery();

			while (result2.next())
			// pegando informaçoes do banco
			{
				int id_turma_selecionada = result2.getInt("id");
				String nome_turma_selecionada = result2.getString("nome");
				int semestralizacao = result2.getInt("semestralizacao");
				int ano_turma_selecionada = result2.getInt("ano");

				// jogando pro DTO
				TransporteNotaDTO transporteNotaDTO = new TransporteNotaDTO(ano_turma_selecionada, nome_turma_selecionada, ano_turma_selecionada, ano_turma_selecionada, ano_turma_selecionada, nome_turma_selecionada);

				transporteNotaDTO.setId_turma(id_turma_selecionada);
				transporteNotaDTO.setAno_semestre(ano_turma_selecionada);
				transporteNotaDTO.setNome_turma(nome_turma_selecionada);
				transporteNotaDTO.setSemestralizacao(semestralizacao);
				listTurma.add(transporteNotaDTO);
			}

			// criando lista para choice box
			ObservableList<TransporteNotaDTO> observableList = FXCollections.observableArrayList(listTurma);
			choiceBoxTurma.getItems().addAll(observableList);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alerts.showAlert("SQLException", "Erro ao buscar turmas", "Ocorreu um erro ao buscar as turmas",
					AlertType.ERROR);
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
    		initializeNodes();
    		atualizarDadosChoiceBox();
    	}
	
	public void setTransporteNota(TransporteNotaDTO transporteNota) {
		this.transporteNota = transporteNota;
	}

	private void initializeNodes() {
		// TODO Auto-generated method stub
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome_aluno"));
		tableColumnTipoTG.setCellValueFactory(new PropertyValueFactory<>("tipo_tg"));
		tableColumnMedia.setCellValueFactory(new PropertyValueFactory<>("media"));
	}

//	public void updateTableView() throws SQLException {
//		if (loadAluno == null) {
//			throw new IllegalStateException("Serviço gerenciar aluno fora do ar");
//		}
//		List<TransporteNotaDTO> listaAlunos = loadAluno.atualizarDados();
//		obsList = FXCollections.observableArrayList(listaAlunos);
//
//		tableViewTransporteNotas.setItems(obsList);
//
//	}
}
