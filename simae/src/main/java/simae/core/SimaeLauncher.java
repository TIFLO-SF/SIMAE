package simae.core;

import simae.core.lib.Lenguaje;
import simae.core.lib.Simae;

public abstract class SimaeLauncher {

    public static String VERSION = "SIMAE 0.3.0";
    protected Simae simae;


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