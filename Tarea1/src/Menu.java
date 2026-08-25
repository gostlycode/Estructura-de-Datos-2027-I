import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private CsvReader csv = new CsvReader("data/datos.csv");

    public void iniciar() {

        try {

            mostrarDashboard();

            int opcion;

            do {

                mostrarMenu();

                opcion = scanner.nextInt();

                switch (opcion) {

                    case 1:
                        diferenciaYoutube();
                        break;

                    case 2:
                        promedioCrecimiento();
                        break;

                    case 3:
                        promedioLikes();
                        break;

                    case 4:
                        System.out.println("Programa finalizado.");
                        break;

                    default:
                        System.out.println("Opción inválida.");

                }

            } while (opcion != 4);

        } catch (FileNotFoundException e) {

            System.out.println("No se pudo encontrar el archivo datos.csv.");

        }

        scanner.close();
    }

    private void mostrarDashboard() throws FileNotFoundException {

        int enero = csv.buscarValor(
                "TWITTER",
                "SEGUIDORES (FOLLOWERS)",
                1
        );

        int junio = csv.buscarValor(
                "TWITTER",
                "SEGUIDORES (FOLLOWERS)",
                6
        );

        int diferencia = junio - enero;

        System.out.println("========================================");
        System.out.println("      ANALIZADOR DE REDES SOCIALES");
        System.out.println("========================================");

        System.out.println();
        System.out.println("TWITTER - SEGUIDORES");
        System.out.println("Enero:      " + enero);
        System.out.println("Junio:      " + junio);
        System.out.println("Diferencia: +" + diferencia);

        System.out.println("----------------------------------------");
    }

    private void mostrarMenu() {

        System.out.println();
        System.out.println("1. Diferencia de visualizaciones de YouTube");
        System.out.println("2. Promedio de crecimiento Twitter y Facebook");
        System.out.println("3. Promedio de Me gusta");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private void diferenciaYoutube() throws FileNotFoundException {

        System.out.println();
        System.out.println("DIFERENCIA DE VISUALIZACIONES DE YOUTUBE");

        int mes1 = ingresarMes("primer");
        int mes2 = ingresarMes("segundo");

        int visualizaciones1 = csv.buscarValor(
                "YOUTUBE",
                "VISUALIZACIONES",
                mes1
        );

        int visualizaciones2 = csv.buscarValor(
                "YOUTUBE",
                "VISUALIZACIONES",
                mes2
        );

        int diferencia = visualizaciones2 - visualizaciones1;

        System.out.println();
        System.out.println("Visualizaciones del primer mes: " + visualizaciones1);
        System.out.println("Visualizaciones del segundo mes: " + visualizaciones2);
        System.out.println("Diferencia: " + diferencia);
    }

    private int ingresarMes(String numeroMes) {

        int mes;

        do {

            System.out.print(
                    "Ingrese el " + numeroMes + " mes (1-6): "
            );

            mes = scanner.nextInt();

            if (mes < 1 || mes > 6) {
                System.out.println("Ingrese un número entre 1 y 6.");
            }

        } while (mes < 1 || mes > 6);

        return mes;
    }

    private void promedioCrecimiento() throws FileNotFoundException {

        double promedioTwitter =
                csv.promedio(
                        "TWITTER",
                        "CRECIMIENTO DE FOLLOWERS"
                );

        double promedioFacebook =
                csv.promedio(
                        "FACEBOOK",
                        "CRECIMIENTO (seguidores)"
                );

        System.out.println();
        System.out.println("PROMEDIO DE CRECIMIENTO");
        System.out.println("----------------------------------------");
        System.out.println("Twitter:  " + promedioTwitter);
        System.out.println("Facebook: " + promedioFacebook);
    }

    private void promedioLikes() throws FileNotFoundException {

        double promedioYoutube =
                csv.promedio(
                        "YOUTUBE",
                        "ME GUSTA"
                );

        double promedioTwitter =
                csv.promedio(
                        "TWITTER",
                        "ME GUSTA"
                );

        double promedioFacebook =
                csv.promedio(
                        "FACEBOOK",
                        "ME GUSTA EN PUBLICACIONES"
                );

        System.out.println();
        System.out.println("PROMEDIO DE ME GUSTA");
        System.out.println("----------------------------------------");
        System.out.println("YouTube:  " + promedioYoutube);
        System.out.println("Twitter:  " + promedioTwitter);
        System.out.println("Facebook: " + promedioFacebook);
    }
}