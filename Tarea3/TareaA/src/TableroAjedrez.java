public class TableroAjedrez {

    private ArrayADT<Character> tablero;

    public TableroAjedrez() {
        tablero = new ArrayADT<>(8, 8);
        inicializarTablero();
    }

    private void inicializarTablero() {

        // Piezas negras
        tablero.insertarElemento(0, 0, '\u265C');
        tablero.insertarElemento(0, 1, '\u265E');
        tablero.insertarElemento(0, 2, '\u265D');
        tablero.insertarElemento(0, 3, '\u265B');
        tablero.insertarElemento(0, 4, '\u265A');
        tablero.insertarElemento(0, 5, '\u265D');
        tablero.insertarElemento(0, 6, '\u265E');
        tablero.insertarElemento(0, 7, '\u265C');

        // Peones negros
        for (int columna = 0; columna < 8; columna++) {
            tablero.insertarElemento(1, columna, '\u265F');
        }

        // Casillas vacías
        for (int fila = 2; fila < 6; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                tablero.insertarElemento(fila, columna, ' ');
            }
        }

        // Peones blancos
        for (int columna = 0; columna < 8; columna++) {
            tablero.insertarElemento(6, columna, '\u2659');
        }

        // Piezas blancas
        tablero.insertarElemento(7, 0, '\u2656');
        tablero.insertarElemento(7, 1, '\u2658');
        tablero.insertarElemento(7, 2, '\u2657');
        tablero.insertarElemento(7, 3, '\u2655');
        tablero.insertarElemento(7, 4, '\u2654');
        tablero.insertarElemento(7, 5, '\u2657');
        tablero.insertarElemento(7, 6, '\u2658');
        tablero.insertarElemento(7, 7, '\u2656');
    }

   public void imprimir() {

    for (int fila = 0; fila < 8; fila++) {

        System.out.print((8 - fila) + " ");

        for (int columna = 0; columna < 8; columna++) {
            System.out.print(
                tablero.obtenerElemento(fila, columna) + " "
            );
        }

        System.out.println();
    }

    System.out.println("  A B C D E F G H");
}
}