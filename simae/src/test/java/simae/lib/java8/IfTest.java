package simae.lib.java8;

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
		  prog = "public class Main {" + nl +
				  nl +
				  "    public static void main(String[] args) {" + nl +
				  "        int a = 5, b = 10, c = 1;" + nl +
				  "        if (c == 5) {" + nl +
				  "            c = a + b;" + nl +
				  "            b = c - b;" + nl +
				  "        }" + nl +
				  "    }" + nl +
				  "}" + nl;
		  esperado = "public class Main /*/CIERRA EN LINEA 10/*/{" + nl +
				  nl +
				  "    public static void main(String[] args)/*/CIERRA EN LINEA 9/*/ {" + nl +
				  "        int a = 5, b = 10, c = 1;" + nl +
				  "        if (c == 5)/*/CIERRA EN LINEA 8/*/ {" + nl +
				  "            c = a + b;" + nl +
				  "            b = c - b;" + nl +
				  "        }/*/CIERRA if (c == 5) DE LINEA 5/*/" + nl +
				  "    }/*/CIERRA void main(String[] args) DE LINEA 3/*/" + nl +
				  "}/*/CIERRA class Main DE LINEA 1/*/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.JAVA8, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
