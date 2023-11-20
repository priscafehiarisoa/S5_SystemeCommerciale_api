package s5_systemecommerciale_api.tools;

import java.util.HashMap;
import java.util.Map;

public class NumberToLetterConverter {

    private static final Map<Integer, String> letterMap = new HashMap<>();

    static {
        letterMap.put(0, "zéro");
        letterMap.put(1, "un");
        letterMap.put(2, "deux");
        letterMap.put(3, "trois");
        letterMap.put(4, "quatre");
        letterMap.put(5, "cinq");
        letterMap.put(6, "six");
        letterMap.put(7, "sept");
        letterMap.put(8, "huit");
        letterMap.put(9, "neuf");
        letterMap.put(10, "dix");
        letterMap.put(11, "onze");
        letterMap.put(12, "douze");
        letterMap.put(13, "treize");
        letterMap.put(14, "quatorze");
        letterMap.put(15, "quinze");
        letterMap.put(16, "seize");
        letterMap.put(17, "dix-sept");
        letterMap.put(18, "dix-huit");
        letterMap.put(19, "dix-neuf");
        letterMap.put(20, "vingt");
        letterMap.put(30, "trente");
        letterMap.put(40, "quarante");
        letterMap.put(50, "cinquante");
        letterMap.put(60, "soixante");
        letterMap.put(70, "soixante-dix");
        letterMap.put(80, "quatre-vingt");
        letterMap.put(90, "quatre-vingt-dix");
    }

    public static String convertNumberToLetter(int number) {
        if (number < 0 || number > 999999999999999L) {
            return "dépassement de capacité";
        }

        String numberToLetter = "";

        if (number == 0) {
            return letterMap.get(0);
        }

        if (number < 0 || number > 999999999999999L) {
            return "Nombre non valide";
        }

        String numberString = String.valueOf(number).replace(" ", "");

        int n = numberString.length();

        if (Math.ceil(number) != number) {
            String[] parts = numberString.split("\\.");
            return convertNumberToLetter(Integer.parseInt(parts[0])) +
                    (parts.length > 1 ? " virgule " + convertNumberToLetter(Integer.parseInt(parts[1])) : "");
        }

        switch (n) {
            case 1:
                numberToLetter = letterMap.get(number);
                break;
            case 2:
                if (number > 19) {
                    int quotient = number / 10;
                    int remainder = number % 10;
                    if (number < 71 || (number > 79 && number < 91)) {
                        if (remainder == 0) {
                            numberToLetter = letterMap.get(quotient * 10);
                        } else if (remainder == 1) {
                            numberToLetter = letterMap.get(quotient * 10) + "-et-" + letterMap.get(remainder);
                        } else {
                            numberToLetter = letterMap.get(quotient * 10) + "-" + letterMap.get(remainder);
                        }
                    } else {
                        numberToLetter = letterMap.get((quotient - 1) * 10) + "-" + letterMap.get(10 + remainder);
                    }
                } else {
                    numberToLetter = letterMap.get(number);
                }
                break;
            // Les autres cas de switch restent inchangés.
            // ...
        }

        // Respect de l'accord de "quatre-vingt"
        if (numberToLetter.endsWith("quatre-vingt")) {
            numberToLetter += "s";
        }

        return numberToLetter;
    }

    public static void main(String[] args) {
        System.out.println(convertNumberToLetter(873));
    }
}
