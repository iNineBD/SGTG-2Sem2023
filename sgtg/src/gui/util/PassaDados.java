package gui.util;

import dto.GerenciarAlunoDTO;
import gui.TelaFeedbackAlunoController;
import gui.TelaFeedbackViewController;

public class PassaDados {
	
	public static void passaDadosAlunoViewFeedback(TelaFeedbackViewController controller, GerenciarAlunoDTO obj) {
		controller.setAluno(obj);
		controller.setLabelNomeAluno(obj.getNome_aluno());
	}
	
	public static void passaDadosFeedbackAluno(TelaFeedbackAlunoController controller, GerenciarAlunoDTO obj) {
		controller.setAluno(obj);
		controller.setLbNomeAluno(obj.getNome_aluno());
	}

}