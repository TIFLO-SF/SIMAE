package simae.lib.python;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void testWhile() throws IOException {
		  prog = "class MyClass:" + nl +
				  "  x = 5" + nl;
		  esperado = "class MyClass:# /CIERRA EN LINEA 2/" + nl +
				  "  x = 5# /CIERRA class MyClass DE LINEA 1/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.PYTHON3, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
