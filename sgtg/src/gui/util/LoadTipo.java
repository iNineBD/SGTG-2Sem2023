package gui.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.DB;
import dto.TipoDTO;

public class LoadTipo {

	public  static List<TipoDTO> carregaTipos() throws SQLException{
		List<TipoDTO> listaTipos = new ArrayList<TipoDTO>();
		
		
		Connection con = DB.getConnection();
		
		PreparedStatement st = con.prepareStatement("select * from tipo");
		ResultSet result = st.executeQuery();
		
		while(result.next()) {
			int id = result.getInt("id");
			String tipo = result.getString("tipo");
			String regra = result.getString("regra");
			
			TipoDTO tipoDTO = new TipoDTO(id, tipo, regra);
			listaTipos.add(tipoDTO);		
		}
		
		for (int i = 0; i < listaTipos.size(); i++) {
			if (listaTipos.get(i).getTipo().equals("Relatório Técnico - Disciplina")){
				listaTipos.get(i).setTipo("Relatórios (disciplina e estágio)");
			}
			if (listaTipos.get(i).getTipo().equals("Relatório Técnico - Estágio")){
				listaTipos.remove(i);
			}
		}
		
		return listaTipos;
	}
	
}
