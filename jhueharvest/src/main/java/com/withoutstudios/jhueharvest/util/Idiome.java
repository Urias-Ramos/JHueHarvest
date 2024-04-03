package com.withoutstudios.jhueharvest.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

/**
 * Esta clase se encarga de cargar el archivo de idioma y aplicarlo a la aplicacion
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class Idiome {
    private ResourceBundle bundle;

    public Idiome() {
        changedIdiome(0);
    }

    public void changedIdiome(int id) {
        switch(id) {
            case 0:
                bundle = loadResourceBundle("idiome/Spanish.properties");
                break;
            case 1:
                bundle = loadResourceBundle("idiome/English.properties");
                break;
            case 2:
                bundle = loadResourceBundle("idiome/Portugues.properties");
                break;
            case 3:
                bundle = loadResourceBundle("idiome/France.properties");
                break;
        }
        Manifest.translationManager.updateIdiome();
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }

    private ResourceBundle loadResourceBundle(String resourceName) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            return new PropertyResourceBundle(new InputStreamReader(inputStream, "UTF-8"));
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "¡Idiome error!", Manifest.applicationName, JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
