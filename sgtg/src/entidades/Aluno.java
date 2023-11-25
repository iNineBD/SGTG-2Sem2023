package entidades;

import java.io.Serializable;

public class Aluno implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		// Atributos da classe Aluno
		private String nome;
		private String emailPessoal;
		private String emailFatecAluno;
		private String orientador;
		private String emailFatecOrientador;
		private String nomeTurma;
		private String tipoTg;
		private String regra;
		private String problemaResolvidoOuEstudoArtigo;
		private String empresa;
		private String disciplina;
		private boolean confirmado = false;
		
		
		// Criando o construtor
		public Aluno(String nome, String emailPessoal ,String emailFatecAluno, String orientador,String emailFatecOrientador, String nomeTurma, String tipoTg,String regra,String problemaResolvidoOuEstudoArtigo, String empresa, String disciplina) {
			this.nome = nome;
			this.emailPessoal = emailPessoal;
			this.emailFatecAluno = emailFatecAluno;
			this.orientador = orientador;
			this.emailFatecOrientador = emailFatecOrientador;
			this.nomeTurma = nomeTurma;
			this.tipoTg = tipoTg;
			this.regra = regra;
			this.problemaResolvidoOuEstudoArtigo = problemaResolvidoOuEstudoArtigo;
			this.empresa = empresa;
			this.disciplina = disciplina;
			
		}
		
		// Métodos getters e setters para receber e editar os dados
		public String getNome() {
			return this.nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		
		public String getEmailPessoal() {
			return this.emailPessoal;
		}
		public void setEmailPessoal(String emailPessoal) {
			this.emailPessoal = emailPessoal;
		}
		
		public String getEmailFatecAluno() {
			return this.emailFatecAluno;
		}
		public void setEmailFatecAluno(String emailFatecAluno) {
			this.emailFatecAluno = emailFatecAluno;
		}
		
		public String getOrientador() {
			return this.orientador;
		}
		public void setOrientador(String orientador) {
			this.orientador = orientador;
		}
		
		public String getEmailFatecOrientador() {
			return this.emailFatecOrientador;
		}
		public void setEmailFatec(String emailFatecOrientador) {
			this.emailFatecOrientador = emailFatecOrientador;
		}
		
		public String getNomeTurma() {
			return this.nomeTurma;
		}
		public void setNomeTurma(String nomeTurma) {
			this.nomeTurma = nomeTurma;
		}

		public String getTipoTG() {
			return tipoTg;
		}

		public void setTipoTG(String tipoTg) {
			this.tipoTg = tipoTg;
		}
		
		public String getRegra() {
			return regra;
		}

		public String getProblemaResolvidoOuEstudoArtigo() {
			return problemaResolvidoOuEstudoArtigo;
		}

		public void setProblemaResolvidoOuEstudoArtigo(String problemaResolvidoOuEstudoArtigo) {
			this.problemaResolvidoOuEstudoArtigo = problemaResolvidoOuEstudoArtigo;
		}

		public String getEmpresa() {
			return empresa;
		}

		public void setEmpresa(String empresa) {
			this.empresa = empresa;
		}

		public String getDisciplina() {
			return disciplina;
		}

		public void setDisciplina(String disciplina) {
			this.disciplina = disciplina;
		}
		
		public void setConfirmado() {
			this.confirmado = true;
		}
		
		@Override
		public String toString() {
			return (confirmado + nome + disciplina) ;
		}
}
