package com.withoutstudios.jhueharvest.core.service;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.withoutstudios.jhueharvest.util.Manifest;

/**
 * La clase ColorLibraryCardService gestiona los datos de cada paleta de color.<br>
 * Esta clase representa una paleta de color y almacena su nombre, estado de favorito, fecha de creación y lista de colores.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class ColorLibraryCardService implements Serializable {
	private String name;
	private boolean favorite;
	private Date date;
	private ArrayList<Color> colorList;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor de la clase ColorLibraryCardService.
     * 
     * @param name El nombre de la paleta de color
     * @param colorList La lista de colores de la paleta
     */
	public ColorLibraryCardService(String name, ArrayList<Color> colorList) {
		this.name = name;//se le asigna el nombre
		this.favorite = false;//por defecto el estado de favorito es false
		this.date = new Date();//la fecha de creacion se establece en el momento actual
		
		//si la lista de color sobrepasa la cantidad maxima permitida, esta se corta y solo se guardan la cantidad de colores permitidos
		if(colorList.size() > Manifest.MAX_TOTAL_PALETTE_CARD_LIBRARY) {
			this.colorList = new ArrayList<Color>(colorList.subList(0, Manifest.MAX_TOTAL_PALETTE_CARD_LIBRARY));
		}
		else {//se crea la lista con todos los colores disponibles
			this.colorList = new ArrayList<Color>(colorList);
		}
	}
	
	/**
	 * Se obtiene el nombre de la paleta color
	 * 
	 * @return nombre de la paleta de color
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Se establece un nombre para la paleta de color
	 * 
	 * @param name nombre para la paleta de color
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
     * Verifica si la paleta de color está marcada como favorita.
     * 
     * @return true si la paleta de color está marcada como favorita, false en caso contrario
     */
	public boolean isFavorite() {
		return favorite;
	}
	
	/**
     * Establece el estado de favorito para la paleta de color.
     * 
     * @param favorite true para marcar la paleta de color como favorita, false para desmarcarla
     */
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
	
	/**
     * Obtiene la fecha de creación de la paleta de color.
     * 
     * @return La fecha de creación de la paleta de color
     */
	public Date getDate() {
		return date;
	}
	
	/**
     * Establece la fecha de creación de la paleta de color.
     * 
     * @param date La nueva fecha de creación de la paleta de color
     */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
     * Obtiene la lista de colores de la paleta de color.
     * 
     * @return La lista de colores de la paleta de color
     */
	public ArrayList<Color> getColorList() {
		return colorList;
	}
	
	/**
     * Establece la lista de colores de la paleta de color.
     * 
     * @param colorList La nueva lista de colores de la paleta de color
     */
	public void setColorList(ArrayList<Color> colorList) {
		this.colorList = colorList;
	}
}