---
title: Plugin
parent: Funcionamiento
has_children: false
---

# Funcionamiento como plugin

El uso de la aplicación standalone está actualmente deprecado para el IDE Visual Studio Code debido a que el uso mediante plugin permite la obtención de marcas sin la modificación del código fuente, y algunas funcionalidades extra que se detallan en las secciones correspondientes a cada IDE:

## Visual Studio Code

El plugin cuenta con distintas funcionalidades:

**Leer marca**

Al presionar la combinación de teclas Ctrl+Alt+S en una línea determinada, se muestra una hint reproducible por el lector de pantalla que contiene información de contexto con la marca correspondiente a la línea en la que el usuario presiona la combinación de teclas.

**Avanzar a la marca siguiente**

Al presionar la combinación de teclas Ctrl+Alt+→, el cursor avanzará a la próxima línea donde exista una marca.

**Retroceder a la marca anterior**

De manera análoga a la función anterior, para retroceder a la marca anterior se debe presionar la combinación de teclas Ctrl+Alt+←. Esto hará que el cursor regrese a la línea anterior donde haya una marca.

**Configuración del plugin**

La extensión ofrece soporte para el uso de la herramienta de marcado en distintos idiomas. Esto permite a los usuarios seleccionar el idioma en el que desea que se presenten los mensajes de información contextual. Al presionar la combinación Ctrl+Alt+C, se abrirá la ventana de configuración de SIMAE en Visual Studio Code. Desde allí, se permite personalizar el idioma (español o inglés) utilizando un menú desplegable.

A modo de resumen, se presentan los atajos de teclado correspondientes a las funcionalidades descritas:

|Combinación de teclas|Funcionalidad|
|---|---|
|Ctrl+Alt+S|Leer marca en línea actual.|
|Ctrl+Alt+→|Mover cursor a marca siguiente y reproducirla.|
|Ctrl+Alt+←|Mover cursor a marca anterior y reproducirla.|
|Ctrl+Alt+C|Abrir configuración de SIMAE.|
