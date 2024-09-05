package sav;

import java.util.Collections;
import java.util.List;

public class BubbleSort extends SortAlgorithm {

    public BubbleSort(String tipo, String ordem, int pausa, List<Integer> valoresInt, List<Character> valoresChar) {
        super(tipo, ordem, pausa, valoresInt, valoresChar);
    }

    @Override
    public void sort() {
        if (tipo.equals("Numérico")) {
            bubbleSortInt();
        } else if (tipo.equals("Caracter")) {
            bubbleSortChar();
        }
    }

    private void bubbleSortInt() {
        int n = valoresInt.size();
        boolean swapped;
        int passo = 0;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if ((ordem.equalsIgnoreCase("az") && valoresInt.get(j) > valoresInt.get(j + 1)) ||
                    (ordem.equalsIgnoreCase("za") && valoresInt.get(j) < valoresInt.get(j + 1))) {
                    Collections.swap(valoresInt, j, j + 1);
                    swapped = true;
                }
            }
            if (swapped) {
                passo++;
                mostrarInt(passo);
            }
            if (!swapped) break;
        }
    }

    private void bubbleSortChar() {
        int n = valoresChar.size();
        boolean swapped;
        int passo = 0;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if ((ordem.equalsIgnoreCase("az") && valoresChar.get(j) > valoresChar.get(j + 1)) ||
                    (ordem.equalsIgnoreCase("za") && valoresChar.get(j) < valoresChar.get(j + 1))) {
                    Collections.swap(valoresChar, j, j + 1);
                    swapped = true;
                }
            }
            if (swapped) {
                passo++;
                mostrarChar(passo);
            }
            if (!swapped) break;
        }
    }
}
