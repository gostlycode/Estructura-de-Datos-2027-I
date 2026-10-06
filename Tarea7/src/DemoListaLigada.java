
public class DemoListaLigada {
    public static void main(String[] args) {
        ListaLigadaADT<Perro> lista = new ListaLigadaADT<>();

        Perro firulais = new Perro("Firulais", "Labrador", 5);
        Perro rex = new Perro("Rex", "Pastor Aleman", 3);
        Perro chispa = new Perro("Chispa", "Chihuahua", 2);

        System.out.println("¿Vacía? " + lista.estaVacia());
        lista.transversal();

        System.out.println("\n--- agregar / agregarAlFinal ---");
        lista.agregar(firulais);
        lista.agregarAlFinal(rex);
        lista.agregarAlFinal(chispa);
        lista.transversal();
        System.out.println("¿Vacía? " + lista.estaVacia() + " | Tamaño: " + lista.getTamanio());

        System.out.println("\n--- agregarAlInicio ---");
        lista.agregarAlInicio(new Perro("Pelusa", "Poodle", 7));
        lista.transversal();

        System.out.println("\n--- agregarDespuesDe (Rex) ---");
        lista.agregarDespuesDe(rex, new Perro("Toby", "Beagle", 4));
        lista.transversal();

        System.out.println("\n--- buscar ---");
        System.out.println("Posición de Rex: " + lista.buscar(rex));
        System.out.println("Posición de un perro inexistente: "
                + lista.buscar(new Perro("Fido", "Husky", 1)));

        System.out.println("\n--- actualizar (Chispa -> Luna) ---");
        lista.actualizar(chispa, new Perro("Luna", "Golden Retriever", 1));
        lista.transversal();

        System.out.println("\n--- eliminarElPrimero ---");
        System.out.println("Eliminado: " + lista.eliminarElPrimero());
        lista.transversal();

        System.out.println("\n--- eliminarElFinal ---");
        System.out.println("Eliminado: " + lista.eliminarElFinal());
        lista.transversal();
        System.out.println("Tamaño: " + lista.getTamanio());

        System.out.println("\n--- vaciar la lista ---");
        while (!lista.estaVacia()) {
            System.out.println("Eliminado: " + lista.eliminarElFinal());
        }
        lista.transversal();
        System.out.println("Eliminar de lista vacía: " + lista.eliminarElPrimero());
    }
}
