package simae.lib.cPlusPlus.en;

import org.junit.jupiter.api.Test;
import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassTest extends Tests {
	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	
	@Test
	void testClass() throws IOException {
		  prog = "class CRender {" + nl +
				  "public:" + nl +
				  "    char buffer[255];" + nl +
				  "    void llamadoFuncion(const char *argumento);" + nl +
				  "};" + nl;
		  esperado = "class CRender /*/CLOSES ON LINE 5/*/{" + nl +
				  "public:" + nl +
 				  "    char buffer[255];" + nl +
				  "    void llamadoFuncion(const char *argumento);" + nl +
				  "}/*/CLOSES class CRender  OF LINE 1/*/;" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "en");
		  assertEquals(esperado,marcado, "The expected code and the result are not equals.");
	}
}
