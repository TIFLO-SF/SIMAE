package simae.lib.java8;

import org.junit.jupiter.api.Test;

import simae.core.lib.Lenguaje;
import simae.lib.cPlusPlus.es.Tests;
import simae.standalone.SimaeLauncherStandalone;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MethodTest extends Tests {

	SimaeLauncherStandalone simae = new SimaeLauncherStandalone();

	@Test
	void methodTest() throws IOException {
		  prog = "public class Main {" + nl +
				  nl +
				  "    public void haceAlgo() {" + nl +
				  "        System.out.println(\"Test\");" + nl +
				  "    }" + nl +
				  nl +
				  "    public static void main(String[] args) {" + nl +
				  nl +
				  "    }" + nl +
				  "    " + nl +
				  "}" + nl;
		  esperado = "public class Main /*/CIERRA EN LINEA 11/*/{" + nl +
				  nl +
				  "    public void haceAlgo()/*/CIERRA EN LINEA 5/*/ {" + nl +
				  "        System.out.println(\"Test\");" + nl +
				  "    }/*/CIERRA void haceAlgo() DE LINEA 3/*/" + nl +
				  nl +
				  "    public static void main(String[] args)/*/CIERRA EN LINEA 9/*/ {" + nl +
				  nl +
				  "    }/*/CIERRA void main(String[] args) DE LINEA 7/*/" + nl +
				  "    " + nl +
				  "}/*/CIERRA class Main DE LINEA 1/*/" + nl;
		  marcado = simae.launchTagging(prog, Lenguaje.JAVA8, "es");
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
