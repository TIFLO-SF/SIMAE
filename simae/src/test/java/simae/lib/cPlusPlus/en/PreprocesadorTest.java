package simae.lib.cPlusPlus.en;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PreprocesadorTest extends Tests {
	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void preprocesadorTest() throws IOException {
		prog = "int main()/*/CIERRA EN LINEA 6/*/ {\n" +
				"	while(c)/*/CIERRA EN LINEA 4/*/ if(c)/*/CIERRA EN LINEA 4/*/ {\n" + 
				"	}/*/CIERRA if(c) DE LINEA 3/*//*/CIERRA while(c) DE LINEA 3/*/\n" + 
				"}/*/CIERRA main() DE LINEA 1/*/";
		 esperado = "int main()/*/CLOSES ON LINE 4/*/ {" + nl +
		 		"	while(c)/*/CLOSES ON LINE 3/*/ if(c)/*/CLOSES ON LINE 3/*/ {" + nl +
		 		"	}/*/CLOSES if(c) OF LINE 2 and CLOSES while(c) OF LINE 2/*/" + nl +
		 		"}/*/CLOSES main() OF LINE 1/*/" + nl;
		 marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "en");
		 assertEquals(esperado,marcado, "The expected code and the result are not equals.");
	}

}
