const path = require('path');
const vscode = require('vscode');
const https = require('https');
const os = require('os');
const fs = require('fs');
const decompress = require('decompress');
const { msg } = require('./locale.js');
const { exec } = require ('child_process');


 /**
 * Retorna la URL de descarga del JRE según el sistema operßativo del usuario.
 * @returns {string} url - URL de descarga del JRE
 */
function obtenerURL() {
  let platform = os.platform();
  let url;

  if (platform === 'win32') {
    url = 'https://firebasestorage.googleapis.com/v0/b/simae-67068.appspot.com/o/jre_win.zip?alt=media&token=da0e97e4-5edc-413d-ae8c-ecb4381e6232';
  } else if (platform === 'darwin') {
    url = 'JRE_MACOS'; //TO-DO
  } else if (platform === 'linux') {
    url = 'JRE_LINUX'; //TO-DO
  } else {
    return null;
  }

  return url;
}

 /**
 * Descarga el JRE en formato .zip a partir la URL proporcionada
 * @param {string} url - URL de descarga del JRE.
 * @param {string} jrePath - PATH del archivo descargado.
 */
function descargarJRE(url, jrePath) {
  return new Promise((resolve, reject) => {
    const archivo = fs.createWriteStream(jrePath);
    https.get(url, (response) => {
      response.pipe(archivo);
      archivo.on('finish', () => {
        archivo.close(() => resolve(jrePath));
      });
    }).on('error', (err) => {
      fs.unlink(jrePath, (errs) => {
        if (errs) throw err;
      });
      reject(err.message);
    });
  });
}

 /**
 * Descomprime el .zip del JRE descargado
 * @param {string} jrePath - path del .zip descargado
 * @param {string} extractPath - Directorio de extracción del .zip
 */
function extraerJRE(jrePath, extractPath) {
  return decompress(jrePath, extractPath);
}


 /**
 * Verifica que el usuario tenga JAVA instalado, si no lo tiene descarga un JRE personalizado, crea el directorio del JRE, y descomprime el .zip
 * @param {vscode.ExtensionContext} context
 * @returns {Promise<string>} extractPath - PATH de instalación del JRE
 */
async function instalarJRE(context) {
  const jreDir = path.join(context.extensionPath, 'jre');
  if (!fs.existsSync(jreDir)) {
    fs.mkdirSync(jreDir);
  }

  const jreUrl = obtenerURL();
  if (!jreUrl) {
    return null;
  }

  const jrePath = path.join(jreDir, 'jre.zip');
  const extractPath = path.join(jreDir, os.platform());

  if (!fs.existsSync(extractPath)) {
    try {
      await descargarJRE(jreUrl, jrePath);
      await extraerJRE(jrePath, extractPath);
      fs.unlinkSync(jrePath); //elimina el .zip luego de extraerlo
    } catch (error) {
      return null;
    }
  }
  return extractPath;
}


/**
 * Permite saber si el usuario tiene instalado Java en su sistema operativo.
 * @returns {Promise<boolean>} instalado - Retorna true en caso de que JAVA esté instalado, false en caso contrario.
 */
async function javaInstalado() {
    return new Promise((resolve) => {
      exec('java -version', (error, stdout, stderr) => {
        if (error) {
          resolve(false);
          return;
        }
        if (stderr.includes('java version') || stderr.includes('openjdk version')) {
          resolve(true);
        } else {
          resolve(false);
        }
      });
    });
  }

 /**
 * @param {vscode.ExtensionContext} context
 * Ejecuta la configuración del plugin, muestra el estado del proceso y setea el PATH de java una vez instalado el JRE.
 */
async function setup(context) {
    try {
        await vscode.window.withProgress({
            location: vscode.ProgressLocation.Notification,
            title: msg("instalando"),
            cancellable: false
          }, async () => {
            const instalado = await javaInstalado(); // si devuelve false siempre deberia descargarse el jre siempre
            if(!instalado){
                let jrePath = await instalarJRE(context);
                jrePath = path.join(jrePath, 'jre');
                context.globalState.update('jrePath', jrePath); 
            } else {
                const javaPath = await getJavaPath()
                context.globalState.update('jrePath', javaPath);
            }
            context.globalState.update('instalado', true);
            return Promise.resolve();
      });
      vscode.window.showInformationMessage(msg("instalado"));
    } catch (error) {
      vscode.window.showErrorMessage(msg("errorInstalando") + error);
    }
  }

  /**
 * Obtiene el path del JDK o JRE de Java instalado en el SO.
 * @returns Una promesa que resuelve al path del JDK o JRE si está instalado, de lo contrario `null`.
 */
  async function getJavaPath() {
    return new Promise((resolve, reject) => {
      exec('java -XshowSettings:properties -version', (error, stdout, stderr) => {
        if (error) {
          reject();
          return;
        }
        const output = stderr || stdout;
        const javaHomeMatch = output.match(/java\.home = (.*)/);
        if (javaHomeMatch && javaHomeMatch[1]) {
          resolve(javaHomeMatch[1].trim());
        } else {
          reject();
        }
      });
    });
  }


module.exports = {
 setup, javaInstalado
};
