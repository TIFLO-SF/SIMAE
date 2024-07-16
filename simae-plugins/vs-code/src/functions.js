const vscode = require('vscode');
const jschardet = require('jschardet');
const path = require('path');
const { spawn } = require('child_process');
const { msg } = require('./locale.js');
const { Marca } = require('./models/marca.js')


 /**
 * @param {vscode.ExtensionContext} context
 */

 
 /**
   * Obtiene la linea donde se encuentra el usuario y verifica que exista una marca para esa linea. 
   * Si existe, la muestra con una hint accesible por el lector de pantalla. Si existe mas de una las une con 'y'/'and'.
   * @param {Map<number, Marca[]>} multimap - Multimap de marcas
   * @param {vscode.TextEditor} editor - Editor.
   */
function mostrarMarcas(multimap, editor) {
    if (multimap.has(editor.selection.active.line + 1)) {
      const marcas = multimap.get(editor.selection.active.line + 1);
      if (marcas.length == 1) { //si tiene una sola marca
        vscode.window.showInformationMessage(msg("linea") + (editor.selection.active.line + 1)  + ":" +  ` ${marcas[0].marca}`);
      } else { //si tiene mas
        const mensaje = marcas.map(marcaArreglo => marcaArreglo.marca).join(msg("concat"));
        vscode.window.showInformationMessage(msg("linea") + (editor.selection.active.line + 1) + ":" + ` ${mensaje}`);
      }
    } else {
      vscode.window.showInformationMessage(msg("linea") + (editor.selection.active.line + 1) + ":" + msg("sinMarcas"));
    }
  }
  

/**
 * Ejecuta el archivo simae.jar utilizando el JRE configurado y devuelve un multimap de marcas resultante.
 * @param {string} filePath - La ruta absoluta del JAR de SIMAE.
 * @param {Object} context - El contexto del editor de vs code.
 * @param {vscode.TextEditor} editor - El editor que se encuentra activo y donde está abierto el archivo.
 * @param {string} idioma - El idioma para la ejecución.
 * @returns {Promise<Map<number, Marca[]>>} Una promesa que se resuelve con un objeto Map que contiene las marcas.
 * @throws {string} Si ocurrió un error en la ejecución del JAR.
 */
async function armarMultimap(filePath, context, editor, idioma) {
  let jrePath = context.globalState.get('jrePath');
  if (!jrePath) {
    vscode.window.showErrorMessage('Error: JRE path not found.');
    return null;
  }

  const javaBin = path.join(jrePath, 'bin', 'java');
  
  process.env['JAVA_HOME'] = jrePath;
  process.env['PATH'] = `${path.join(jrePath, 'bin')}${path.delimiter}${process.env['PATH']}`;


  const simaeJar = path.join(context.extensionPath, 'libs', 'resources', 'simae.jar');
  let errorConsola = '';
  await editor.document.save();

  return new Promise((resolve, reject) => {
    getEncoding(editor).then(encoding => {
      let encodingString = encoding ? encoding : "UTF-8";
      const proceso = spawn(javaBin, ['-jar', simaeJar, filePath, encodingString, idioma]);

      let salidaConsola = '';
      proceso.stdout.on('data', (datos) => {
        salidaConsola += datos;
      });

      proceso.stderr.on('data', (datos) => {
        errorConsola += datos;
      });

      proceso.on('close', (code) => {
        if (code == 0) {
          console.log("Idioma: " + idioma);
          const salida = salidaConsola.trim().split('\n');
          const marcas = procesarSalida(salida);
          resolve(marcas);
        } else {
          vscode.window.showErrorMessage("Error en la ejecución: " + errorConsola);
          reject(msg("errorEjecucion") + " " + errorConsola);
        }
      });

      proceso.on('error', (err) => {
        vscode.window.showErrorMessage("Error al iniciar el proceso: " + err.message);
        reject(err.message);
      });
    });
  });
}

    
    /**
 * Procesa la salida y crea un multimap basado en las marcas.
 * @param {string[]} salida - Un arreglo de strings que representa la salida de ejecución del jar.
 * @returns {Map<number, Marca[]>} Map que contiene las marcas agrupadas por fila.
 * Cada clave es el número de fila y el valor es un arreglo de objetos Marca que se encuentran en esa fila.
 */
  function procesarSalida(salida) {
    let multimap = new Map();
    for (let i = 0; i < salida.length; i++) {
      const linea = salida[i].split(' ');
      const fila = Number(linea[0]);
      const columna = Number(linea[1]);
      const marcaTexto = linea.slice(2).join(' ');
      const marca = new Marca(fila, columna, marcaTexto);
      if (!multimap.has(fila)) {
        multimap.set(fila, []);
      }
      multimap.get(fila).push(marca);
    }
    return multimap;
  }
  
 /**
 * Convierte el multimap en un arreglo de objetos Marca.
 * @param {Map<number, Marca[]>} multimap - El Map que contiene las marcas agrupadas por fila.
 * @returns {Array} Arreglo de marcas. Si hay mas de una marca con la misma clave se colocan de forma consecutiva en el arreglo
 */


