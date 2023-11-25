package gui.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.DB;
import dto.TurmasDTO;

public class LoadTurmas {
	
	public static List<TurmasDTO> carregaTurmas(int semestralizacao, int ano) throws SQLException{
		
		List<TurmasDTO> listaTurmas = new ArrayList<TurmasDTO>();
		
		new DB();
		Connection conn = DB.getConnection();
		
		
		PreparedStatement st = conn.prepareStatement("select id, nome from turma where semestralizacao = ? and ano = ?");
		st.setInt(1, semestralizacao);
		st.setInt(2, ano);
		
		
		ResultSet resultado = st.executeQuery();
		

			while(resultado.next()) {
				int id_turma = resultado.getInt("id");
				
//				System.out.println(id_turma);
				
				String nome_turma = resultado.getString("nome");
				
				TurmasDTO turma = new TurmasDTO(id_turma, nome_turma);
				
				listaTurmas.add(turma);
				
			}
		
		return listaTurmas;
		
	}

}
