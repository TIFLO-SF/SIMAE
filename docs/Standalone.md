---
title: Aplicación standalone
parent: Funcionamiento
has_children: false
---

# Funcionamiento como aplicación standalone

La aplicación de SIMAE permite el marcado de archivos mediante la modificación de su código fuente. A continuación se detalla un ejemplo de funcionamiento:

Dado el siguiente archivo de entrada, denominado `Nodo.java`

```text
   1  import java.util.List;
   2  import java.util.ArrayList;
   3  
   4  public class Nodo {
   5      private String etiqueta;
   6      private List<Nodo> vecinos = new ArrayList<Nodo>();
   7      private boolean visitado = false;
   8  
   9      void dfs() {
  10          visitado = true;
  11          for (Nodo vecino : vecinos) {
  12              if (!vecino.visitado) {
  13                  vecino.dfs();
  14              }
  15          }
  16      }
  17  }
```

Este código fuente presenta diferentes estructuras gramaticales en forma anidada, en particular contiene: 
- definición de la clase `Nodo`
  * definición del método `dfs()`
    - estructura de control iterativa `for`
      * estructura de control condicional `if`

Al procesar el archivo `Nodo.java` con **SIMAE**, el código del archivo es modificado agregando marcas en forma de comentarios que describen su estructura.

La salida obtenida para el archivo anterior será:

```java
import java.util.List;
import java.util.ArrayList;

public class Nodo /*/CIERRA EN LINEA 17/*/{
    private String etiqueta;
    private List<Nodo> vecinos = new ArrayList<Nodo>();
    private boolean visitado = false;

    void dfs()/*/CIERRA EN LINEA 16/*/ {
        visitado = true;
        for (Nodo vecino : vecinos)/*/CIERRA EN LINEA 15/*/ {
            if (!vecino.visitado)/*/CIERRA EN LINEA 14/*/ {
                vecino.dfs();
            }/*/CIERRA if (!vecino.visitado) DE LINEA 12/*/
        }/*/CIERRA for (Nodo vecino : vecinos) DE LINEA 11/*/
    }/*/CIERRA void dfs() DE LINEA 9/*/
}/*/CIERRA class Nodo DE LINEA 4/*/
```

Como puede leerse, las marcas insertadas describen las líneas de apertura y cierre de las diferentes estructuras gramaticales.

Para leer las marcas en el código, el programador ciego utiliza un editor de texto (ya sea genérico o como parte de un IDE) junto con el lector de pantalla que emplea habitualmente. El lector de pantalla traduce a voz el contenido de la línea de código activa, indicando su número de línea, de manera que las marcas brindan información contextual asistiendo al programador para posicionarse, navegar e interpretar el código. 

> **Importante:** En el lenguaje Java, el delimitador utilizado para indicar el inicio y fin de las marcas es `/*/`. Para utilizar **SIMAE** el programador debe evitar rigurosamente incluir comentarios delimitados con `/*/`, ya que los mismos (si existieran) serán eliminados automáticamente por la herramienta antes de marcar el archivo de entrada. 