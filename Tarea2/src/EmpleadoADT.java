public class EmpleadoADT {

    private ArrayADT<Empleado> empleados;
    private int cantidad;

    public EmpleadoADT(int capacidad) {
        empleados = new ArrayADT<>(capacidad);
        cantidad = 0;
    }

    public void insertar(Empleado empleado) {
        if (cantidad < empleados.obtenerLongitud()) {
            empleados.insertarElemento(cantidad, empleado);
            cantidad++;
        } else {
            throw new IllegalStateException("El arreglo está lleno");
        }
    }

    public Empleado obtener(int index) {
        if (index >= 0 && index < cantidad) {
            return empleados.obtenerElemento(index);
        }

        throw new IndexOutOfBoundsException("Indice fuera de rango");
    }

    public int obtenerCantidad() {
        return cantidad;
    }

    public Empleado mayorAntiguedad(int anioActual) {

        Empleado mayor = obtener(0);

        for (int i = 1; i < cantidad; i++) {

            Empleado actual = obtener(i);

            if (actual.calcularAntiguedad(anioActual)
                    > mayor.calcularAntiguedad(anioActual)) {

                mayor = actual;
            }
        }

        return mayor;
    }

    public Empleado menorAntiguedad(int anioActual) {

        Empleado menor = obtener(0);

        for (int i = 1; i < cantidad; i++) {

            Empleado actual = obtener(i);

            if (actual.calcularAntiguedad(anioActual)
                    < menor.calcularAntiguedad(anioActual)) {

                menor = actual;
            }
        }

        return menor;
    }
}