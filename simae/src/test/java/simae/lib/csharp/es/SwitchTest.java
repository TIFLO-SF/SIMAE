package simae.lib.csharp.es;

import org.junit.jupiter.api.Test;
import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SwitchTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void switchTest() throws IOException {
		prog =	"using System;\n" +
				"\n" +
				"class Program\n" +
				"{\n" +
				"    static void Main(string[] args)\n" +
				"    {\n" +
				"        int numero = 2;\n" +
				"\n" +
				"        switch (numero)\n" +
				"        {\n" +
				"            case 1: Console.WriteLine(\"El número es 1\"); break;\n" +
				"            case 2: Console.WriteLine(\"El número es 2\"); break;\n" +
				"            case 3: Console.WriteLine(\"El número es 3\"); break;\n" +
				"            default: Console.WriteLine(\"El número no es 1, 2 ni 3\"); break;\n" +
				"        }\n" +
				"    }\n" +
				"}" + nl;
		esperado = "using System;\n" +
				"\n" +
				"class Program/*/CIERRA EN LINEA 17/*/\n" +
				"{\n" +
				"    static void Main(string[] args)/*/CIERRA EN LINEA 16/*/\n" +
				"    {\n" +
				"        int numero = 2;\n" +
				"\n" +
				"        switch (numero)/*/CIERRA EN LINEA 15/*/\n" +
				"        {\n" +
				"            case 1: Console.WriteLine(\"El número es 1\"); break;\n" +
				"            case 2: Console.WriteLine(\"El número es 2\"); break;\n" +
				"            case 3: Console.WriteLine(\"El número es 3\"); break;\n" +
				"            default: Console.WriteLine(\"El número no es 1, 2 ni 3\"); break;\n" +
				"        }/*/CIERRA switch (numero DE LINEA 9/*/\n" +
				"    }/*/CIERRA Main(string[] args) DE LINEA 5/*/\n" +
				"}/*/CIERRA Program DE LINEA 3/*/" + nl;
		 marcado = simae.launchTagging(prog, Lenguaje.CSHARP, "es");
		 assertEquals(esperado,marcado, "No son iguales.");
	}

}
