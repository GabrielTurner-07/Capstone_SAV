package sav;

import java.util.List;

public class InsertionSort extends SortAlgorithm {

    public InsertionSort(String tipo, String ordem, int pausa, List<Integer> valoresInt, List<Character> valoresChar) {
        super(tipo, ordem, pausa, valoresInt, valoresChar);
    }

    @Override
    public void sort() {
        if (tipo.equals("Numérico")) {
            insertionSortInt();
        } else if (tipo.equals("Caracter")) {
            insertionSortChar();
        }
    }

    private void insertionSortInt() {
        int n = valoresInt.size();
        int passo = 0;
        for (int i = 1; i < n; i++) {
            int key = valoresInt.get(i);
            int j = i - 1;
            while (j >= 0 && ((ordem.equalsIgnoreCase("az") && valoresInt.get(j) > key) ||
                              (ordem.equalsIgnoreCase("za") && valoresInt.get(j) < key))) {
                valoresInt.set(j + 1, valoresInt.get(j));
                j = j - 1;
            }
            valoresInt.set(j + 1, key);
            passo++;
            mostrarInt(passo);
        }
    }

    private void insertionSortChar() {
        int n = valoresChar.size();
        int passo = 0;
        for (int i = 1; i < n; i++) {
            char key = valoresChar.get(i);
            int j = i - 1;
            while (j >= 0 && ((ordem.equalsIgnoreCase("az") && valoresChar.get(j) > key) ||
                              (ordem.equalsIgnoreCase("za") && valoresChar.get(j) < key))) {
                valoresChar.set(j + 1, valoresChar.get(j));
                j = j - 1;
            }
            valoresChar.set(j + 1, key);
            passo++;
            mostrarChar(passo);
        }
    }
}
