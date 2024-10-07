---
title: Instalación
has_children: false
nav_order: 3
---
# Instalación

**SIMAE** está desarrollado en [Java](https://www.java.com/) y es compatible con **_Windows_**, **_macOS_** y **_Linux_**, entre otras plataformas. 

##### Contenidos
- [Plugin de Visual Studio Code](#plugin-vscode)
- [Instalación directa (Windows)](#instalacion-directa)
- [Instalación estándar (Windows, Linux, macOS) ](#instalacion-estandar)
- [Integración en ZinjaI IDE](#integracion-en-zinjaI)
- [Integración en Notepad++](#integracion-en-notepad)

## Plugin para Visual Studio Code <a name="plugin-vscode"/>

A partir de la versión 1.0 de SIMAE, el uso recomendado de la herramienta es mediante el plugin para el editor de código Visual Studio Code. Este plugin contiene características adicionales como:

* Utilizar atajos de teclados para moverse entre marcas y leer la marca actual.
* Utilizar hints en lugar de comentarios quitando la necesidad de editar el código para utilizar SIMAE.
* Modificar desde vs-code el lenguaje de las marcas que se utilizará.

Este plugin demostró ser muy práctico a la hora de utilizar SIMAE, y está en contínuo desarrollo para lograr una versión estable y convertirse en el modo de uso recomendado para los usuarios de Visual Studio Code.

La extensión se encuentra disponible para su descarga directa desde el [marketplace](https://marketplace.visualstudio.com/items?itemName=tiflo-sf.simae) de vscode y puede instalarse mediante dos simples pasos:

1. Presionar control + p en Visual Studio Code.
2. Ejecutar el mandato "ext install tiflo-sf.simae".

En caso de preferir utilizar otro IDE, SIMAE cuenta con una versión llamada "SIMAE standalone" que permite su integración en forma de macro con otros IDE, o la utilización manual de la aplicación mediante una Interfaz Gráfica de Usuario, o una Interfaz de Línea de Comandos. A continuación se muestran las diferentes alternativas de instalación manual para usuarios que no deseen utilizar el plugin de Visual Studio Code.

## Alternativa 1. Instalación directa (Windows) <a name ="instalacion-directa"/>

Para instalar **SIMAE standalone** en un paso descargue y ejecute el instalador para Windows siguiente:

|Release|Enlace directo|Fecha de publicación|
|---|---|---|
|1.0.0| [simae-instalador.exe](https://github.com/tiflo-sf/simae/releases/download/v1.0.0/simae-instalador.exe)| 2024\-10\-07|

Este instalador configura un runtime personalizado de Java y la aplicación **SIMAE** como archivo `simae.exe` con acceso directo desde el escritorio (opcional).


## Alternativa 2. Instalación estándar (Windows, Linux, macOS) <a name ="instalacion-estandar"/>

### Paso 1. Instalación de Runtime Java

**SIMAE standalone** es una aplicación **Java**, y requiere instalar previamente el **Java Development Kit (JDK)** versión 11 o posterior.

> Para instalar Java, las principales versiones disponibles son:
> - [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
> - [OpenJDK](http://jdk.java.net/archive/)


Verifique que Java está funcionando en su sistema, para ello abra una consola que le permita escribir comandos y escriba:

```bash
java --version
```

Si le muestra un número de versión 11 o superior, su instalación está lista para el siguiente paso.

### Paso 2. Descarga de SIMAE

Descargar la última versión de la herramienta desde el apartado [Releases](https://github.com/tiflo-sf/simae/releases) del repositorio.

|Release|Enlace directo|Fecha de publicación|
|---|---|---|
|1.0.0| [simae-0.1.0.zip](https://github.com/tiflo-sf/simae/releases/download/v1.0.0/simae.zip)| 2024\-10\-07|

Crear una carpeta para la instalación de SIMAE. La carpeta se denominará `C:\simae\` 

> **Nota:** Estas instrucciones utilizan la convención _MS Windows_ para rutas de archivos.  Si tiene _macOS_ o _GNU/Linux_ utilice la que corresponde a su sistema operativo.

Descomprimir el archivo `.zip` en la carpeta `C:\simae\`

Renombrar el archivo `.jar` que se encuentra en `C:\simae\` a `C:\simae\simae.jar`.


## Integración en ZinjaI (opcional) <a name="integracion-en-zinjaI"/>

El entorno integrado de desarrollo [ZinjaI](http://zinjai.sourceforge.net/) está orientado a estudiantes de programación que están tomando un curso inicial en la temática. A continuación se incluyen instrucciones para integrar la herramienta **SIMAE** en el [IDE ZinjaI](http://zinjai.sourceforge.net/).

Básicamente, se configura una macro de manera que el código fuente en el archivo activo se marque automáticamente con una combinación de teclas o _hotkey_.

### En ZinjaI:

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
Comando: java -jar c:\simae\simae.jar "${CURRENT_SOURCE}" -sl=es
Directorio de trabajo: vacio (no escribir nada)
Acción antes de ejecutar: Guardar el fuente actual
Ejecución asíncrona: NO
Salida (std y err): Ocultas
Acción luego de ejecutar: Recargar fuente actual
Mostrar en la barra de herramientas: SI
````

Finalmente, la macro se encuentra agregada al IDE.

![Pestaña de ZinjaI con macros añadidas](https://user-images.githubusercontent.com/42981462/175972523-b1d526d3-3f07-47a2-89b4-4497816c8647.png)

## Integración en Notepad++ (opcional) <a name="integracion-en-notepad"/>

Una vez finalizada la instalación de la herramienta, se puede agregar las macros para ejecutar SIMAE desde Notepad++.

### Marcado

1. Abrir Notepad++
2. Presionar F5
3.Pegar en el campo de texto "Programa a ejecutar" la siguiente instrucción:

```
C:\simae\1.0.0\simae.exe $(FULL_CURRENT_PATH) -s  
```

4. Presionar el botón Guardar…
5. En el campo de texto 'Nombre' colocar SIMAE MARCADO
6. Seleccionar las opciones CTRL y ALT
7. En la lista desplegable elegir la opción M
8. Presionar el botón OK

### Desmarcado

9. Abrir Notepad++
10. Presionar F5
11. Pegar en el campo de texto 'Programa a ejecutar' la siguiente instrucción:

```
C:\simae\1.0.0\simae.exe $(FULL_CURRENT_PATH) -u
```

12. Presionar el botón Guardar…
13. En el campo de texto 'Nombre' colocar SIMAE DESMARCADO
14. Seleccionar las opciones SHIFT y ALT
15. En la lista desplegable elegir la opción B
16. Presionar el botón OK
