package simae.standalone;

import simae.core.SimaeLauncher;
import simae.core.lib.Lenguaje;
import simae.core.lib.Simae;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class SimaeLauncherStandalone extends SimaeLauncher {

    public SimaeLauncherStandalone() {
        this.simae = new Simae();
    }

    private boolean writeFile(String outputFileName, BufferedReader inputReader, File workFile) {
        try {
            inputReader.close();
            Files.move(Path.of(workFile.getPath()), Path.of(outputFileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Fallo en la escritura del archivo de trabajo");
            return false;
        }

        return true;
    }

    public int launchTagging(File inputFile, String outpuftFileName, String lenguajeString){
        BufferedReader inputReaderC = null;
        PrintWriter workWriterC = null;
        File workFileC = null;
        try {
            inputReaderC = new BufferedReader(new FileReader(inputFile));
            workFileC = new File(inputFile.getPath() + ".work");
            workWriterC = new PrintWriter(new FileWriter(workFileC));
        } catch (IOException e) {
            System.out.println("Fallo algo en los argumentos");
        }
        try {
            Lenguaje lenguaje = lenguaje(lenguajeString.toLowerCase());
            this.simae.fuenteMarcado(inputReaderC, workWriterC, lenguaje, null);
            workWriterC.close();
        } catch (IOException e) {
            return 1;
        }
        return writeFile(outpuftFileName, inputReaderC, workFileC) ? 0 : 2;
    }

    public String launchTagging(String entrada, Lenguaje lenguaje, String idioma) throws IOException {
        StringReader srEntrada = new StringReader(entrada);
        BufferedReader reader = new BufferedReader(srEntrada);
        StringWriter swSalida = new StringWriter();
        PrintWriter writer = new PrintWriter(swSalida);
        this.simae.fuenteMarcado(reader, writer, lenguaje, idioma);
        String salida = swSalida.toString();
        srEntrada.close();
        swSalida.close();
        return salida;
    }

    public boolean launchUntagging(File inputFile, String outputFileName, String lenguajeString) {

        BufferedReader inputReaderC = null;
        PrintWriter workWriterC = null;
        File workFileC = null;

        try {
            inputReaderC = new BufferedReader(new FileReader(inputFile));
            workFileC = new File(inputFile.getPath() + ".work");
            workWriterC = new PrintWriter(new FileWriter(workFileC));
        } catch (IOException e) {
            System.out.println("Fallo algo en los argumentos");
        }
        Lenguaje lenguaje = lenguaje(lenguajeString.toLowerCase());
        simae.fuenteDesmarcado(inputReaderC, workWriterC, lenguaje);
        workWriterC.close();

        return writeFile(outputFileName, inputReaderC ,workFileC);
    }

    public void reproducirAudio(Integer caso) throws Exception {
        Clip sonido = AudioSystem.getClip();

        switch (caso) {
            case 0:
                sonido.open(AudioSystem.getAudioInputStream(SimaeLauncherStandalone.class.getResource("success.wav")));
                break;
            default:
                sonido.open(AudioSystem.getAudioInputStream(SimaeLauncherStandalone.class.getResource("error.wav")));
                break;
        }

        sonido.start();
        do {
            Thread.sleep(15);
        } while (sonido.isRunning());
        sonido.close();
    }
}
