package dto;

public class TransporteNotaDTO {
	
	private int id; //turma
	
	private String nome; // turma
	
	private String tipo_tg;
	
	private int id_tipo;
	
	private String nome_aluno;
	
	private int id_aluno;
	
	private int semestralizacao; // turma
	
	private int ano; // turma 
	
	private int media;
	

	public TransporteNotaDTO(String nome, String tipo_tg, int media) {
		this.nome = nome;
		this.tipo_tg = tipo_tg;
		this.media = media;
	}

	public int getId() {
		return id;
	}

	public String getTipo_tg() {
		return tipo_tg;
	}

	public void setTipo_tg(String tipo_tg) {
		this.tipo_tg = tipo_tg;
	}

	public int getId_tipo() {
		return id_tipo;
	}

	public void setId_tipo(int id_tipo) {
		this.id_tipo = id_tipo;
	}

	public String getNome_aluno() {
		return nome_aluno;
	}

	public void setNome_aluno(String nome_aluno) {
		this.nome_aluno = nome_aluno;
	}

	public int getId_aluno() {
		return id_aluno;
	}

	public void setId_aluno(int id_aluno) {
		this.id_aluno = id_aluno;
	}

	public int getMedia() {
		return media;
	}

	public void setMedia(int media) {
		this.media = media;
	}

	public void setId(int id) {
		this.id = id;
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
