package simae.lib.cPlusPlus.es;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.standalone.SimaeLauncherStandalone;

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
		  esperado = "int main()/*/CIERRA EN LINEA 8/*/ {" + nl +
		  		"	if(c == 1)/*/CIERRA EN LINEA 4/*/ {" + nl +
		  		"		c++;" + nl +
		  		"	}/*/CIERRA if(c == 1) DE LINEA 2/*/" + nl +
		  		"	else/*/CIERRA EN LINEA 7/*/ {" + nl +
		  		"		c--;" + nl +
		  		"	}/*/CIERRA else DE LINEA 5/*/" + nl +
		  		"}/*/CIERRA main() DE LINEA 1/*/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
