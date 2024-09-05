package sav;

import java.util.Collections;
import java.util.List;

public class SelectionSort extends SortAlgorithm {

    public SelectionSort(String tipo, String ordem, int pausa, List<Integer> valoresInt, List<Character> valoresChar) {
        super(tipo, ordem, pausa, valoresInt, valoresChar);
    }

    @Override
    public void sort() {
        if (tipo.equals("Numérico")) {
            selectionSortInt();
        } else if (tipo.equals("Caracter")) {
            selectionSortChar();
        }
    }

    private void selectionSortInt() {
        int n = valoresInt.size();
        int passo = 0;
        for (int i = 0; i < n - 1; i++) {
            int index = i;
            for (int j = i + 1; j < n; j++) {
                if ((ordem.equalsIgnoreCase("az") && valoresInt.get(j) < valoresInt.get(index)) ||
                    (ordem.equalsIgnoreCase("za") && valoresInt.get(j) > valoresInt.get(index))) {
                    index = j;
                }
            }
            Collections.swap(valoresInt, index, i);
            passo++;
            mostrarInt(passo);
        }
    }

    private void selectionSortChar() {
        int n = valoresChar.size();
        int passo = 0;
        for (int i = 0; i < n - 1; i++) {
            int index = i;
            for (int j = i + 1; j < n; j++) {
                if ((ordem.equalsIgnoreCase("az") && valoresChar.get(j) < valoresChar.get(index)) ||
                    (ordem.equalsIgnoreCase("za") && valoresChar.get(j) > valoresChar.get(index))) {
                    index = j;
                }
            }
            Collections.swap(valoresChar, index, i);
            passo++;
            mostrarChar(passo);
        }
    }
}
