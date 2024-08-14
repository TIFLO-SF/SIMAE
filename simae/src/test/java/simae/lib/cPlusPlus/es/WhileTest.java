package simae.lib.cPlusPlus.es;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.standalone.SimaeLauncherStandalone;

class WhileTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void testWhile() throws IOException {
		  prog = "int main() {" + nl +
		  		"	while(c>0) {" + nl +
		  		"		c++;" + nl +
		  		"	}" + nl +
		  		"}" + nl;
		  esperado = "int main()/*/CIERRA EN LINEA 5/*/ {" + nl +
			  		"	while(c>0)/*/CIERRA EN LINEA 4/*/ {" + nl +
			  		"		c++;" + nl +
			  		"	}/*/CIERRA while(c>0) DE LINEA 2/*/" + nl +
			  		"}/*/CIERRA main() DE LINEA 1/*/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
