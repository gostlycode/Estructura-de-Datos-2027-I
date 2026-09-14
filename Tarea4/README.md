# Tarea 4 — ConjuntoADT y caso de uso de materias inscritas

## Resolución de los requerimientos

### 1. Completar el ADT de conjuntos

Se partió del `ConjuntoADT<T>` proporcionado en clase, que ya incluía el constructor, `longitud()` y `contieneElemento(elemento)` apoyados en un `ArrayList<T>` interno.

A partir de ahí se completaron las operaciones faltantes del ADT:

* `agregarElemento(elemento)`: agrega un elemento solo si `contieneElemento` regresa `false`, evitando así duplicados dentro del conjunto.
* `eliminarElemento(elemento)`: quita el elemento del `ArrayList` si existe.
* `equals(otroConjunto)`: compara que ambos conjuntos tengan la misma longitud y que uno sea subconjunto del otro, sin importar el orden de los elementos.
* `esSubConjunto(otroConjunto)`: recorre los elementos propios y verifica que todos existan en `otroConjunto`.
* `union(otroConjunto)`, `interseccion(otroConjunto)` y `diferencia(otroConjunto)`: cada una construye y regresa un `ConjuntoADT<T>` nuevo, usando siempre `agregarElemento` para garantizar que el resultado tampoco contenga duplicados.

### 2. Caso de uso elegido

De las diapositivas anexas se eligió el caso **"Materias inscritas por dos alumnos"**:

```
Ana  = {ED, BD, Redes, IA}
Luis = {ED, Redes, SO}
```

Cada alumno se representa con un `ConjuntoADT<String>` independiente, donde cada elemento es la clave de una materia.

### 3. Operaciones de conjuntos sobre las materias

El programa aplica las operaciones del ADT sobre los conjuntos de Ana y Luis para responder preguntas típicas de este escenario:

* **Unión**: todas las materias que cursa Ana o Luis (o ambos).
* **Intersección**: las materias que ambos tienen en común.
* **Diferencia**: las materias que solo cursa Ana (Ana − Luis) y las que solo cursa Luis (Luis − Ana).
* **esSubConjunto**: por ejemplo, se valida si el conjunto `{Redes}` está contenido en las materias de Ana y en las de Luis.
* **equals**: se compara el conjunto de Ana contra una copia armada en distinto orden (para comprobar que el orden no afecta la igualdad) y contra el conjunto de Luis.

### 4. Pruebas en `Main`

`Main` construye los conjuntos de Ana y Luis con `agregarElemento` y ejecuta, en orden, pruebas de:

* `longitud()` de cada conjunto.
* `contieneElemento(elemento)` para verificar materias específicas.
* `agregarElemento` intentando insertar una materia repetida, confirmando que la longitud no cambia.
* `eliminarElemento` sobre una copia de prueba.
* `equals` y `esSubConjunto` con distintos conjuntos.
* `union`, `interseccion` y `diferencia` entre Ana y Luis.

Cada prueba imprime en consola el resultado obtenido, de manera que se puede verificar visualmente que cada operación del ADT funciona como se espera.

## Organización

El programa se dividió en dos clases:

* `Main`: construye los conjuntos de Ana y Luis y ejecuta las pruebas de todas las operaciones del ADT.
* `ConjuntoADT<T>`: implementa el tipo abstracto de datos Conjunto sobre un `ArrayList<T>`, incluyendo `longitud`, `contieneElemento`, `agregarElemento`, `eliminarElemento`, `equals`, `esSubConjunto`, `union`, `interseccion` y `diferencia`.

## Compilación y ejecución

Desde la raíz del proyecto (donde está la carpeta `src`):

```powershell
javac -d out src\ConjuntoADT.java src\Main.java
java -cp out Main
```