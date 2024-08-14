package simae.lib.cPlusPlus.es;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.standalone.SimaeLauncherStandalone;

class PreprocesadorTest extends Tests{
	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void preprocesadorTest() throws IOException {
		prog = "int main()/*/CIERRA EN LINEA 6/*/ {\n" + 
				"	while(c)/*/CIERRA EN LINEA 4/*/ if(c)/*/CIERRA EN LINEA 4/*/ {\n" + 
				"	}/*/CIERRA if(c) DE LINEA 3/*//*/CIERRA while(c) DE LINEA 3/*/\n" + 
				"}/*/CIERRA main() DE LINEA 1/*/";
		 esperado = "int main()/*/CIERRA EN LINEA 4/*/ {" + nl +
		 		"	while(c)/*/CIERRA EN LINEA 3/*/ if(c)/*/CIERRA EN LINEA 3/*/ {" + nl +
		 		"	}/*/CIERRA if(c) DE LINEA 2 y CIERRA while(c) DE LINEA 2/*/" + nl + 
		 		"}/*/CIERRA main() DE LINEA 1/*/" + nl; 
		 marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "es");
		 assertEquals(esperado,marcado, "No son iguales.");
	}

}
