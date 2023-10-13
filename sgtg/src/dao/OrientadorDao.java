package dao;

public class OrientadorDao {
	
	private int idOrientador;
	
	private String nomeOrientador;
	
	private String emailOrientador;
	
	public OrientadorDao(int idOrientador, String nomeOrientador, String emailOrientador) {
		this.idOrientador = idOrientador;
		this.nomeOrientador = nomeOrientador;
		this.emailOrientador = emailOrientador;
	}
	
	public int getIdOrientador() {
		return this.idOrientador;
	}
	
	public void setIdOrientador(int id) {
		this.idOrientador = id;
	}
	
	public String getNomeOrientador() {
		return this.nomeOrientador;
	}
	
	public void setNomeOrientador(String nome) {
		this.nomeOrientador = nome;
	}
	
	public String getEmailOrientador() {
		return this.emailOrientador;
	}
	
	public void setEmailOrientador(String email) {
		this.emailOrientador = email;
		
	}
}
