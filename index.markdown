---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

layout: default
nav_order: 1
---

# SIMAE
**SI**stema de **MA**rcado **E**structural de Código Fuente

![](https://i.imgur.com/oSRdStq.png)

##### Contenidos
- [Descripción](#descripcion)  
- [Estado de la Herramienta](#Estado-de-la-herramienta)
- [Instalación y uso](#Instalacion-y-uso)
    * [Uso con GUI](#usocongui)
    * [Uso con CLI](#usoconcli)
    * [Integración en ZinjaI](#Integracion-en-ZinjaI)
- [Instrucciones de compilación](#Instrucciones-de-compilacion)
    * [Construcción](#Construccion)
    * [Ejecución](#Ejecucion)
    * [Producción de JAR](#Produccion-de-JAR)
    * [IntelliJ Idea IDE para compilar](#IntelliJ-Idea-IDE-para-compilar)
- [Referencias](#Referencias)

## Descripción <a name ="descripcion"/>

SIMAE es una herramienta de asistencia a la programación para desarrolladores con discapacidad visual, que brinda información contextual mediante el marcado de las estructuras contenidas en el código fuente.

## Estado de la herramienta <a name ="Estado-de-la-herramienta"/>

Una versión alpha está disponible para ser usada dentro de los releases de este repositorio. Se realiza un continuo mantenimiento del software.

To-do:

* Implementar integración continua.
* Traducción a otros lenguajes.
* Mejora de la CLI.
* Re-diseño de módulos de código para facilitar su desarrollo.

## Instalación y uso <a name ="Instalacion-y-uso"/>

### Uso con GUI (Graphical User Interface) <a name="usocongui"/>

1. Descargar la versión más reciente en el apartado de "Releases" del proyecto.
2. Ejecutar el archivo .jar.

### Uso con CLI (Command Line Interface) <a name="usoconcli"/>

1. Descargar la versión más reciente en el apartado de "Releases" del proyecto.
2. Desde una terminal, ejecutar el comando:

```bash=
java -jar simae<version>.jar <nombreDelArchivoDeEntrada> <nombreDelArchivoDeSalida> <lenguaje>
```

|Nombre del lenguaje|Nombre como se debe poner en CLI|
|-------------------|--------------------------------|
|C++|c++|
|Java|java|
|Python (versión 3.x)|python3|

Ejemplo:
java -jar simae-all.jar test.py salidaTest.py python3

### Integración en ZinjaI <a name="Integracion-en-ZinjaI"/>

Desde la materia Algoritmos y Estructuras de Datos se utiliza el IDE Zinjai con las librerías de Windows. Por lo que se añade un instructivo para generar una macro de manera que el código se marque automáticamente con un botón o hotkeys.

#### En ZinjaI:

0) Guardar el jar en un lugar accesible

Se propone como paso cero guardar el .jar generado en el título anterior en `C:\Simae` o `/home/simae` con el nombre `simae.jar` de manera que se pueda acceder fácilmente en la configuración de la macro.

1) Agregar hotkey

Dentro de las opciones desplegables en la parte superior:

```
Archivo->Preferencias...
```

* En las opciones de la izquierda presionar General
* Presionar Personalizar atajos de teclado
* En el buscador escribir "herramienta"
* Hacer click en el botón "..." a la derecha de "Herramientas -> Herramientas Personalizables -> 0
* Presionar las teclas que se quieran usar como hotkey (se recomienda la combinación Control + Shift + A)
* Presionar aceptar y no salir del menú preferencias

2) Agregar al menú las herramientas

* En las opciones de la izquierda presionar Barras de herramientas.
* Activar la opción "Herramientas".
* Presionar modificar, buscar la opción "0:" y activarla si no está activada.
* Aceptar, Aceptar

3) Agregar una "Herramienta personalizable"

Dentro de las opciones desplegables en la parte superior:

```
Herramientas->Herramientas Personalizables->Configurar (generales)...
```

Y para agregar la macro usamos esta configuración:

```
Nombre: simae
Comando: java -jar <DirecciónDeZinjai> "${CURRENT_SOURCE}" "${CURRENT_SOURCE}"
Directorio de trabajo: vacio (no escribir nada)
Acción antes de ejecutar: Guardar el fuente actual
Ejecución asíncrona: NO
Salida (std y err): Ocultas
Acción luego de ejecutar: Recargar fuente actual
Mostrar en la barra de herramientas: SI
````

**IMPORTANTE: reemplazar <DirecciónDeZijaI> por la dirección donde se encuentra el software. En caso de cumplir el paso 0 será `C:/simae/simae.jar` o `/home/simae/simae.jar`**

![Pestaña de ZinjaI con macros añadidas](https://gitlab.com/Patacon/patacon.gitlab.io/-/raw/main/images/simae-macro.png)

Finalmente, la macro se encuentra agregada al IDE.


## Instrucciones de compilación <a name="Instrucciones-de-compilacion"/>

### Construcción <a name="Construccion"/>
0. Instalar dependencias
```shell=
sudo apt install git
sudo apt install openjdk-11-jre-headless
```

1. Clonar proyecto
```shell=
git clone https://github.com/TIFLO-SF/SIMAE
cd SIMAE
```
2. Ejecutar build
```shell=
gradle build
```

### Ejecución <a name="Ejecucion"/>

1. Clonar proyecto
```shell=
git clone https://github.com/TIFLO-SF/SIMAE
cd SIMAE
```
2. Ejecutar run
```shell=
gradle run
```

### Producción de JAR <a name="Produccion-de-JAR"/>

1. Clonar proyecto
```shell=
git clone https://github.com/TIFLO-SF/SIMAE
cd SIMAE
```
2. Ejecutar shadowJar
```shell=
gradle shadowJar
```

*El jar se generará en `simae/build/libs` con el nombre `simae-all`*


### IntelliJ Idea IDE para compilar <a name="IntelliJ-Idea-IDE-para-compilar"/>

1. Dentro de un terminal instalar Intellij Idea 
```shell=
sudo snap install intellij-idea-ultimate --classic
```
2. Activar IntelliJ
3. Clonar repositorio
```shell=
git clone https://github.com/TIFLO-SF/SIMAE
cd SIMAE
```
4. Dentro de IntelliJ ir a Open y seleccionar el proyecto
    ![](https://i.imgur.com/OO64PeR.png)
5. Abrir proyecto como Gradle Project e indicar que se confía en el mismo.
6. Dentro de IntelliJ ir a Add Configuration... y crear una configuración que ejecute el comando "clean run"
    ![](https://i.imgur.com/mw6ECiq.png)
7. Presionando el botón de play o shift+F10 compilar y ejecutar la herramienta

## Referencias <a name="Referencias"/>
* Assenza T., Ballardini C., Marchetti P.A., Golobisky, M.F.. (2021, 13 de Agosto). Herramienta de Soporte para Programadores con Discapacidad Visual mediante el Marcado de Código Fuente. IEEE Xplore. https://ieeexplore.ieee.org/abstract/document/9505334/
