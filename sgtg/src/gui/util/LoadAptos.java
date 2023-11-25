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
			stAlunos = conn.prepareStatement(
					"select aluno.nome as nome_aluno, aluno.email_pessoal as email_pessoal_aluno, orientador.email_fatec as email_orientador, tipo.id as id_tipo, tipo.tipo as tipo_tg, tg.problema_a_resolver as titulo_tg, aluno.id as id_aluno, turma.nome as nome_turma from aluno inner join orientador on aluno.id_orientador = orientador.id inner join tipo on aluno.id_tipo = tipo.id inner join tg on aluno.id = tg.id_aluno inner join entrega_tipo on entrega_tipo.id_tipo = tipo.id inner join entrega on entrega_tipo.id_entrega = entrega.id inner join matricula on aluno.id = matricula.id_aluno inner join turma on matricula.id_turma = turma.id group by aluno.nome, aluno.email_pessoal, orientador.email_fatec, tipo.tipo, tg.problema_a_resolver, aluno.id, turma.nome;");

			ResultSet resultAlunos = stAlunos.executeQuery();

			while (resultAlunos.next()) {

				String nome_aluno = resultAlunos.getString("nome_aluno");
				String email_aluno = resultAlunos.getString("email_pessoal_aluno");
				String email_orientador = resultAlunos.getString("email_orientador");
				String tipo_tg = resultAlunos.getString("tipo_tg");
				String nome_tuma = resultAlunos.getString("nome_turma");
				String titulo_tg = resultAlunos.getString("titulo_tg");
				int id_tipo = resultAlunos.getInt("id_tipo");

				// informações que não serão exibidas na tela
				int id_aluno = resultAlunos.getInt("id_aluno");
				int total_entregas = 0;

				PreparedStatement st2 = conn.prepareStatement(
						"select count(entrega.id) as n_entrega from entrega_tipo inner join entrega on entrega.id = entrega_tipo.id_entrega inner join entrega_turma on entrega.id = entrega_turma.id_entrega inner join matricula on entrega_turma.id_turma = matricula.id_turma where entrega_tipo.id_tipo = ? and id_aluno = ? and entrega.visibility = 1");
				st2.setInt(1, id_tipo);
				st2.setInt(2, id_aluno);

				ResultSet result2 = st2.executeQuery();

				while (result2.next()) {
					total_entregas += result2.getInt("n_entrega");
				}

				// tabela com id aluno + total_feedback
				stFeedback = conn.prepareStatement("select count(feedback.id_aluno) total_feedback " + "from feedback "
						+ "where feedback.id_aluno = ?;");

				stFeedback.setInt(1, id_aluno);

				ResultSet resultFeedback = stFeedback.executeQuery();

				resultFeedback.next();
				int total_feedback = resultFeedback.getInt("total_feedback");

				if (total_feedback >= total_entregas) {
					AptosADefenderDTO aptosADefender = new AptosADefenderDTO(id_aluno, nome_aluno, email_aluno,
							email_orientador, tipo_tg, titulo_tg, nome_tuma, total_entregas);
					listaAptos.add(aptosADefender);
				}

			}

			List<AptosADefenderDTO> listaAlunos = new ArrayList<AptosADefenderDTO>();
			listaAlunos = listaAptos;
			if (listaAlunos != null) {
//		    	verifica os alunos do tg1 e tg2
				for (int i = 0; i < listaAlunos.size(); i++) {
					AptosADefenderDTO alunoAtual = listaAlunos.get(i);

					for (int j = i; j < listaAlunos.size(); j++) {
						if (alunoAtual.getId_aluno() == (listaAlunos.get(j).getId_aluno())
								&& !alunoAtual.getNome_turma().equals(listaAlunos.get(j).getNome_turma())) {
							alunoAtual.setNome_turma("TG1 e TG2");
							if ((!alunoAtual.getTipo_tg().equals("Portfólio")) && alunoAtual.getEnt_total() > 0) {
								alunoAtual.setEnt_total(alunoAtual.getEnt_total() / 2);
							}

							listaAlunos.remove(j);
						}

					}

//		            System.out.println(alunoAtual.getNome_aluno() + " - " + alunoAtual.getEntregas_feitas() + "/" + alunoAtual.getTotal_entregas());
//	                System.out.println();

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
