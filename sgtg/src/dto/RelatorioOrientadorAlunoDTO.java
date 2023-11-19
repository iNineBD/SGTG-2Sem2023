package dto;

//montar get e set, modelo adriana
//com relação a tableview
			
public class RelatorioOrientadorAlunoDTO {
	
		private String nome_aluno;
		
		private String nomeOrientador;
				
		public RelatorioOrientadorAlunoDTO (String nome_aluno, String nomeOrientador) {
			this.nome_aluno = nome_aluno;
			this.nomeOrientador = nomeOrientador;
		
		}
		
		public String getNome_aluno() {
			return this.nome_aluno;
		}
		
		
		public String getNomeOrientador() {
			return this.nomeOrientador;
		}
		
		public void setNome_aluno(String nome_aluno) {
			this.nome_aluno = nome_aluno;
		}
		
		
		public void setNomeOrientador(String nomeOrientador) {
			this.nomeOrientador = nomeOrientador;
		}
		
				
		@Override
		public String toString() {
			return nome_aluno;
		}
			
		public String toString_orientador() {
			return nomeOrientador;
			}
}