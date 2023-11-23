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
	    	stAlunos = conn.prepareStatement("SELECT aluno.nome AS nome_aluno, aluno.email_pessoal AS email_aluno, orientador.email_fatec AS email_orientador "
	    			+ ", tipo.tipo AS tipo_tg, IFNULL(tg.problema_a_resolver, '') AS titulo_tg, aluno.id AS id_aluno, count(entrega.id) total_entregas "
	    			+ "FROM aluno, orientador, tipo, tg, entrega, matricula\r\n"
	    			+ "WHERE aluno.id_orientador = orientador.id AND tipo.id = tg.id_tipo AND tg.id_aluno = aluno.id AND aluno.id = matricula.id_aluno AND matricula.id_turma = entrega.id_turma "
	    			+ "GROUP BY nome_aluno, email_aluno, email_orientador, tipo_tg, titulo_tg, id_aluno;");
	        
	        ResultSet resultAlunos = stAlunos.executeQuery();

	        while (resultAlunos.next()) {
	        	
	            String nome_aluno = resultAlunos.getString("nome_aluno");
	            String email_aluno = resultAlunos.getString("email_aluno");
	            String email_orientador = resultAlunos.getString("email_orientador");
	            String tipo_tg = resultAlunos.getString("tipo_tg");
	            String titulo_tg = resultAlunos.getString("titulo_tg");
	            
	            // informações que não serão exibidas na tela
	            int id_aluno = resultAlunos.getInt("id_aluno");
	            int total_entregas = resultAlunos.getInt("total_entregas");
		        
		        // tabela com id aluno + total_feedback
		        stFeedback = conn.prepareStatement("SELECT count(feedback.id_aluno) total_feedback "
		        		+ "FROM feedback "
		        		+ "WHERE feedback.id_aluno = " + id_aluno + ";");
		       
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
