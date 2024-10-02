package simae.lib.cPlusPlus.es;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.standalone.SimaeLauncherStandalone;

class DosMarcasJuntasTest extends Tests{
	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void testDosMarcasJuntas() throws IOException {
		 prog = "int main() {" + nl +
		 		"" + nl + 
		 		"	while(c) " + nl +
		 		"if(c) { a=4;" + nl +
		 		"	}" + nl +
		 		"" + nl +
		 		"}" + nl;
		 esperado = "int main()/*/CIERRA EN LINEA 7/*/ {" + nl +
		 		"" + nl +
		 		"	while(c)/*/CIERRA EN LINEA 5/*/ " + nl +
		 		"if(c)/*/CIERRA EN LINEA 5/*/ { a=4;" + nl +
		 		"	}/*/CIERRA if(c) DE LINEA 4 y CIERRA while(c) DE LINEA 3/*/" + nl + 
		 		"" + nl +
		 		"}/*/CIERRA main() DE LINEA 1/*/" + nl;
		 marcado = simae.launchTagging(prog, Lenguaje.CPLUSPLUS, "es");
		 assertEquals(esperado,marcado, "No son iguales.");
	}

}
