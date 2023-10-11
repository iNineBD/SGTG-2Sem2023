package gui.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import conexao.DB;
import entidades.Aluno;

public class InsertBd {
	
	private PreparedStatement stBuscaEmailOrientador = null;
	 
	private PreparedStatement stBuscaIdTurma = null;
	
	private PreparedStatement stBuscaIdAluno = null;
	
	private PreparedStatement stBuscaIdTipo = null;
	
	private PreparedStatement stBuscaSemestreEAno = null;
	
	private PreparedStatement stAnoESemestre = null;
	
	private PreparedStatement stAluno = null;
	
	private PreparedStatement stOrientador = null;
	
	private PreparedStatement stTurma = null;
	
	private PreparedStatement stTg = null;
	
	private PreparedStatement stTipo = null;
	
	private PreparedStatement stMatricula = null;
	
	 public void insertBd(Aluno aluno){
	    	
	    	int ano = LocalDate.now().getYear();
	    	
	    	int mes = LocalDate.now().getMonthValue();
	    	
	    	int semestre;
	    	
	    	if(mes > 6) {
	    		semestre = 2;
	    	}else {
	    		semestre = 1;
	    	}
	    	
	    	
	    	Connection conecta = null;
	    	

	    	try	 {
	    	conecta = DB.getConnection();
	    	
	    	stBuscaEmailOrientador = conecta.prepareStatement("select orientador.id, orientador.email_fatec from orientador where email_fatec like ?");
	    	
	    	stBuscaIdTurma = conecta.prepareStatement("select turma.id, turma.nome from turma where turma.nome = ?");
	    	
	    	stBuscaIdAluno = conecta.prepareStatement("select aluno.id,aluno.email_institucional from aluno where aluno.email_institucional = ?");
	    	
	    	stBuscaIdTipo = conecta.prepareStatement("select tipo.id,tipo.tipo from tipo where tipo.tipo = ?");
	    	
	    	stBuscaSemestreEAno = conecta.prepareStatement("select semestre.semestralizacao, semestre.ano from semestre where semestre.semestralizacao = ? and semestre.ano = ?");
	    	
	    	stAnoESemestre = conecta.prepareStatement("insert into semestre(semestralizacao,ano) values(?,?)");
	    	
	    	stAluno = conecta.prepareStatement("insert into aluno (nome,email_institucional,email_pessoal,id_orientador) values(?,?,?,?)");
	    	
	    	stOrientador = conecta.prepareStatement("insert into orientador (nome,email_fatec) values(?,?)");
	    	
	    	stTurma = conecta.prepareStatement("insert into turma(nome,semestralizacao,ano) values(?,?,?)");
	    	
	    	stTg = conecta.prepareStatement("insert into tg(problema_a_resolver,empresa,disciplina,id_aluno,id_tipo) values(?,?,?,?,?)");
	    	
	    	stTipo = conecta.prepareStatement("insert into tipo(tipo,regra) values(?,?)");
	    	
	    	stMatricula = conecta.prepareStatement("insert into matricula(id_aluno,id_turma) values(?,?)");
	    	
	    	//Procurar se já existe o orientador no banco
	    	stBuscaEmailOrientador.setString(1, aluno.getEmailFatecOrientador());
	    	ResultSet result1 = stBuscaEmailOrientador.executeQuery();
	    	
	    	if(!result1.next()){
	    	
	    	stOrientador.setString(1,aluno.getOrientador());
	    	stOrientador.setString(2, aluno.getEmailFatecOrientador());
	    	stOrientador.executeUpdate();
	    	
	    	// Para fazer a consulta e achar o ID do orientador
	    	stBuscaEmailOrientador.setString(1, aluno.getEmailFatecOrientador());
	    	result1 = stBuscaEmailOrientador.executeQuery();
	    	result1.next();
	    	int idOrientador = result1.getInt("id");
	    	
	    	stAluno.setString(1, aluno.getNome());
	    	stAluno.setString(2, aluno.getEmailFatecAluno());
	    	stAluno.setString(3, aluno.getEmailPessoal());
	    	stAluno.setInt(4, idOrientador);
	    	stAluno.executeUpdate();
	    	
	    	// Para fazer a consulta e localizar o ID do aluno
	    	stBuscaIdAluno.setString(1, aluno.getEmailFatecAluno());
	    	ResultSet result3 = stBuscaIdAluno.executeQuery();
	    	result3.next();
	    	int idAluno = result3.getInt("id");
	    	
	    	// Para fazer a consulta e localizar o ID da turma
	    	stBuscaIdTurma.setString(1, aluno.getNomeTurma());
	    	ResultSet result2 = stBuscaIdTurma.executeQuery();
	        	
	    	if(!result2.next()) {
	    	// Verificação se no banco já existe ano e semestre
	    	try {
	    		stBuscaSemestreEAno.setInt(1, semestre);
	    		stBuscaSemestreEAno.setInt(2,ano);
	    		ResultSet result5 = stBuscaSemestreEAno.executeQuery();
	    		result5.next();
	    		int sem = result5.getInt("semestralizacao");
	    		int year = result5.getInt("ano");
	        	stTurma.setString(1,aluno.getNomeTurma());
	        	stTurma.setInt(2, sem);
	        	stTurma.setInt(3, year);
	        	stTurma.executeUpdate();
	        	
	        	stBuscaIdTurma.setString(1, aluno.getNomeTurma());
	        	result2 = stBuscaIdTurma.executeQuery();
	        	result2.next();
	        	int idTurma = result2.getInt("id");
	        	
	        	stMatricula.setInt(1, idAluno);
	        	stMatricula.setInt(2, idTurma);
	        	stMatricula.executeUpdate();
	        	       	
	        	stTipo.setString(1, aluno.getTipoTG());
	        	stTipo.setString(2, "123");
	        	stTipo.executeUpdate();
	        	
	        	// Para fazer a consulta e localizar o ID do tipo
	        	stBuscaIdTipo.setString(1, aluno.getTipoTG());
	        	ResultSet result4 = stBuscaIdTurma.executeQuery();
	        	result4.next();
	        	int idTipo = result4.getInt("id");
	        	
	        	stTg.setString(1, aluno.getProblemaResolvidoOuEstudoArtigo());
	        	stTg.setString(2, aluno.getEmpresa());
	        	stTg.setString(3, aluno.getDisciplina());
	        	stTg.setInt(4, idAluno);
	        	stTg.setInt(5, idTipo);
	        	stTg.executeUpdate();
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    		stAnoESemestre.setInt(1,semestre);
	    		stAnoESemestre.setInt(2, ano);
	    		stAnoESemestre.executeUpdate();
	    		
	    		stBuscaSemestreEAno.setInt(1, semestre);
	    		stBuscaSemestreEAno.setInt(2,ano);
	    		ResultSet result5 = stBuscaSemestreEAno.executeQuery();
	    		result5.next();
	    		int sem = result5.getInt("semestralizacao");
	    		int year = result5.getInt("ano");
	        	stTurma.setString(1,aluno.getNomeTurma());
	        	stTurma.setInt(2, sem);
	        	stTurma.setInt(3,year);
	        	stTurma.executeUpdate();
	        	
	        	stBuscaIdTurma.setString(1, aluno.getNomeTurma());
	        	result2 = stBuscaIdTurma.executeQuery();
	        	result2.next();
	        	int idTurma = result2.getInt("id");
	        	stMatricula.setInt(1, idAluno);
	        	stMatricula.setInt(2, idTurma);
	        	stMatricula.executeUpdate();
	        	
	        	
	        	stTipo.setString(1, aluno.getTipoTG());
	        	stTipo.setString(2, "123");
	        	stTipo.executeUpdate();
	        	
	        	// Para fazer a consulta e localizar o ID do tipo
	        	stBuscaIdTipo.setString(1, aluno.getTipoTG());
	        	ResultSet result4 = stBuscaIdTurma.executeQuery();
	        	result4.next();
	        	int idTipo = result4.getInt("id");
	        	
	        	stTg.setString(1, aluno.getProblemaResolvidoOuEstudoArtigo());
	        	stTg.setString(2, aluno.getEmpresa());
	        	stTg.setString(3, aluno.getDisciplina());
	        	stTg.setInt(4, idAluno);
	        	stTg.setInt(5, idTipo);
	        	stTg.executeUpdate();
	    	}}
	    	else {
	    	int idTurma = result2.getInt("id");
	    	
	    	stMatricula.setInt(1, idAluno);
	    	stMatricula.setInt(2, idTurma);
	    	stMatricula.executeUpdate();
	    	
	    	
	    	stTipo.setString(1, aluno.getTipoTG());
	    	stTipo.setString(2, "123");
	    	stTipo.executeUpdate();
	    	
	    	// Para fazer a consulta e localizar o ID do tipo
	    	stBuscaIdTipo.setString(1, aluno.getTipoTG());
	    	ResultSet result4 = stBuscaIdTurma.executeQuery();
	    	result4.next();
	    	int idTipo = result4.getInt("id");
	    	
	    	stTg.setString(1, aluno.getProblemaResolvidoOuEstudoArtigo());
	    	stTg.setString(2, aluno.getEmpresa());
	    	stTg.setString(3, aluno.getDisciplina());
	    	stTg.setInt(4, idAluno);
	    	stTg.setInt(5, idTipo);
	    	stTg.executeUpdate();
	    	}
	    	}else {
	        	// Para fazer a consulta e achar o ID do orientador
	        	int idOrientador = result1.getInt("id");
	        	
	        	stAluno.setString(1, aluno.getNome());
	        	stAluno.setString(2, aluno.getEmailFatecAluno());
	        	stAluno.setString(3, aluno.getEmailPessoal());
	        	stAluno.setInt(4, idOrientador);
	        	stAluno.executeUpdate();
	        	
	        	// Para fazer a consulta e localizar o ID do aluno
	        	stBuscaIdAluno.setString(1, aluno.getEmailFatecAluno());
	        	ResultSet result3 = stBuscaIdAluno.executeQuery();
	        	result3.next();
	        	int idAluno = result3.getInt("id");
	        	
	        	// Para fazer a consulta e localizar o ID da turma
	        	stBuscaIdTurma.setString(1, aluno.getNomeTurma());
	        	ResultSet result2 = stBuscaIdTurma.executeQuery();
	            	
	        	if(!result2.next()) {
	        	// Verificação se no banco já existe ano e semestre
	        	try {
	        		stBuscaSemestreEAno.setInt(1, semestre);
	        		stBuscaSemestreEAno.setInt(2,ano);
	        		ResultSet result5 = stBuscaSemestreEAno.executeQuery();
	        		result5.next();
	        		int sem = result5.getInt("semestralizacao");
	        		int year = result5.getInt("ano");
	            	stTurma.setString(1,aluno.getNomeTurma());
	            	stTurma.setInt(2, sem);
	            	stTurma.setInt(3, year);
	            	stTurma.executeUpdate();
	            	
	            	stBuscaIdTurma.setString(1, aluno.getNomeTurma());
	            	result2 = stBuscaIdTurma.executeQuery();
	            	result2.next();
	            	int idTurma = result2.getInt("id");
	            	
	            	stMatricula.setInt(1, idAluno);
	            	stMatricula.setInt(2, idTurma);
	            	stMatricula.executeUpdate();
	            	       	
	            	stTipo.setString(1, aluno.getTipoTG());
	            	stTipo.setString(2, "123");
	            	stTipo.executeUpdate();
	            	
	            	// Para fazer a consulta e localizar o ID do tipo
	            	stBuscaIdTipo.setString(1, aluno.getTipoTG());
	            	ResultSet result4 = stBuscaIdTurma.executeQuery();
	            	result4.next();
	            	int idTipo = result4.getInt("id");
	            	
	            	stTg.setString(1, aluno.getProblemaResolvidoOuEstudoArtigo());
	            	stTg.setString(2, aluno.getEmpresa());
	            	stTg.setString(3, aluno.getDisciplina());
	            	stTg.setInt(4, idAluno);
	            	stTg.setInt(5, idTipo);
	            	stTg.executeUpdate();
	        	}catch(SQLException e) {
	        		e.printStackTrace();
	        		stAnoESemestre.setInt(1,semestre);
	        		stAnoESemestre.setInt(2, ano);
	        		stAnoESemestre.executeUpdate();
	        		
	        		stBuscaSemestreEAno.setInt(1, semestre);
	        		stBuscaSemestreEAno.setInt(2,ano);
	        		ResultSet result5 = stBuscaSemestreEAno.executeQuery();
	        		result5.next();
	        		int sem = result5.getInt("semestralizacao");
	        		int year = result5.getInt("ano");
	            	stTurma.setString(1,aluno.getNomeTurma());
	            	stTurma.setInt(2, sem);
	            	stTurma.setInt(3,year);
	            	stTurma.executeUpdate();
	            	
	            	stBuscaIdTurma.setString(1, aluno.getNomeTurma());
	            	result2 = stBuscaIdTurma.executeQuery();
	            	result2.next();
	            	int idTurma = result2.getInt("id");
	            	stMatricula.setInt(1, idAluno);
	            	stMatricula.setInt(2, idTurma);
	            	stMatricula.executeUpdate();
	            	
	            	
	            	stTipo.setString(1, aluno.getTipoTG());
	            	stTipo.setString(2, "123");
	            	stTipo.executeUpdate();
	            	
	            	// Para fazer a consulta e localizar o ID do tipo
	            	stBuscaIdTipo.setString(1, aluno.getTipoTG());
	            	ResultSet result4 = stBuscaIdTurma.executeQuery();
	            	result4.next();
	            	int idTipo = result4.getInt("id");
	            	
	            	stTg.setString(1, aluno.getProblemaResolvidoOuEstudoArtigo());
	            	stTg.setString(2, aluno.getEmpresa());
	            	stTg.setString(3, aluno.getDisciplina());
	            	stTg.setInt(4, idAluno);
	            	stTg.setInt(5, idTipo);
	            	stTg.executeUpdate();
	        	}}
	        	else {
	        	int idTurma = result2.getInt("id");
	        	
	        	stMatricula.setInt(1, idAluno);
	        	stMatricula.setInt(2, idTurma);
	        	stMatricula.executeUpdate();
	        	
	        	
	        	stTipo.setString(1, aluno.getTipoTG());
	        	stTipo.setString(2, "123");
	        	stTipo.executeUpdate();
	        	
	        	// Para fazer a consulta e localizar o ID do tipo
	        	stBuscaIdTipo.setString(1, aluno.getTipoTG());
	        	ResultSet result4 = stBuscaIdTurma.executeQuery();
	        	result4.next();
	        	int idTipo = result4.getInt("id");
	        	
	        	stTg.setString(1, aluno.getProblemaResolvidoOuEstudoArtigo());
	        	stTg.setString(2, aluno.getEmpresa());
	        	stTg.setString(3, aluno.getDisciplina());
	        	stTg.setInt(4, idAluno);
	        	stTg.setInt(5, idTipo);
	        	stTg.executeUpdate();
	        	}
	    		
	    	}

	    	} catch (SQLException e) {
				// TODO Auto-generated catch block
	    		e.printStackTrace();
//	    		Alerts.showAlert("SQL Exception","Erro","Este aluno já está cadastrado", AlertType.ERROR);
			} 
	    		
	    	}

}
