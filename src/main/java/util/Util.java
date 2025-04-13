package util;

public class Util {

    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        String[] palavras = input.trim().split("\\s+");
        StringBuilder resultado = new StringBuilder();
        for (String palavra : palavras) {
                resultado.append(Character.toUpperCase(palavra.charAt(0)))
                        .append(palavra.substring(1).toLowerCase())
                        .append(" ");

        }
        System.out.println(resultado);
        return resultado.toString().trim();
    }

}
