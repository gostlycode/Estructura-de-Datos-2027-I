public class Empleado {

    private int numeroTrabajador;
    private String nombres;
    private String paterno;
    private String materno;
    private double horasExtra;
    private double sueldoBase;
    private int anioIngreso;

    public Empleado(int numeroTrabajador, String nombres, String paterno,
                      String materno, double horasExtra, double sueldoBase,
                      int anioIngreso) {

        this.numeroTrabajador = numeroTrabajador;
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
        this.horasExtra = horasExtra;
        this.sueldoBase = sueldoBase;
        this.anioIngreso = anioIngreso;
    }

    public int getNumeroTrabajador() {
        return numeroTrabajador;
    }

    public String getNombres() {
        return nombres;
    }

    public String getPaterno() {
        return paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public double getHorasExtra() {
        return horasExtra;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public int getAnioIngreso() {
        return anioIngreso;
    }

    public double calcularSueldo(int anioActual) {

        int antiguedad = anioActual - anioIngreso;

        double pagoHorasExtra = horasExtra * 276.5;

        double prestacion = sueldoBase * (0.03 * antiguedad);

        return sueldoBase + pagoHorasExtra + prestacion;
    }

    public int calcularAntiguedad(int anioActual) {
        return anioActual - anioIngreso;
    }

    public String nombreCompleto() {
        return nombres + " " + paterno + " " + materno;
    }
}