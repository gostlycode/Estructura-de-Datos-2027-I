import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CsvReader {

    private String nombreArchivo;

    public CsvReader(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public int buscarValor(
            String redSocial,
            String concepto,
            int mes
    ) throws FileNotFoundException {

        File archivo = new File(nombreArchivo);
        Scanner lector = new Scanner(archivo);

        if (lector.hasNextLine()) {
            lector.nextLine();
        }

        int resultado = 0;

        while (lector.hasNextLine()) {

            String linea = lector.nextLine();

            String[] datos = linea.split(",");

            if (datos.length >= 9) {

                if (datos[0].equalsIgnoreCase(redSocial)
                        && datos[1].equalsIgnoreCase(concepto)) {

                    int posicionMes = mes + 2;

                    resultado = Integer.parseInt(
                            datos[posicionMes]
                    );

                    break;
                }
            }
        }

        lector.close();

        return resultado;
    }

    public double promedio(
            String redSocial,
            String concepto
    ) throws FileNotFoundException {

        File archivo = new File(nombreArchivo);
        Scanner lector = new Scanner(archivo);

        if (lector.hasNextLine()) {
            lector.nextLine();
        }

        int suma = 0;
        int cantidad = 0;

        while (lector.hasNextLine()) {

            String linea = lector.nextLine();

            String[] datos = linea.split(",");

            if (datos.length >= 9) {

                if (datos[0].equalsIgnoreCase(redSocial)
                        && datos[1].equalsIgnoreCase(concepto)) {

                    for (int i = 3; i <= 8; i++) {

                        suma += Integer.parseInt(datos[i]);

                        cantidad++;
                    }

                    break;
                }
            }
        }

        lector.close();

        return (double) suma / cantidad;
    }
}