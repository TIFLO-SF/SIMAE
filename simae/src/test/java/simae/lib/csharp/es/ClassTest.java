package simae.lib.csharp.es;

import org.junit.jupiter.api.Test;
import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void classTest() throws IOException {
		prog =	"using System;\n" +
				"\n" +
				"class Program\n" +
				"{\n" +
				"    static void Main(string[] args)\n" +
				"    {\n" +
				"        int numero = 2;\n" +
				"    }\n" +
				"}" + nl;
		esperado = "using System;\n" +
				"\n" +
				"class Program/*/CIERRA EN LINEA 9/*/\n" +
				"{\n" +
				"    static void Main(string[] args)/*/CIERRA EN LINEA 8/*/\n" +
				"    {\n" +
				"        int numero = 2;\n" +
				"    }/*/CIERRA Main(string[] args) DE LINEA 5/*/\n" +
				"}/*/CIERRA Program DE LINEA 3/*/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.CSHARP, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
