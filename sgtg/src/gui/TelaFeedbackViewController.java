package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import conexao.DB;
import dto.FeedbackDTO;
import gui.util.LoadFeedback;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class TelaFeedbackViewController implements Initializable {

	private LoadFeedback loadFeedback;

	private TableView<FeedbackDTO> tableViewFeedback;
	@FXML
	private Label labelNomeAluno;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initializeNodes();
	}

	private void initializeNodes() {
		// TODO Auto-generated method stub
		try {
			Connection connection = DB.getConnection();

			PreparedStatement statement = connection.prepareStatement("SELECT nome FROM aluno WHERE id = ?");

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String nome = resultSet.getString("nome");

				labelNomeAluno.setText(labelNomeAluno.getText() + nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
