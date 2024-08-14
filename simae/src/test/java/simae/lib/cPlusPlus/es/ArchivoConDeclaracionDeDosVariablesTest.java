package simae.lib.cPlusPlus.es;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import simae.core.lib.Lenguaje;
import simae.standalone.SimaeLauncherStandalone;

class ArchivoConDeclaracionDeDosVariablesTest extends Tests{
	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void testArchivoConDeclaracionDeDosVariablesTest() throws IOException {
		prog = "int a = 4;" + nl +
				 "int b = 4;";
		 esperado = "int a = 4;" + nl +
				 	"int b = 4;" + nl; 
		 marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "es");
		 assertEquals(esperado,marcado, "No son iguales.");
	}

}
