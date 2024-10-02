const fs = require('fs');
const vscode = require('vscode');
const path = require('path');
const i18next = require('i18next');

/**
 * Carga el archivo json del idioma.
 * @param {*} lang - Idioma a cagar ('es' o 'en')
 * @returns 
 */
function cargarLocale(lang) {
  const filePath = path.join(__dirname, 'locales', `${lang}.json`);
    const fileContent = fs.readFileSync(filePath, 'utf8');
    return JSON.parse(fileContent);
}

// Configuración de i18next
i18next.init({
  fallbackLng: 'es',
  lng: 'es',
  resources: {
    en: {
      translation: cargarLocale('en'),
    },
    es: {
      translation: cargarLocale('es'),
    },
  },
  interpolation: {
    escapeValue: false,
  },
});

/**
 * Retorna el mensaje correspondiente a una key en el idioma actual.
 * @param {*} key - key del mensaje.
 * @returns 
 */
function msg(key) {
  return i18next.t(key);
}
/**
 * Retorna el idioma seleccionado por el usuario en la configuracion de SIMAE.
 */
function getLocale() {
    let idioma = vscode.workspace.getConfiguration('SIMAE').get('idioma');
    //obtener idioma actual segun configuracion o vs code
    if (idioma == 'Ingles') {
      return 'en';
    } else if (idioma == 'Español') {
      return 'es';
    } else {
      const vsLanguage = vscode.env.language;
      if (vsLanguage.startsWith('en')) {
        return 'en';
      } else{
        return 'es';
      }
    }
  }
module.exports = { msg, getLocale };
