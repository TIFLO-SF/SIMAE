package simae.lib.cPlusPlus.en;

import org.junit.jupiter.api.Test;
import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoWhileTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void testDoWhile() throws IOException {
		  prog = "int main() {" + nl +
		  		"	do {" + nl +
		  		"	} while(c<0);" + nl +
		  		"}" + nl;
		  esperado = "int main()/*/CLOSES ON LINE 4/*/ {" + nl +
		  		"	do/*/CLOSES ON LINE 3/*/ {" + nl +
		  		"	} while(c<0);/*/CLOSES do while OF LINE 2/*/" + nl +
		  		"}/*/CLOSES main() OF LINE 1/*/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "en");

		  assertEquals(esperado,marcado, "The expected code and the result are not equals.");
	}
}
