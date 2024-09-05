package sav;

import java.util.List;

public abstract class SortAlgorithm {
    protected String tipo;
    protected String ordem;
    protected int pausa;
    protected List<Integer> valoresInt;
    protected List<Character> valoresChar;

    public SortAlgorithm(String tipo, String ordem, int pausa, List<Integer> valoresInt, List<Character> valoresChar) {
        this.tipo = tipo;
        this.ordem = ordem;
        this.pausa = pausa;
        this.valoresInt = valoresInt;
        this.valoresChar = valoresChar;
    }

    public abstract void sort();

    protected void mostrarInt(int passo) {
        System.out.println("Passo " + passo + ":");
        for (int valor : valoresInt) {
            System.out.print(valor + " | ");
            for (int j = 0; j < Math.abs(valor); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        try {
            Thread.sleep(pausa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void mostrarChar(int passo) {
        System.out.println("Passo " + passo + ":");
        for (char valor : valoresChar) {
            System.out.print(valor + " | ");
            int numAsteriscos = Character.isUpperCase(valor) ? valor - 'A' + 1 : -(valor - 'a' + 1);
            numAsteriscos = Math.max(-50, Math.min(numAsteriscos, 50));
            for (int j = 0; j < Math.abs(numAsteriscos); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        try {
            Thread.sleep(pausa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
