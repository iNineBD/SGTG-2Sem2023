package dto;

public class GerenciarAlunoDTO {
	
	private int id_aluno;

	private String nome_aluno;

	private String nome_orientador;

	private String nome_turma;
		
	private String tipo_tg;

	

	public GerenciarAlunoDTO(int id_aluno, String nome_aluno, String nome_orientador, String nome_turma, String tipo_tg) {
		this.id_aluno = id_aluno;
		this.nome_aluno = nome_aluno;
		this.nome_orientador = nome_orientador;
		this.nome_turma = nome_turma;
		this.tipo_tg = tipo_tg;
	}

	public int getId_aluno() {
		return id_aluno;
	}

	public void setId_aluno(int id_aluno) {
		this.id_aluno = id_aluno;
	}

	public String getNome_aluno() {
		return nome_aluno;
	}

	public void setNome_aluno(String nome_aluno) {
		this.nome_aluno = nome_aluno;
	}

	public String getNome_orientador() {
		return nome_orientador;
	}

	public void setNome_orientador(String nome_orientador) {
		this.nome_orientador = nome_orientador;
	}

	public String getNome_turma() {
		return nome_turma;
	}

	public void setNome_turma(String nome_turma) {
		this.nome_turma = nome_turma;
	}

	public String getTipo_tg() {
		return tipo_tg;
	}

	public void setTipo_tg(String tipo_tg) {
		this.tipo_tg = tipo_tg;
	}
	
}
