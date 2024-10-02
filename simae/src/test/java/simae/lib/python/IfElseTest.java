package simae.lib.python;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IfElseTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void testIfELse() throws IOException {
		  prog = "if(p==1):" + nl +
				  "	p=0" + nl +
				  "else:" + nl +
				  "	p=1" + nl;
		  esperado = "if(p==1):# /CIERRA EN LINEA 2/" + nl +
				  "	p=0# /CIERRA if(p==1) DE LINEA 1/" + nl +
				  "else:# /CIERRA EN LINEA 4/" + nl +
				  "	p=1# /CIERRA else DE LINEA 3/" +nl;
		  marcado = simae.launchTagging(prog, Lenguaje.PYTHON3, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
