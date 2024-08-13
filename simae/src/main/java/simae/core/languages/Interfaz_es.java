package simae.core.languages;

import java.util.ListResourceBundle;

public class Interfaz_es extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
            return new Object[][] {
                    //Marcas
                    {"endsOn", "CIERRA EN LINEA "},
                    {"closes", "CIERRA "},
                    {"ofLine", " DE LINEA "},
                    {"and", " y "},
                   //Descripcion de argumentos
                    {"language", "Idioma utilizado para el marcado y mensajes"},
                    {"input", "Archivo de entrada"},
                    {"output", "Archivo de salida (por defecto es <inputFile>)"},
                    {"gui", "Muestra la interfaz gráfica"},
                    {"version", "Versión de SIMAE"},
                    {"untag", "Quitar las marcas de SIMAE de <inputFile>"},
                    {"help", "Muestra esta descripción de uso"},
                    {"usage.synopsisHeading", "Uso: "},
                    {"withSound", "Reproduce un sonido para indicar el resultado del proceso (solo CLI)"},
                    //Mensajes de exito y error
                    {"extension", "El archivo de entrada debe tener extensión .java, .cpp, .py o .cs.\n"},
                    {"emptyInput", "Debe ingresar un archivo de entrada.\n"},
                    {"invalidInput", "El archivo de entrada no es válido.\n"},
                    {"falloMarcado", "Fallo en el proceso de escritura de marcas.\n"},
                    {"falloDesmarcado", "Fallo en el proceso de desmarcado.\n"},
                    {"workFileError", "Fallo en la escritura del archivo de trabajo.\n"},
                    {"success", "El archivo ha sido marcado correctamente.\n"},
                    {"successUntag", "El archivo ha sido desmarcado correctamente.\n"},
                    {"undefinedArgument1", "Los argumentos "},
                    {"undefinedArgument2", " no existen.\n"},
                    {"missing", "Debe proporcionar un valor para el argumento "},
                    {"missingResource", "El idioma ingresado no está disponible.\n"},
                    {"overwritten1", "La opción "},
                    {"overwritten2", " debería ser especificada una sola vez.\n"},
            };
    }

}
