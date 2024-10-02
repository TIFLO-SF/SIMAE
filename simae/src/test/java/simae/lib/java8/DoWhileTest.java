package simae.lib.java8;

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
		  prog = "public class Main {" + nl +
				  "	public static void main(String[] args) {" + nl +
				  "		int i = 5;" + nl +
				  "		do {" + nl +
				  "			System.out.println(i);" + nl +
				  "			i++;" + nl +
				  "		} while (i <= 10);" + nl +
				  "	}" + nl +
				  "}";

		  esperado = "public class Main /*/CIERRA EN LINEA 9/*/{" + nl +
				  "	public static void main(String[] args)/*/CIERRA EN LINEA 8/*/ {" + nl +
				  "		int i = 5;" + nl +
				  "		do/*/CIERRA EN LINEA 7/*/ {" + nl +
				  "			System.out.println(i);" + nl +
				  "			i++;" + nl +
				  "		} while (i <= 10);/*/CIERRA do while DE LINEA 4/*/" + nl +
				  "	}/*/CIERRA void main(String[] args) DE LINEA 2/*/" + nl +
				  "}/*/CIERRA class Main DE LINEA 1/*/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.JAVA8, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
