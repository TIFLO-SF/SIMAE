package simae.lib.cPlusPlus.es;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
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
		  esperado = "class CRender /*/CIERRA EN LINEA 5/*/{" + nl +
				  "public:" + nl +
 				  "    char buffer[255];" + nl +
				  "    void llamadoFuncion(const char *argumento);" + nl +
				  "}/*/CIERRA class CRender  DE LINEA 1/*/;" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
