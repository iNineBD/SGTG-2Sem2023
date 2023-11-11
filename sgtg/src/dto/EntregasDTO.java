package dto;

import java.time.LocalDate;

public class EntregasDTO {
	
	private int id;
	
	private String titulo;
	
	private String descricao;
	
	private LocalDate data_final;
	
	private int id_turma;
	
	private String nome_turma;



	@Override
	public String toString() {
		return titulo;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public LocalDate getData_final() {
		return data_final;
	}



	public void setData_final(LocalDate data_final) {
		this.data_final = data_final;
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



	public EntregasDTO(int id, String titulo, String descricao, LocalDate data_final, int id_turma, String nome_turma) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.data_final = data_final;
		this.id_turma = id_turma;
		this.nome_turma = nome_turma;
	}


	
	

}
