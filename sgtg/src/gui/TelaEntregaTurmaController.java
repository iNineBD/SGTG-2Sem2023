package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import conexao.DB;
import dto.EntregasDTO;
import dto.TurmasDTO;
import gui.util.Alerts;
import gui.util.LoadEntregas;
import gui.util.LoadTurmas;
import gui.util.Telas;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaEntregaTurmaController implements Initializable {

	private LoadEntregas loadEntregas;

	@FXML
	private ComboBox<TurmasDTO> comboBoxTurma;
	@FXML
	private TableView<EntregasDTO> tableViewEntregas;
	@FXML
	private TableColumn<EntregasDTO, String> tableColumnTitulo;
	@FXML
	private TableColumn<EntregasDTO, String> tableColumnDescricao;
	@FXML
	private TableColumn<EntregasDTO, LocalDate> tableColumnData;
	@FXML
	private TableColumn<EntregasDTO, EntregasDTO> tableColumnEditar;
	
	@FXML
	private TableColumn<EntregasDTO, EntregasDTO> tableColumnDel;

	private ObservableList<EntregasDTO> obsList;

	public void setLoadEntregas(LoadEntregas loadEntregas) {
		this.loadEntregas = loadEntregas;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initializeNodes();

		comboBoxTurma.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				try {
					this.loadEntregas = new LoadEntregas();
					updateTableView();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					Alerts.showAlert("SQLException", "Erro ao buscar entregas",
							"Ocorreu um erro ao buscar as entregas para turma selcionada.", AlertType.WARNING);
				}
			}
		});

		// preenchimento do combobox Entregas

		List<TurmasDTO> listaTurmas = new ArrayList<TurmasDTO>();

		try {

			listaTurmas = LoadTurmas.carregaTurmas((LocalDate.now().getMonthValue() > 6 ? 2 : 1),
					LocalDate.now().getYear());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alerts.showAlert("SQLException", "Erro ao buscar turmas",
					"Ocorreu um erro ao buscar as turmas para o semestre atual.", AlertType.ERROR);
		}

		if (listaTurmas != null) {
			ObservableList<TurmasDTO> turmas = FXCollections.observableArrayList(listaTurmas);
			comboBoxTurma.setItems(turmas);
		}

	}

	private void initializeNodes() {
		// TODO Auto-generated method stub
		tableColumnData.setCellValueFactory(new PropertyValueFactory<>("data_final"));
		tableColumnTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
	}

	public void updateTableView() throws SQLException {
		if (loadEntregas == null) {
			throw new IllegalStateException("Serviço carregar entregas fora do ar");
		}
		List<EntregasDTO> listaEntregas = loadEntregas.atualizarDados(comboBoxTurma.getValue().getId());
		obsList = FXCollections.observableArrayList(listaEntregas);

		tableViewEntregas.setItems(obsList);

		initEditButtons();
	}

	private void initEditButtons() {
		tableColumnEditar.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEditar.setCellFactory(param -> new TableCell<EntregasDTO, EntregasDTO>() {
			private final Button button = new Button("‍✏");

			@Override
			protected void updateItem(EntregasDTO obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> {
					Telas telas = new Telas();
					telas.loadView87("/gui/TelaEditarEntrega.fxml", obj);
				});
			}
		});

		tableColumnDel.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnDel.setCellFactory(param -> new TableCell<EntregasDTO, EntregasDTO>() {
			private final Button del = new Button("❌");

			@Override
			protected void updateItem(EntregasDTO obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(del);
				del.setOnAction(event -> {
					delEntrega(obj);
				});
			}
		});

	}
	
	public void delEntrega(EntregasDTO entrega) {
		
		boolean resultado = Alerts.showAlertConfirmation("Excluir entrega", "Você esta prestes a excluir uma entrega...", "Tem certeza?\nTal ação não poderá ser desfeita");
		
		if (resultado){
			
			try {
				
				new DB();
				Connection conn = DB.getConnection();
				
				PreparedStatement st = conn.prepareStatement(
						"update entrega set visibility = false where id = ?");
				st.setInt(1, entrega.getId());

				st.executeUpdate();
				
				updateTableView();
			} catch (SQLException e) {
				Alerts.showAlert("Erro ao acessar banco de dados", "Houve algum problema com a conexão.", "Tente novamente", AlertType.ERROR);
			}
			
			
		}
		
	}

}
