package simae.core.languages;

import java.util.ListResourceBundle;

public class Interfaz_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
            return new Object[][]{
                    {"endsOn", "CLOSES ON LINE "},
                    {"closes", "CLOSES "},
                    {"ofLine", " OF LINE "},
                    {"and", " and "},
                    //Descripcion de argumentos
                    {"language", "Language used for tagging and messages"},
                    {"untag", "Removes SIMAE tags from <inputFile>"},
                    {"input", "Input filename"},
                    {"output", "Output filename (default is <inputFile>)"},
                    {"gui", "Shows graphical interface"},
                    {"version", "Version of SIMAE"},
                    {"help", "Displays this usage description"},
                    {"usage.synopsisHeading", "Usage: "},
                    {"withSound", "Plays a sound to indicate the result of the process (CLI only)"},
                    //Mensajes de exito y error"
                    {"extension", "The input file must have a .java, .cpp, .py or .cs extension.\n"},
                    {"emptyInput", "You must enter an input file.\n"},
                    {"invalidInput", "The input file is not valid.\n"},
                    {"falloMarcado", "Error in the process of writing marks.\n"},
                    {"falloDesmarcado", "Error in the process of untagging marks.\n"},
                    {"workFileError", "Failed to write work file.\n"},
                    {"success", "The file has been successfully marked.\n"},
                    {"successUntag", "The file has been successfully untagged.\n"},
                    {"undefinedArgument1", "The arguments "},
                    {"undefinedArgument2", " don't exist.\n"},
                    {"missing", "You must provide a value for the argument "},
                    {"missingResource", "The entered language is not available."},
                    {"overwritten1", "The option "},
                    {"overwritten2", " should be specified only once.\n"},


            };
    }

}
