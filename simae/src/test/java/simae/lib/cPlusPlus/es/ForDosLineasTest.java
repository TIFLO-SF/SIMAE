package simae.lib.cPlusPlus.es;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.standalone.SimaeLauncherStandalone;

class ForDosLineasTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void testForSeparado() throws IOException {
		  prog = "int main() {" + nl +
		  		"	for(int i = 0;" + nl +
		  		"			i<10; i++) {" + nl +
		  		"		c++;" + nl +
		  		"	}" + nl +
		  		"}" + nl;
		  esperado = "int main()/*/CIERRA EN LINEA 6/*/ {" + nl + 
		  		"	for(int i = 0;" + nl +
		  		"			i<10; i++)/*/CIERRA EN LINEA 5/*/ {" + nl +
		  		"		c++;" + nl +
		  		"	}/*/CIERRA for(int i = 0; i<10; i++) DE LINEA 2/*/" + nl +
		  		"}/*/CIERRA main() DE LINEA 1/*/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
