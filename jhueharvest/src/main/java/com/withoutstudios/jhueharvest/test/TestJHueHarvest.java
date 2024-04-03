package com.withoutstudios.jhueharvest.test;

import javax.swing.JFrame;

import com.withoutstudios.jhueharvest.ui.MainFrame;
import com.withoutstudios.jhueharvest.util.Manifest;

/**
 * La clase TestJHueHarvest es el punto de entrada principal de la aplicación.
 * Sirve como lanzador de la aplicación y configura la ventana principal.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class TestJHueHarvest {
	public static void main(String[] args) {
		JFrame ventana = new MainFrame();
		ventana.setTitle(Manifest.applicationName);
		ventana.setIconImage(Manifest.imageDebug.getAdjustedImage(ventana.getClass().getResource("/icon/icon_app.png"), 64, 64));
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(955, 603);
		ventana.setResizable(true);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
}