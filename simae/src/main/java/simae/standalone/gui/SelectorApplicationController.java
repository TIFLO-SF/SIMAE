package simae.standalone.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import simae.standalone.SimaeLauncherStandalone;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class SelectorApplicationController {

    private List<File> archivos = null;
    private List<Archivo> listaArchivosObjeto;

    @FXML
    private CheckBox soloQuitaMarcas;
    @FXML
    private ListView<?> listaDeArchivos;

    private List<String> listaCompleta;

    @FXML
    private Button botonMarcar;

    @FXML
    private Button botonQuitar;

    @FXML
    private Button botonQuitarSeleccion;

    @FXML
    private Text textoProcesado;

    @FXML
    private Text textoError;

    private ObservableList listaObservable = FXCollections.observableArrayList();

    private FileChooser fc = new FileChooser();

    private String extension() {
        return null;
    }

    private String lenguaje(String extension) {
        switch (extension) {
            case ".cpp":
                return "C++";
            case ".java":
                return "Java";
            case ".py":
                return "Python3";
            case "cSharp":
            case ".cs":
                return "CSharp";
        }
        return null;
    }

    private void eliminaOtrosLenguajes() {
        for (int i = 0; i < listaObservable.size(); i++) {
            Archivo archivo = (Archivo) listaObservable.get(i);
            String nombreArchivo = archivo.toString();
            if (!nombreArchivo.substring(nombreArchivo.lastIndexOf(".")).equals(extension())) {
                listaObservable.remove(i);
                i--;
            }
        }
    }

    @FXML
    private void initialize() {
        listaArchivosObjeto = new ArrayList<>();
        listaCompleta = new ArrayList<>();
        fc.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("Todos (.*)", "*.cpp", "*.java", "*.py", "*.cs"), new FileChooser.ExtensionFilter("C++ (.cpp)", "*.cpp"), new FileChooser.ExtensionFilter("Java (.java)", "*.java"), new FileChooser.ExtensionFilter("Python (.py)", "*.py"), new FileChooser.ExtensionFilter("C# (.cs)", "*.cs"));

    }


    @FXML
    void multiFileChooser() {
        archivos = fc.showOpenMultipleDialog(null);
        if(archivos != null) {
            archivos.stream()
                    .filter(file -> !listaArchivosObjeto.stream().anyMatch(archivo -> file.equals(archivo.getFile()))) //FIXME: sobreescribir equals y usar contains
                    .forEach(file -> listaArchivosObjeto.add(new Archivo(file)));

            actualizaLista();
        }
    }

    @FXML
    void multiFolderChooser() throws IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory == null) {
            System.out.println("No seleccionó ninguna carpeta");
        } else {
            Path dir = selectedDirectory.toPath();
            Files.find(dir, Integer.MAX_VALUE, (path, attributes) ->
                            path.getFileName().toString().toLowerCase().endsWith(extension()))
                    .forEach(file -> listaArchivosObjeto.add(new Archivo(file.toFile())));
            actualizaLista();
        }
    }

    private void actualizaLista() {
        listaObservable.addAll(listaArchivosObjeto
                .stream()
                .filter(file -> !listaObservable.contains(file))
                .toArray());
        listaDeArchivos.setItems(listaObservable);
        if (extension() != null) eliminaOtrosLenguajes();
        if (listaObservable.size() > 0) habilitarMarcado();
    }

    @FXML
    void marcaArchivos() throws Exception {
        long inicio = System.currentTimeMillis();
        char decideMarca = soloQuitaMarcas.isSelected() ? 'D' : 'M'; //FIXME: intentar cambiar metodo
        SimaeLauncherStandalone simaeLauncher = new SimaeLauncherStandalone();
        textoError.setVisible(false);
        textoProcesado.setVisible(false);


        if (listaObservable.parallelStream()
                .anyMatch(file -> (decideMarca == 'M') ?
                        !(simaeLauncher.launchTagging(
                                ((Archivo)file).getFile(),
                                ((Archivo)file).getFile().toString(),
                                lenguaje(file.toString().substring(file.toString().lastIndexOf(".")))
                        ) == 0) :
                        !simaeLauncher.launchUntagging(
                                ((Archivo)file).getFile(),
                                ((Archivo)file).getFile().toString(),
                                lenguaje(file.toString().substring(file.toString().lastIndexOf(".")))
                        ))) {
            simaeLauncher.reproducirAudio(1);
            textoError.setVisible(true);
        }

        if (!textoError.isVisible()) {
            simaeLauncher.reproducirAudio(0);
            textoProcesado.setVisible(true);
        }


        /*for (File file : archivos) {
            System.out.println(file.toString().substring(file.toString().lastIndexOf(".")));
            simae.marcaDesmarcaPorArchivos(file, file.toString(), lenguaje(file.toString().substring(file.toString().lastIndexOf("."))), decideMarca);
        }*/
        //archivos.parallelStream().forEach(file -> simae.marcaDesmarcaPorArchivos(file, file.toString(), lenguaje(file.toString().substring(file.toString().lastIndexOf("."))), decideMarca));
        long fin = System.currentTimeMillis();
        long resta = fin - inicio;
        System.out.println("Tiempo de ejec: " + resta + " milisegundos.");

    }


    @FXML
    void eliminarTodos() {
        listaObservable.removeAll(listaObservable);
        listaCompleta.removeAll(listaCompleta);
        listaArchivosObjeto.removeAll(listaArchivosObjeto);
        detectaHabilitaQuitado();
        detectaHabilitaMarcado();
    }

    @FXML
    void eliminarArchivoSeleccionado() {
        Iterator<Archivo> iterator = listaArchivosObjeto.iterator();
        while (iterator.hasNext()) {
            Archivo archivo = iterator.next();
            if (listaDeArchivos.getSelectionModel().getSelectedItems().contains(archivo)) {
                iterator.remove();
            }
        }
        listaObservable.removeAll(listaDeArchivos.getSelectionModel().getSelectedItems());
        listaCompleta.removeAll(listaDeArchivos.getSelectionModel().getSelectedItems());
        detectaHabilitaQuitado();
        detectaHabilitaMarcado();
    }


    @FXML
    void detectaHabilitaQuitado() {
        botonQuitarSeleccion.setDisable(listaDeArchivos.getSelectionModel().getSelectedItems().size() == 0);
    }

    void detectaHabilitaMarcado(){
        botonMarcar.setDisable(listaDeArchivos.getSelectionModel().getSelectedItems().size() == 0);
    }

    private void habilitarMarcado() {
        botonMarcar.setDisable(false);
        botonQuitar.setDisable(false);
    }





}
