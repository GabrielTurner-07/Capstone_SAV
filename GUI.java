package sav;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {

    private JComboBox<String> tipoComboBox;
    private JComboBox<String> algoritmoComboBox;
    private JComboBox<String> ordemComboBox;
    private JTextField valoresField;
    private JButton ordenarButton;
    private JTextArea resultadoArea;
    private BarChartPanel barChartPanel;
    private List<Integer> valoresInt;

    public GUI() {
        setTitle("Algoritmos de Ordenação");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        controlPanel.add(new JLabel("Tipo:"));
        tipoComboBox = new JComboBox<>(new String[]{"Numérico", "Caracter"});
        controlPanel.add(tipoComboBox);

        controlPanel.add(new JLabel("Algoritmo:"));
        algoritmoComboBox = new JComboBox<>(new String[]{"Bubble Sort", "Insertion Sort", "Selection Sort"});
        controlPanel.add(algoritmoComboBox);

        controlPanel.add(new JLabel("Ordem:"));
        ordemComboBox = new JComboBox<>(new String[]{"az", "za"});
        controlPanel.add(ordemComboBox);

        controlPanel.add(new JLabel("Valores (separados por vírgula):"));
        valoresField = new JTextField(20);
        controlPanel.add(valoresField);

        ordenarButton = new JButton("Ordenar");
        controlPanel.add(ordenarButton);

        add(controlPanel, BorderLayout.NORTH);

        resultadoArea = new JTextArea(5, 30);
        resultadoArea.setEditable(false);
        add(new JScrollPane(resultadoArea), BorderLayout.SOUTH);

        valoresInt = new ArrayList<>();
        barChartPanel = new BarChartPanel(valoresInt);
        add(barChartPanel, BorderLayout.CENTER);

        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenar();
            }
        });
    }

    private void ordenar() {
        String tipo = (String) tipoComboBox.getSelectedItem();
        String algoritmo = (String) algoritmoComboBox.getSelectedItem();
        String ordem = (String) ordemComboBox.getSelectedItem();
        String valores = valoresField.getText();

        valoresInt.clear();
        List<Character> valoresChar = new ArrayList<>();

        String[] valoresArray = valores.split(",");
        if (tipo.equals("Numérico")) {
            try {
                for (String valor : valoresArray) {
                    valoresInt.add(Integer.parseInt(valor.trim()));
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valores inseridos não são inteiros", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (tipo.equals("Caracter")) {
            for (String valor : valoresArray) {
                valoresChar.add(valor.trim().charAt(0));
            }
        }

        barChartPanel.setValores(valoresInt);

        SortAlgorithm sortAlgorithm = getSortAlgorithm(tipo, algoritmo, ordem, valoresInt, valoresChar);
        long startTime = System.currentTimeMillis();
        sortAlgorithm.sort();
        long endTime = System.currentTimeMillis();

        String resultado = "Resultado: \n";
        if (tipo.equals("Numérico")) {
            for (Integer val : valoresInt) {
                resultado += val + " ";
            }
        } else {
            for (Character val : valoresChar) {
                resultado += val + " ";
            }
        }
        resultado += "\nTempo de execução: " + (endTime - startTime) + "ms";
        resultadoArea.setText(resultado);
    }

    private SortAlgorithm getSortAlgorithm(String tipo, String algoritmo, String ordem, List<Integer> valoresInt, List<Character> valoresChar) {
        switch (algoritmo) {
            case "Bubble Sort":
                return new BubbleSort(tipo, ordem, 100, valoresInt, valoresChar) {
                    @Override
                    protected void mostrarInt(int passo) {
                        barChartPanel.setValores(valoresInt);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
            case "Insertion Sort":
                return new InsertionSort(tipo, ordem, 100, valoresInt, valoresChar) {
                    @Override
                    protected void mostrarInt(int passo) {
                        barChartPanel.setValores(valoresInt);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
            case "Selection Sort":
                return new SelectionSort(tipo, ordem, 100, valoresInt, valoresChar) {
                    @Override
                    protected void mostrarInt(int passo) {
                        barChartPanel.setValores(valoresInt);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
            default:
                throw new IllegalArgumentException("Algoritmo de ordenação desconhecido: " + algoritmo);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
}