package simae.lib.cPlusPlus.en;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ForTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void testFor() throws IOException {
		  prog = "int main() {" + nl +
		  		"	for(int i = 0; i<10; i++) {" + nl +
		  		"		c++;" + nl +
		  		"	}" + nl +
		  		"}" + nl +
		  		"";
		  esperado = "int main()/*/CLOSES ON LINE 5/*/ {" + nl +
			  		"	for(int i = 0; i<10; i++)/*/CLOSES ON LINE 4/*/ {" + nl +
			  		"		c++;" + nl +
			  		"	}/*/CLOSES for(int i = 0; i<10; i++) OF LINE 2/*/" + nl +
			  		"}/*/CLOSES main() OF LINE 1/*/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "en");
		  assertEquals(esperado,marcado, "The expected code and the result are not equals.");
	}
}
