package gui.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;

import conexao.DB;
import dto.GerenciarAlunoDTO;
import entidades.Aluno;
import gui.TelaEditarAlunoController;
import javafx.scene.control.Alert.AlertType;

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
    
    private PreparedStatement stAtualizaAluno = null;
    private PreparedStatement stAtualizaTg = null;
    private PreparedStatement stAtualizaMatricula = null;
    private PreparedStatement stBuscaIdOrientador = null;
    

    public void insertBd(Aluno aluno) {
        int ano = LocalDate.now().getYear();
        int mes = LocalDate.now().getMonthValue();
        int semestre = (mes > 6) ? 2 : 1;
        Connection conecta = null;
        
        try {
            conecta = DB.getConnection();
            prepararStatements(conecta);

            int idOrientador = buscarOuInserirOrientador(aluno.getEmailFatecOrientador(), aluno.getOrientador());
            int idAluno = buscarOuInserirAluno(aluno);
            int idTurma = buscarOuInserirTurma(aluno.getNomeTurma(), semestre, ano,aluno);
            inserirMatricula(idAluno, idTurma,semestre,ano,aluno);

            int idTipo = buscarOuInserirTipo(aluno.getTipoTG(), aluno.getRegra());
            inserirTg(aluno, idAluno, idTipo);
            

        }catch(SQLIntegrityConstraintViolationException a) {
        	try {
        		conecta = DB.getConnection();
        		prepararStatements(conecta);
        		
        		int idAluno = buscarOuInserirAluno(aluno);
        		int idOrientador = buscarOuInserirOrientador(aluno.getEmailFatecOrientador(), aluno.getOrientador());
                int idTurma = buscarOuInserirTurma(aluno.getNomeTurma(), semestre, ano,aluno);
        		int idTipo = buscarOuInserirTipo(aluno.getTipoTG(), aluno.getRegra());
                
                PreparedStatement stAtualizaAluno = conecta.prepareStatement("update aluno set aluno.nome = ?, aluno.email_institucional = ?, aluno.email_pessoal = ?, aluno.id_orientador = ? where aluno.id = ?;");
                stAtualizaAluno.setString(1, aluno.getNome());
				stAtualizaAluno.setString(2, aluno.getEmailFatecAluno());
				stAtualizaAluno.setString(3, aluno.getEmailPessoal());
				stAtualizaAluno.setInt(4,idOrientador);
				stAtualizaAluno.setInt(5, idAluno);
				stAtualizaAluno.executeUpdate();
	
                
				stAtualizaMatricula = conecta.prepareStatement("update matricula set matricula.id_turma = ? where matricula.id_aluno = ?");
				stAtualizaMatricula.setInt(1, idTurma);
				stAtualizaMatricula.setInt(2, idAluno);
				stAtualizaMatricula.executeUpdate();
				
				stAtualizaTg = conecta.prepareStatement("update tg set tg.problema_a_resolver = ?, tg.empresa = ?, tg.disciplina = ?, tg.id_tipo = ? where tg.id_aluno = ?");
				stAtualizaTg.setString(1, aluno.getProblemaResolvidoOuEstudoArtigo());
				stAtualizaTg.setString(2, aluno.getEmpresa());
				stAtualizaTg.setString(3,aluno.getDisciplina());
				stAtualizaTg.setInt(4,idTipo);
				stAtualizaTg.setInt(5,idAluno);
				stAtualizaTg.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Alerts.showAlert("SQL Exception","Erro","O aluno " + aluno.getNome()+ " já está cadastrado.", AlertType.ERROR);
			}
//        	
//        	return;
        }catch (SQLException e) {
//            e.printStackTrace();
        	Alerts.showAlert("SQL Exception","Erro","O aluno " + aluno.getNome()+ " já está cadastrado.", AlertType.ERROR);
            // Handle the exception as needed (e.g., show an error message).
        }
    }

    private void prepararStatements(Connection conecta) throws SQLException {
        stBuscaEmailOrientador = conecta.prepareStatement("select orientador.id, orientador.email_fatec from orientador where email_fatec like ?");
        stBuscaIdTurma = conecta.prepareStatement("select turma.id, turma.nome from turma where turma.nome = ?");
        stBuscaIdAluno = conecta.prepareStatement("select aluno.id,aluno.email_institucional,aluno.email_pessoal from aluno where aluno.email_pessoal = ?");
        stBuscaIdTipo = conecta.prepareStatement("select tipo.id,tipo.tipo from tipo where tipo.tipo = ?");
        stBuscaSemestreEAno = conecta.prepareStatement("select semestre.semestralizacao, semestre.ano from semestre where semestre.semestralizacao = ? and semestre.ano = ?");
        stAnoESemestre = conecta.prepareStatement("insert into semestre(semestralizacao,ano) values(?,?)");
        stAluno = conecta.prepareStatement("insert into aluno (nome,email_institucional,email_pessoal,id_orientador) values(?,?,?,?)");
        stOrientador = conecta.prepareStatement("insert into orientador (nome,email_fatec) values(?,?)");
        stTurma = conecta.prepareStatement("insert into turma(nome,semestralizacao,ano) values(?,?,?)");
        stTg = conecta.prepareStatement("insert into tg(problema_a_resolver,empresa,disciplina,id_aluno,id_tipo) values(?,?,?,?,?)");
        stTipo = conecta.prepareStatement("insert into tipo(tipo,regra) values(?,?)");
        stMatricula = conecta.prepareStatement("insert into matricula(id_aluno,id_turma) values(?,?)");
       
    }

    private int buscarOuInserirOrientador(String emailFatecOrientador, String nomeOrientador) throws SQLException {
        stBuscaEmailOrientador.setString(1, emailFatecOrientador);
        ResultSet result1 = stBuscaEmailOrientador.executeQuery();

        if (!result1.next()) {
            stOrientador.setString(1, nomeOrientador);
            stOrientador.setString(2, emailFatecOrientador);
            stOrientador.executeUpdate();
            
            stBuscaEmailOrientador.setString(1, emailFatecOrientador);
            result1 = stBuscaEmailOrientador.executeQuery();
            result1.next();
        }

        return result1.getInt("id");
    }

    private int buscarOuInserirAluno(Aluno aluno) throws SQLException {
        stBuscaIdAluno.setString(1, aluno.getEmailPessoal());
        ResultSet result3 = stBuscaIdAluno.executeQuery();

        if (!result3.next()) {
            stAluno.setString(1, aluno.getNome());
            stAluno.setString(2, aluno.getEmailFatecAluno());
            stAluno.setString(3, aluno.getEmailPessoal());
            stAluno.setInt(4, buscarOuInserirOrientador(aluno.getEmailFatecOrientador(), aluno.getOrientador()));
            stAluno.executeUpdate();

            stBuscaIdAluno.setString(1, aluno.getEmailPessoal());
            result3 = stBuscaIdAluno.executeQuery();
            result3.next();
        }

        return result3.getInt("id");
    }

    private int buscarOuInserirTurma(String nomeTurma, int semestre, int ano, Aluno aluno) throws SQLException {
        if (nomeTurma.equals("TG1 E TG2")) {
            int idTurmaTG1 = buscarOuInserirTurma("TG1", semestre, ano,aluno);
            int idTurmaTG2 = buscarOuInserirTurma("TG2", semestre, ano,aluno);
            
            
            // Verifique se ambas as turmas TG1 e TG2 existem antes de retornar a nova turma.
            if (idTurmaTG1 != -1 && idTurmaTG2 != -1) {
                // Ambas as turmas existem, você pode prosseguir.
                return -1;
            } else if (idTurmaTG1 != -1) {
                // Apenas TG2 está faltando, insira TG2.
                return idTurmaTG2;
            } else if (idTurmaTG2 != -1) {
                // Apenas TG1 está faltando, insira TG1.
                return idTurmaTG1;
            }
            
        }        
        stBuscaIdTurma.setString(1, nomeTurma);
        ResultSet result2 = stBuscaIdTurma.executeQuery();

        if (!result2.next()) {
            try {
                stBuscaSemestreEAno.setInt(1, semestre);
                stBuscaSemestreEAno.setInt(2, ano);
                ResultSet result5 = stBuscaSemestreEAno.executeQuery();
                result5.next();
                stTurma.setString(1, nomeTurma);
                stTurma.setInt(2, result5.getInt("semestralizacao"));
                stTurma.setInt(3, result5.getInt("ano"));
                stTurma.executeUpdate();

                stBuscaIdTurma.setString(1, nomeTurma);
                result2 = stBuscaIdTurma.executeQuery();
                result2.next();
            } catch (SQLException e) {
                
                stAnoESemestre.setInt(1, semestre);
                stAnoESemestre.setInt(2, ano);
                stAnoESemestre.executeUpdate();
                
                stBuscaSemestreEAno.setInt(1, semestre);
                stBuscaSemestreEAno.setInt(2, ano);
                ResultSet result5 = stBuscaSemestreEAno.executeQuery();
                result5.next();
                stTurma.setString(1, nomeTurma);
                stTurma.setInt(2, result5.getInt("semestralizacao"));
                stTurma.setInt(3, result5.getInt("ano"));
                stTurma.executeUpdate();

                stBuscaIdTurma.setString(1, nomeTurma);
                result2 = stBuscaIdTurma.executeQuery();
                result2.next();
            }
        }

        return result2.getInt("id");
    }


    private int buscarOuInserirTipo(String tipoTG, String regra) throws SQLException {
        stBuscaIdTipo.setString(1, tipoTG);
        ResultSet result4 = stBuscaIdTipo.executeQuery();

        if (!result4.next()) {
            stTipo.setString(1, tipoTG);
            stTipo.setString(2, regra);
            stTipo.executeUpdate();
            
            stBuscaIdTipo.setString(1, tipoTG);
            result4 = stBuscaIdTipo.executeQuery();
            result4.next();
        }

        return result4.getInt("id");
    }

    private void inserirTg(Aluno aluno, int idAluno, int idTipo) throws SQLException {
        stTg.setString(1, aluno.getProblemaResolvidoOuEstudoArtigo());
        stTg.setString(2, aluno.getEmpresa());
        stTg.setString(3, aluno.getDisciplina());
        stTg.setInt(4, idAluno);
        stTg.setInt(5, idTipo);
        stTg.executeUpdate();
    }

    private void inserirMatricula(int idAluno, int idTurma, int semestre, int ano,Aluno aluno) throws SQLException {
        // Verifique se a turma é "TG1 E TG2".
        if (idTurma == -1) {
            // Inserir o aluno nas turmas "TG1" e "TG2" se ele estiver matriculado na turma composta "TG1 E TG2".
            int idTurmaTG1 = buscarOuInserirTurma("TG1", semestre, ano,aluno);
            int idTurmaTG2 = buscarOuInserirTurma("TG2", semestre, ano,aluno);

            if (idTurmaTG1 != -1 && idTurmaTG2 != -1) {
                // O aluno não está matriculado em nenhuma das turmas individuais, então matricule-o em ambas.
                stMatricula.setInt(1, idAluno);
                stMatricula.setInt(2, idTurmaTG1);
                stMatricula.executeUpdate();

                stMatricula.setInt(1, idAluno);
                stMatricula.setInt(2, idTurmaTG2);
                stMatricula.executeUpdate();
            }
        } else {
            // Inserir o aluno na turma especificada.
            stMatricula.setInt(1, idAluno);
            stMatricula.setInt(2, idTurma);
            stMatricula.executeUpdate();
        }
    }    
    
    public void atualizaAluno(int id_aluno,TelaEditarAlunoController controller) throws SQLException {
    	Connection conecta = conecta = DB.getConnection();
    	
    	stBuscaIdOrientador = conecta.prepareStatement("select orientador.id as id, orientador.email_fatec as email_fatec from orientador where orientador.nome = ?");
        
        stAtualizaAluno = conecta.prepareStatement("update aluno set aluno.nome = ?, aluno.email_institucional = ?, aluno.email_pessoal = ?, aluno.id_orientador = ? where aluno.id = ?");
    	
    	int id = id_aluno;
    	int id_orientador;    	    	
    	try {
    		stBuscaIdOrientador.setString(1, controller.getNomeOrientador());
    		ResultSet result = stBuscaIdOrientador.executeQuery();
    		result.next();
    		id_orientador = result.getInt("id");
        	stAtualizaAluno.setString(1, controller.getTxtNome());
        	stAtualizaAluno.setString(2, controller.getTxtEmailInstitucional());
        	stAtualizaAluno.setString(3, controller.getTxtdEmailPessoal());
        	stAtualizaAluno.setInt(4, id_orientador);
        	stAtualizaAluno.setInt(5,id);    
        	stAtualizaAluno.executeUpdate();
    	}catch(NullPointerException e) {
//    		e.printStackTrace();
    		Alerts.showAlert("IO Exception", "Erros ao salvar dados", "Dados em branco, por favor verfique os dados", AlertType.WARNING);
    	}
    }
}
