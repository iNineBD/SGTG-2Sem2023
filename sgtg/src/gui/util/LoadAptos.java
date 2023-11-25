package gui.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.DB;
import dto.AptosADefenderDTO;
import javafx.scene.control.Alert.AlertType;

public class LoadAptos {
	
	public static List<AptosADefenderDTO> selectDados() throws SQLException {
		
		// buscar todos os alunos - ok
		// verificar total de entregas para O ALUNO - erro para "portifólio tg1/tg2"
		// verificar total de feedbacks O ALUNO - ok
		// se feedback => entregas -> APTO
		
		List<AptosADefenderDTO> listaAptos = new ArrayList<AptosADefenderDTO>();
		
		Connection conn = DB.getConnection();

	    PreparedStatement stAlunos; // todos os alunos
	    PreparedStatement stFeedback; // entregas

	    
	    try {
	    	// tabela aluno + total entregas
	    	stAlunos = conn.prepareStatement("select aluno.nome as nome_aluno, aluno.email_pessoal as email_pessoal_aluno, orientador.email_fatec as email_orientador,\r\n"
	    			+ "tipo.id as id_tipo, tipo.tipo as tipo_tg, tg.problema_a_resolver as titulo_tg, aluno.id as id_aluno from aluno\r\n"
	    			+ "inner join orientador on\r\n"
	    			+ "aluno.id_orientador = orientador.id\r\n"
	    			+ "inner join tipo on\r\n"
	    			+ "aluno.id_tipo = tipo.id\r\n"
	    			+ "inner join tg on\r\n"
	    			+ "aluno.id = tg.id_aluno\r\n"
	    			+ "inner join entrega_tipo on\r\n"
	    			+ "entrega_tipo.id_tipo = tipo.id\r\n"
	    			+ "inner join entrega on\r\n"
	    			+ "entrega_tipo.id_entrega = entrega.id\r\n"
	    			+ "inner join matricula on\r\n"
	    			+ "aluno.id = matricula.id_aluno\r\n"
	    			+ "group by aluno.nome, aluno.email_pessoal, orientador.email_fatec, tipo.tipo, tg.problema_a_resolver, aluno.id;");
	        
	        ResultSet resultAlunos = stAlunos.executeQuery();

	        while (resultAlunos.next()) {
	        	
	            String nome_aluno = resultAlunos.getString("nome_aluno");
	            String email_aluno = resultAlunos.getString("email_pessoal_aluno");
	            String email_orientador = resultAlunos.getString("email_orientador");
	            String tipo_tg = resultAlunos.getString("tipo_tg");
	            String titulo_tg = resultAlunos.getString("titulo_tg");
	            int id_tipo = resultAlunos.getInt("id_tipo");
	            
	            // informações que não serão exibidas na tela
	            int id_aluno = resultAlunos.getInt("id_aluno");
	            int total_entregas = 0;
	            
	            PreparedStatement st2 = conn.prepareStatement("select count(entrega.id) as n_entrega from entrega_tipo inner join entrega on entrega.id = entrega_tipo.id_entrega inner join entrega_turma on entrega.id = entrega_turma.id_entrega inner join matricula on entrega_turma.id_turma = matricula.id_turma where entrega_tipo.id_tipo = ? and id_aluno = ? and entrega.visibility = 1");
		    	st2.setInt(1, id_tipo);
		    	st2.setInt(2, id_aluno);
		    	
		    	ResultSet result2 = st2.executeQuery();
		    	
		    	while (result2.next()) {
		    		total_entregas += result2.getInt("n_entrega");
				}
		    	
		    	if ((!tipo_tg.equals("Portfólio")) && total_entregas > 0) {
		    		total_entregas = total_entregas/2;
				}
		    	
		        // tabela com id aluno + total_feedback
		        stFeedback = conn.prepareStatement("select count(feedback.id_aluno) total_feedback "
		        		+ "from feedback "
		        		+ "where feedback.id_aluno = ?;");
		        
		        stFeedback.setInt(1,id_aluno);
		        
		        ResultSet resultFeedback = stFeedback.executeQuery();

	            resultFeedback.next();
	            int total_feedback = resultFeedback.getInt("total_feedback");
	            
	            if(total_feedback >= total_entregas) {
	            	AptosADefenderDTO aptosADefender = new AptosADefenderDTO(nome_aluno, email_aluno, email_orientador, tipo_tg, titulo_tg);
		            listaAptos.add(aptosADefender);
	            }
	            
	        }
	        

	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        Alerts.showAlert("SQLException", "Erro ao buscar alunos", "Ocorreu um erro ao buscar os alunos aptos",
	                AlertType.ERROR);
	    }
	    
		return listaAptos;
		
	}
}
