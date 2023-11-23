package gui.util;

import dto.RelatorioOrientadorAlunoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.DB;

public class LoadOrientadorAluno {

	
	public static List<RelatorioOrientadorAlunoDTO> carregaOrientadoresAlunos(int id_turma) throws SQLException{
		
		List<dto.RelatorioOrientadorAlunoDTO> listaOrientadorAluno = new ArrayList<RelatorioOrientadorAlunoDTO>();
		
		new DB();
		Connection con = DB.getConnection();
		
		PreparedStatement st = con.prepareStatement("select aluno.nome as nome_aluno, orientador.nome as nome_orientador from aluno, orientador, " + "matricula where aluno.id_orientador = orientador.id " + "and matricula.id_aluno = aluno.id and matricula.id_turma = ?");
		st.setInt(1, id_turma);
		ResultSet result = st.executeQuery();
		
		while(result.next()) {
			String nome_aluno = result.getString ("nome_aluno");
			String nomeOrientador = result.getString("nome_orientador");
						
			 
			
			dto.RelatorioOrientadorAlunoDTO OrientadorAluno = new RelatorioOrientadorAlunoDTO (nome_aluno,nomeOrientador);
			
			listaOrientadorAluno.add(OrientadorAluno);
		}
		
		return (listaOrientadorAluno);
		
	}

	public List<RelatorioOrientadorAlunoDTO> atualizarDados(int id) {
		// TODO Auto-generated method stub
		return null;
	}}