package com.withoutstudios.jhueharvest.util;

import javax.swing.JComponent;

/**
 * Esta clase se encarga de guardar el Componentes graficos y la llave para poder modificar el idioma en tiempo real.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class TranslatableComponent {
	private JComponent component;
	private String key;
	
	/**
	 * Constructor de la clase, crean un Objeto TranslatableComponent con el componente y la llave asociado.
	 * 
	 * @param component componente grafico.
	 * @param key llave para obtener el texto.
	 */
	public TranslatableComponent(JComponent component, String key) {
		setComponent(component);
		setKey(key);
	}
	
	/**
	 * Obtiene el JComponent asociado.
	 *  
	 * @return JComponent
	 */
	public JComponent getComponent() {
		return component;
	}
	
	/**
	 * Cambiar el Component asociado.
	 * 
	 * @param component nuevo component
	 */
	public void setComponent(JComponent component) {
		this.component = component;
	}
	
	/**
	 * Devuelve la llave asociada.
	 * 
	 * @return llave para obtener el texto
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Establece una nueva llave.
	 * 
	 * @param key nueva llave
	 */
	public void setKey(String key) {
		this.key = key;
	}
}