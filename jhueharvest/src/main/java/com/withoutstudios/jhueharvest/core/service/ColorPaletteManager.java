package com.withoutstudios.jhueharvest.core.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * ColorPaletteManager es una clase dise�ada para administrar una colecci�n de objetos ColorLibraryCardService,<br> 
 * ofrece funcionalidades para a�adir y eliminar paletas de colores, as� como para realizar diversas operaciones de gesti�n
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class ColorPaletteManager implements Serializable {
	private ArrayList<ColorLibraryCardService> colorPaletteList;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor de la clase ColorPaletteManager.
     * Inicializa la lista de paletas de color.
     */
	public ColorPaletteManager() {
		colorPaletteList = new ArrayList<ColorLibraryCardService>();
	}
	
	/**
     * Registra un objeto ColorLibraryCardService en la lista.
     *
     * @param colorLibraryCardService el objeto ColorLibraryCardService a registrar
     */
	public void register(ColorLibraryCardService colorLibraryCardService) {
		colorPaletteList.add(colorLibraryCardService);
	}
	
	/**
     * Elimina un objeto ColorLibraryCardService de la lista.
     *
     * @param colorLibraryCardService el objeto ColorLibraryCardService a eliminar
     */
	public void remove(ColorLibraryCardService colorLibraryCardService) {
		colorPaletteList.remove(colorLibraryCardService);
	}
	
	/**
	 * Obtiene el n�mero total de paletas de color en la lista.
	 *
	 * @return el n�mero total de paletas de color en la lista
	 */
	public int getTotalColorPalette() {
		return colorPaletteList.size();
	}
	
	/**
	 * Obtiene el objeto ColorLibraryCardService en el �ndice especificado de la lista.
	 *
	 * @param index el �ndice del objeto a obtener
	 * @return el objeto ColorLibraryCardService en el �ndice especificado
	 */
	public ColorLibraryCardService getColorLibraryCardService(int index) {
		return colorPaletteList.get(index);
	}
	
	/**
	 * Ordena la lista de paletas de color en orden alfab�tico ascendente seg�n el nombre.
	 */
	public void sortAZ() {
		Collections.sort(colorPaletteList, Comparator.comparing(ColorLibraryCardService::getName));
	}
	
	/**
	 * Ordena la lista de paletas de color en orden alfab�tico descendente seg�n el nombre.
	 */
	public void sortZA() {
		Collections.sort(colorPaletteList, Comparator.comparing(ColorLibraryCardService::getName).reversed());
	}
	
	/**
	 * Ordena la lista de paletas de color en orden ascendente seg�n la fecha.
	 * Utiliza la clase AscendingDate como comparador para la ordenaci�n.
	 */
	public void sortAscendingDate() {
		Collections.sort(colorPaletteList, new AscendingDate());
	}
	
	/**
	 * Ordena la lista de paletas de color en orden descendente seg�n la fecha.
	 * Utiliza la clase DescendingDate como comparador para la ordenaci�n.
	 */
	public void sortDescendingDate() {
		Collections.sort(colorPaletteList, new DescendingDate());
	}
	
	/**
	 * Busca coincidencias en el nombre de las paletas de color y devuelve una lista con los �ndices
	 * de las coincidencias encontradas.
	 *
	 * @param key la palabra clave a buscar
	 * @return una lista de los �ndices de las paletas que contienen la palabra clave en su nombre
	 */
	public ArrayList<Integer> searchName(String key) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i=0; i<colorPaletteList.size(); i++) {
			if(colorPaletteList.get(i).getName().toUpperCase().contains(key.toUpperCase())) {
				list.add(i);
			}
		}
		
		return list;
	}
}