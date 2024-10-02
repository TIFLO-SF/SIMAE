package simae.lib.csharp.es;

import org.junit.jupiter.api.Test;
import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ForTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void forTest() throws IOException {
		  prog = "using System;\n" +
				  "\n" +
				  "class Program\n" +
				  "{\n" +
				  "    static void Main()\n" +
				  "    {\n" +
				  "        for (int i = 1; i <= 5; i++)\n" +
				  "        {\n" +
				  "            Console.WriteLine(\"Este es el ciclo número \" + i);\n" +
				  "        }\n" +
				  "    }\n" +
				  "}\n";
		  esperado = "using System;\n" +
				  "\n" +
				  "class Program/*/CIERRA EN LINEA 12/*/\n" +
				  "{\n" +
				  "    static void Main()/*/CIERRA EN LINEA 11/*/\n" +
				  "    {\n" +
				  "        for (int i = 1; i <= 5; i++)/*/CIERRA EN LINEA 10/*/\n" +
				  "        {\n" +
				  "            Console.WriteLine(\"Este es el ciclo número \" + i);\n" +
				  "        }/*/CIERRA for (int i = 1; i <= 5; i++) DE LINEA 7/*/\n" +
				  "    }/*/CIERRA Main() DE LINEA 5/*/\n" +
				  "}/*/CIERRA Program DE LINEA 3/*/\n";
		  marcado = simae.launchTagging(prog, Lenguaje.CSHARP, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
