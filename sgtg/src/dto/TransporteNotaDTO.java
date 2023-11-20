package dto;

public class TransporteNotaDTO {
	
	private int id_turma;
	
	private String nome_turma;
	
	private int semestralizacao;
	
	private int ano_semestre;
	
	private int id_tg;
	
	private String tipo_tg;
	
	public TransporteNotaDTO(int id_turma, String nome_turma, int semestralizacao, int ano_semestre, int id_tg, String tipo_tg) {
		
		this.id_turma = id_turma;
		this.nome_turma = nome_turma;
		this.semestralizacao = semestralizacao;
		this.ano_semestre = ano_semestre;
		
		this.tipo_tg = tipo_tg;
		
	}

	public int getId_tg() {
		return id_tg;
	}

	public void setId_tg(int id_tg) {
		this.id_tg = id_tg;
	}

	public String getTipo_tg() {
		return tipo_tg;
	}

	public void setTipo_tg(String tipo_tg) {
		this.tipo_tg = tipo_tg;
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

	public int getSemestralizacao() {
		return semestralizacao;
	}

	public void setSemestralizacao(int semestralizacao) {
		this.semestralizacao = semestralizacao;
	}

	public int getAno_semestre() {
		return ano_semestre;
	}

	public void setAno_semestre(int ano_semestre) {
		this.ano_semestre = ano_semestre;
	}
	
	@Override
    public String toString() {
        return nome_turma + " - " + semestralizacao + "/" + ano_semestre;
    }
}
	
