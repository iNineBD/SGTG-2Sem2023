package entidades;

import java.io.Serializable;

public class CsvTurma implements Serializable{
	
	// Criando os atributos
	private String nome;
	
	// Criando o construtor
	public CsvTurma(String nome) {
		this.nome = nome;
	}
	
	// Criando o método getter e setter
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
