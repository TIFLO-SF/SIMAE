package simae.standalone.cli;

import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import javafx.application.Application;
import picocli.CommandLine;
import simae.core.SimaeLauncher;
import simae.core.lib.Lenguaje;
import simae.standalone.SimaeLauncherStandalone;
import simae.standalone.lib.SimaeStandalone;
import simae.standalone.gui.SelectorApplication;

class InitLocale {

	@CommandLine.Option(names = { "-l", "--locale" }, paramLabel = "<locale>", descriptionKey = "language")
	void setLocale(String locale) {
		Locale.setDefault(locale.equals("es") ? new Locale(locale) : new Locale("en"));
	}
	@CommandLine.Unmatched
	List<String> remainder; // ignore any other parameters and options in the first parsing phase
}

//FIXME: faltan tests para la clase
@CommandLine.Command(resourceBundle = "simae.core.languages.Interfaz", name="simae", sortOptions = false)
public class CommandLineInterface implements Callable<Integer> {

	@CommandLine.Parameters(index="0", arity="0..1", paramLabel = "<inputFile>", descriptionKey = "input")
	static String inputFile;

	@CommandLine.Option(names = {"-o", "--out"}, paramLabel = "<outputFile>", required=false, descriptionKey = "output")
	static String outputFile;

	static String languageString;

	@CommandLine.Option(names = { "-l", "--locale" }, paramLabel = "<locale>", descriptionKey = "language")
	static String language;

	private static ResourceBundle rb;

	@CommandLine.Option(names = { "-u", "--untag" }, required = false, descriptionKey = "untag")
	static Boolean untag;

	@CommandLine.Option(names = {"-s", "--sound"}, required=false, descriptionKey = "withSound")
	static Boolean withSound;

	@CommandLine.Option(names = {"-g", "--gui"}, required=false, descriptionKey = "gui")
	static Boolean gui;

	@CommandLine.Option(names = { "-h", "--help" }, usageHelp = true, descriptionKey = "help")
	boolean helpRequested;

	@CommandLine.Option(names = { "-v", "--version" }, versionHelp = true, descriptionKey = "version")
	boolean versionRequested;

	static @CommandLine.Spec
	CommandLine.Model.CommandSpec spec;


	public static void main(String[] args) {
		try {
			new CommandLine(new InitLocale()).parseArgs(args);
			if (language == null && (!Locale.getDefault().getLanguage().equals("en") && !Locale.getDefault().getLanguage().equals("es"))){
				Locale.setDefault(new Locale("en"));
			}
			rb = ResourceBundle.getBundle("simae.core.languages.Interfaz", Locale.getDefault());
			CommandLine commandLine = new CommandLine(new CommandLineInterface());
			commandLine.parseArgs(args);

			if (commandLine.isUsageHelpRequested()) {
				commandLine.usage(System.out);
				return;
			} else if (commandLine.isVersionHelpRequested()) {
				System.out.println(new SimaeLauncher().getVERSION());
				return;
			}
			commandLine.execute(args);
		} catch(CommandLine.UnmatchedArgumentException e){
			spec.commandLine().usage(System.out.printf(rb.getObject("undefinedArgument1")+ e.getUnmatched().toString() + rb.getObject("undefinedArgument2")));
		} catch(picocli.CommandLine.MissingParameterException e){
			spec.commandLine().usage(System.out.printf(rb.getObject("missing") + e.getMissing().get(0).paramLabel() + ". \n"));
		} catch(java.util.MissingResourceException e){
			System.out.println((String) rb.getObject("missingResource"));
		} catch(CommandLine.OverwrittenOptionException e){
		spec.commandLine().usage(System.out.printf((String) rb.getObject("overwritten1") + e.getOverwritten().paramLabel() + rb.getObject("overwritten2")));
	}

	}

	@Override
	public Integer call() throws Exception {

		if (gui == null && inputFile == null && outputFile == null && languageString == null) {
			Application.launch(SelectorApplication.class);
			return 0;
		}

		if (inputFile == null && gui == null) {
			spec.commandLine().usage(System.out.printf((String) rb.getObject("emptyInput")));
			return -1;
		}

		if (gui != null) {
			Application.launch(SelectorApplication.class);
		} else {
			//FIXME: mantener o modificar por la funcion marcaPorArchivos?

			//si outputfilename no
			if (outputFile == null) {
				outputFile = inputFile;
			}

			languageString = this.getFileExtension(inputFile);


			switch (languageString) { //FIXME: esto esta hardcodeado en muchos lugares
				case ".cpp":
					languageString = "c++";
					break;
				case ".java":
					languageString = "java8";
					break;
				case ".py":
					languageString = "python3";
					break;
				case ".cs":
					languageString = "csharp";
					break;
				default:
					spec.commandLine().usage(System.out.printf((String) rb.getObject("extension")));
					return -1;
			}

			SimaeLauncherStandalone launcher = new SimaeLauncherStandalone();

			File fileToTag = new File(inputFile);

			if (!fileToTag.exists()) {
				System.out.println((String) rb.getObject("invalidInput"));
				if (withSound != null) SimaeStandalone.reproducirAudio(1);
				return 1;
			}

			if (untag == null) {
				switch (launcher.launchTagging(new File(inputFile), outputFile, languageString)) {
					case 0:
						System.out.printf((String) rb.getObject("success"));
						if (withSound != null) {
							SimaeStandalone.reproducirAudio(0);
						}
						break;
					case 1:
						System.out.println((String) rb.getObject("falloMarcado"));
						if (withSound != null) {
							SimaeStandalone.reproducirAudio(1);
						}
						break;
					case 2:
						System.out.println((String) rb.getObject("workFileError"));
						if (withSound != null) {
							SimaeStandalone.reproducirAudio(1);
						}
				}
			}
			else {
				if (launcher.launchUntagging(new File(inputFile), outputFile, languageString)) {
					System.out.printf((String) rb.getObject("successUntag"));
					if (withSound != null) {
						SimaeStandalone.reproducirAudio(0);
					}
				}
				else {
					System.out.println((String) rb.getObject("falloDesmarcado"));
					if (withSound != null) {
						SimaeStandalone.reproducirAudio(1);
					}
				}
			}
		}
		return 0;
	}
		private String getFileExtension (String name){
			int lastIndexOf = name.lastIndexOf(".");
			if (lastIndexOf == -1) {
				return ""; // empty extension
			}
			return name.substring(lastIndexOf);
		}

}



