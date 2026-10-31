import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

public class TareaLaberintos {

    private static final int A = Solucionador.ARRIBA;
    private static final int D = Solucionador.DERECHA;
    private static final int B = Solucionador.ABAJO;
    private static final int I = Solucionador.IZQUIERDA;

    private static String buscarArchivo(String[] args) {
        if (args.length > 0) {
            return args[0];
        }
        String[] candidatos = {"entrada.lab", "src/entrada.lab", "../entrada.lab"};
        for (String c : candidatos) {
            if (new File(c).isFile()) {
                return c;
            }
        }
        JFileChooser chooser = new JFileChooser(new File("."));
        chooser.setDialogTitle("Selecciona el archivo del laberinto (.lab)");
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String ruta = buscarArchivo(args);
            if (ruta == null) {
                return;
            }
            Laberinto laberinto;
            try {
                laberinto = Laberinto.desdeArchivo(ruta);
            } catch (IOException | RuntimeException e) {
                JOptionPane.showMessageDialog(null,
                        "Error al leer '" + ruta + "':\n" + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Solucionador[] estrategias = {
                    new Solucionador(laberinto, "Izquierda primero", new int[]{I, A, D, B}),
                    new Solucionador(laberinto, "Arriba primero",    new int[]{A, D, B, I}),
                    new Solucionador(laberinto, "Derecha primero",   new int[]{D, B, I, A}),
            };

            Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
            int anchoTablero = (pantalla.width - 80) / estrategias.length;
            int altoTablero = pantalla.height - 330;

            new VentanaLaberinto(estrategias, anchoTablero, altoTablero).iniciar();
        });
    }
}