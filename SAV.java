package sav;

import java.util.*;

public class SAV {
    private static String tipo = "";
    private static String algoritmo = "";
    private static String ordem = "";
    private static int pausa = 100;
    private static List<Integer> valoresInt = new ArrayList<>();
    private static List<Character> valoresChar = new ArrayList<>();
    private static SortAlgorithm sortAlgorithm;

    public static void main(String[] args) {
        parseArgs(args);
        sortAlgorithm = getSortAlgorithm();
        ordenar();
    }

    private static void parseArgs(String[] args) {
        for (String arg : args) {
            String[] part = arg.split("=");
            switch (part[0].toLowerCase()) {
                case "t":
                    tipo = part[1].equalsIgnoreCase("n") ? "Numérico" : "Caracter";
                    break;
                case "a":
                    algoritmo = part[1];
                    break;
                case "o":
                    ordem = part[1];
                    break;
                case "s":
                    pausa = Integer.parseInt(part[1]);
                    break;
                case "v":
                    parseValores(part[1]);
                    break;
                case "r":
                    gerarValoresAleatorios(Integer.parseInt(part[1]));
                    break;
            }
        }
    }

    private static void parseValores(String valores) {
        String[] valoresArray = valores.split(",");
        for (String valor : valoresArray) {
            if (tipo.equals("Numérico")) {
                try {
                    valoresInt.add(Integer.parseInt(valor));
                } catch (NumberFormatException e) {
                    System.out.println("Valores inseridos não são inteiros");
                    System.exit(0);
                }
            } else if (tipo.equals("Caracter")) {
                valoresChar.add(valor.charAt(0));
            }
        }
    }

    private static void gerarValoresAleatorios(int quantidade) {
        Random random = new Random();
        if (tipo.equals("Numérico")) {
            for (int i = 0; i < quantidade; i++) {
                valoresInt.add(random.nextInt(101) - 50);
            }
        } else if (tipo.equals("Caracter")) {
            for (int i = 0; i < quantidade; i++) {
                char c = (char) (random.nextInt(52) + 'A');
                if (c > 'Z') c += 6; 
                valoresChar.add(c);
            }
        }
    }

    private static SortAlgorithm getSortAlgorithm() {
        switch (algoritmo.toLowerCase()) {
            case "b":
                return new BubbleSort(tipo, ordem, pausa, valoresInt, valoresChar);
            case "s":
                return new SelectionSort(tipo, ordem, pausa, valoresInt, valoresChar);
            case "i":
                return new InsertionSort(tipo, ordem, pausa, valoresInt, valoresChar);
            default:
                throw new IllegalArgumentException("Algoritmo de ordenação desconhecido: " + algoritmo);
        }
    }

    private static void ordenar() {
        long startTime = System.currentTimeMillis();
        sortAlgorithm.sort();
        long endTime = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + (endTime - startTime) + "ms");
    }
}
