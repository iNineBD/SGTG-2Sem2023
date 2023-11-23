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
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmailALuno() {
		return email_aluno;
	}
	public void setEmailALuno(String emailAluno) {
		this.email_aluno = emailAluno;
	}
	
	public String getEmailOrientador() {
		return email_orientador;
	}
	public void setEmailOrientador(String emailOrientador) {
		this.email_orientador = emailOrientador;
	}
	
	public String getTipoTg() {
		return tipo_tg;
	}
	public void setTipoTg(String tipoTg) {
		tipo_tg = tipoTg;
	}
	
	public String getTituloTg() {
		return titulo_tg;
	}
	public void setTituloTg(String tituloTg) {
		titulo_tg = tituloTg;
	}
	
	

}
