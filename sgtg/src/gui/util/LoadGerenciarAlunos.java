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
	    PreparedStatement st = conecta.prepareStatement("select aluno.id as id_aluno,aluno.nome as nome_aluno,aluno.email_institucional as email_fatec_aluno,aluno.email_pessoal as email_pessoal, aluno.id_tipo as id_tipo, tipo.tipo as nome_tipo,tipo.id as id_tipo, tipo.regra as regra, orientador.id as id_orientador,orientador.nome as nome_orientador,orientador.email_fatec as email_fatec_orientador,turma.id as id_turma,turma.nome as nome_turma, tg.problema_a_resolver as tema,tg.empresa as empresa,tg.disciplina as disciplina from aluno inner join orientador on aluno.id_orientador = orientador.id inner join tg on aluno.id = tg.id_aluno inner join matricula on aluno.id = matricula.id_aluno inner join turma on matricula.id_turma = turma.id inner join tipo on aluno.id_tipo = tipo.id where aluno.visibility = 1 order by aluno.id;");

	    ResultSet result = st.executeQuery();
	    
	    while (result.next()) {
	    	
	    	int id_aluno = result.getInt("id_aluno");
	    	String nome_aluno = result.getString("nome_aluno");
	    	String emailAlunoFatec = result.getString("email_fatec_aluno");
	    	String emailAlunoPessoal = result.getString("email_pessoal");
	    	String nome_orientador = result.getString("nome_orientador");
	    	String emailOrientador = result.getString("email_fatec_orientador");
	    	String tipo_tg = result.getString("nome_tipo");
	    	String nome_turma = result.getString("nome_turma");
	    	String disciplina = result.getString("disciplina");
	    	String tituloTg = result.getString("tema");
	    	String regra = result.getString("regra");
	    	String empresa = result.getString("empresa");
	    	int id_turma = result.getInt("id_turma");
	    	int id_tipo = result.getInt("id_tipo");
	    	int id_orientador = result.getInt("id_orientador");
	    	
//	    	System.out.println(id +" - "+ nome_aluno+" - "+ nome_orientador+" - "+ turma+" - "+ tipo+" - "+ id_turma);	
	    	GerenciarAlunoDTO aluno = new GerenciarAlunoDTO(id_aluno,nome_aluno,emailAlunoPessoal,emailAlunoFatec,nome_orientador,emailOrientador,nome_turma,tipo_tg,tituloTg,empresa,disciplina,regra,id_turma,id_orientador);
	    	
	    	
	    	// total de entregas
	    	int total_entregas = 0;
	    	int entrega_aluno = 0;
	    	
	    	PreparedStatement st2 = conecta.prepareStatement("select count(entrega.id) as n_entrega from entrega_tipo inner join entrega on entrega.id = entrega_tipo.id_entrega inner join entrega_turma on entrega.id = entrega_turma.id_entrega inner join matricula on entrega_turma.id_turma = matricula.id_turma where entrega_tipo.id_tipo = ? and id_aluno = ? and entrega.visibility = 1");
	    	st2.setInt(1, id_tipo);
	    	st2.setInt(2, id_aluno);
	    	
	    	ResultSet result2 = st2.executeQuery();
	    	
	    	while (result2.next()) {
	    		total_entregas += result2.getInt("n_entrega");
			}
	    	
	    	// entregas feitas pelo aluno
	    	PreparedStatement st3 = conecta.prepareStatement("select count(id_aluno) entrega_aluno from feedback where id_aluno = ? and visibility = 1");
	    	st3.setInt(1, id_aluno);
	    	
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
	            

	            for (int j = i ; j < listaAlunos.size(); j++) {
	                if (alunoAtual.getId_aluno() == (listaAlunos.get(j).getId_aluno())&& alunoAtual.getId_turma() !=(listaAlunos.get(j).getId_turma()) ) {
	                	alunoAtual.setNome_turma("TG1 e TG2");
	                    listaAlunos.remove(j);
	                }
	                
	                alunoAtual.setEntregas_format(alunoAtual.getEntregas_feitas() + "/" + alunoAtual.getTotal_entregas());
	            }
	            
//	            System.out.println(alunoAtual.getNome_aluno() + " - " + alunoAtual.getEntregas_feitas() + "/" + alunoAtual.getTotal_entregas());
//                System.out.println();
	            
	        }
	    }
	    return listaAlunos;
	}

	
	
}
