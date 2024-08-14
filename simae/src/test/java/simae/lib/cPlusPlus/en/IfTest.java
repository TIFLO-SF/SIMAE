package simae.lib.cPlusPlus.en;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IfTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void testIf() throws IOException {
		  prog = "int main() {" + nl +
		  		"	if(c == 1) {" + nl +
		  		"		c++;" + nl +
		  		"	}" + nl +
		  		"}" + nl;
		  esperado = "int main()/*/CLOSES ON LINE 5/*/ {" + nl +
		  		"	if(c == 1)/*/CLOSES ON LINE 4/*/ {" + nl +
		  		"		c++;" + nl +
		  		"	}/*/CLOSES if(c == 1) OF LINE 2/*/" + nl +
		  		"}/*/CLOSES main() OF LINE 1/*/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "en");
		  assertEquals(esperado,marcado, "The expected code and the result are not equals.");
	}
}
