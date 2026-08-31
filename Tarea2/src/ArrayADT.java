public class ArrayADT <T> {
    private int longitud;
    private Object[] datos;

    public ArrayADT(int longitud) {
        this.longitud = longitud;
        this.datos = new Object[longitud];
    }

    public T obtenerElemento(int index){

        if (index >= 0 && index < longitud) {
            return (T) datos[index];
        } else {
            throw new IndexOutOfBoundsException("Index " + index + " Fuera de rango " + longitud);
        }

    
    }
    public void insertarElemento(int index, T elemento){
        if (index >= 0 && index < longitud) {
            datos[index] = elemento;
        } else {
            throw new IndexOutOfBoundsException("Index " + index + " Fuera de rango " + longitud);
        }


    }
    
    public int obtenerLongitud() {
        return longitud;
    }

    public void rellenar (T elemento) {
        for (int i = 0; i < longitud; i++) {
            datos[i] = elemento;
        }
    }
}
