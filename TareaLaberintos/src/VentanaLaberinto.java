import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class VentanaLaberinto extends JFrame {

    private static final Color COLOR_MURO = new Color(20, 10, 30);        
    private static final Color COLOR_LIBRE = new Color(255, 120, 0);      
    private static final Color COLOR_ENTRADA = new Color(130, 120, 140);  
    private static final Color COLOR_SALIDA = new Color(255, 120, 0);       
    private static final Color COLOR_CAMINO = new Color(80, 200, 60);      
    private static final Color COLOR_ACTUAL = new Color(200, 20, 20);     
    private static final Color COLOR_DESCARTADO =new Color(130, 50, 200);     
    private static final Color COLOR_GANADOR = new Color(255, 170, 0);     

    private final Solucionador[] solucionadores;
    private final Laberinto laberinto;

    private final TableroPanel[] tableros;
    private final JLabel[] lblTitulos;
    private final JLabel[] lblEstados;
    private final JPanel[] tarjetas;
    private final boolean[] terminado;

    private JButton btnPausa, btnPaso, btnReiniciar;
    private JLabel lblResumen;
    private Timer timer;

    public VentanaLaberinto(Solucionador[] solucionadores, int anchoMax, int altoMax) {
        super("Laberinto - Backtracking con pila (comparación de estrategias)");
        this.solucionadores = solucionadores;
        this.laberinto = solucionadores[0].getLaberinto();
        int n = solucionadores.length;
        this.tableros = new TableroPanel[n];
        this.lblTitulos = new JLabel[n];
        this.lblEstados = new JLabel[n];
        this.tarjetas = new JPanel[n];
        this.terminado = new boolean[n];
        construirInterfaz(anchoMax, altoMax);
    }

    private void construirInterfaz(int anchoMax, int altoMax) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                timer.stop();
            }
        });

        int lado = Math.max(12, Math.min(anchoMax / laberinto.getColumnas(),
                altoMax / laberinto.getFilas()));
        Dimension tamTablero = new Dimension(lado * laberinto.getColumnas(),
                lado * laberinto.getFilas());

        JPanel centro = new JPanel(new GridLayout(1, solucionadores.length, 8, 0));
        centro.setBorder(BorderFactory.createEmptyBorder(6, 6, 0, 6));
        for (int i = 0; i < solucionadores.length; i++) {
            Solucionador s = solucionadores[i];

            lblTitulos[i] = new JLabel("", SwingConstants.CENTER);
            lblTitulos[i].setBorder(BorderFactory.createEmptyBorder(4, 2, 4, 2));

            tableros[i] = new TableroPanel(s);
            tableros[i].setPreferredSize(tamTablero);

            lblEstados[i] = new JLabel(" ", SwingConstants.CENTER);
            lblEstados[i].setFont(lblEstados[i].getFont().deriveFont(Font.BOLD, 12f));
            lblEstados[i].setBorder(BorderFactory.createEmptyBorder(6, 2, 6, 2));

            tarjetas[i] = new JPanel(new BorderLayout());
            tarjetas[i].add(lblTitulos[i], BorderLayout.NORTH);
            tarjetas[i].add(tableros[i], BorderLayout.CENTER);
            tarjetas[i].add(lblEstados[i], BorderLayout.SOUTH);
            centro.add(tarjetas[i]);
        }

        btnPausa = new JButton("Pausar");
        btnPaso = new JButton("Paso");
        btnReiniciar = new JButton("Reiniciar");

        JSlider velocidad = new JSlider(1, 20, 6);
        velocidad.setPreferredSize(new Dimension(220, velocidad.getPreferredSize().height));
        velocidad.setToolTipText("Velocidad de la animación (para las 3 ventanas)");

        JPanel controles = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 4));
        controles.add(btnPausa);
        controles.add(btnPaso);
        controles.add(btnReiniciar);
        controles.add(new JLabel("   Velocidad:"));
        controles.add(velocidad);

        lblResumen = new JLabel(" ", SwingConstants.CENTER);
        lblResumen.setFont(lblResumen.getFont().deriveFont(Font.BOLD, 13f));
        lblResumen.setBorder(BorderFactory.createEmptyBorder(2, 4, 6, 4));

        JPanel sur = new JPanel(new GridLayout(3, 1));
        sur.add(crearLeyenda());
        sur.add(controles);
        sur.add(lblResumen);

        add(centro, BorderLayout.CENTER);
        add(sur, BorderLayout.SOUTH);

        timer = new Timer(retardoPara(velocidad.getValue()), e -> avanzar());

        btnPausa.addActionListener(e -> {
            if (timer.isRunning()) {
                timer.stop();
                btnPausa.setText("Reanudar");
            } else {
                timer.start();
                btnPausa.setText("Pausar");
            }
        });
        btnPaso.addActionListener(e -> {
            if (timer.isRunning()) {
                timer.stop();
                btnPausa.setText("Reanudar");
            }
            avanzar();
        });
        btnReiniciar.addActionListener(e -> reiniciarTodo());
        velocidad.addChangeListener(e -> timer.setDelay(retardoPara(velocidad.getValue())));

        refrescarTarjetas();
        pack();
        setLocationRelativeTo(null);
    }

    public void iniciar() {
        setVisible(true);
        timer.start();
    }

    private static int retardoPara(int velocidad) {
        return 1000 / velocidad; // velocidad 1..20 -> 1000 ms .. 50 ms
    }

    private void avanzar() {
        boolean todosTerminaron = true;
        for (int i = 0; i < solucionadores.length; i++) {
            Solucionador s = solucionadores[i];
            if (s.getEstado() == Solucionador.Estado.EXPLORANDO) {
                s.paso();
            }
            if (s.getEstado() == Solucionador.Estado.EXPLORANDO) {
                todosTerminaron = false;
            } else if (!terminado[i]) {
                terminado[i] = true;
                imprimirResultadoEnConsola(s);
            }
        }

        if (todosTerminaron) {
            timer.stop();
            btnPausa.setEnabled(false);
            btnPaso.setEnabled(false);
            mostrarGanador();
        }
        refrescarTarjetas();
    }

    private void reiniciarTodo() {
        for (int i = 0; i < solucionadores.length; i++) {
            solucionadores[i].reiniciar();
            terminado[i] = false;
        }
        btnPausa.setEnabled(true);
        btnPaso.setEnabled(true);
        btnPausa.setText("Pausar");
        lblResumen.setText(" ");
        refrescarTarjetas();
        timer.restart();
    }

    private boolean resuelto(Solucionador s) {
        return s.getEstado() == Solucionador.Estado.RESUELTO;
    }

    private long puntaje(Solucionador s) {
        return (long) s.getIteraciones() * 100000L + s.getLongitudCamino();
    }

    private long mejorPuntaje() {
        long mejor = -1;
        for (Solucionador s : solucionadores) {
            if (resuelto(s) && (mejor < 0 || puntaje(s) < mejor)) {
                mejor = puntaje(s);
            }
        }
        return mejor;
    }

    private boolean todosTerminaron() {
        for (Solucionador s : solucionadores) {
            if (s.getEstado() == Solucionador.Estado.EXPLORANDO) {
                return false;
            }
        }
        return true;
    }

    private void mostrarGanador() {
        long mejor = mejorPuntaje();
        if (mejor < 0) {
            lblResumen.setText("Ninguna estrategia encontró solución");
            System.out.println("\nNinguna estrategia encontró solución.");
            return;
        }

        StringBuilder ganadores = new StringBuilder();
        StringBuilder detalle = new StringBuilder();
        int cuantos = 0;
        for (Solucionador s : solucionadores) {
            if (resuelto(s) && puntaje(s) == mejor) {
                if (cuantos++ > 0) {
                    ganadores.append(" y ");
                }
                ganadores.append(s.getNombre());
            }
            if (detalle.length() > 0) {
                detalle.append("   |   ");
            }
            detalle.append(s.getNombre()).append(": ")
                    .append(resuelto(s) ? s.getIteraciones() + " it." : "sin solución");
        }
        String titulo = cuantos > 1 ? "Empate, mejores: " : "Mejor optimizada: ";
        lblResumen.setText(titulo + ganadores + "     (" + detalle + ")");
        System.out.println("\n>>> " + titulo + ganadores);
    }

    private void refrescarTarjetas() {
        boolean fin = todosTerminaron();
        long mejor = fin ? mejorPuntaje() : -1;

        for (int i = 0; i < solucionadores.length; i++) {
            Solucionador s = solucionadores[i];
            boolean esMejor = fin && resuelto(s) && puntaje(s) == mejor;

            lblTitulos[i].setText("<html><div style='text-align:center'><b>" + s.getNombre()
                    + (esMejor ? " &nbsp;&#9733; MEJOR &#9733;" : "")
                    + "</b><br>Orden: " + s.getDescripcionOrden() + "</div></html>");

            switch (s.getEstado()) {
                case EXPLORANDO:
                    lblEstados[i].setForeground(Color.DARK_GRAY);
                    lblEstados[i].setText("Explorando...  iteraciones: " + s.getIteraciones()
                            + "  |  camino: " + s.getLongitudCamino());
                    break;
                case RESUELTO:
                    lblEstados[i].setForeground(new Color(20, 120, 40));
                    lblEstados[i].setText("TERMINÓ - " + s.getLongitudCamino() + " pasos, "
                            + s.getIteraciones() + " iteraciones");
                    break;
                default:
                    lblEstados[i].setForeground(new Color(180, 30, 30));
                    lblEstados[i].setText("TERMINÓ - sin solución (" + s.getIteraciones()
                            + " iteraciones)");
            }

            tarjetas[i].setBorder(esMejor
                    ? BorderFactory.createLineBorder(COLOR_GANADOR, 4)
                    : BorderFactory.createEmptyBorder(4, 4, 4, 4));
            tableros[i].repaint();
        }
    }

    private void imprimirResultadoEnConsola(Solucionador s) {
        System.out.println("=== " + s.getNombre() + " (" + s.getDescripcionOrden() + ") ===");
        if (!resuelto(s)) {
            System.out.println("TERMINO: sin solución (" + s.getIteraciones() + " iteraciones)");
            return;
        }
        List<int[]> camino = s.getCamino();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < camino.size(); i++) {
            int[] p = camino.get(i);
            sb.append("(").append(p[0]).append(",").append(p[1]).append(")");
            if (i < camino.size() - 1) {
                sb.append(" -> ");
            }
        }
        System.out.println("TERMINO: " + (camino.size() - 1) + " pasos, "
                + s.getIteraciones() + " iteraciones");
        System.out.println("Camino: " + sb);
    }

    private JPanel crearLeyenda() {
        JPanel leyenda = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 4));
        Object[][] items = {
                {"Muro", COLOR_MURO},
                {"Camino libre", COLOR_LIBRE},
                {"Entrada", COLOR_ENTRADA},
                {"Salida", COLOR_SALIDA},
                {"Camino actual", COLOR_CAMINO},
                {"Descartado", COLOR_DESCARTADO}
        };
        for (Object[] it : items) {
            leyenda.add(new JLabel((String) it[0], new ColorIcon((Color) it[1]), SwingConstants.LEFT));
        }
        return leyenda;
    }

    private static class ColorIcon implements Icon {
        private final Color color;

        ColorIcon(Color color) {
            this.color = color;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(color);
            g.fillRect(x, y, 14, 14);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(x, y, 14, 14);
        }

        @Override
        public int getIconWidth() {
            return 15;
        }

        @Override
        public int getIconHeight() {
            return 15;
        }
    }

    private class TableroPanel extends JPanel {

        private final Solucionador solucionador;

        TableroPanel(Solucionador solucionador) {
            this.solucionador = solucionador;
            setBackground(new Color(235, 235, 235));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            int filas = laberinto.getFilas();
            int columnas = laberinto.getColumnas();
            int lado = Math.max(1, Math.min(getWidth() / columnas, getHeight() / filas));
            int x0 = (getWidth() - lado * columnas) / 2;
            int y0 = (getHeight() - lado * filas) / 2;
            Celda tope = solucionador.getCima();

            g2.setFont(new Font("SansSerif", Font.BOLD, Math.max(10, lado / 2)));
            FontMetrics fm = g2.getFontMetrics();

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    int x = x0 + j * lado;
                    int y = y0 + i * lado;

                    g2.setColor(colorDe(i, j, tope));
                    g2.fillRect(x, y, lado, lado);
                    g2.setColor(Color.GRAY);
                    g2.drawRect(x, y, lado, lado);

                    if (laberinto.esEntrada(i, j) || laberinto.esSalida(i, j)) {
                        String t = laberinto.esEntrada(i, j) ? "E" : "S";
                        g2.setColor(laberinto.esEntrada(i, j) ? Color.WHITE : Color.BLACK);
                        int tx = x + (lado - fm.stringWidth(t)) / 2;
                        int ty = y + (lado - fm.getHeight()) / 2 + fm.getAscent();
                        g2.drawString(t, tx, ty);
                    }
                }
            }
        }

        private Color colorDe(int i, int j, Celda tope) {
            if (laberinto.esMuro(i, j)) return COLOR_MURO;
            if (laberinto.esEntrada(i, j)) return COLOR_ENTRADA;
            if (laberinto.esSalida(i, j)) return COLOR_SALIDA;
            if (tope != null && tope.fila == i && tope.col == j) return COLOR_ACTUAL;
            if (solucionador.estaEnPila(i, j)) return COLOR_CAMINO;
            if (solucionador.estaDescartada(i, j)) return COLOR_DESCARTADO;
            return COLOR_LIBRE;
        }
    }
}