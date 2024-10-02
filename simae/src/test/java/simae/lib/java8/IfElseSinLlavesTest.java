package simae.lib.java8;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IfElseSinLlavesTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void ifElseTest() throws IOException {
		  prog = "public class Main {" + nl +
				  "    public static void main(String[] args) {" + nl +
				  "        if (esPar(numero)) " + nl +
				  "            System.out.println(\"El número es par\");" + nl +
				  "        else" + nl +
				  "            System.out.println(\"El número es impar\");" + nl +
				  "    }" + nl +
				  "}" + nl;
		  esperado = "public class Main /*/CIERRA EN LINEA 8/*/{" + nl +
				  "    public static void main(String[] args)/*/CIERRA EN LINEA 7/*/ {" + nl +
				  "        if (esPar(numero))/*/CIERRA EN LINEA 4/*/ " + nl +
				  "            System.out.println(\"El número es par\");/*/CIERRA if (esPar(numero)) DE LINEA 3/*/" + nl +
				  "        else/*/CIERRA EN LINEA 6/*/" + nl +
				  "            System.out.println(\"El número es impar\");/*/CIERRA else DE LINEA 5/*/" + nl +
				  "    }/*/CIERRA void main(String[] args) DE LINEA 2/*/" + nl +
				  "}/*/CIERRA class Main DE LINEA 1/*/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.JAVA8, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
