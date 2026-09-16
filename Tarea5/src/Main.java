public class Main {

    public static void imprimirLista(Nodo<String> head) {

        Nodo<String> actual = head;

        while (actual != null) {
            System.out.print(actual.getDato());

            if (actual.getSiguiente() != null) {
                System.out.print(" -> ");
            }

            actual = actual.getSiguiente();
        }

        System.out.println(" -> null");
    }

    public static void imprimirEstadoCompleto(Nodo<String> head) {

        Nodo<String> actual = head;

        while (actual != null) {
            System.out.println(actual);
            actual = actual.getSiguiente();
        }
    }

    public static void main(String[] args) {

        Nodo<String> head = new Nodo<>("AI", new Nodo<>("B", new Nodo<>("C", new Nodo<>("De", new Nodo<>("Mc", new Nodo<>("Zi"))))));

        System.out.println("==========================================");
        System.out.println("1. ESTADO INICIAL DE LA LISTA");
        System.out.println("==========================================");

        imprimirEstadoCompleto(head);

        System.out.println("\nLista:");
        imprimirLista(head);

        System.out.println("\n==========================================");
        System.out.println("2. DATO DEL PRIMER NODO");
        System.out.println("==========================================");

        System.out.println("Primer dato: " + head.getDato());

        System.out.println("\n==========================================");
        System.out.println("3. ESTADO DEL ULTIMO NODO");
        System.out.println("==========================================");

        Nodo<String> actual = head;

        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }

        System.out.println(actual);

        System.out.println("\n==========================================");
        System.out.println("4. INSERTAR Fe ENTRE De Y Mc");
        System.out.println("==========================================");

        actual = head;

        while (!actual.getDato().equals("De")) {
            actual = actual.getSiguiente();
        }

        Nodo<String> nuevo = new Nodo<>("Fe", actual.getSiguiente());

        actual.setSiguiente(nuevo);

        System.out.println("Lista despues de insertar Fe:");
        imprimirLista(head);

        System.out.println("\nEstado completo:");
        imprimirEstadoCompleto(head);

        System.out.println("\n==========================================");
        System.out.println("5. INSERTAR Zz AL FINAL");
        System.out.println("==========================================");

        actual = head;

        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }

        actual.setSiguiente(new Nodo<>("Zz"));

        System.out.println("Lista despues de insertar Zz:");
        imprimirLista(head);

        System.out.println("\nEstado completo:");
        imprimirEstadoCompleto(head);

        System.out.println("\n==========================================");
        System.out.println("6. INSERTAR Aa AL INICIO");
        System.out.println("==========================================");

        head = new Nodo<>("Aa", head);

        System.out.println("Lista despues de insertar Aa:");
        imprimirLista(head);

        System.out.println("\nEstado final completo:");
        imprimirEstadoCompleto(head);
    }
}