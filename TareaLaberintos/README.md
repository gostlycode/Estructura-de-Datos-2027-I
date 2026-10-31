# Tarea — Solución de laberintos con backtracking y pilas

## Resolución de los requerimientos

### 1. Backtracking con una pila

El laberinto se resuelve con la técnica de **backtracking** apoyada en una pila (`Stack<Celda>`) que guarda el camino actual desde la entrada. El algoritmo vive en `Solucionador` y avanza **de a un paso** con `paso()`:

* **Avanzar (`push`)**: desde la celda en la cima de la pila prueba la siguiente dirección pendiente; si la celda vecina está dentro del tablero, no es muro y no ha sido visitada, se marca como visitada y se apila.
* **Retroceder (`pop`)**: cuando la celda de la cima ya probó sus 4 direcciones sin éxito, se desapila y se marca como descartada. Así se regresa a la celda anterior para intentar otra ruta.
* **Terminar**: si la celda apilada es la salida (`S`), el contenido de la pila es el camino solución. Si la pila se vacía, el laberinto no tiene solución.

Para saber por dónde seguir al retroceder, cada elemento de la pila es una `Celda` que guarda su `fila`, su `col` y la `direccion` (índice dentro del orden de direcciones) que toca probar a continuación.

### 2. Estructuras de datos adicionales

Además de la pila, el programa usa arreglos bidimensionales:

* `char[][] tablero` (en `Laberinto`): el mapa, con muros, caminos, entrada y salida.
* `boolean[][] visitado`: celdas ya exploradas, para no repetirlas.
* `boolean[][] enPila`: celdas que forman el camino actual.
* `boolean[][] descartado`: celdas de las que se retrocedió.

### 3. Visualización paso a paso

`VentanaLaberinto` dibuja el avance del algoritmo en una ventana
Los controles son **Pausar / Reanudar**, **Paso** (avanza una sola iteración), **Reiniciar** y un deslizador de **velocidad**.

### 4. Comparación de tres estrategias

El orden en que se prueban las direcciones cambia cuánto trabajo hace el algoritmo. Por eso la misma ventana muestra **tres tableros a la vez**, uno por estrategia, que avanzan en paralelo una iteración por tick:

| Estrategia | Orden de direcciones |
|---|---|
| Izquierda primero | izquierda -> arriba -> derecha -> abajo |
| Arriba primero | arriba -> derecha -> abajo -> izquierda |
| Derecha primero | derecha -> abajo -> izquierda -> arriba |

* Los botones y la velocidad son compartidos por los tres tableros.
* Cuando una estrategia termina, su tablero muestra **"TERMINÓ"** con sus pasos e iteraciones (o "sin solución"), y su camino se imprime en la consola.
* Al terminar las tres, se marca con borde dorado y **"★ MEJOR ★"** la mejor optimizada. El criterio es el **menor número de iteraciones**; a igualdad de iteraciones, gana el camino más corto.

## Formato del archivo `.lab`

```
10          <- número de filas
10          <- número de columnas
1,1,1,...   <- una línea por fila, celdas separadas por coma
```

Cada celda es `1` (muro), `0` (camino libre), `E` (entrada) o `S` (salida). El programa valida que existan filas y columnas completas, y que haya una entrada y una salida.

## Organización

El programa se dividió en cinco clases, de modo que la lógica no depende de lo visual:

* `Celda`: elemento de la pila (fila, columna y dirección que toca probar).
* `Laberinto`: el mapa (array 2D), las posiciones de entrada y salida, y la lectura del archivo `.lab`.
* `Solucionador`: el backtracking con pila, con orden de direcciones configurable. No sabe nada de ventanas.
* `VentanaLaberinto`: toda la parte visual (tableros, leyenda, controles, animación y ganador).
* `TareaLaberintos`: el `main`; carga el laberinto, crea las tres estrategias y abre la ventana.

## Compilación y ejecución

Desde la raíz del proyecto (donde está la carpeta `src`):

```powershell
javac -encoding UTF-8 -d out src\*.java
java -cp out TareaLaberintos entrada.lab
```

Si no se indica el archivo, el programa busca `entrada.lab` en la carpeta actual o en `src`; si no lo encuentra, abre un selector de archivos.