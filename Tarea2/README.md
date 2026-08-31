# Tarea 2 — ArrayADT nómina empleados

## Resolución de los requerimientos

### 1. Creación del ADT de empleados

El programa utiliza el `ArrayADT` proporcionado en clase para crear un nuevo ADT llamado `EmpleadoADT`.

`EmpleadoADT` utiliza un `ArrayADT<Empleado>` para almacenar la información de los trabajadores del archivo `junio.dat`, ubicado dentro de la carpeta `data`.

Cada empleado se representa mediante un objeto de la clase `Empleado`, que contiene su número de trabajador, nombre, apellidos, horas extra, sueldo base y año de ingreso.

### 2. Cálculo del sueldo

El programa calcula el sueldo de cada trabajador tomando en cuenta las reglas de negocio indicadas.

Cada hora extra tiene un valor de `$276.50`, por lo que el pago correspondiente se obtiene multiplicando las horas extra por dicho valor.

También se calcula la prestación por antigüedad, otorgando un `3%` del sueldo base por cada año trabajado.

El sueldo final se obtiene sumando el sueldo base, el pago de horas extra y la prestación por antigüedad.

### 3. Mayor y menor antigüedad

El programa identifica al trabajador con mayor antigüedad y al trabajador con menor antigüedad.

Para obtener la antigüedad se resta el año de ingreso al año actual. Posteriormente, `EmpleadoADT` compara la antigüedad de los trabajadores para determinar cuál tiene más y menos años de servicio.

### 4. Mostrar información de los empleados

Finalmente, el programa recorre todos los empleados almacenados en `EmpleadoADT` y muestra sus datos.

Además de la información original del archivo, se muestra la antigüedad y el sueldo total a pagar en el mes.

## Organización

El programa se dividió en cinco clases:

* `Main`: inicia el programa y coordina las operaciones.
* `Empleado`: representa a un trabajador y realiza los cálculos de sueldo y antigüedad.
* `EmpleadoADT`: administra los empleados utilizando el `ArrayADT`.
* `ArchivoEmpleado`: se encarga de leer el archivo `junio.dat` y crear los objetos `Empleado`.
* `ArrayADT`: proporciona la estructura de arreglo utilizada para almacenar los empleados.
