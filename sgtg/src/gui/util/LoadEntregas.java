package gui.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import conexao.DB;
import dto.EntregasDTO;
import dto.TurmasDTO;
import gui.TelaEditarEntregaController;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class LoadEntregas {
	public List<EntregasDTO> atualizarDados(int tipo_selecionado) throws SQLException {

		List<EntregasDTO> entregas = new ArrayList<EntregasDTO>();

		Connection conn = DB.getConnection();

		PreparedStatement st = conn.prepareStatement(
				"select entrega.id, entrega.titulo_entrega, entrega.descricao, entrega.data_entrega, turma.id as id_turma, turma.nome as turma from entrega, turma, entrega_tipo, entrega_turma where entrega.id = entrega_tipo.id_entrega and entrega.id = entrega_turma.id_entrega and turma.id = entrega_turma.id_turma and entrega_tipo.id_tipo = ? and entrega.visibility = true");
		st.setInt(1, tipo_selecionado);

		ResultSet result = st.executeQuery();

		while (result.next()) {

			int id_entrega = result.getInt("id");
			String titulo = result.getString("titulo_entrega");
			String descricao = result.getString("descricao");
			Date data = result.getDate("data_entrega");
			int id_turma_selecionada = result.getInt("id_turma");
			String turma = result.getString("turma");

			entregas.add(
					new EntregasDTO(id_entrega, titulo, descricao, data.toLocalDate(), id_turma_selecionada, turma));
		}

		if (entregas.size() > 1) {
			for (int i = 0; i < entregas.size(); i++) {
				for (int j = i+1; j < entregas.size(); j++) {
					if (entregas.get(i).getId() == entregas.get(j).getId()) {
						entregas.get(i).setNome_turma("TG1 e TG2");
						entregas.remove(j);
					}
				}
			}
		}

		return entregas;

	}

//	public static List<EntregasDTO> atualizarDadosComboBox(int id_aluno_selecionada ) throws SQLException{
//
//        List<EntregasDTO> entregas = new ArrayList<EntregasDTO>();
//
//        Connection conn = DB.getConnection();
//
//        PreparedStatement st2 = conn.prepareStatement("select id_turma from matricula where id_aluno = ? ");
//        st2.setInt(1, id_aluno_selecionada); 
//
//        ResultSet result2 = st2.executeQuery();
//
//        while (result2.next()) { 
//            int id_turma_selecionada = result2.getInt("id_turma");
//            PreparedStatement st = conn.prepareStatement(
//                    "select id, titulo_entrega, data_entrega, descricao from entrega where id_turma = ? and visibility = 1");
//            st.setInt(1, id_turma_selecionada);
//
//
//
//            ResultSet result = st.executeQuery();
//
//            while (result.next()) {
//
//                int id_entrega = result.getInt("id");
//                String titulo = result.getString("titulo_entrega");
//                String descricao = result.getString("descricao");
//                Date data= result.getDate("data_entrega");
//
//                entregas.add(new EntregasDTO(id_entrega, titulo, descricao, data.toLocalDate(),id_turma_selecionada));
//            }
//
//        }
//
//
//
//
//        return entregas;
//
//    }
//	

	public static void editarEntregaAUX(TelaEditarEntregaController controller, EntregasDTO obj, String tipo) {
		
		if (tipo.equals("Relatório técnico - disciplina") || tipo.equals("Relatório técnico - estágio")) {
			tipo = "Relatórios (disciplina e estágio)";
		}

		controller.setTxtFieldTituloEntrega(obj.getTitulo());
		controller.setTxtAreaDescricao(obj.getDescricao());
		controller.setDatePickerDataFinal(obj.getData_final());
		controller.setLbSemestreAno(tipo + " - " + obj.getNome_turma());

		controller.setId(obj.getId());

	}

}
