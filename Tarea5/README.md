# Tarea 5 - Lista Enlazada

## Descripción

Programa en Java que implementa y manipula una lista enlazada utilizando nodos genéricos.

La lista inicial se construye manualmente mediante referencias entre objetos `Nodo`.

## Lista inicial

AI -> B -> C -> De -> Mc -> Zi -> null

## Operaciones realizadas

1. Se construye manualmente la lista enlazada inicial.
2. Se muestra el estado completo de la lista.
3. Se obtiene el dato del primer nodo.
4. Se obtiene el estado completo del último nodo.
5. Se inserta el nodo `Fe` entre `De` y `Mc`.
6. Se inserta el nodo `Zz` al final.
7. Se inserta el nodo `Aa` al inicio.

## Inserción de Fe

El método para insertar en medio de dos elementos fue diferente, pues usar la variable `tmp` no fue necesario. Se aprovechó el constructor de `Nodo` que recibe el dato y la referencia al siguiente nodo:

Nodo<String> nuevo = new Nodo<>("Fe", actual.getSiguiente());
actual.setSiguiente(nuevo);

De esta forma, `Fe` queda entre `De` y `Mc` manteniendo las referencias de la lista.

## Inserción de Zz

Para agregar `Zz` al final, se recorre la lista hasta encontrar el último nodo y se establece su siguiente:

actual.setSiguiente(new Nodo<>("Zz"));

La lista queda:

AI -> B -> C -> De -> Fe -> Mc -> Zi -> Zz -> null

## Inserción de Aa

Para agregar `Aa` al inicio, se crea un nuevo nodo cuyo siguiente es el `head` actual:

head = new Nodo<>("Aa", head);

La lista final queda:

Aa -> AI -> B -> C -> De -> Fe -> Mc -> Zi -> Zz -> null

## Archivos

- `Nodo.java`: Implementación del nodo genérico.
- `Main.java`: Programa principal y pruebas de las operaciones realizadas.

## Tecnologías

- Java
- IntelliJ IDEA