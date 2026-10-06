
/**
 * ADT Lista Ligada simple (Simple Linked List).
 * Genérica: funciona con cualquier clase (Perro, PolloAsado, String...).
 * Para que buscar/actualizar/agregarDespuesDe funcionen, la clase T debe
 * sobrescribir equals().
 */
public class ListaLigadaADT<T> {
    private Nodo<T> head;

    /** Constructor: crea una lista vacía. */
    public ListaLigadaADT() {
        this.head = null;
    }

    /** @return true si la lista no tiene elementos. */
    public boolean estaVacia() {
        return head == null;
    }

    /** @return número de elementos de la lista. */
    public int getTamanio() {
        int contador = 0;
        Nodo<T> actual = head;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

    /** Agrega un valor al final de la lista. */
    public void agregar(T dato) {
        agregarAlFinal(dato);
    }

    /** Agrega un nodo al final, entrando por head. */
    public void agregarAlFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (head == null) {
            head = nuevo;
        } else {
            Nodo<T> actual = head;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    /** Agrega un valor al inicio de la lista. */
    public void agregarAlInicio(T dato) {
        head = new Nodo<>(dato, head);
    }

    /**
     * Agrega un nodo con 'valor' después del primer nodo que sea igual a 'referencia'.
     * @return true si se encontró la referencia y se agregó el valor.
     */
    public boolean agregarDespuesDe(T referencia, T valor) {
        Nodo<T> actual = buscarNodo(referencia);
        if (actual == null) {
            return false;
        }
        actual.setSiguiente(new Nodo<>(valor, actual.getSiguiente()));
        return true;
    }

    /**
     * Elimina el primer elemento de la lista.
     * @return el dato eliminado, o null si la lista está vacía.
     */
    public T eliminarElPrimero() {
        if (head == null) {
            return null;
        }
        T dato = head.getDato();
        head = head.getSiguiente();
        return dato;
    }

    /**
     * Elimina el último elemento de la lista.
     * @return el dato eliminado, o null si la lista está vacía.
     */
    public T eliminarElFinal() {
        if (head == null) {
            return null;
        }
        if (head.getSiguiente() == null) {      // un solo elemento
            T dato = head.getDato();
            head = null;
            return dato;
        }
        Nodo<T> actual = head;
        while (actual.getSiguiente().getSiguiente() != null) {  // penúltimo
            actual = actual.getSiguiente();
        }
        T dato = actual.getSiguiente().getDato();
        actual.setSiguiente(null);
        return dato;
    }

    /**
     * Busca un valor en la lista.
     * @return posición (0 = primero) de la primera coincidencia, o -1 si no existe.
     */
    public int buscar(T valor) {
        int posicion = 0;
        Nodo<T> actual = head;
        while (actual != null) {
            if (iguales(actual.getDato(), valor)) {
                return posicion;
            }
            posicion++;
            actual = actual.getSiguiente();
        }
        return -1;
    }

    /**
     * Reemplaza la primera coincidencia de 'aBuscar' por 'nuevoValor'.
     * @return true si se actualizó, false si no se encontró.
     */
    public boolean actualizar(T aBuscar, T nuevoValor) {
        Nodo<T> actual = buscarNodo(aBuscar);
        if (actual == null) {
            return false;
        }
        actual.setDato(nuevoValor);
        return true;
    }

    /** Recorrido transversal: muestra todos los elementos. */
    public void transversal() {
        if (head == null) {
            System.out.println("Vacia");
            return;
        }
        Nodo<T> actual = head;
        while (actual != null) {
            System.out.print("|" + actual.getDato());
            actual = actual.getSiguiente();
        }
        System.out.println("|");
    }

    // ---------- auxiliares privados ----------

    private Nodo<T> buscarNodo(T valor) {
        Nodo<T> actual = head;
        while (actual != null) {
            if (iguales(actual.getDato(), valor)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    private boolean iguales(T a, T b) {
        return a == null ? b == null : a.equals(b);
    }
}
