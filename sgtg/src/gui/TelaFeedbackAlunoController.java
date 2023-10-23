package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import conexao.DB;
import dto.EntregasDTO;
import dto.FeedbackDTO;
import dto.GerenciarAlunoDTO;
import dto.TurmasDTO;
import gui.util.Alerts;
import gui.util.LoadEntregas;
import gui.util.Telas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelaFeedbackAlunoController implements Initializable {

	@FXML
	private Label lbNomeAluno;

	public void setLbNomeAluno(String nomeAluno) {
		this.lbNomeAluno.setText(nomeAluno);
	}

	@FXML
	private ComboBox<EntregasDTO> comboBoxEntrega;

	@FXML
	private Label lbDescricao;

	@FXML
	private TextField TxtFieldNota;

	@FXML
	private TextArea TxtAreaComentario;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	// AS INFORMAÇÕES DO ALUNO EM QUESTÃO ESTÃO AQUIIIIIIII!!!!!!!
	private GerenciarAlunoDTO aluno;

	public void setAluno(GerenciarAlunoDTO aluno) {
		this.aluno = aluno;
	}

	public void onBtnSalvarAction() throws SQLException {
		EntregasDTO entrega_selecionada = comboBoxEntrega.getValue();
		float nota = 0; 
		try {
			nota = Float.parseFloat(TxtFieldNota.getText().trim()) ;
			String comentario = TxtAreaComentario.getText();

			if (entrega_selecionada == null || comentario.trim().isEmpty()) {
				Alerts.showAlert("Campo nulo", "Cuidado", "Todos os campos devem ser preenchidos", AlertType.WARNING);
			} else {
				try {
					Connection conn = DB.getConnection();

					PreparedStatement st2 = conn.prepareStatement(
							"insert into feedback (id_entrega, nota, comentario, id_aluno) values (?, ?, ?, ?)");
					st2.setInt(1, entrega_selecionada.getId());
					st2.setFloat(2, nota);
					st2.setString(3, comentario);
					st2.setInt(4, aluno.getId_aluno());
					st2.executeUpdate();
					Alerts.showAlert("Sucesso", "Feedback dado!!!", "O feedback foi armazenado com sucesso",
							AlertType.INFORMATION);
					Telas load = new Telas();
					load.loadView99("/gui/TelaFeedbackView.fxml", this.aluno);
				} catch (SQLException e) {
				
					Alerts.showAlert("Atenção", "Já foi dado um feedback para este aluno", "Já foi atribuido um feedback para esta entrega",
							AlertType.INFORMATION);

				}
			}
		
		} catch (NumberFormatException e) {
			Alerts.showAlert("Formato invalido!", "A nota foi dada no formato invalido!", "Favor digite neste formato: 0.0",
					AlertType.INFORMATION);
		}
		

	}

	public void onBtnCancelarAction() {
		Telas tela = new Telas();

		tela.loadView99("/gui/TelaFeedbackView.fxml", this.aluno);

	}

	public void carregarEntregas() {
		// preenchimento do ComboBox Entrega
		List<EntregasDTO> listaEntrega = new ArrayList<EntregasDTO>();
		// TODO Auto-generated method stub
		try {
			int id_aluno_selecionada = this.aluno.getId_aluno();

			listaEntrega = LoadEntregas.atualizarDadosComboBox(id_aluno_selecionada);// Oq eu preciso passar no parenteses
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alerts.showAlert("SQLException", "Erro ao buscar entregas",
					"Ocorreu um erro ao buscar as entregas por aluno.", AlertType.ERROR);
		}
		if (listaEntrega != null) {
			ObservableList<EntregasDTO> entregas = FXCollections.observableArrayList(listaEntrega);
			comboBoxEntrega.getItems().addAll(entregas);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comboBoxEntrega.setOnAction(event -> {
            EntregasDTO selectedItem = comboBoxEntrega.getValue();
            // Execute ação com base na seleção
            lbDescricao.setText(selectedItem.getDescricao());
        });
	}
}
