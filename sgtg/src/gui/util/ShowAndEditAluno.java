package gui.util;


public class ShowAndEditAluno {

	Constraints filtro = new Constraints();

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
    
    public void mostraAluno2(TelaMostrarAlunoController controller, Aluno aluno) {
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
    
    public void mostraAluno3(TelaEditarAlunoController controller, Aluno aluno) {
    	
        controller.setTxtNome(aluno.getNome());
        controller.setTxtEmailInstitucional(aluno.getEmailFatecAluno());
        controller.setTxtdEmailPessoal(aluno.getEmailPessoal());
        controller.setTxtEmailInstitucionalOrientador(aluno.getEmailFatecOrientador());
        controller.setTxtTgMatriculado(aluno.getNomeTurma());
        controller.setTxtTipoTg(aluno.getTipoTG());
        controller.setTxtTituloTg(aluno.getProblemaResolvidoOuEstudoArtigo());
        controller.setTxtEmpresa(aluno.getEmpresa());
        controller.setTxtDisciplina(aluno.getDisciplina());
    }
    
    // Método somente para exibir os alunos na tela
    public void mostraAlunoTravado(TelaMostrarAlunoController controller) {
    	controller.setTxtNomeTravado();
    	controller.setTxtDisciplinaTravado();
    	controller.setTxtEmailInstitucionalOrientadorTravado();
    	controller.setTxtEmailInstitucionalTravado();
    	controller.setTxtEmailPessoalTravado();
    	controller.setTxtEmpresaTravado();
    	controller.setTxtNomeOrientadorTravado();
    	controller.setTxtTgMatriculadoTravado();
    	controller.setTxtTipoTgTravado();
    	controller.setTxtTituloTgTravado();
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
    
    //Método para editar informações
    public void editaInformacao2(TelaEditarAlunoController controller,Aluno aluno) {
    	String novoNome = controller.getTxtNome();
    	aluno.setNome(novoNome);
    	
    	String novoEmailPessoal = controller.getTxtdEmailPessoal();
    	aluno.setEmailPessoal(novoEmailPessoal);
    	
    	String novoEmailInstitucional = controller.getTxtEmailInstitucional();
    	aluno.setEmailFatecAluno(novoEmailInstitucional);
    	
    	String novoEmailOrientador = controller.getTxtEmailInstitucionalOrientador();
    	aluno.setEmailFatec(novoEmailOrientador);
    	
    }
    
    public boolean confirmaDados(TelaConfirmaController controller, Aluno aluno) {
        boolean dadosCorretos = true;
        
        //Verifica se o nome está vazio
        if(aluno.getNome().isEmpty() || aluno.getNome() == null) {
        	 dadosCorretos = false;
        }

        // Verifique se o e-mail do orientador não é nulo
        if (aluno.getEmailFatecOrientador() == null || aluno.getEmailFatecOrientador().isEmpty()) {
            dadosCorretos = false;
       }else if(!aluno.getEmailFatecOrientador().endsWith("@fatec.sp.gov.br")) {
    	    dadosCorretos = false;
       }

        // Verifique se o nome do orientador não é nulo
        if (aluno.getOrientador() == null || aluno.getOrientador().isEmpty()) {
            dadosCorretos = false;
        }

        // Verifique se o e-mail do aluno termina com @fatec.sp.gov.br
        if (aluno.getEmailFatecAluno() == null || aluno.getEmailFatecAluno().isEmpty()) {
            dadosCorretos = true;
        }else if(!aluno.getEmailFatecAluno().endsWith("@fatec.sp.gov.br")){
        	dadosCorretos = false;
        }

        // Verifique se a turma não está em branco
        if (aluno.getNomeTurma() == null || aluno.getNomeTurma().isEmpty()) {
            dadosCorretos = false;

        }
        if(aluno.getTipoTG() == null || aluno.getTipoTG().isEmpty()) {
        	dadosCorretos = false;
        }
        
        return dadosCorretos;

    }
    public boolean confirmaDados2(TelaEditarAlunoController controller) {
        boolean dadosCorretos = true;
        
        //Verifica se o nome está vazio
        if(controller.getTxtNome().isEmpty() ||controller.getTxtNome() == null) {
        	 dadosCorretos = false;
        }
        if(controller.getTxtdEmailPessoal().isEmpty() || controller.getTxtdEmailPessoal() == null) {
        	dadosCorretos = false;
        }

        // Verifique se o nome do orientador não é nulo
        if (controller.getComboxNomeOrientador() == null || controller.getComboxNomeOrientador().isEmpty()) {
            dadosCorretos = false;
        }

        // Verifique se o e-mail do aluno termina com @fatec.sp.gov.br
        if (controller.getTxtEmailInstitucional() == null || controller.getTxtEmailInstitucional().isEmpty()) {
            dadosCorretos = true;
        }else if(!controller.getTxtEmailInstitucional().endsWith("@fatec.sp.gov.br")){
        	dadosCorretos = false;
        }
        
        return dadosCorretos;

    }

	public void excluirUser(int id_usuario) {
		if (Alerts.showAlertConfirmation("Atenção", "Voce está prestes a excluir um aluno", "Tem certeza?")) {
			PreparedStatement st2;
			try {
				st2 = conecta.prepareStatement("update sgtg.aluno set visibility = 0 where id = ?");
				st2.setInt(1, id_usuario);
				st2.executeUpdate();
				
				Telas loadTelas = new Telas();
				loadTelas.loadView2("/gui/TelaGerenciarAlunos.fxml");

			} catch (SQLException e) {

				Alerts.showAlert("Erro ao conectar com o Banco", "Atenção", "Ocorreu um erro em excluir o usuario",
						AlertType.WARNING);
			}
			
		}

	}

}
