package simae.lib.csharp.es;

import org.junit.jupiter.api.Test;
import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoWhileTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void doWhileTest() throws IOException {
		  prog = "using System;\n" +
				  "\n" +
				  "class Program\n" +
				  "{\n" +
				  "    static void Main()\n" +
				  "    {\n" +
				  "        int contador = 0;\n" +
				  "\n" +
				  "        do\n" +
				  "        {\n" +
				  "            Console.WriteLine(\"Este es el ciclo número \" + (contador + 1));\n" +
				  "            contador++;\n" +
				  "        } while (contador < 5);\n" +
				  "    }\n" +
				  "}\n";

		esperado = "using System;\n" +
				"\n" +
				"class Program/*/CIERRA EN LINEA 15/*/\n" +
				"{\n" +
				"    static void Main()/*/CIERRA EN LINEA 14/*/\n" +
				"    {\n" +
				"        int contador = 0;\n" +
				"\n" +
				"        do/*/CIERRA EN LINEA 13/*/\n" +
				"        {\n" +
				"            Console.WriteLine(\"Este es el ciclo número \" + (contador + 1));\n" +
				"            contador++;\n" +
				"        } while (contador < 5);/*/CIERRA DO DE LINEA 9/*/\n" +
				"    }/*/CIERRA Main() DE LINEA 5/*/\n" +
				"}/*/CIERRA Program DE LINEA 3/*/\n";
		  marcado = simae.launchTagging(prog, Lenguaje.CSHARP, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
