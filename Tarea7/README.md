# Tarea 7 — Estructura de datos de diapositivas

## Resolución de los requerimientos

### 1. Completar el ADT de lista ligada

Se partió de `ListaLigadaADT<T>` proporcionado en clase, que ya incluía el constructor, `agregar`, `transversal`, `actualizar`, `getTamanio` y `agregarDespuesDe`, apoyados en una cadena de objetos `Nodo<T>` a partir de `head`.

A partir de ahí se completaron las operaciones faltantes del ADT:

* `estaVacia()`: regresa `true` si `head` es `null`.
* `agregarAlFinal(valor)`: recorre la lista desde `head` hasta el último nodo y enlaza el nuevo. `agregar(valor)` simplemente lo invoca.
* `agregarAlInicio(valor)`: crea un nodo que apunta al `head` actual y lo convierte en el nuevo `head`.
* `eliminarElPrimero()`: mueve `head` al siguiente nodo y regresa el dato eliminado (`null` si la lista está vacía).
* `eliminarElFinal()`: se detiene en el penúltimo nodo y corta el enlace; también contempla el caso de un solo elemento.
* `buscar(valor)`: regresa la posición (empezando en 0) de la primera coincidencia, o `-1` si no existe.

Además, `actualizar` y `agregarDespuesDe` se corrigieron para que regresen `boolean` en lugar de fallar con `NullPointerException` cuando el valor buscado no está en la lista.

### 2. Clase distinta de `String`

En lugar de almacenar `String`, la lista se probó con la clase `Perro` (`nombre`, `raza`, `edad`). Para que `buscar`, `actualizar` y `agregarDespuesDe` comparen por contenido y no por referencia, `Perro` sobrescribe `equals()`, `hashCode()` y `toString()`.

### 3. Pruebas en `DemoListaLigada`

`DemoListaLigada` ejecuta, en orden, pruebas de:

* `estaVacia()` y `transversal()` sobre una lista vacía.
* `agregar` / `agregarAlFinal` con tres perros, y `getTamanio()`.
* `agregarAlInicio` y `agregarDespuesDe`.
* `buscar` de un perro existente y de uno inexistente (`-1`).
* `actualizar` sobre la primera coincidencia.
* `eliminarElPrimero` y `eliminarElFinal`, hasta dejar la lista vacía y probar la eliminación sobre una lista sin elementos.

Cada prueba imprime en consola el resultado obtenido, de manera que se puede verificar visualmente que cada operación funciona como se espera.

## Organización

El programa se dividió en cinco clases:

* `Nodo<T>`: guarda un dato y la referencia al nodo siguiente.
* `ListaLigadaADT<T>`: implementa la lista ligada simple con todas las operaciones del ADT.
* `Perro`: clase de prueba que se almacena en la lista.
* `DemoListaLigada`: ejecuta las pruebas de todas las operaciones de la lista.
* `DemoNodo`: demo de clase que enlaza nodos a mano, sin usar la lista.

## Compilación y ejecución

Desde la raíz del proyecto (donde está la carpeta `src`):

```powershell
javac -encoding UTF-8 -d out src\*.java
java -cp out DemoListaLigada
```
