import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArchivoCSV {

    public static ArrayADT<Character> leerArchivo(String ruta) {

        try (BufferedReader lector = new BufferedReader(new FileReader(ruta))) {

            String linea;
            int filas = 0;
            int columnas = 0;

            // Primero contamos las dimensiones del archivo
            while ((linea = lector.readLine()) != null) {

                if (!linea.trim().isEmpty()) {
                    filas++;

                    String[] valores = linea.split(",");

                    if (columnas == 0) {
                        columnas = valores.length;
                    }
                }
            }

            if (filas < 10 || columnas < 10) {
                throw new IllegalArgumentException(
                    "El tablero debe tener al menos 10 filas y 10 columnas."
                );
            }

            // Creamos el ArrayADT con las dimensiones encontradas
            ArrayADT<Character> tablero = new ArrayADT<>(filas, columnas);

            // Volvemos al inicio del archivo
            try (BufferedReader lector2 = new BufferedReader(new FileReader(ruta))) {

                int fila = 0;

                while ((linea = lector2.readLine()) != null) {

                    if (!linea.trim().isEmpty()) {

                        String[] valores = linea.split(",");

                        for (int columna = 0; columna < valores.length; columna++) {

                            int valor = Integer.parseInt(valores[columna].trim());

                            if (valor == 1) {
                                tablero.insertarElemento(fila, columna, '\u2588');
                            } else {
                                tablero.insertarElemento(fila, columna, ' ');
                            }
                        }

                        fila++;
                    }
                }
            }

            return tablero;

        } catch (IOException e) {

            throw new RuntimeException(
                "No se pudo leer el archivo: " + ruta, e
            );
        }
    }
}