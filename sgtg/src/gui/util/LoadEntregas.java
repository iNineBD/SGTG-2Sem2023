package gui.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.DB;
import dto.EntregasDTO;
import dto.TurmasDTO;
import gui.TelaEditarEntregaController;
import javafx.collections.ObservableList;

public class LoadEntregas {
	public List<EntregasDTO> atualizarDados(int id_turma_selecionada) throws SQLException{

        List<EntregasDTO> entregas = new ArrayList<EntregasDTO>();

        Connection conn = DB.getConnection();

        PreparedStatement st = conn.prepareStatement("select id, titulo_entrega, data_entrega, descricao from entrega where id_turma = ? and visibility = true");
        st.setInt(1, id_turma_selecionada);

        ResultSet result = st.executeQuery();

        while (result.next()) {

            int id_entrega = result.getInt("id");
            String titulo = result.getString("titulo_entrega");
            String descricao = result.getString("descricao");
            Date data= result.getDate("data_entrega");

            entregas.add(new EntregasDTO(id_entrega, titulo, descricao, data.toLocalDate(), id_turma_selecionada));
        }

        return entregas;

    }
	

	
	public static List<EntregasDTO> atualizarDadosComboBox(int id_aluno_selecionada ) throws SQLException{

        List<EntregasDTO> entregas = new ArrayList<EntregasDTO>();

        Connection conn = DB.getConnection();

        PreparedStatement st2 = conn.prepareStatement("select id_turma from matricula where id_aluno = ? ");
        st2.setInt(1, id_aluno_selecionada); 

        ResultSet result2 = st2.executeQuery();

        while (result2.next()) { 
            int id_turma_selecionada = result2.getInt("id_turma");
            PreparedStatement st = conn.prepareStatement(
                    "select id, titulo_entrega, data_entrega, descricao from entrega where id_turma = ?");
            st.setInt(1, id_turma_selecionada);



            ResultSet result = st.executeQuery();

            while (result.next()) {

                int id_entrega = result.getInt("id");
                String titulo = result.getString("titulo_entrega");
                String descricao = result.getString("descricao");
                Date data= result.getDate("data_entrega");

                entregas.add(new EntregasDTO(id_entrega, titulo, descricao, data.toLocalDate(),id_turma_selecionada));
            }

        }




        return entregas;

    }
	
	
	
	public static void editarEntregaAUX(TelaEditarEntregaController controller, EntregasDTO obj) {
		
		controller.setTxtFieldTituloEntrega(obj.getTitulo());
		controller.setTxtAreaDescricao(obj.getDescricao());
		controller.setDatePickerDataFinal(obj.getData_final());
		
		ObservableList<TurmasDTO> listaChoice = controller.getItensChoiceBox();
		
		for (TurmasDTO turma: listaChoice) {
			
			if (turma.getId() == obj.getId_turma()) {
				controller.setChoiceBox(turma);
			}
			
		}
		
		controller.setId(obj.getId());
		
	}

}
