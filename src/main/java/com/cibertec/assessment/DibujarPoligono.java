package com.cibertec.assessment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DibujarPoligono extends JPanel {
	// Coordenadas de los vértices del polígono
    Double xpoint1[] = {10.0, 205.0, 305.0, 405.0, 500.0};
    Double ypoint1[] = {10.0, 501.0, 506.0, 107.0, 30.0};

//    Double xpoint1[] = {100.0, 605.0, 305.0, 405.0, 500.0};
//    Double ypoint1[] = {100.0, 601.0, 506.0, 337.0, 300.0};
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Dibujar el polígono
        g2d.setColor(Color.BLUE);
        g2d.drawPolygon(toIntArray(xpoint1), toIntArray(ypoint1), xpoint1.length);
    }

    // Método para convertir Double[] a int[]
    private int[] toIntArray(Double[] array) {
        int[] intArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            intArray[i] = array[i].intValue();
        }
        return intArray;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Dibujar Polígono");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new DibujarPoligono());
            frame.setSize(300, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
