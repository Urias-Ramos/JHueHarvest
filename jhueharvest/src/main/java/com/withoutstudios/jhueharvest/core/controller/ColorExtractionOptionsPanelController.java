package com.withoutstudios.jhueharvest.core.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.withoutstudios.jhueharvest.ui.ColorExtractionOptionsPanel;
import com.withoutstudios.jhueharvest.ui.ColorPaletteCard;
import com.withoutstudios.jhueharvest.ui.MainFrame;
import com.withoutstudios.jhueharvest.ui.library.LibraryPanel;
import com.withoutstudios.jhueharvest.util.Manifest;
import com.withoutstudios.jhueharvest.util.enumerators.DataType;

/**
 * Esta clase es la que se encarga de manejar toda la iteracion de la UI con los servicios o funciones
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class ColorExtractionOptionsPanelController {
	private ColorExtractionOptionsPanel view;

	public ColorExtractionOptionsPanelController(ColorExtractionOptionsPanel view, LibraryPanel libraryPanel, MainFrame ventana) {
		this.view = view;
		
		view.getTxtPaletteCount().setValue(Manifest.MANAGER_DATA_FILE.getApplicationPreferences().getColorCounter());
		view.getComboExtractionMethod().setSelectedIndex(Manifest.MANAGER_DATA_FILE.getApplicationPreferences().getIndexExtractionMethod());
		
		view.getTxtPaletteCount().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				Manifest.MANAGER_DATA_FILE.getApplicationPreferences().setColorCounter((int) view.getTxtPaletteCount().getValue());
				Manifest.MANAGER_DATA_FILE.save(DataType.PROFILE);
			}
		});
		
		view.getComboExtractionMethod().addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					Manifest.MANAGER_DATA_FILE.getApplicationPreferences().setIndexExtractionMethod(view.getComboExtractionMethod().getSelectedIndex());
					Manifest.MANAGER_DATA_FILE.save(DataType.PROFILE);
				}
			}
		});
		
		view.getBtnExtractor().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(Manifest.imageDebug.image != null) {
					Manifest.imageDebug.ColorPaletteList.clear();
					getPaletteColors(view.getPanelPaletteList(), Integer.parseInt(view.getTxtPaletteCount().getValue().toString()), view.getComboExtractionMethod().getSelectedIndex());
					view.getBtnDownload().setEnabled(true);
				}
				else {
					view.getBtnDownload().setEnabled(false);
					JOptionPane.showMessageDialog(ventana, Manifest.IDIOME.getValue("101"), Manifest.applicationName, JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
			}
		});
		
		view.getBtnDownload().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.getLibraryColorController().addPaletteCardAndColor(ventana, libraryPanel);
			}
		});
		
		view.getPanelPaletteList().addContainerListener(new ContainerListener() {

			@Override
			public void componentAdded(ContainerEvent e) {
				changeView(view);
			}

			@Override
			public void componentRemoved(ContainerEvent e) {
				changeView(view);
			}
		});
	}
	
	/**
	 * Este metodo agrega un nuevo color a la lista.
	 * 
	 * @param panelList lista con los colores
	 * @param color nuevo color a agregar
	 */
	public void addNewColorPalette(JPanel panelList, Color color) {
		panelList.add(new ColorPaletteCard(color, panelList));
		panelList.revalidate();
	}
	
	/**
	 * Este metodo se encarga de obtener la paleta de colores y luego mostralos.
	 * 
	 * @param panelList panel que guardara los colores.
	 * @param count total de colores a mostrar.
	 * @param method metodo a utilizar para obtener la paleta.
	 */
	public void getPaletteColors(JPanel panelList, int count, int method) {
		switch(method) {
		case 0:
			Manifest.imageDebug.ColorPaletteList = Manifest.imageDebug.getPaletteQuantization(Manifest.imageDebug.image, count);
			break;
		case 1:
			Manifest.imageDebug.ColorPaletteList = Manifest.imageDebug.getDominantColorPalette(Manifest.imageDebug.image, count);
			break;
		}
		
		//elimina los colores mostrados anteriormente
		panelList.removeAll();
		
		//agrega los nuevos colores
		for (Color color : Manifest.imageDebug.ColorPaletteList) {
			panelList.add(new ColorPaletteCard(color, panelList));
        }
		//para que se actualize el panel
		panelList.revalidate();
	}
	
	public void getServiceAddNewColorPalette(Color color) {
		addNewColorPalette(view.getPanelPaletteList(), color);
	}
	
	private void changeView(ColorExtractionOptionsPanel view) {
		if(view.getPanelPaletteList().getComponentCount() > 0) {
			view.getBtnDownload().setEnabled(true);
			view.getCard().show(view.getPanelCard(), "paletteList");
		}
		else {
			view.getBtnDownload().setEnabled(false);
			view.getCard().show(view.getPanelCard(), "empty");
		}
	}
	
	public ColorExtractionOptionsPanel getView() {
		return view;
	}
}