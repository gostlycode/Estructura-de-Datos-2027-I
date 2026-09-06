public class JuegoVida {

    private ArrayADT<Character> tablero;

    public JuegoVida(String rutaArchivo) {
        tablero = ArchivoCSV.leerArchivo(rutaArchivo);
    }

    // Cuenta las células vivas alrededor de una posición
    private int contarVecinos(int fila, int columna) {

        int vecinosVivos = 0;

        // Las 8 posiciones alrededor de la célula
        int[][] direcciones = {
            {-1, -1}, {-1, 0}, {-1, 1},
            { 0, -1},           { 0, 1},
            { 1, -1}, { 1, 0}, { 1, 1}
        };

        for (int[] direccion : direcciones) {

            int nuevaFila = fila + direccion[0];
            int nuevaColumna = columna + direccion[1];

            // Verificamos que la posición esté dentro del tablero
            if (nuevaFila >= 0 &&
                nuevaFila < tablero.obtenerFilas() &&
                nuevaColumna >= 0 &&
                nuevaColumna < tablero.obtenerColumnas()) {

                if (tablero.obtenerElemento(nuevaFila, nuevaColumna) == '\u2588') {
                    vecinosVivos++;
                }
            }
        }

        return vecinosVivos;
    }

    // Calcula y genera la siguiente generación
    public void calcularSiguienteGeneracion() {

        ArrayADT<Character> siguienteGeneracion =
            new ArrayADT<>(
                tablero.obtenerFilas(),
                tablero.obtenerColumnas()
            );

        for (int fila = 0; fila < tablero.obtenerFilas(); fila++) {

            for (int columna = 0; columna < tablero.obtenerColumnas(); columna++) {

                char celda = tablero.obtenerElemento(fila, columna);

                int vecinos = contarVecinos(fila, columna);

                // Regla 1, 2 y 3: célula viva
                if (celda == '\u2588') {

                    if (vecinos == 2 || vecinos == 3) {

                        // Sobrevive
                        siguienteGeneracion.insertarElemento(
                            fila, columna, '\u2588'
                        );

                    } else {

                        // Muere por soledad o sobrepoblación
                        siguienteGeneracion.insertarElemento(
                            fila, columna, ' '
                        );
                    }

                // Regla 4: célula muerta
                } else {

                    if (vecinos == 3) {

                        // Nace
                        siguienteGeneracion.insertarElemento(
                            fila, columna, '\u2588'
                        );

                    } else {

                        // Permanece muerta
                        siguienteGeneracion.insertarElemento(
                            fila, columna, ' '
                        );
                    }
                }
            }
        }

        // La nueva generación se convierte en la generación actual
        tablero = siguienteGeneracion;
    }

    // Muestra el tablero actual
    public void mostrar() {

    int columnas = tablero.obtenerColumnas();

    // Borde superior
    System.out.print("+");
    for (int i = 0; i < columnas; i++) {
        System.out.print("-");
    }
    System.out.println("+");

    // Tablero
    for (int fila = 0; fila < tablero.obtenerFilas(); fila++) {

        System.out.print("|");

        for (int columna = 0; columna < columnas; columna++) {

            System.out.print(
                tablero.obtenerElemento(fila, columna)
            );
        }

        System.out.println("|");
    }

    // Borde inferior
    System.out.print("+");
    for (int i = 0; i < columnas; i++) {
        System.out.print("-");
    }
    System.out.println("+");
}
}