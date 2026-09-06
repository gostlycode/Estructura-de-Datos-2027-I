# Tarea 3.B — Juego de la Vida

## Descripción

Programa que implementa el **Juego de la Vida de Conway** utilizando un **Array2D ADT** para almacenar el estado del tablero.

La configuración inicial se obtiene desde un archivo CSV y el programa permite ejecutar un número determinado de generaciones, mostrando la evolución del tablero en consola.

## Estructura

```text
TareaB/
├── src/
│   ├── Main.java
│   ├── ArrayADT.java
│   ├── JuegoVida.java
│   └── ArchivoCSV.java
├── data/
│   └── poblacion.csv
└── out/
```

## Funcionamiento

* `ArrayADT.java`: implementa la estructura de datos bidimensional utilizada para almacenar el tablero.
* `ArchivoCSV.java`: lee la configuración inicial desde `poblacion.csv` y la almacena en un `ArrayADT<Character>`.
* `JuegoVida.java`: contiene las reglas del Juego de la Vida y calcula cada nueva generación.
* `Main.java`: solicita el número de generaciones, controla la ejecución y muestra el tablero en consola.
* `poblacion.csv`: contiene la población inicial utilizando `0` para células muertas y `1` para células vivas.

## Reglas implementadas

Para cada célula se cuentan sus ocho posibles vecinos:

1. Una célula viva sobrevive si tiene 2 o 3 vecinos vivos.
2. Una célula viva muere si tiene menos de 2 vecinos vivos.
3. Una célula viva muere si tiene más de 3 vecinos vivos.
4. Una célula muerta nace si tiene exactamente 3 vecinos vivos.

Cada generación se calcula en un nuevo `ArrayADT` y posteriormente se convierte en el tablero actual.

## Ejecución

Desde la carpeta `TareaB`:

```powershell
javac -d out src\*.java
java -cp out Main
```

El programa solicita el número de generaciones a ejecutar y muestra cada generación con un intervalo de 2 segundos.

## Características

* Tablero mínimo de `10 × 10`.
* El tamaño del tablero se obtiene directamente del archivo CSV.
* La población inicial se carga desde un archivo externo.
* Utiliza un ADT bidimensional para representar el tablero.
* Implementa las cuatro reglas del Juego de la Vida.
* Muestra la evolución de las generaciones mediante una animación en consola.
