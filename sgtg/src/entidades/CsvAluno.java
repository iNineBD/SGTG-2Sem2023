package entidades;

import java.io.Serializable;

public class CsvAluno implements Serializable{
	
	// Atributos da classe CSV Aluno
	private String nome;
	private String emailPessoal;
	private String emailFatec;
	
	// O construtor precisa receber o nome, e-mail pessoal e e-mail fatec do aluno
	public CsvAluno(String nome, String emailPessoal,String emailFatec) {
		this.nome = nome;
		this.emailPessoal = emailPessoal;
		this.emailFatec = emailFatec;
	}
	
	// Métodos getters e setters para receber e editar os dados
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmailPessoal() {
		return this.emailPessoal;
	}
	public void setEmailPessoal(String emailPessoal) {
		this.emailPessoal= emailPessoal;
	}
	
	public String getEmailFatec() {
		return this.emailFatec;
	}
	public void setEmailFatec(String emailFatec) {
		this.emailFatec = emailFatec;
	}
}
