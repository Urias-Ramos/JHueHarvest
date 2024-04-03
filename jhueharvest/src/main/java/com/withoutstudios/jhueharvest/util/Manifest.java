package com.withoutstudios.jhueharvest.util;

import java.awt.Color;

import com.withoutstudios.jhueharvest.core.data.ManagerDataFile;
import com.withoutstudios.jhueharvest.core.service.ColorPaletteManager;
import com.withoutstudios.jhueharvest.ui.ImageViewPanel;

/**
 * Clase con atributos staticos que se usaran para tener un mejor acceso a la aplicacion.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class Manifest {
	public static String applicationName = "JHueHarvest";
	public static String version_app = "0.0.1";
	
	public static int maxTotalColorsPalette = 64;
	public static int TOTAL_PALETTE_LIBRARY = 27;
	public static int MAX_TOTAL_PALETTE_CARD_LIBRARY = 12;
	
	public static Color THEME_BACKGROUND = Color.decode("#ffffff");
	
	public static TranslationManager translationManager = new TranslationManager();
	public static Idiome IDIOME = new Idiome();
	
	public static ImageDebug imageDebug = new ImageDebug();
	public static ImageViewPanel imageViewPanel = new ImageViewPanel();
	
	public static ColorPaletteManager COLOR_PALETTE_MANAGER = new ColorPaletteManager();
	
	public static ManagerDataFile MANAGER_DATA_FILE = new ManagerDataFile();
}