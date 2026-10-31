import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solucionador {

    public enum Estado { EXPLORANDO, RESUELTO, SIN_SOLUCION }

    public static final int ARRIBA = 0, DERECHA = 1, ABAJO = 2, IZQUIERDA = 3;
    private static final int[] DF = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};
    private static final String[] NOMBRES = {"arriba", "derecha", "abajo", "izquierda"};

    private final Laberinto laberinto;
    private final int[] orden;     
    private final String nombre;
    private boolean[][] visitado;   
    private boolean[][] descartado;  
    private boolean[][] enPila;      
    private Stack<Celda> pila;
    private Estado estado;
    private int iteraciones;

    public Solucionador(Laberinto laberinto, String nombre, int[] orden) {
        if (orden.length != 4) {
            throw new IllegalArgumentException("El orden debe tener 4 direcciones");
        }
        this.laberinto = laberinto;
        this.nombre = nombre;
        this.orden = orden.clone();
        reiniciar();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcionOrden() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < orden.length; i++) {
            sb.append(NOMBRES[orden[i]]);
            if (i < orden.length - 1) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }

    public void reiniciar() {
        int filas = laberinto.getFilas();
        int columnas = laberinto.getColumnas();
        visitado = new boolean[filas][columnas];
        descartado = new boolean[filas][columnas];
        enPila = new boolean[filas][columnas];
        pila = new Stack<>();
        iteraciones = 0;
        estado = Estado.EXPLORANDO;

        int fe = laberinto.getFilaEntrada();
        int ce = laberinto.getColEntrada();
        pila.push(new Celda(fe, ce));
        visitado[fe][ce] = true;
        enPila[fe][ce] = true;
    }

    private boolean esValida(int f, int c) {
        return laberinto.estaDentro(f, c) && !laberinto.esMuro(f, c) && !visitado[f][c];
    }

    public boolean paso() {
        if (estado != Estado.EXPLORANDO) {
            return false;
        }
        if (pila.isEmpty()) {
            estado = Estado.SIN_SOLUCION;
            return false;
        }

        iteraciones++;
        Celda actual = pila.peek();

        if (actual.direccion < 4) {
            int d = orden[actual.direccion++];  
            int nf = actual.fila + DF[d];
            int nc = actual.col + DC[d];
            if (esValida(nf, nc)) {
                visitado[nf][nc] = true;
                enPila[nf][nc] = true;
                pila.push(new Celda(nf, nc));           
                if (laberinto.esSalida(nf, nc)) {
                    estado = Estado.RESUELTO;            
                }
            }
        } else {
            pila.pop();                                
            enPila[actual.fila][actual.col] = false;
            descartado[actual.fila][actual.col] = true;
            if (pila.isEmpty()) {
                estado = Estado.SIN_SOLUCION;
            }
        }
        return estado == Estado.EXPLORANDO;
    }

    public Laberinto getLaberinto() {
        return laberinto;
    }

    public Estado getEstado() {
        return estado;
    }

    public int getIteraciones() {
        return iteraciones;
    }

    public int getLongitudCamino() {
        return Math.max(0, pila.size() - 1);
    }

    public Celda getCima() {
        return pila.isEmpty() ? null : pila.peek();
    }

    public boolean estaEnPila(int f, int c) {
        return enPila[f][c];
    }

    public boolean estaDescartada(int f, int c) {
        return descartado[f][c];
    }

    public List<int[]> getCamino() {
        List<int[]> camino = new ArrayList<>();
        for (Celda c : pila) {                          
            camino.add(new int[]{c.fila, c.col});
        }
        return camino;
    }
}