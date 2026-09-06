import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Ingresa el numero de generaciones: ");
        int generaciones = sc.nextInt();

        JuegoVida juego = new JuegoVida("data/poblacion.csv");

        for (int i = 0; i < generaciones; i++) {

            limpiarPantalla();

            System.out.println("Generacion: " + (i + 1));
            System.out.println();

            juego.mostrar();

            if (i < generaciones - 1) {

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                juego.calcularSiguienteGeneracion();
            }
        }

        sc.close();
    }

    public static void limpiarPantalla() {

        try {
            new ProcessBuilder("cmd", "/c", "cls")
                .inheritIO()
                .start()
                .waitFor();

        } catch (Exception e) {
        }
    }
}