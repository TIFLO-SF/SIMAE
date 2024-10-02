package simae.lib.cPlusPlus.es;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.standalone.SimaeLauncherStandalone;

class ArchivoConIncludeTest extends Tests{
	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void testArchivoConIncludeTest() throws IOException {
		 prog = "#include <iostream>" + nl +
				 "int a = 4;" + nl;
		 esperado = "#include <iostream>" + nl +
				 	"int a = 4;" + nl;
		 marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "es");
		 assertEquals(esperado,marcado, "No son iguales.");
	}

}
