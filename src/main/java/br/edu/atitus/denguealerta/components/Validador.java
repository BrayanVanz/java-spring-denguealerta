package br.edu.atitus.denguealerta.components;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {

	public static boolean validarCPF(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF possui 11 dígitos
        if (cpf.length() != 11)
            return false;

        // Verifica se todos os dígitos são iguais
        boolean digitosIguais = true;
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                digitosIguais = false;
                break;
            }
        }
        if (digitosIguais)
            return false;

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int resto = soma % 11;
        int digito1 = resto < 2 ? 0 : 11 - resto;

        // Verifica o primeiro dígito verificador
        if (Character.getNumericValue(cpf.charAt(9)) != digito1)
            return false;

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        resto = soma % 11;
        int digito2 = resto < 2 ? 0 : 11 - resto;

        // Verifica o segundo dígito verificador
        return Character.getNumericValue(cpf.charAt(10)) == digito2;
    }
	
	public static boolean validarEmail(String email) {
        // Expressão regular para validar o formato do email
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        
        // Compila a expressão regular em um padrão
        Pattern pattern = Pattern.compile(regex);
        
        // Cria um objeto Matcher para corresponder a expressão regular com o email fornecido
        Matcher matcher = pattern.matcher(email);
        
        // Retorna verdadeiro se o email corresponder ao padrão e falso caso contrário
        return matcher.matches();
    }
}
