package com.withoutstudios.jhueharvest.core.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * ColorPaletteManager es una clase diseñada para administrar una colección de objetos ColorLibraryCardService,<br> 
 * ofrece funcionalidades para añadir y eliminar paletas de colores, así como para realizar diversas operaciones de gestión
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
	 * Obtiene el número total de paletas de color en la lista.
	 *
	 * @return el número total de paletas de color en la lista
	 */
	public int getTotalColorPalette() {
		return colorPaletteList.size();
	}
	
	/**
	 * Obtiene el objeto ColorLibraryCardService en el índice especificado de la lista.
	 *
	 * @param index el índice del objeto a obtener
	 * @return el objeto ColorLibraryCardService en el índice especificado
	 */
	public ColorLibraryCardService getColorLibraryCardService(int index) {
		return colorPaletteList.get(index);
	}
	
	/**
	 * Ordena la lista de paletas de color en orden alfabético ascendente según el nombre.
	 */
	public void sortAZ() {
		Collections.sort(colorPaletteList, Comparator.comparing(ColorLibraryCardService::getName));
	}
	
	/**
	 * Ordena la lista de paletas de color en orden alfabético descendente según el nombre.
	 */
	public void sortZA() {
		Collections.sort(colorPaletteList, Comparator.comparing(ColorLibraryCardService::getName).reversed());
	}
	
	/**
	 * Ordena la lista de paletas de color en orden ascendente según la fecha.
	 * Utiliza la clase AscendingDate como comparador para la ordenación.
	 */
	public void sortAscendingDate() {
		Collections.sort(colorPaletteList, new AscendingDate());
	}
	
	/**
	 * Ordena la lista de paletas de color en orden descendente según la fecha.
	 * Utiliza la clase DescendingDate como comparador para la ordenación.
	 */
	public void sortDescendingDate() {
		Collections.sort(colorPaletteList, new DescendingDate());
	}
	
	/**
	 * Busca coincidencias en el nombre de las paletas de color y devuelve una lista con los índices
	 * de las coincidencias encontradas.
	 *
	 * @param key la palabra clave a buscar
	 * @return una lista de los índices de las paletas que contienen la palabra clave en su nombre
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