package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class GraficoAsistencias extends JPanel {

    private int[] asistencias = new int[7];

    private final String[] dias = {
        "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"
    };

    public GraficoAsistencias() {
        setBackground(Color.WHITE);
        setOpaque(true);
    }

    public void actualizarDatos(int[] datos) {
        if (datos != null && datos.length == 7) {
            asistencias = datos.clone();
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int ancho = getWidth();
        int alto = getHeight();

        int margenIzquierdo = 25;
        int margenDerecho = 15;
        int margenSuperior = 25;
        int margenInferior = 30;

        int areaAncho = ancho - margenIzquierdo - margenDerecho;
        int areaAlto = alto - margenSuperior - margenInferior;

        int maximo = 1;

        for (int valor : asistencias) {
            maximo = Math.max(maximo, valor);
        }

        int espacio = 10;
        int anchoBarra = (areaAncho - espacio * 6) / 7;

        // Líneas horizontales de referencia
        g2.setColor(new Color(230, 233, 237));

        for (int i = 0; i < 4; i++) {
            int y = margenSuperior + (areaAlto * i / 3);
            g2.drawLine(margenIzquierdo, y,
                    ancho - margenDerecho, y);
        }

        for (int i = 0; i < 7; i++) {

            int x = margenIzquierdo + i * (anchoBarra + espacio);

            int alturaBarra = (int) ((double) asistencias[i] / maximo * (areaAlto - 22));

            int y = margenSuperior + areaAlto - alturaBarra;

            // Barra
            g2.setColor(new Color(184, 243, 74));

            if (asistencias[i] > 0) {
                g2.fill(new RoundRectangle2D.Double(
                        x, y, anchoBarra, alturaBarra, 8, 8
                ));
            }

            // Cantidad
            g2.setColor(new Color(45, 50, 55));
            g2.setFont(new Font("SansSerif", Font.BOLD, 11));

            String cantidad = String.valueOf(asistencias[i]);
            int textoAncho = g2.getFontMetrics().stringWidth(cantidad);

            g2.drawString(
                    cantidad,
                    x + (anchoBarra - textoAncho) / 2,
                    Math.max(14, y - 6)
            );

            // Día
            g2.setFont(new Font("SansSerif", Font.PLAIN, 10));
            g2.setColor(new Color(105, 110, 116));

            int diaAncho = g2.getFontMetrics().stringWidth(dias[i]);

            g2.drawString(
                    dias[i],
                    x + (anchoBarra - diaAncho) / 2,
                    alto - 10
            );
        }

        g2.dispose();
    }
}
