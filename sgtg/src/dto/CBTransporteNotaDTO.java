package dto;

public class CBTransporteNotaDTO {
	
	private int idTurma;
	
	private String nome;
	
	private int semestralizacao;
	
	private int ano;
	@Override
    public String toString() {
        return nome + " - " + semestralizacao + "/" + ano;
    }
		

	public int getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getSemestralizacao() {
		return semestralizacao;
	}


	public void setSemestralizacao(int semestralizacao) {
		this.semestralizacao = semestralizacao;
	}


	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}
	
	

}
