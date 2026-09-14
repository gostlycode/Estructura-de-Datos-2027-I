
public class Main {

    public static void main(String[] args) {

        // --- Construccion de los conjuntos de cada alumno ---
        ConjuntoADT<String> ana = new ConjuntoADT<>();
        ana.agregarElemento("ED");
        ana.agregarElemento("BD");
        ana.agregarElemento("Redes");
        ana.agregarElemento("IA");

        ConjuntoADT<String> luis = new ConjuntoADT<>();
        luis.agregarElemento("ED");
        luis.agregarElemento("Redes");
        luis.agregarElemento("SO");

        System.out.println("Materias de Ana : " + ana);
        System.out.println("Materias de Luis: " + luis);
        System.out.println();

        // --- Prueba: longitud ---
        System.out.println("Numero de materias de Ana : " + ana.longitud());
        System.out.println("Numero de materias de Luis: " + luis.longitud());
        System.out.println();

        // --- Prueba: contiene ---
        System.out.println("Ana esta inscrita en 'IA'?    " + ana.contieneElemento("IA"));
        System.out.println("Luis esta inscrito en 'IA'?   " + luis.contieneElemento("IA"));
        System.out.println();

        // --- Prueba: agregar sin duplicar ---
        ana.agregarElemento("BD"); // ya existe, no debe duplicarse
        System.out.println("Ana tras intentar agregar 'BD' de nuevo: " + ana);
        System.out.println("Longitud de Ana (debe seguir en 4): " + ana.longitud());
        System.out.println();

        // --- Prueba: eliminar ---
        ConjuntoADT<String> anaTemporal = new ConjuntoADT<>();
        anaTemporal.setElementos(new java.util.ArrayList<>(ana.getElementos()));
        anaTemporal.eliminarElemento("IA");
        System.out.println("Ana sin 'IA' (copia de prueba): " + anaTemporal);
        System.out.println();

        // --- Prueba: equals ---
        ConjuntoADT<String> anaCopia = new ConjuntoADT<>();
        anaCopia.agregarElemento("IA");
        anaCopia.agregarElemento("Redes");
        anaCopia.agregarElemento("BD");
        anaCopia.agregarElemento("ED");
        System.out.println("Ana equals anaCopia (mismos elementos, distinto orden)? "
                + ana.equals(anaCopia));
        System.out.println("Ana equals Luis? " + ana.equals(luis));
        System.out.println();

        // --- Prueba: esSubConjunto ---
        ConjuntoADT<String> soloRedes = new ConjuntoADT<>();
        soloRedes.agregarElemento("Redes");
        System.out.println("{Redes} es subconjunto de Ana?  " + soloRedes.esSubConjunto(ana));
        System.out.println("{Redes} es subconjunto de Luis? " + soloRedes.esSubConjunto(luis));
        System.out.println("Ana es subconjunto de Luis?     " + ana.esSubConjunto(luis));
        System.out.println();

        // --- Prueba: union ---
        ConjuntoADT<String> union = ana.union(luis);
        System.out.println("Union (materias que cursa Ana o Luis): " + union);
        System.out.println();

        // --- Prueba: interseccion ---
        ConjuntoADT<String> interseccion = ana.interseccion(luis);
        System.out.println("Interseccion (materias en comun): " + interseccion);
        System.out.println();

        // --- Prueba: diferencia ---
        ConjuntoADT<String> diferenciaAnaLuis = ana.diferencia(luis);
        ConjuntoADT<String> diferenciaLuisAna = luis.diferencia(ana);
        System.out.println("Diferencia Ana - Luis (solo Ana cursa): " + diferenciaAnaLuis);
        System.out.println("Diferencia Luis - Ana (solo Luis cursa): " + diferenciaLuisAna);
    }
}