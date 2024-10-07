---
title: Interfaz por Línea de Comandos
parent: Instrucciones de uso standalone
has_children: false
---

# Interfaz por Línea de Comandos

La herramienta cuenta con una interfaz de línea de comandos (command line interface -- CLI) que permite procesar los archivos en forma directa, uno por uno.

La estructura para ejecutar la aplicación es:

```
java -jar simae.jar [<inputFile>] [<opciones>]
```

Donde \<_inputFile_\> es el archivo de entrada, y las opciones disponibles son:

|Opción | Nombre completo |Parámetro|Descripción|
|---|---|---|---|
|  -o | \-\-out | \<outputFile\> |  Archivo de salida (por defecto es \<inputFile\>) |
|  -l | \-\-locale | \<locale\>  |  Idioma utilizado para el marcado y mensajes |
|  -u | \-\-untag |           |  Quitar las marcas de SIMAE de \<inputFile\> |
|  -s | \-\-sound  |          |  Reproduce un sonido para indicar el resultado del proceso (solo CLI) |
|  -g | \-\-gui   |           |  Muestra la interfaz gráfica |
|  -h | \-\-help   |          |  Muestra la descripción de uso |
|  -v | \-\-version |         |  Versión de SIMAE |

Lenguajes de programación disponibles (parámetro \<_language_\>):

|Lenguaje|Identificador a utilizar en CLI|
|---|---|
|C++|c++|
|Java|java|
|Python (versión 3.x)|python3|

> **Nota:** El lenguaje de programación se determina automáticamente en base a la extensión del archivo. Este parámetro se utiliza para forzar un determinado lenguaje.

Idiomas disponibles (parámetro \<_locale_\>):

|Idioma|Identificador a utilizar en CLI|
|---|---|
|Inglés|en|
|Español|es|

> **Nota:** Si no es indicado, la localización se obtiene de la configuración del sistema operativo.


## Ejemplos

* Marcar el archivo `test.cpp` en español:
  ```
  java -jar simae.jar test.cpp --locale=es
  ```

* Marcar el archivo `test.java` en inglés con salida en el archivo `testOutput.java`:
  ```
  java -jar simae.jar test.java -o testOutput.java -l en
  ```

* Desmarcar el archivo `test.py` emitiendo un sonido que indica éxito o falla de la operación:
  ```
  java -jar simae.jar test.py -su
  ```

