package simae.lib.cPlusPlus.es;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.standalone.SimaeLauncherStandalone;

class ArchivoConDeclaracionDeVariableTest extends Tests{
	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void testArchivoConDeclaracionDeVariableTest() throws IOException {
		 
		 prog = "int a = 4;" + nl;
		 esperado = "int a = 4;" + nl;
		 marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "es");
		 assertEquals(esperado,marcado, "No son iguales.");
	}

}