function multimapToArray(multimap) {
  const resultado = [];
  for (const [key, marcas] of multimap) {
    for (const marca of marcas) {
      let fila= marca.fila;
      let columna = marca.columna;
      let texto = marca.marca;
      resultado.push({fila, columna, texto});
    }
  }
  return resultado;
}

/**
 * Encuentra la siguiente marca más cercana en dirección derecha a partir de la línea donde se encuentra el usuario.
 * @param {Array} arreglo - Arreglo de marcas.
 * @param {number} fila - El número de línea en la que se encuentra el usuario.
 */
function siguientePosicion(arreglo, fila) {
  for (let i = 0; i < arreglo.length; i++) {
    const marcaFila = arreglo[i].fila;
    if (fila < marcaFila) {
      return arreglo[i];
    } else if (i < arreglo.length - 1 && fila > marcaFila && fila < arreglo[i + 1].fila) {
      return arreglo[i + 1];
    }
  }
  return -1;
}

/**
 * Encuentra la siguiente marca más cercana en dirección izquierda a partir de la línea donde se encuentra el usuario.
 * @param {Array} arreglo - Arreglo de marcas
 * @param {number} fila - El número de línea en la que se encuentra el usuario.
 */
function anteriorPosicion(arreglo, fila) {
  for (let i = arreglo.length - 1; i >= 0; i--) {
    const marcaFila = arreglo[i].fila;
    if (fila > marcaFila) {
      return arreglo[i];
    } else if (i > 0 && fila < marcaFila && fila > arreglo[i - 1].fila) {
      return arreglo[i - 1];
    }
  }
  return -1;
}

/**
 * Mueve el cursor del editor a la siguiente marca o a la marca anterior, según el valor de 'antsig'.
 * Si 'antsig' es 1, el cursor se mueve a la siguiente marca. Si 'antsig' es -1, el cursor se mueve a la marca anterior.
 * @param {Map<number, Marca[]>} ultimasMarcas - Map que contiene las marcas agrupadas por fila.
 * @param {vscode.TextEditor} editor - El editor de texto 
 * @param {number} antsig - Entero que indica la dirección del movimiento del cursor (1 para sig marca y -1 para anterior)
 */
function moverCursor(ultimasMarcas, editor, antsig){ 
  const direccion = antsig === 1 ? msg("derecha") : msg("izquierda");
  let arreglo = multimapToArray(ultimasMarcas);
  let siguienteMarca = antsig === 1? siguientePosicion(arreglo, editor.selection.active.line + 1) : anteriorPosicion(arreglo, editor.selection.active.line + 1);
  if(siguienteMarca != -1){
    const posicion = new vscode.Position(siguienteMarca.fila - 1, siguienteMarca.columna+1);
    const rango = new vscode.Range(posicion, posicion);
    editor.selection = new vscode.Selection(posicion, posicion);
    editor.revealRange(rango);
    mostrarMarcas(ultimasMarcas, editor);
  } else {
    vscode.window.showInformationMessage(msg("noMasMarcas") + direccion);
  }
}
  
/**
 * Obtiene la codificación del archivo actual.
 * @param {vscode.TextEditor} editor - Editor actual.
 * @returns {Promise<string|null>} Una promesa que se resuelve con el encoding o null si la confianza es baja.
 * @async
 */

async function getEncoding(editor) {
  const deteccion = jschardet.detect(Buffer.from(await vscode.workspace.fs.readFile(editor.document.uri)));
  return deteccion.confidence > 0.8 ? deteccion.encoding : null;
}

/**
 * Obtiene los atajos de teclado en el idioma actual y los muestra en un dialog accesible por el lector de pantalla.
 */
function abrirAyuda(){
    const i18next = require('i18next');
    const {getLocale } = require('./locale.js'); 
    i18next.changeLanguage(getLocale());
    vscode.window.showInformationMessage(msg("ayuda"), {modal: true});
}




module.exports = { //exporta funcionas usadas por la extension
  armarMultimap,
  mostrarMarcas,
  moverCursor,
  abrirAyuda
}