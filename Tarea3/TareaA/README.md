# Tarea 3.A — Tablero de Ajedrez

## Resolución de los requerimientos

### 1. Creación del tablero

El programa utiliza un `ArrayADT` para representar un arreglo bidimensional de **8 × 8**, correspondiente a las dimensiones de un tablero de ajedrez.

El tablero se declara como un `ArrayADT<Character>`, permitiendo almacenar en cada posición el carácter correspondiente a una pieza o una casilla vacía.

### 2. Inicialización de las piezas

La clase `TableroAjedrez` se encarga de inicializar el tablero con la posición inicial de una partida de ajedrez.

Las piezas se almacenan utilizando sus respectivos **caracteres Unicode**, incluyendo torres, caballos, alfiles, dama, rey y peones de ambos colores.

Las cuatro filas centrales del tablero se rellenan con espacios, representando las casillas que se encuentran vacías al inicio de la partida.

### 3. Impresión del tablero

El programa recorre el `ArrayADT` mediante dos ciclos para mostrar cada una de sus posiciones.

El tablero se imprime en la consola utilizando los números del `8` al `1` para identificar las filas y las letras de la `A` a la `H` para identificar las columnas.

La salida representa únicamente el **estado inicial del tablero**, tal como solicita el problema.

### 4. Tablero no funcional

El programa no implementa movimientos de piezas, turnos, capturas ni reglas de ajedrez.

Su única función es almacenar las piezas dentro del `ArrayADT` y mostrar el tablero inicial en la consola.

## Organización

El programa se dividió en tres clases:

* `Main`: inicia el programa, crea el objeto `TableroAjedrez` y solicita la impresión del tablero.

* `TableroAjedrez`: administra el tablero de 8 × 8, inicializa las piezas utilizando caracteres Unicode y muestra el tablero en consola.

* `ArrayADT`: proporciona la estructura de arreglo bidimensional utilizada para almacenar los elementos del tablero.
