package simae.lib.cPlusPlus.es;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.standalone.SimaeLauncherStandalone;

class SwitchTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void testSwitch() throws IOException {
		  prog = "int main() {" + nl +
		  		"	switch(c) {" + nl +
		  		"" + nl +
		  		"	}" + nl + 
		  		"}" + nl;
		  esperado = "int main()/*/CIERRA EN LINEA 5/*/ {" + nl +
			  		"	switch(c)/*/CIERRA EN LINEA 4/*/ {" + nl +
			  		"" + nl +
			  		"	}/*/CIERRA switch(c) DE LINEA 2/*/" + nl + 
			  		"}/*/CIERRA main() DE LINEA 1/*/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
