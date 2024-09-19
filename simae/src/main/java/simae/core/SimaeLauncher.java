package simae.core;

import simae.core.lib.Lenguaje;
import simae.core.lib.Simae;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class SimaeLauncher {

    public static String VERSION;

    static {
        Properties properties = new Properties();
        try (InputStream input = SimaeLauncher.class.getResourceAsStream("/version.properties")) {
            properties.load(input);
            VERSION = properties.getProperty("version");
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar la version.", e);
        }
    }

    protected Simae simae;

    public String getFileExtension (String name){
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    public Lenguaje lenguaje(String lenguajeString) {
        switch(lenguajeString) {
            case "c++":
            case ".cpp":
                return Lenguaje.CPLUSPLUS;
            case "java":
            case ".java":
            case "java8":
                return Lenguaje.JAVA8;
            case "python3":
            case ".py":
                return Lenguaje.PYTHON3;
            case "csharp":
            case ".cs":
                return Lenguaje.CSHARP;
            default:
                System.out.println("Lenguaje no valido");
                return null;
        }
    }


}