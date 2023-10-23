package dto;

public class FeedbackDTO {
	
	private int id_turma; // tabela entrega
	
	private String titulo_entrega; // tabela entrega
	
	private String descricao; // tabela entrega
	
	private double nota; // tabela feedback
	
	private String comentario; // tabela feedback

	private int id_aluno; // tabela feedback

	public FeedbackDTO(int id_turma, String titulo_entrega, String descricao, int id_aluno, double nota, String comentario) {
		super();
		this.id_turma = id_turma;
		this.titulo_entrega = titulo_entrega;
		this.descricao = descricao;
		this.id_aluno = id_aluno;
		this.nota = nota;
		this.comentario = comentario;
	}

	public String getTitulo_entrega() {
		return titulo_entrega;
	}

	public void setTitulo_entrega(String titulo_entrega) {
		this.titulo_entrega = titulo_entrega;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getId_turma() {
		return id_turma;
	}

	public void setId_turma(int id_turma) {
		this.id_turma = id_turma;
	}

	public int getId_aluno() {
		return id_aluno;
	}

	public void setId_aluno(int id_aluno) {
		this.id_aluno = id_aluno;
	}
	
}
