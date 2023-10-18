package gui.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexao.DB;
import dto.FeedbackDTO;

public class LoadFeedback {

	public List<FeedbackDTO> dadosFeed() throws SQLException {

		List<FeedbackDTO> dadosFeed = new ArrayList<FeedbackDTO>();

		Connection conecta = DB.getConnection();

		PreparedStatement st = conecta.prepareStatement(
				"SELECT id_turma.entrega, titulo_entrega.entrega, descricao.entrega, id_aluno.feedback, nota.feedback, comentario.feedback FROM entrega, feedback WHERE id_turma, id_aluno");

		ResultSet result = st.executeQuery();

		while (result.next()) {

			int id_turma = result.getInt("id_turma");
			String titulo_entrega = result.getString("titulo_entrega");
			String descricao = result.getString("descricao");
			int id_aluno = result.getInt("id_aluno");
			double nota = result.getDouble("nota");
			String comentario = result.getString("comentario");
			
			dadosFeed.add(new FeedbackDTO(id_turma, titulo_entrega, descricao, id_aluno, nota, comentario));
			
		}

		return dadosFeed;
	}

}
