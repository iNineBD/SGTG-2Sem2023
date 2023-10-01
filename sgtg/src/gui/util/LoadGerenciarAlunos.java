package gui.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.DB;
import dto.GerenciarAlunoDTO;

public class LoadGerenciarAlunos {
	
	public static List<GerenciarAlunoDTO> atualizarDados() throws SQLException {
		List<GerenciarAlunoDTO> listaAlunos = new ArrayList<>();
		
		Connection conecta = DB.getConnection();

	    PreparedStatement st = conecta.prepareStatement("select aluno.id aluno_id, aluno.nome aluno, orientador.nome orientador, tipo.tipo, turma.nome turma from aluno, orientador, tg, tipo, turma, matricula where aluno.id_orientador = orientador.id and tg.id_aluno = aluno.id and tg.id_tipo = tipo.id and aluno.id = matricula.id_aluno and turma.id = matricula.id_turma order by aluno.id");

	    ResultSet result = st.executeQuery();
	    while (result.next()) {
	    	
	    	int id = result.getInt("aluno_id");
	    	String nome_aluno = result.getString("aluno");
	    	String nome_orientador = result.getString("orientador");
	    	String tipo = result.getString("tipo");
	    	String turma = result.getString("turma");
	    	
	    	
	    	GerenciarAlunoDTO aluno = new GerenciarAlunoDTO(id, nome_aluno, nome_orientador, turma, tipo);
	    	
	    	
	    	listaAlunos.add(aluno);
	    }
		
	    if (listaAlunos != null){

	        for (int i = 0; i < listaAlunos.size(); i++) {
	            GerenciarAlunoDTO alunoAtual = listaAlunos.get(i);
	            for (int j = i + 1; j < listaAlunos.size(); j++) {
	                if (alunoAtual.getId_aluno() == (listaAlunos.get(j).getId_aluno())) {
	                    alunoAtual.setNome_turma("TG1 e TG2");
	                    listaAlunos.remove(j);
	                }
	            }
	            
	        }
	    	
	    }
	    
	    return listaAlunos;
	}

	
	
}
