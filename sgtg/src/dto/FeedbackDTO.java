package dto;

public class FeedbackDTO {
	
	private String titulo_entrega; // tabela entrega
	
	private String descricao; // tabela entrega
	
	private double nota; // tabela feedback
	
	private String comentario; // tabela feedback

	public FeedbackDTO(String titulo_entrega, String descricao, double nota, String comentario) {
		super();
		
		this.titulo_entrega = titulo_entrega;
		this.descricao = descricao;
		
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

	
}
