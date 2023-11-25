package dto;

public class AptosADefenderDTO {
	private String nome; // tabela aluno
	private String email_aluno; // tabela aluno
	private String email_orientador;// tabela orientador
	private String tipo_tg; // tabela tg
	private String titulo_tg; // tabela tg
	private String nome_turma;
	private int id_aluno;
	private int ent_total;

	public AptosADefenderDTO(int id_aluno, String nome, String email_aluno, String email_orientador, String tipo_tg,
			String titulo_tg, String nome_turma, int ent_total) {
		this.nome = nome;
		this.email_aluno = email_aluno;
		this.email_orientador = email_orientador;
		this.tipo_tg = tipo_tg;
		this.titulo_tg = titulo_tg;
		this.nome_turma = nome_turma;
		this.id_aluno = id_aluno;
	}

	public int getEnt_total() {
		return ent_total;
	}

	public void setEnt_total(int ent_total) {
		this.ent_total = ent_total;
	}

	public int getId_aluno() {
		return id_aluno;
	}

	public void setId_aluno(int id_aluno) {
		this.id_aluno = id_aluno;
	}

	public String getNome_turma() {
		return nome_turma;
	}

	public void setNome_turma(String nome_turma) {
		this.nome_turma = nome_turma;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail_aluno() {
		return this.email_aluno;
	}

	public void setEmail_aluno(String emailAluno) {
		this.email_aluno = emailAluno;
	}

	public String getEmail_orientador() {
		return this.email_orientador;
	}

	public void setEmail_orientador(String emailOrientador) {
		this.email_orientador = emailOrientador;
	}

	public String getTipo_tg() {
		return this.tipo_tg;
	}

	public void setTipo_tg(String tipoTg) {
		tipo_tg = tipoTg;
	}

	public String getTitulo_tg() {
		return this.titulo_tg;
	}

	public void setTitulo_tg(String tituloTg) {
		titulo_tg = tituloTg;
	}

}
