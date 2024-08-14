package simae.lib.cPlusPlus.en;

import org.junit.jupiter.api.Test;
import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArchivoConDeclaracionDeVariableTest extends Tests {
	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void testArchivoConDeclaracionDeVariableTest() throws IOException {
		 
		 prog = "int a = 4;" + nl;
		 esperado = "int a = 4;" + nl;
		 marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "en");
		 assertEquals(esperado,marcado, "The expected code and the result are not equals.");
	}

}
