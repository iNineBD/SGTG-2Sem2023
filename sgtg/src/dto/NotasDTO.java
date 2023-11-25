package dto;

public class NotasDTO {
	
	private String nome_aluno; // tabela aluno
	
	private String nome_turma;
	
	private String tipo_tg; // tabela tipo
	
	private int entregas_feitas;
	
	private int total_entregas;
	
	private String entregas_format; // tabela entrega 
	
	private Double nota; // tabela feedback
	
	private int id_aluno;
	
	private int id_tipo;
	
	private int id_turma;
	

	public NotasDTO(String nome_aluno, String tipo_tg, Double nota, String entregas_format, String nome_turma) {
		this.nome_aluno = nome_aluno;
		this.tipo_tg = tipo_tg;
		this.nota = nota;
		this.entregas_format = entregas_format;
		this.nome_turma = nome_turma;
	}
	
	public int getId_turma() {
		return id_turma;
	}

	public void setId_turma(int id_turma) {
		this.id_turma = id_turma;
	}

	public String getNome_turma() {
		return nome_turma;
	}

	public void setNome_turma(String nome_turma) {
		this.nome_turma = nome_turma;
	}

	public int getId_aluno() {
		return id_aluno;
	}


	public void setId_aluno(int id_aluno) {
		this.id_aluno = id_aluno;
	}

	public int getId_tipo() {
		return id_tipo;
	}

	public void setId_tipo(int id_tipo) {
		this.id_tipo = id_tipo;
	}

	public String getNome_aluno() {
		return nome_aluno;
	}

	public void setNome_aluno(String nome_aluno) {
		this.nome_aluno = nome_aluno;
	}

	public String getTipo_tg() {
		return tipo_tg;
	}

	public void setTipo_tg(String tipo_tg) {
		this.tipo_tg = tipo_tg;
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

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}
	
	

}
