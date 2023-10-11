package gui.util;

import entidades.Aluno;
import gui.TelaConfirmaController;

public class ShowAndEditAluno {
	
    // Método somente para exibir os alunos na tela
    public void mostraAluno(TelaConfirmaController controller, Aluno aluno) {
        controller.setTxtNome(aluno.getNome());
        controller.setTxtEmailInstitucional(aluno.getEmailFatecAluno());
        controller.setTxtdEmailPessoal(aluno.getEmailPessoal());
        controller.setTxtNomeOrientador(aluno.getOrientador());
        controller.setTxtEmailInstitucionalOrientador(aluno.getEmailFatecOrientador());
        controller.setTxtTgMatriculado(aluno.getNomeTurma());
        controller.setTxtTipoTg(aluno.getTipoTG());
        controller.setTxtTituloTg(aluno.getProblemaResolvidoOuEstudoArtigo());
        controller.setTxtEmpresa(aluno.getEmpresa());
        controller.setTxtDisciplina(aluno.getDisciplina());
    }
    
    //Método para editar informações
    public void editaInformacao(TelaConfirmaController controller,Aluno aluno) {
    	String novoNome = controller.getTxtNome();
    	aluno.setNome(novoNome);
    	
    	String novoEmailPessoal = controller.getTxtdEmailPessoal();
    	aluno.setEmailPessoal(novoEmailPessoal);
    	
    	String novoEmailInstitucional = controller.getTxtEmailInstitucional();
    	aluno.setEmailFatecAluno(novoEmailInstitucional);
    	
    	String novoOrientador = controller.getTxtNomeOrientador();
    	aluno.setOrientador(novoOrientador);
    	
    	String novoEmailOrientador = controller.getTxtEmailInstitucionalOrientador();
    	aluno.setEmailFatec(novoEmailOrientador);
    	
    	String novaTurmaTg = controller.getTxtTgMatriculado();
    	aluno.setNomeTurma(novaTurmaTg);
    	
    	String novoTipoTg = controller.getTxtTipoTg();
    	aluno.setTipoTG(novoTipoTg);
    	
    	String novoTituloTg = controller.getTxtTituloTg();
    	aluno.setProblemaResolvidoOuEstudoArtigo(novoTituloTg);
    	
    	String novaEmpresa = controller.getTxtEmpresa();
    	aluno.setEmpresa(novaEmpresa);
    	
    	String novaDisciplina = controller.getTxtDisciplina();
    	aluno.setDisciplina(novaDisciplina);
    	
    }

}
