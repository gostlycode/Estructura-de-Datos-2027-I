public class ArrayADT<T> {

    private int filas;
    private int columnas;
    private Object[][] datos;

    public ArrayADT(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.datos = new Object[filas][columnas];
    }

    @SuppressWarnings("unchecked")
    public T obtenerElemento(int fila, int columna) {

        if (fila >= 0 && fila < filas &&
            columna >= 0 && columna < columnas) {

            return (T) datos[fila][columna];

        } else {
            throw new IndexOutOfBoundsException(
                "Posicion [" + fila + "][" + columna + "] fuera de rango"
            );
        }
    }

    public void insertarElemento(int fila, int columna, T elemento) {

        if (fila >= 0 && fila < filas &&
            columna >= 0 && columna < columnas) {

            datos[fila][columna] = elemento;

        } else {
            throw new IndexOutOfBoundsException(
                "Posicion [" + fila + "][" + columna + "] fuera de rango"
            );
        }
    }

    public int obtenerFilas() {
        return filas;
    }

    public int obtenerColumnas() {
        return columnas;
    }

    public void rellenar(T elemento) {

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                datos[i][j] = elemento;

            }
        }
    }
}