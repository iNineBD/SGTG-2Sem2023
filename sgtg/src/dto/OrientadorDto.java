package dto;

public class OrientadorDto {
	
	private int idOrientador;
	
	private String nomeOrientador;
	
	private String emailOrientador;
	
	public OrientadorDto(int idOrientador, String nomeOrientador, String emailOrientador) {
		this.idOrientador = idOrientador;
		this.nomeOrientador = nomeOrientador;
		this.emailOrientador = emailOrientador;
	}
	
	public int getIdOrientador() {
		return this.idOrientador;
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
	
	@Override
	public String toString() {
		return nomeOrientador;
	}

	public void setEmailOrientador(String email) {
		this.emailOrientador = email;
	}
}