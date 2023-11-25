package gui.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.DB;
import dto.TransporteNotaDTO;

public class LoadTransporteNota {
	public static List<TransporteNotaDTO> dados(int id_turma) throws SQLException {
		List<TransporteNotaDTO> listNotas = new ArrayList<>();

		Connection conecta = DB.getConnection();

		PreparedStatement st = conecta.prepareStatement("select aluno.id id_aluno, aluno.nome, tipo.id id_tipo, tipo.tipo, matricula.id_turma from aluno inner join tipo on aluno.id_tipo = tipo.id inner join matricula on matricula.id_aluno = aluno.id where matricula.id_turma = ?");
		st.setInt(1, id_turma);
		
		ResultSet result = st.executeQuery();
		while (result.next()) {
			String nome_aluno = result.getString("nome");
			String tipo_tg = result.getString("tipo");
			int id_aluno = result.getInt("id_aluno");
			
			
			PreparedStatement st2 = conecta.prepareStatement("SELECT AVG(feedback.nota) AS media FROM feedback WHERE feedback.id_Aluno = ?");
			
			st2.setInt(1, id_aluno);
			ResultSet result2 = st2.executeQuery();
			
			while (result2.next()) {
				Double media = result2.getDouble("media");
				 @SuppressWarnings("deprecation")
				BigDecimal mdDecimal = new BigDecimal(media).setScale(2, BigDecimal.ROUND_HALF_UP);
				
				if(media >= 6) {
					TransporteNotaDTO notas = new TransporteNotaDTO(nome_aluno, tipo_tg, mdDecimal.doubleValue());
					listNotas.add(notas);					
				}
			}
		}

		return listNotas;

	}

}
