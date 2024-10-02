package simae.lib.csharp.es;

import org.junit.jupiter.api.Test;
import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WhileTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void whileTest() throws IOException {
		  prog = "using System;\n" +
				  "\n" +
				  "class Program\n" +
				  "{\n" +
				  "    static void Main()\n" +
				  "    {\n" +
				  "        int contador = 1;\n" +
				  "\n" +
				  "        Console.WriteLine(\"¡Bienvenido al bucle del infinito!\");\n" +
				  "\n" +
				  "        while (contador <= 10)\n" +
				  "        {\n" +
				  "            Console.WriteLine(\"Vuelta número \" + contador);\n" +
				  "            contador++;\n" +
				  "        }\n" +
				  "\n" +
				  "        Console.WriteLine(\"¡Listo, salimos del bucle!\");\n" +
				  "    }\n" +
				  "}\n";
		  esperado = "using System;\n" +
				  "\n" +
				  "class Program/*/CIERRA EN LINEA 19/*/\n" +
				  "{\n" +
				  "    static void Main()/*/CIERRA EN LINEA 18/*/\n" +
				  "    {\n" +
				  "        int contador = 1;\n" +
				  "\n" +
				  "        Console.WriteLine(\"¡Bienvenido al bucle del infinito!\");\n" +
				  "\n" +
				  "        while (contador <= 10)/*/CIERRA EN LINEA 15/*/\n" +
				  "        {\n" +
				  "            Console.WriteLine(\"Vuelta número \" + contador);\n" +
				  "            contador++;\n" +
				  "        }/*/CIERRA while (contador <= 10) DE LINEA 11/*/\n" +
				  "\n" +
				  "        Console.WriteLine(\"¡Listo, salimos del bucle!\");\n" +
				  "    }/*/CIERRA Main() DE LINEA 5/*/\n" +
				  "}/*/CIERRA Program DE LINEA 3/*/\n";
		  marcado = simae.launchTagging(prog, Lenguaje.CSHARP, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
