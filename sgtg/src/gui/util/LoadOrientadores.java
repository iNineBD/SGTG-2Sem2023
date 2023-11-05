package gui.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import conexao.DB;
import dto.OrientadorDto;

public class LoadOrientadores {
	
	public static List<OrientadorDto> carregaOrientadores() throws SQLException{
		
		List<OrientadorDto> listaOrientador = new ArrayList<OrientadorDto>();
		
		new DB();
		Connection con = DB.getConnection();
		
		PreparedStatement st = con.prepareStatement("select id, nome, email_fatec from orientador");
		ResultSet result = st.executeQuery();
		
		while(result.next()) {
			int idOrientador = result.getInt("id");
			String nomeOrientador = result.getString("nome");
			String emailOrientador = result.getString("email_fatec");
			
			OrientadorDto orientador = new OrientadorDto(idOrientador,nomeOrientador,emailOrientador);
			
			listaOrientador.add(orientador);
		}
		
		return (listaOrientador);
		
	}
}
