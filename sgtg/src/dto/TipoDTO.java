package dto;

public class TipoDTO {
	private int id;
	private String tipo;
	private String regra;
	
	
	public TipoDTO(int id, String tipo, String regra) {
		super();
		this.tipo = tipo;
		this.regra = regra;
		this.id = id;
	}
	
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getRegra() {
		return regra;
	}
	public void setRegra(String regra) {
		this.regra = regra;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return this.tipo;
	
	}
	

}
