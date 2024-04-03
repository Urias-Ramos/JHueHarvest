package com.withoutstudios.jhueharvest.core.service;

import java.util.Comparator;

/**
 * Esta clase implementa la interfaz Comparator para comparar objetos ColorLibraryCardService
 *  en función de la fecha en orden descendente.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class DescendingDate implements Comparator<ColorLibraryCardService> {
	
	@Override
	public int compare(ColorLibraryCardService o1, ColorLibraryCardService o2) {
		return o2.getDate().compareTo(o1.getDate());
	}
}