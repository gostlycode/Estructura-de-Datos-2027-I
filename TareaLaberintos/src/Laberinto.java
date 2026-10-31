import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Laberinto {

    private final char[][] tablero;
    private final int filas;
    private final int columnas;
    private final int filaEntrada, colEntrada;
    private final int filaSalida, colSalida;

    private Laberinto(char[][] tablero, int filaEntrada, int colEntrada,
                      int filaSalida, int colSalida) {
        this.tablero = tablero;
        this.filas = tablero.length;
        this.columnas = tablero[0].length;
        this.filaEntrada = filaEntrada;
        this.colEntrada = colEntrada;
        this.filaSalida = filaSalida;
        this.colSalida = colSalida;
    }

    public static Laberinto desdeArchivo(String ruta) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            int filas = Integer.parseInt(br.readLine().trim());
            int columnas = Integer.parseInt(br.readLine().trim());
            char[][] tablero = new char[filas][columnas];

            int fe = -1, ce = -1, fs = -1, cs = -1;
            for (int i = 0; i < filas; i++) {
                String linea = br.readLine();
                if (linea == null) {
                    throw new IOException("Faltan filas en el archivo (fila " + i + ")");
                }
                String[] partes = linea.trim().split(",");
                if (partes.length != columnas) {
                    throw new IOException("La fila " + i + " tiene " + partes.length
                            + " columnas, se esperaban " + columnas);
                }
                for (int j = 0; j < columnas; j++) {
                    char c = partes[j].trim().toUpperCase().charAt(0);
                    tablero[i][j] = c;
                    if (c == 'E') {
                        fe = i;
                        ce = j;
                    } else if (c == 'S') {
                        fs = i;
                        cs = j;
                    }
                }
            }
            if (fe < 0 || fs < 0) {
                throw new IOException("El laberinto debe tener una entrada (E) y una salida (S)");
            }
            return new Laberinto(tablero, fe, ce, fs, cs);
        }
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getFilaEntrada() {
        return filaEntrada;
    }

    public int getColEntrada() {
        return colEntrada;
    }

    public int getFilaSalida() {
        return filaSalida;
    }

    public int getColSalida() {
        return colSalida;
    }

    public boolean estaDentro(int f, int c) {
        return f >= 0 && f < filas && c >= 0 && c < columnas;
    }

    public boolean esMuro(int f, int c) {
        return tablero[f][c] == '1';
    }

    public boolean esEntrada(int f, int c) {
        return tablero[f][c] == 'E';
    }

    public boolean esSalida(int f, int c) {
        return tablero[f][c] == 'S';
    }
}
