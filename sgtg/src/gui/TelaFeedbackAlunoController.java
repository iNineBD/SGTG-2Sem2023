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
import dto.FeedbackDTO;
import gui.util.Alerts;
import gui.util.Telas;
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

	@FXML
	private ComboBox<FeedbackDTO> comboBoxEntrega;

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

	public void onBtnSalvarAction() throws SQLException {
		FeedbackDTO entrega_selecionada = comboBoxEntrega.getValue();
		String nota = TxtFieldNota.getText();
		String comentario = TxtAreaComentario.getText();

		if (entrega_selecionada == null || nota.trim().isEmpty() || comentario.trim().isEmpty()) {
			Alerts.showAlert("Campo nulo", "Cuidado", "Todos os campos devem ser preenchidos", AlertType.WARNING);
		} else {
			try {
				Connection conn = DB.getConnection();
				PreparedStatement st = conn.prepareStatement("select nome from aluno where id = ?");
				ResultSet resultSet = st.executeQuery();
				while (resultSet.next()) {
					String nome = resultSet.getString("nome");

					lbNomeAluno.setText(lbNomeAluno.getText() + nome);

				
				PreparedStatement st2 = conn.prepareStatement(
						"insert into feedback (id_entrega, nota, comentario, id_aluno) values (?, ?, ?, ?)");
				st2.setString(1, entrega_selecionada.getTitulo_entrega());
				st2.setObject(2, nota);
				st2.setString(3, comentario);
//				st2.setInt(4,  id_aluno);
				st2.executeUpdate();
				Alerts.showAlert("Sucesso", "Feedback dado!!!",
						"O feedback foi armazenado com sucesso", AlertType.INFORMATION);
				}
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
	}
	public void onBtnCancelarAction() {
		Telas tela = new Telas();

		tela.loadView("/gui/TelaFeedbackView.fxml");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
