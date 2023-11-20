package gui.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.DB;
import dto.TransporteNotaDTO;

public class LoadTransporteNota {

	public static List<TransporteNotaDTO> carregarNomeTipoNota(int id_tg) throws SQLException {

		List<dto.TransporteNotaDTO> listacarregarTipoTG = new ArrayList<TransporteNotaDTO>();
		new DB();
		Connection con = DB.getConnection();
		PreparedStatement st = con.prepareStatement(
				"select tipo.id, substring(tipo, 1, 100) AS tipo from tipo where tipo LIKE 'R%' or tipo LIKE 'A%'");
		st.setInt(1, id_tg);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {

			int id_tg1 = rs.getInt("id");
			String tipo_tg = rs.getString("tipo");

			TransporteNotaDTO transporteNotaDTO = new TransporteNotaDTO(id_tg1, tipo_tg, id_tg1, id_tg1, id_tg1, tipo_tg);
			listacarregarTipoTG.add(transporteNotaDTO);
		}

		return listacarregarTipoTG;
	}
}
