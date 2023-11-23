package gui.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.DB;
import dto.GerenciarAlunoDTO;
import dto.NotasDTO;

public class LoadNotas {
	public List<NotasDTO>informacoes() throws SQLException {

		List<NotasDTO> listaNotas = new ArrayList<>();

		Connection conecta = DB.getConnection();

		PreparedStatement st = conecta.prepareStatement(
				"SELECT aluno.id id_aluno, aluno.nome, tipo.id id_tipo, tipo.tipo FROM aluno, tipo where aluno.id_tipo = tipo.id");

		ResultSet result = st.executeQuery();

		while (result.next()) {
			String nome_aluno = result.getString("nome");
			String tipo_tg = result.getString("tipo");
			int id_aluno = result.getInt("id_aluno");
			int id_tipo = result.getInt("id_tipo");
			String nome_turma = "";


			// total de entregas cadastradas para um aluno
			int total_entregas = 0;
			int entrega_aluno = 0;

			PreparedStatement st2 = conecta
					.prepareStatement("select count(id) n_entregas from entrega, entrega_tipo, entrega_turma, matricula where entrega.id = entrega_tipo.id_entrega and entrega_tipo.id_tipo = ? and matricula.id_turma = entrega_turma.id_turma and matricula.id_aluno = ? and entrega_turma.id_entrega = entrega.id and visibility = 1");
			st2.setInt(1, id_tipo);
			st2.setInt(2,id_aluno);
			
			ResultSet result2 = st2.executeQuery();

			while (result2.next()) {
				total_entregas += result2.getInt("n_entregas");
			}
			
			if ((!tipo_tg.equals("Portfólio")) && total_entregas > 0) {
				total_entregas = total_entregas/2;
			}

			// entregas feitas pelo aluno
			PreparedStatement st3 = conecta.prepareStatement(
					"select count(id) n_entregas from feedback where feedback.id_aluno = ?");
			st3.setInt(1, id_aluno);

			ResultSet result3 = st3.executeQuery();

			if (result3.next()) {
				entrega_aluno = result3.getInt("n_entregas");
			}
			
//			if ((!tipo_tg.equals("Portfólio")) && entrega_aluno > 0) {
//				entrega_aluno = entrega_aluno/2;
//			}
			//buscando turma
			PreparedStatement st6 = conecta.prepareStatement("SELECT turma.nome, turma.id, matricula.id_aluno, matricula.id_turma FROM turma, matricula where matricula.id_turma = turma.id and matricula.id_aluno = ?");
			st6.setInt(1, id_aluno);
			
			ResultSet result6 = st6.executeQuery();
			while (result6.next()) {
				nome_turma = nome_turma + " " + result6.getString("nome");
				
			}

			// media das notas
			PreparedStatement st5 = conecta.prepareStatement("SELECT AVG(feedback.nota) AS media FROM feedback WHERE feedback.id_Aluno = ?");
			st5.setInt(1, id_aluno);
			
			ResultSet result5 = st5.executeQuery();
			

			while (result5.next()) {
				String entregas_format = entrega_aluno + "/" + total_entregas;
				String media = result5.getString("media");
				NotasDTO notas = new NotasDTO(nome_aluno, tipo_tg, media,entregas_format,nome_turma);
				listaNotas.add(notas);
			}
		}
		return listaNotas;
	}
}
