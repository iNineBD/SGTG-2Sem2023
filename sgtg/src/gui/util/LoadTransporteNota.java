package gui.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import conexao.DB;
import dto.TransporteNotaDTO;

public class LoadTransporteNota {
	public static List<TransporteNotaDTO> dados() throws SQLException {
		List<TransporteNotaDTO> listNotas = new ArrayList<>();

		Connection conecta = DB.getConnection();

		PreparedStatement st = conecta.prepareStatement(
				"SELECT aluno.id id_aluno, aluno.nome, tipo.id id_tipo, tipo.tipo FROM aluno, tipo where aluno.id_tipo = tipo.id AND tipo LIKE 'R%' or tipo LIKE 'A%'");
		ResultSet result = st.executeQuery();
		while (result.next()) {
			String nome_aluno = result.getString("nome");
			String tipo_tg = result.getString("tipo");
			int id_aluno = result.getInt("id_aluno");
			int id_tipo = result.getInt("id_tipo");
			PreparedStatement st2 = conecta
					.prepareStatement("SELECT AVG(feedback.nota) AS media FROM feedback WHERE feedback.id_Aluno = ?");
			st2.setInt(1, id_aluno);
			ResultSet result2 = st2.executeQuery();
			while (result2.next()) {
				String media = result2.getString("media");
				TransporteNotaDTO notas = new TransporteNotaDTO(nome_aluno, tipo_tg, media);
				listNotas.add(notas);

			}

		}

		return listNotas;

	}

}
