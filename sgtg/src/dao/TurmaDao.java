package dao;

public class TurmaDao {
	
	private int idTurma;
	
	private String nome;
	
	public TurmaDao(int idTurma, String nome) {
		this.idTurma = idTurma;
		this.nome = nome;
	}
	
	public int getIdTurma() {
		return this.idTurma;
	}
	
	public String getNomeTurma() {
		return this.nome;
	}
	
	public void setNomeTurma(String nome) {
		this.nome = nome;
	}

}
