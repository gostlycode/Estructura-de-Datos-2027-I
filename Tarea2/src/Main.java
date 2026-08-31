public class Main {

    public static void main(String[] args) throws Exception {

        int anioActual = 2026;

        // Leer empleados del archivo
        NominaICO empleados =
                ArchivoEmpleado.leerArchivo("data/junio.dat");

        // Cantidad de empleados
        System.out.println("Cantidad de empleados: "
                + empleados.obtenerCantidad());

        // Empleado con mayor antigüedad
        Empleado mayor = empleados.mayorAntiguedad(anioActual);

        System.out.println("\nEmpleado con mayor antiguedad:");
        System.out.println(mayor.nombreCompleto());
        System.out.println("Antiguedad: "
                + mayor.calcularAntiguedad(anioActual)
                + " años");

        // Empleado con menor antigüedad
        Empleado menor = empleados.menorAntiguedad(anioActual);

        System.out.println("\nEmpleado con menor antiguedad:");
        System.out.println(menor.nombreCompleto());
        System.out.println("Antiguedad: "
                + menor.calcularAntiguedad(anioActual)
                + " años");

        // Mostrar todos los empleados
        System.out.println("\n===== TODOS LOS EMPLEADOS =====");

        for (int i = 0; i < empleados.obtenerCantidad(); i++) {

            Empleado empleado = empleados.obtener(i);

            System.out.println("\nEmpleado #" + (i + 1));
            System.out.println("Numero de trabajador: "
                    + empleado.getNumeroTrabajador());
            System.out.println("Nombre: "
                    + empleado.nombreCompleto());
            System.out.println("Horas extra: "
                    + empleado.getHorasExtra());
            System.out.println("Sueldo base: $"
                    + empleado.getSueldoBase());
            System.out.println("Año de ingreso: "
                    + empleado.getAnioIngreso());
            System.out.println("Antiguedad: "
                    + empleado.calcularAntiguedad(anioActual)
                    + " años");
            System.out.println("Sueldo a pagar: $"
                    + empleado.calcularSueldo(anioActual));
        }
    }
}