package com.withoutstudios.jhueharvest.core.data;

import java.io.Serializable;

/**
 * ApplicationPreferences es una clase que administra los datos de preferencias de la aplicacion, como los ajustes.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class ApplicationPreferences implements Serializable {
	private int colorCounter;
	private int indexExtractionMethod;
	
	private int filterIndex;
	
	private int idiomeIndex;
	private int themeIndex;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationPreferences() {
		colorCounter = 6;
		indexExtractionMethod = 0;
		
		filterIndex = 0;
		
		idiomeIndex = 0;
		themeIndex = 1;
	}
	
	public int getColorCounter() {
		return colorCounter;
	}
	
	public void setColorCounter(int colorCounter) {
		this.colorCounter = colorCounter;
	}

	public int getIndexExtractionMethod() {
		return indexExtractionMethod;
	}

	public void setIndexExtractionMethod(int indexExtractionMethod) {
		this.indexExtractionMethod = indexExtractionMethod;
	}

	public int getFilterIndex() {
		return filterIndex;
	}

	public void setFilterIndex(int filterIndex) {
		this.filterIndex = filterIndex;
	}

	public int getIdiomeIndex() {
		return idiomeIndex;
	}

	public void setIdiomeIndex(int idiomeIndex) {
		this.idiomeIndex = idiomeIndex;
	}

	public int getThemeIndex() {
		return themeIndex;
	}

	public void setThemeIndex(int themeIndex) {
		this.themeIndex = themeIndex;
	}
}