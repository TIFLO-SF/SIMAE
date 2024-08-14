package simae.lib.cPlusPlus.en;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ForEachTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void testForEach() throws IOException {
		  prog = "int main() {" + nl +
		  		"	for(int it : vector) {" + nl +
		  		"		c++;" + nl +
		  		"	}" + nl +
		  		"}" + nl;
		  esperado = "int main()/*/CLOSES ON LINE 5/*/ {" + nl +
		  		"	for(int it : vector)/*/CLOSES ON LINE 4/*/ {" + nl +
		  		"		c++;" + nl +
		  		"	}/*/CLOSES for(int it : vector) OF LINE 2/*/" + nl +
		  		"}/*/CLOSES main() OF LINE 1/*/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "en");
		  assertEquals(esperado,marcado, "The expected code and the result are not equals.");
	}
}
