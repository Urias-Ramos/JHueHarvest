package com.withoutstudios.jhueharvest.core.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.withoutstudios.jhueharvest.core.service.ColorPaletteManager;
import com.withoutstudios.jhueharvest.util.Manifest;
import com.withoutstudios.jhueharvest.util.enumerators.DataType;

/**
 * Esta clase se encarga de manejar la creación de directorios y archivos, así como de cargar y guardar 
 * los datos de la aplicación
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class ManagerDataFile {
	private ApplicationPreferences applicationPreferences;
	
	/**
	 * Constructor de la clase ManagerDataFile
	 * 
	 * Carga al inicio los datos de la aplicacion, libreria de paleta de color y el directorio de exportacion
	 */
	public ManagerDataFile() {
		applicationPreferences = new ApplicationPreferences();
		
		loadDataFile(DataType.PROFILE);
		loadDataFile(DataType.PALETTE);
		loadDataFile(DataType.EXPORT);
	}
	
	public void loadDataFile(DataType dataType) {
		if(dataExists(getPath(dataType))) {
			switch(dataType) {
			case PROFILE:
				applicationPreferences = (ApplicationPreferences) load(dataType);
				break;
			case PALETTE:
				Manifest.COLOR_PALETTE_MANAGER = (ColorPaletteManager) load(dataType);
				break;
			default:
				break;
			}
		}
		else {
			save(dataType);
		}
	}
	
	/**
	 * Verifica si el archivo existe, si el archivo no existe este crea el directorio y el archivo.
	 * 
	 * @param path ruta del archivo a verificar
	 * @return True si el archivo existe, False de lo contrario
	 */
	private boolean dataExists(String path) {
		File fichero = new File(path);
		if(fichero.exists()) {
			return true;
		}
		else {
			File directory = new File(path).getParentFile();
			directory.mkdirs();
			return false;
		}
	}
	
	/**
	 * Devuelve la ruta del tipo de dato que se desea obtener
	 * 
	 * @param dataType tipo de dato a obtener la ruta
	 * @return Ruta o directorio donde se guardaran los archivos
	 */
	private String getPath(DataType dataType) {
		switch(dataType) {
		case PROFILE:
			return new File("").getAbsolutePath()+"\\data\\profile.dat";
		case PALETTE:
			return new File("").getAbsolutePath()+"\\data\\palette.dat";
		case EXPORT:
			return new File("").getAbsolutePath()+"\\library\\library";
			default:
				return null;
		}
	}
	
	/**
	 * Carga el tipo de dato solicitado
	 * 
	 * @param dataType Tipo de dato a cargar
	 * @return Un objeto con la data serializada
	 */
	public Object load(DataType dataType) {
		FileInputStream fis = null;
		ObjectInputStream entrada = null;
		
		try {
			fis = new FileInputStream(getPath(dataType));
			entrada = new ObjectInputStream(fis);
			
			return entrada.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Guarda el tipo de dato solicitado
	 * 
	 * @param dataType tipo de dato a guardar
	 */
	public void save(DataType dataType) {
		dataExists(getPath(dataType));
		FileOutputStream fos = null;
		ObjectOutputStream salida = null;
		
		try {
			fos = new FileOutputStream(getPath(dataType));
			salida = new ObjectOutputStream(fos);
			
			switch(dataType) {
			case PROFILE:
				salida.writeObject(getApplicationPreferences());
				break;
			case PALETTE:
				salida.writeObject(Manifest.COLOR_PALETTE_MANAGER);
				break;
			default:
				break;
			}
			
			fos.close();
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Obtiene ApplicationPreferences
	 * 
	 * @return ApplicationPreferences
	 */
	public ApplicationPreferences getApplicationPreferences() {
		return applicationPreferences;
	}
}