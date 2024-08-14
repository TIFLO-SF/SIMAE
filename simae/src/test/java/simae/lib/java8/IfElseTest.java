package simae.lib.java8;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IfElseTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void ifElseTest() throws IOException {
		  prog = "public class Main {" + nl +
				  "      public static void main(String[] args) {" + nl +
				  "      	int k = 1;" + nl +
				  "      	if(k == 1){" + nl +
				  "      		k++;" + nl +
				  "      	} else {" + nl +
				  "      		k--;" + nl +
				  "      	}" + nl +
				  "      }" + nl +
				  "}" + nl;
		  esperado = "public class Main /*/CIERRA EN LINEA 10/*/{" + nl +
				  "      public static void main(String[] args)/*/CIERRA EN LINEA 9/*/ {" + nl +
				  "      	int k = 1;" + nl +
				  "      	if(k == 1)/*/CIERRA EN LINEA 6/*/{" + nl +
				  "      		k++;" + nl +
 				  "      	}/*/CIERRA if(k == 1) DE LINEA 4/*/ else/*/CIERRA EN LINEA 8/*/ {" + nl +
				  "      		k--;" + nl +
 				  "      	}/*/CIERRA else DE LINEA 6/*/" + nl +
				  "      }/*/CIERRA void main(String[] args) DE LINEA 2/*/" + nl +
				  "}/*/CIERRA class Main DE LINEA 1/*/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.JAVA8, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
