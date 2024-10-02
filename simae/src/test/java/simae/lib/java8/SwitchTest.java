package simae.lib.java8;

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
		  prog = "public class Main {" + nl +
				  nl +
				  "    public static void main(String[] args) {" + nl +
				  "        int c = 3;" + nl +
				  "        switch (c) {" + nl +
				  "            case 1:" + nl +
				  nl +
				  "            case 2:" + nl +
				  nl +
				  "            case 3:" + nl +
				  nl +
				  "        }" + nl +
				  "    }" + nl +
				  "}" + nl;
		  esperado = "public class Main /*/CIERRA EN LINEA 14/*/{" + nl +
				  nl +
				  "    public static void main(String[] args)/*/CIERRA EN LINEA 13/*/ {" + nl +
				  "        int c = 3;" + nl +
				  "        switch (c)/*/CIERRA EN LINEA 12/*/ {" + nl +
				  "            case 1:" + nl +
				  nl +
				  "            case 2:" + nl +
				  nl +
				  "            case 3:" + nl +
				  nl +
				  "        }/*/CIERRA switch (c) DE LINEA 5/*/" + nl +
				  "    }/*/CIERRA void main(String[] args) DE LINEA 3/*/" + nl +
				  "}/*/CIERRA class Main DE LINEA 1/*/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.JAVA8, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
