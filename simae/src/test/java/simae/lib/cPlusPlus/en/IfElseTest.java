package simae.lib.cPlusPlus.en;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IfElseTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void testIfElse() throws IOException {
		  prog = "int main() {" + nl +
		  		"	if(c == 1) {" + nl +
		  		"		c++;" + nl +
		  		"	}" + nl +
		  		"	else {" + nl +
		  		"		c--;" + nl +
		  		"	}" + nl +
		  		"}" + nl;
		  esperado = "int main()/*/CLOSES ON LINE 8/*/ {" + nl +
		  		"	if(c == 1)/*/CLOSES ON LINE 4/*/ {" + nl +
		  		"		c++;" + nl +
		  		"	}/*/CLOSES if(c == 1) OF LINE 2/*/" + nl +
		  		"	else/*/CLOSES ON LINE 7/*/ {" + nl +
		  		"		c--;" + nl +
		  		"	}/*/CLOSES else OF LINE 5/*/" + nl +
		  		"}/*/CLOSES main() OF LINE 1/*/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "en");
		  assertEquals(esperado,marcado, "The expected code and the result are not equals.");
	}
}
