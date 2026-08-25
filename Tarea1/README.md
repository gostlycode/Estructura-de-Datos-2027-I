# Tarea 1 — Programa incicial

## Resolución de los requerimientos

### 1. Lectura del archivo

El programa lee el archivo `datos.csv`, ubicado dentro de la carpeta `data`.

Para realizar la lectura fue necesario investigar el manejo de archivos en Java, ya que este tema no había sido visto durante primero ni segundo semestre. Se utilizó `File` para acceder al archivo y `Scanner` para leerlo línea por línea. Posteriormente, `split(",")` permite separar cada línea en sus respectivas columnas.

### 2. Diferencia de seguidores de Twitter entre enero y junio

Esta métrica se muestra directamente al iniciar el programa, dentro del dashboard principal, debido a que representa una de las métricas principales y permite visualizar rápidamente el crecimiento de seguidores de Twitter entre enero y junio.

El programa obtiene los valores correspondientes a enero y junio del archivo y calcula la diferencia mediante una resta.

### 3. Diferencia de visualizaciones de YouTube

Se agregó una opción en el menú que permite al usuario seleccionar dos meses mediante teclado.

El programa busca las visualizaciones de YouTube correspondientes a ambos meses y calcula la diferencia entre ellos.

### 4. Promedio de crecimiento de Twitter y Facebook

Se agregó una opción en el menú para calcular el promedio de crecimiento entre enero y junio.

El programa obtiene los valores de crecimiento de cada red social, suma los valores correspondientes a los seis meses y los divide entre la cantidad de meses.

### 5. Promedio de "Me gusta"

Se agregó una opción en el menú que calcula el promedio de "Me gusta" de YouTube, Twitter y Facebook.

Para cada red social se obtienen los datos de enero a junio, se suman y se dividen entre seis para obtener el promedio correspondiente.

## Organización

El programa se dividió en tres clases:

- `Main`: inicia el programa.
- `Menu`: controla la interacción con el usuario y muestra los resultados.
- `CsvReader`: se encarga de leer y consultar los datos del archivo CSV.