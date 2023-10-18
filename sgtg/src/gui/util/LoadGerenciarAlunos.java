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
	
	public List<GerenciarAlunoDTO> atualizarDados() throws SQLException {
		
		List<GerenciarAlunoDTO> listaAlunos = new ArrayList<>();
		
		Connection conecta = DB.getConnection();

		// busca as informações da tela gerenciar aluno
		
	    PreparedStatement st = conecta.prepareStatement("select aluno.id aluno_id, aluno.nome aluno,aluno.email_institucional email_fatec,aluno.email_pessoal email_pessoal,orientador.nome orientador,orientador.email_fatec email_fatec_orientador, tipo.tipo tipo, tipo.regra regra, turma.nome turma, turma.id id_turma,tg.disciplina disciplina,tg.empresa empresa,tg.problema_a_resolver tema from aluno, orientador, tg, tipo, turma, matricula where aluno.id_orientador = orientador.id and tg.id_aluno = aluno.id and tg.id_tipo = tipo.id and aluno.id = matricula.id_aluno and turma.id = matricula.id_turma and tg.id_aluno = aluno.id and tg.id_tipo = tipo.id order by aluno.id");

	    ResultSet result = st.executeQuery();
	    
	    while (result.next()) {
	    	
	    	int id = result.getInt("aluno_id");
	    	String nome_aluno = result.getString("aluno");
	    	String emailAlunoFatec = result.getString("email_fatec");
	    	String emailAlunoPessoal = result.getString("email_pessoal");
	    	String nome_orientador = result.getString("orientador");
	    	String emailOrientador = result.getString("email_fatec");
	    	String tipo_tg = result.getString("tipo");
	    	String nome_turma = result.getString("turma");
	    	String disciplina = result.getString("disciplina");
	    	String tituloTg = result.getString("tema");
	    	String regra = result.getString("regra");
	    	String empresa = result.getString("empresa");
	    	int id_turma = result.getInt("id_turma");
	    	
//	    	System.out.println(id +" - "+ nome_aluno+" - "+ nome_orientador+" - "+ turma+" - "+ tipo+" - "+ id_turma);	
	    	GerenciarAlunoDTO aluno = new GerenciarAlunoDTO(id,nome_aluno,emailAlunoPessoal,emailAlunoFatec,nome_orientador,emailOrientador,nome_turma,tipo_tg,tituloTg,empresa,disciplina,regra,id_turma);
	    	
	    	
	    	// total de entregas
	    	int total_entregas = 0;
	    	int entrega_aluno = 0;
	    	
	    	PreparedStatement st2 = conecta.prepareStatement("select count(id) n_entregas from entrega where id_turma = ?");
	    	st2.setInt(1, id_turma);
	    	
	    	ResultSet result2 = st2.executeQuery();
	    	
	    	while (result2.next()) {
	    		total_entregas += result2.getInt("n_entregas");
			}
	    	
	    	// entregas feitas pelo aluno
	    	PreparedStatement st3 = conecta.prepareStatement("select count(id_aluno) entrega_aluno from feedback where id_aluno = ?");
	    	st3.setInt(1, id);
	    	
	    	ResultSet result3 = st3.executeQuery();
	    	
	    	if (result3.next()) {
	    		entrega_aluno = result3.getInt("entrega_aluno");
			}
	    	
	    	// setando no objeto
	    	aluno.setEntregas_feitas(entrega_aluno);
	    	aluno.setTotal_entregas(total_entregas);
	    	
	    	listaAlunos.add(aluno);
	    }
		
	    if (listaAlunos != null){
//	    	verifica os alunos do tg1 e tg2
	        for (int i = 0; i < listaAlunos.size(); i++) {
	            GerenciarAlunoDTO alunoAtual = listaAlunos.get(i);

	            for (int j = i + 1; j < listaAlunos.size(); j++) {
	                if (alunoAtual.getId_aluno() == (listaAlunos.get(j).getId_aluno())&& alunoAtual.getId_turma() !=(listaAlunos.get(j).getId_turma()) ) {
	                	alunoAtual.setNome_turma("TG1 e TG2");
	                	alunoAtual.setTotal_entregas(alunoAtual.getTotal_entregas()+listaAlunos.get(j).getTotal_entregas());
	                    listaAlunos.remove(j);
	                }
	                
	                alunoAtual.setEntregas_format(alunoAtual.getEntregas_feitas() + "/" + alunoAtual.getTotal_entregas());
	            }
//	            
//	            System.out.println(alunoAtual.getEntregas_feitas() + "/" + alunoAtual.getTotal_entregas());
//                System.out.println(alunoAtual.getNome_aluno());
	            
	        }
	    }
	    return listaAlunos;
	}

	
	
}
