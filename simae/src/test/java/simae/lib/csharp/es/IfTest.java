package simae.lib.csharp.es;

import org.junit.jupiter.api.Test;
import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IfTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void ifTest() throws IOException {
		  prog = "using System;\n" +
				  "\n" +
				  "class Program\n" +
				  "{\n" +
				  "    static void Main()\n" +
				  "    {\n" +
				  "        int edad = 18;\n" +
				  "\n" +
				  "        if (edad >= 18)\n" +
				  "        {\n" +
				  "            Console.WriteLine(\"Sos mayor de edad.\");\n" +
				  "        }\n" +
				  "\n" +
				  "        Console.WriteLine(\"Fin del programa.\");\n" +
				  "    }\n" +
				  "}\n";
		  esperado = "using System;\n" +
				  "\n" +
				  "class Program/*/CIERRA EN LINEA 16/*/\n" +
				  "{\n" +
				  "    static void Main()/*/CIERRA EN LINEA 15/*/\n" +
				  "    {\n" +
				  "        int edad = 18;\n" +
				  "\n" +
				  "        if (edad >= 18)/*/CIERRA EN LINEA 12/*/\n" +
				  "        {\n" +
				  "            Console.WriteLine(\"Sos mayor de edad.\");\n" +
				  "        }/*/CIERRA if (edad >= 18) DE LINEA 9/*/\n" +
				  "\n" +
				  "        Console.WriteLine(\"Fin del programa.\");\n" +
				  "    }/*/CIERRA Main() DE LINEA 5/*/\n" +
				  "}/*/CIERRA Program DE LINEA 3/*/\n";
		  marcado = simae.launchTagging(prog, Lenguaje.CSHARP, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
