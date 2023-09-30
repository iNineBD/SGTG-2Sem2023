package entidades;

import java.io.Serializable;

public class CsvOrientador implements Serializable{
	
	// Criando atributos
	
	private String nome;
	private String emailFatec;
	
	// Criando construtor
	public CsvOrientador(String nome, String emailFatec) {
		this.nome = nome;
		this.emailFatec = emailFatec;
	}

	// Criando os métodos getters e setters
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmailFatec() {
		return this.emailFatec;
	}
	public void setEmailFatec(String emailFatec) {
		this.emailFatec = emailFatec;
	}
}
