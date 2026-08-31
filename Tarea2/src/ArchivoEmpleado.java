import java.io.BufferedReader;
import java.io.FileReader;

public class ArchivoEmpleado {

    public static EmpleadoADT leerArchivo(String nombreArchivo) throws Exception {

        EmpleadoADT empleados = new EmpleadoADT(100);

        FileReader archivo = new FileReader(nombreArchivo);
        BufferedReader lector = new BufferedReader(archivo);

        // Saltar encabezado
        lector.readLine();

        String linea;

        while ((linea = lector.readLine()) != null) {

            String[] datos = linea.split(",");

            int numeroTrabajador = Integer.parseInt(datos[0]);

            String nombres = datos[1];
            String paterno = datos[2];
            String materno = datos[3];

            double horasExtra = Double.parseDouble(datos[4]);
            double sueldoBase = Double.parseDouble(datos[5]);

            int anioIngreso = Integer.parseInt(datos[6]);

            Empleado empleado = new Empleado(
                numeroTrabajador,
                nombres,
                paterno,
                materno,
                horasExtra,
                sueldoBase,
                anioIngreso
            );

            empleados.insertar(empleado);
        }

        lector.close();

        return empleados;
    }
}