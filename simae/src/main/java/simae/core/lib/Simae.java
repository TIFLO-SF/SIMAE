package simae.core.lib;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import simae.core.lib.factories.ANTLRRegistry;
import simae.core.lib.factories.abstractfactories.*;
import simae.core.lib.listener.StringTags;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class Simae {

	protected ANTLRRegistry antlrRegistry;

	public Simae() {
		antlrRegistry = new ANTLRRegistry();
		antlrRegistry.register(Lenguaje.CPLUSPLUS, new CPlusPlusFactory());
		antlrRegistry.register(Lenguaje.JAVA8, new JavaFactory());
		antlrRegistry.register(Lenguaje.PYTHON3, new PythonFactory());
		antlrRegistry.register(Lenguaje.CSHARP, new CSharpFactory());
	}


	public List<AnotacionMarca> iniciaReglaPrincipalLenguaje(ANTLRRegistry antlrRegistry, CharStream antlrEntrada, Lenguaje lenguaje, String language) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		StringTags st;
		HashMap<String, String> strings;
		st = new StringTags((language != null) ? language : "");
		strings = st.getStrings();
		AbstractFactory factory = antlrRegistry.getFactory(lenguaje);
		Lexer lexer = factory.getLexer(antlrEntrada);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Parser parser = factory.getParser(tokens);
		ParseTree tree = factory.getParseTree(parser);
		ParseTreeListener extractor = factory.getParseTreeListener(parser, strings);
		ParseTreeWalker walker = new ParseTreeWalker(); // create standard walker
		walker.walk(extractor, tree); // initiate walk of tree with listener
		return factory.getMarcas(extractor);
	}

	public String fuenteDesmarcado(BufferedReader br, Lenguaje lenguaje) {
		//FIXME: la seleccion de la marca de acuerdo al lenguaje no va aca
		String gramaticaMarca = (lenguaje == Lenguaje.PYTHON3) ? "# /.*/" : "/\\*/[^/]*/\\*/";
		StringBuilder programaCompleto = new StringBuilder();
		br.lines().forEach(linea -> programaCompleto
				.append(linea.replaceAll(gramaticaMarca, ""))
				.append("\n"));
		return programaCompleto.toString();
	}

	public List<AnotacionMarca> fuenteMarcado(BufferedReader br, Lenguaje programmingLanguage, String language) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		String armaCompleto = this.fuenteDesmarcado(br, programmingLanguage);
		CharStream antlrEntrada = CharStreams.fromString(armaCompleto);
		return iniciaReglaPrincipalLenguaje(antlrRegistry, antlrEntrada, programmingLanguage, language);
	}

	public void fuenteMarcado(BufferedReader br, PrintWriter pw, Lenguaje programmingLanguage, String language) throws IOException {
		String armaCompleto = fuenteDesmarcado(br, programmingLanguage);
		CharStream antlrEntrada = CharStreams.fromString(armaCompleto);
		BufferedReader brPreprocesado = new BufferedReader(new StringReader(armaCompleto));
		StringTags st = new StringTags((language != null) ? language : "");
		HashMap<String, String> strings = st.getStrings();
		List<AnotacionMarca> todasMarcas = null;
		try {
			todasMarcas = iniciaReglaPrincipalLenguaje(antlrRegistry, antlrEntrada, programmingLanguage, language);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		algoritmoMarcado(brPreprocesado, pw, todasMarcas, strings);
	}



	private void algoritmoMarcado(BufferedReader brPreprocesado, PrintWriter pw, List<AnotacionMarca> todasMarcas, HashMap<String, String> strings) throws IOException {

		/*
		 * Se recorre la lista de marcas
		 * p/cada marca hay que imprimir en
		 * la salida las filas hasta que
		 * la fila coincida con alguna marca
		 * PARA(CadaMarca)
		 * MIENTRAS(Fila != FilaMarca) IMPRIMIR(PosicionDentroDeFila, Fila.length())
		 * SI NO IMPRIMIR(HastaLaMarca),IMPRIMIR(Marca) <- Guardar PosicionDentroDeFila
		 */

		String entrada;
		int nroFila = 1;

		entrada = brPreprocesado.readLine();
		int posEnFila = 0;
		//Collections.sort(todasMarcas);
		Iterator<AnotacionMarca> it = todasMarcas.iterator();
		AnotacionMarca marca;
		AnotacionMarca marcaSiguiente = null;

		while (it.hasNext() || marcaSiguiente != null) {
			marca = (marcaSiguiente != null) ? marcaSiguiente : it.next();
			marcaSiguiente = it.hasNext() ? it.next() : null;

			while (nroFila != marca.getFila()) {
				pw.println(entrada.substring(posEnFila));
				entrada = brPreprocesado.readLine();
				nroFila++;
				posEnFila = 0;
			}
			// Coincide la linea, se imprime hasta la marca y luego se imprime la marca.
			pw.print(entrada.substring(posEnFila, marca.getPosicion() + 1));
			pw.print(marca.getInicioComentario());
			pw.print(marca.getMarca());

			// En este punto nroFila coincide con marca.getFila()
			// Asegurar ademas que posEnFila coincide con marca.getPosicion()
			posEnFila = marca.getPosicion();

			while (marcaSiguiente != null) {
				if (nroFila != marcaSiguiente.getFila()
						|| posEnFila != marcaSiguiente.getPosicion()) break;
				pw.print(strings.get("and") + marcaSiguiente.getMarca());
				marcaSiguiente = it.hasNext() ? it.next() : null;
			}
			pw.print(marca.getFinComentario());
			posEnFila = posEnFila + 1;
		}

		// Terminaron las marcas, imprimir el resto de la entrada
		pw.println(entrada.substring(posEnFila));
		entrada = brPreprocesado.readLine();
		while(entrada != null) {
			pw.println(entrada);
			entrada = brPreprocesado.readLine();
		}
	}


	public void fuenteDesmarcado(BufferedReader br, PrintWriter pw, Lenguaje lenguaje) {
		//FIXME: la seleccion de la marca de acuerdo al lenguaje no va aca
		String gramaticaMarca = (lenguaje == Lenguaje.PYTHON3) ? "# /.*/" : "/\\*/[^/]*/\\*/";
		br.lines().forEach(linea -> pw.println(linea.replaceAll(gramaticaMarca, "")));
	}
}