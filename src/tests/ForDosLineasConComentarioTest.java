package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ForDosLineasConComentarioTest extends Tests {

	
	@Test
	void testForSeparadoConComentario() throws IOException {
		  prog = "int main() {" + nl +
		  		"	for(int i = 0;//Soy un comentario" + nl +
		  		"			i<10; i++) {" + nl +
		  		"		c++;" + nl +
		  		"	}" + nl +
		  		"}" + nl;
		  esperado = "int main()/*/CIERRA EN LINEA 6/*/ {" + nl + 
		  		"	for(int i = 0;//Soy un comentario" + nl +
		  		"			i<10; i++)/*/CIERRA EN LINEA 5/*/ {" + nl +
		  		"		c++;" + nl +
		  		"	}/*/CIERRA for(int i = 0; i<10; i++) DE LINEA 2/*/" + nl +
		  		"}/*/CIERRA main() DE LINEA 1/*/" + nl;
		  marcado = b.testMarcado(prog);
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}
