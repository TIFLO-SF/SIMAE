package simae.api;

import simae.core.lib.AnotacionMarca;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class API {

    public static void main(String[] args) {
        String languageString = getFileExtension(args[0]);
        Charset codificacion = getEncoding(args[1]);
        String idioma = args[2];
        SimaeLauncherAPI launcher = new SimaeLauncherAPI();
        List<AnotacionMarca> marcas = null;
        try {
            marcas = launcher.obtenerMarcas(new File(args[0]), languageString, codificacion, idioma);
        } catch (FileNotFoundException e) {
            System.out.println("Fallo la lectura del archivo");
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        for (AnotacionMarca marca : marcas) {
            System.out.println(marca);
        }
    }




    private static String getFileExtension(String name) {
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    public static Charset getEncoding(String encodingJS) {
        switch (encodingJS.toUpperCase()) {
            case "ASCII":
                return StandardCharsets.US_ASCII;
            case "ISO-8859-1":
                return StandardCharsets.ISO_8859_1;
            case "UTF-8":
                return StandardCharsets.UTF_8;
            case "UTF-16BE":
                return StandardCharsets.UTF_16BE;
            case "UTF-16LE":
                return StandardCharsets.UTF_16LE;
            default:
                return StandardCharsets.UTF_8;
        }
    }


}

