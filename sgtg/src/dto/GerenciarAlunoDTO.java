package dto;

public class GerenciarAlunoDTO {
	
	private int id_aluno;

	private String nome_aluno;
	
	private String emailFatecAluno;
	
	private String emailPessoal;

	private String nome_orientador;
	
	private String emailOrientador;

	private String nome_turma;
		
	private String tipo_tg;
	
	private String regra;
	
	private String tituloTg;
	
	private String empresa;
	
	private String disciplina;
	
	private int entregas_feitas;
	
	private int total_entregas;
	
	private String entregas_format;
	
	private int id_turma;	

	public GerenciarAlunoDTO(int id_aluno, String nome_aluno,String emailPessoal,String emailFatecAluno ,String nome_orientador,String emailOrientador, String nome_turma, String tipo_tg,String tituloTg,String empresa , String disciplina,String regra,int id_turma) {
		this.id_aluno = id_aluno;
		this.nome_aluno = nome_aluno;
		this.emailFatecAluno = emailFatecAluno;
		this.emailPessoal = emailPessoal;
		this.nome_orientador = nome_orientador;
		this.emailOrientador = emailOrientador;
		this.nome_turma = nome_turma;
		this.tipo_tg = tipo_tg;
		this.id_turma = id_turma;
		this.tituloTg = tituloTg;
		this.empresa = empresa;
		this.disciplina = disciplina;
		this.regra = regra;
	}
	
	
	public String getEntregas_format() {
		return entregas_format;
	}

	public void setEntregas_format(String entregas_format) {
		this.entregas_format = entregas_format;
	}


	public int getEntregas_feitas() {
		return entregas_feitas;
	}



	public void setEntregas_feitas(int entregas_feitas) {
		this.entregas_feitas = entregas_feitas;
	}



	public int getTotal_entregas() {
		return total_entregas;
	}



	public void setTotal_entregas(int total_entregas) {
		this.total_entregas = total_entregas;
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
	
	public String getEmailFatecAluno() {
		return this.emailFatecAluno;
	}
	
	public void setEmailFatecAluno(String email) {
		this.emailFatecAluno = email;
	}
	
	public String getEmailPessoalAluno() {
		return this.emailPessoal;
	}
	
	public void setEmailPessoalAluno(String email) {
		this.emailPessoal = email;
	}
	public String getNome_orientador() {
		return nome_orientador;
	}

	public void setNome_orientador(String nome_orientador) {
		this.nome_orientador = nome_orientador;
	}
	
	public String getEmailOrientador() {
		return this.emailOrientador;
	}
	
	public void setEmailOrientador(String email) {
		this.emailOrientador = email;
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
	
	public String getTituloTg() {
		return this.tituloTg;
	}
	
	public void setTituloTg(String titulo) {
		this.tituloTg = titulo;
	}
	
	public String getEmpresa() {
		return this.empresa;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public String getDisciplina() {
		return this.disciplina;
	}
	
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	
	public String getRegra() {
		return this.regra;
	}
	
	public void setRegra(String regra) {
		this.regra = regra;
	}

	public int getId_turma() {
		return id_turma;
	}

	public void setId_turma(int id_turma) {
		this.id_turma = id_turma;
	}
	
	
	
}
