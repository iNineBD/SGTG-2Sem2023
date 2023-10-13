package gui.util;

import java.util.Arrays;
import java.util.List;

public class Constraints {

	public static String verificarNome(String name) {
		
		String nome = name;
		String[] separarNomes = nome.split(" ");
		
		for (int i = 0; i < separarNomes.length; i++) {
			separarNomes[i] = separarNomes[i].toLowerCase();
		}

		for (int i = 0; i < separarNomes.length; i++) {
			separarNomes[i] = Character.toUpperCase(separarNomes[i].charAt(0)) + separarNomes[i].substring(1);
		}

		String novoNome = String.join(" ", separarNomes);
		
		return novoNome;
	}

	public static String verificarEmail(String emailFatec) {

		String email = emailFatec;
		if(emailFatec.isEmpty()) {
			return email;
		}else {
			String[] institucional = email.split("@", 2);

			String novoEmail = institucional[0] + "@fatec.sp.gov.br";
			return novoEmail;	
		}
	}

	public static String verificarEmailPessoal(String emailPessoalAluno) {
		String novoEmail = null;
		String email = emailPessoalAluno;
		String[] data = email.split("@", 2);

		List<String> dominios = Arrays.asList("gmail.com", "hotmail.com", "outlook.com", "yahoo.com", "icloud.com", "hotmail.com.br", "yahoo.com.br");

		if (data.length > 1 && dominios.contains(data[1])) {
			String entrada = data[1];
			novoEmail = data[0] + "@" + entrada;
			System.out.println("dominio(email) " + entrada + " válido");
		} else {
			System.out.println("Erro, email inválido");
			email = null;
		}
		return novoEmail;
	}
	
}