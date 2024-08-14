package simae.lib.python;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ForTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void testWhile() throws IOException {
		  prog = "for x in students:" + nl +
				 "    print(\"x\")" + nl +
				 "print(\"print2\")" + nl;
		  esperado = "for x in students:# /CIERRA EN LINEA 2/" + nl +
			  		"    print(\"x\")# /CIERRA for x in students DE LINEA 1/" + nl +
			  		"print(\"print2\")" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.PYTHON3, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
