package dto;

public class AptosADefenderDTO {
	private String nome; // tabela aluno
	private String email_aluno; // tabela aluno 
	private String email_orientador;// tabela orientador  
	private String tipo_tg; // tabela  tg
	private String titulo_tg; //tabela tg 
	
	public AptosADefenderDTO(String nome, String email_aluno, String email_orientador, String tipo_tg, String titulo_tg) {
		this.nome = nome;
		this.email_aluno = email_aluno;
		this.email_orientador = email_orientador;
		this.tipo_tg = tipo_tg;
		this.titulo_tg = titulo_tg;
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
