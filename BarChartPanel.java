package sav;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BarChartPanel extends JPanel {
    private List<Integer> valores;

    public BarChartPanel(List<Integer> valores) {
        this.valores = valores;
    }

    public void setValores(List<Integer> valores) {
        this.valores = valores;
        repaint(); // Redesenha o painel para atualizar o gráfico de barras
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (valores == null || valores.isEmpty()) {
            return;
        }

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int numBars = valores.size();
        int space = 5; // Espaço entre as barras
        int barWidth = (panelWidth - (space * (numBars - 1))) / numBars; // Ajusta a largura das barras

        int maxVal = valores.stream().max(Integer::compareTo).orElse(1);

        for (int i = 0; i < numBars; i++) {
            int barHeight = (int) (((double) valores.get(i) / maxVal) * panelHeight);
            int x = i * (barWidth + space); // Posição X da barra com espaço incluído
            int y = panelHeight - barHeight; // Posição Y da barra

            g.setColor(Color.BLUE);
            g.fillRect(x, y, barWidth, barHeight); // Desenha a barra

            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, barHeight); // Desenha o contorno da barra
        }
    }
}