package simae.lib.cPlusPlus.es;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.standalone.SimaeLauncherStandalone;

class DoWhileTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void testDoWhile() throws IOException {
		  prog = "int main() {" + nl +
		  		"	do {" + nl +
		  		"	} while(c<0);" + nl +
		  		"}" + nl;
		  esperado = "int main()/*/CIERRA EN LINEA 4/*/ {" + nl + 
		  		"	do/*/CIERRA EN LINEA 3/*/ {" + nl + 
		  		"	} while(c<0);/*/CIERRA do while DE LINEA 2/*/" + nl +
		  		"}/*/CIERRA main() DE LINEA 1/*/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "es");

		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
