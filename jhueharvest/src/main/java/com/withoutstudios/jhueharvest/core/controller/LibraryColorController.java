package com.withoutstudios.jhueharvest.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.withoutstudios.jhueharvest.core.service.ColorLibraryCardService;
import com.withoutstudios.jhueharvest.ui.library.ColorLibraryCard;
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
public class LibraryColorController {
	private boolean searchInit, onlyFavorite;

	public LibraryColorController(JFrame ventana, LibraryPanel view) {
		searchInit = false;
		onlyFavorite = false;
		
		view.getBtnAddPaletteCard().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addPaletteCard(ventana, view);
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
				view.gbcList.gridx = 0;
				for(int i=0; i<view.getPanelPaletteList().getComponentCount(); i++) {
					view.getPanelPaletteList().add(view.getPanelPaletteList().getComponent(i), view.gbcList);
					
					view.gbcList.gridx++;

		            if (view.gbcList.gridx == 3) {
		            	view.gbcList.gridx = 0;
		            	view.gbcList.gridy++;
		            }
				}
			}
		});
		
		ventana.addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentResized(ComponentEvent e) {
				view.panelCard.setBounds(0, 0, view.jLayeredPane.getWidth(), view.jLayeredPane.getHeight());
				view.getBtnAddPaletteCard().setBounds(view.jLayeredPane.getWidth() - 74, view.jLayeredPane.getHeight() - 74 , 54, 54);
				
				view.updateUI();
				view.repaint();
			}
			
		});
		
		//filter
		view.getBtnClearText().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getBtnClearText().setEnabled(false);
				view.getBtnClearText().setIcon(null);
				view.getTxtSearch().setText("");
				
				if(searchInit) {
					addCard(ventana, view, true);
				}
			}
		});
		
		view.getTxtSearch().addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					searchPalette(ventana, view);
				}
				
				if(view.getTxtSearch().getText().length() > 0) {
					view.getBtnClearText().setEnabled(true);
					view.getBtnClearText().setIcon(view.getIconClearText());
				}
				else {
					view.getBtnClearText().setEnabled(false);
					view.getBtnClearText().setIcon(null);
				}
			}
		});
		
		view.getBtnSearch().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchPalette(ventana, view);
			}
		});
		
		view.getFilter().addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					onlyFavorite = false;
					
					applyFilter(ventana, view, view.getFilter().getSelectedIndex());
					
					Manifest.MANAGER_DATA_FILE.getApplicationPreferences().setFilterIndex(view.getFilter().getSelectedIndex());
					Manifest.MANAGER_DATA_FILE.save(DataType.PROFILE);
					Manifest.MANAGER_DATA_FILE.save(DataType.PALETTE);
				}
			}
		});
		//filte
		
		view.getFilter().setSelectedIndex(-1);
		view.getFilter().setSelectedIndex(Manifest.MANAGER_DATA_FILE.getApplicationPreferences().getFilterIndex());
	}
	
	private void changeView(LibraryPanel view) {
		if(view.getPanelPaletteList().getComponentCount() > 0) {
			view.getCard().show(view.getPanelCard(), "library");
		}
		else {
			view.getCard().show(view.getPanelCard(), "empty");
		}
		
		view.getLblCount().setText(view.getPanelPaletteList().getComponentCount()+"/"+Manifest.TOTAL_PALETTE_LIBRARY);
		
		if(view.getPanelPaletteList().getComponentCount() < Manifest.TOTAL_PALETTE_LIBRARY) {
			view.getBtnAddPaletteCard().setEnabled(true);
		}
		else {
			view.getBtnAddPaletteCard().setEnabled(false);
		}
	}
	
	private void addPaletteCard(JFrame ventana, LibraryPanel view) {
		String name = "";
		name = JOptionPane.showInputDialog(ventana, Manifest.IDIOME.getValue("5006"), Manifest.applicationName, JOptionPane.QUESTION_MESSAGE);
		
		if(name != null) {
			if(!name.contentEquals("")) {
				Manifest.imageDebug.ColorPaletteList.clear();
				
				ColorLibraryCardService colorLibraryCardService = new ColorLibraryCardService(name.toUpperCase(), Manifest.imageDebug.ColorPaletteList);
				
				Manifest.COLOR_PALETTE_MANAGER.register(colorLibraryCardService);
				
				ColorLibraryCard colorLibraryCard = new ColorLibraryCard(view, colorLibraryCardService);
				new ColorLibraryCardController(colorLibraryCard, ventana);
				
				view.getPanelPaletteList().add(colorLibraryCard, view.gbcList);
				
				view.gbcList.gridx++;

	            if (view.gbcList.gridx == 3) {
	            	view.gbcList.gridx = 0;
	            	view.gbcList.gridy++;
	            }
	            
	            applyFilter(ventana, view, view.getFilter().getSelectedIndex());
				
	            Manifest.MANAGER_DATA_FILE.save(DataType.PALETTE);
				JOptionPane.showMessageDialog(ventana, Manifest.IDIOME.getValue("5007"), Manifest.applicationName, JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private void applyFilter(JFrame ventana, LibraryPanel view, int index) {
		switch(index) {
		case 0:
			Manifest.COLOR_PALETTE_MANAGER.sortDescendingDate();
			addCard(ventana, view, true);
			break;
		case 1:
			Manifest.COLOR_PALETTE_MANAGER.sortAscendingDate();
			addCard(ventana, view, true);
			break;
		case 2:
			Manifest.COLOR_PALETTE_MANAGER.sortAZ();
			addCard(ventana, view, true);
			break;
		case 3:
			Manifest.COLOR_PALETTE_MANAGER.sortZA();
			addCard(ventana, view, true);
			break;
		case 4:
			onlyFavorite = true;
			addCard(ventana, view, true);
			break;
		}
	}
	
	public void addPaletteCardAndColor(JFrame ventana, LibraryPanel view) {
		if(view.getPanelPaletteList().getComponentCount() >= Manifest.TOTAL_PALETTE_LIBRARY) {
			JOptionPane.showMessageDialog(ventana, Manifest.IDIOME.getValue("5008"), Manifest.applicationName, JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			String name = "";
			name = JOptionPane.showInputDialog(ventana, Manifest.IDIOME.getValue("5006"), Manifest.applicationName, JOptionPane.QUESTION_MESSAGE);
			
			if(name != null) {
				if(!name.contentEquals("")) {
					ColorLibraryCardService colorLibraryCardService = new ColorLibraryCardService(name.toUpperCase(), Manifest.imageDebug.ColorPaletteList);
					
					Manifest.COLOR_PALETTE_MANAGER.register(colorLibraryCardService);
					
					ColorLibraryCard colorLibraryCard = new ColorLibraryCard(view, colorLibraryCardService);
					new ColorLibraryCardController(colorLibraryCard, ventana);
					
					view.getPanelPaletteList().add(colorLibraryCard, view.gbcList);
					
					view.gbcList.gridx++;

		            if (view.gbcList.gridx == 3) {
		            	view.gbcList.gridx = 0;
		            	view.gbcList.gridy++;
		            }
		            
		            applyFilter(ventana, view, view.getFilter().getSelectedIndex());
					
		            Manifest.MANAGER_DATA_FILE.save(DataType.PALETTE);
					JOptionPane.showMessageDialog(ventana, Manifest.IDIOME.getValue("5007"), Manifest.applicationName, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	
	private void addCard(JFrame ventana, LibraryPanel view, boolean resetGridX) {
		view.getPanelPaletteList().removeAll();
		
		if(resetGridX) {
			view.gbcList.gridx = 0;
		}
		
		for(int i=0; i<Manifest.COLOR_PALETTE_MANAGER.getTotalColorPalette(); i++) {
			ColorLibraryCard colorLibraryCard = null;
			
			if(onlyFavorite && !Manifest.COLOR_PALETTE_MANAGER.getColorLibraryCardService(i).isFavorite()) {
				continue;
			}
			
			colorLibraryCard = new ColorLibraryCard(view, Manifest.COLOR_PALETTE_MANAGER.getColorLibraryCardService(i));
			new ColorLibraryCardController(colorLibraryCard, ventana);
			view.getPanelPaletteList().add(colorLibraryCard, view.gbcList);
			
			view.gbcList.gridx++;
            if(view.gbcList.gridx == 3) {
            	view.gbcList.gridx = 0;
            	view.gbcList.gridy++;
            }
		}
		
		view.getPanelPaletteList().updateUI();
		view.getPanelPaletteList().repaint();
	}
	
	private void searchPalette(JFrame ventana, LibraryPanel view) {
		searchInit = true;
		view.getPanelPaletteList().removeAll();
		
		ArrayList<Integer> list = Manifest.COLOR_PALETTE_MANAGER.searchName(view.getTxtSearch().getText());
		
		if(list.size() > 0) {
			view.getPanelPaletteList().removeAll();
			
			view.gbcList.gridx = 0;
			
			for(int i=0; i<list.size(); i++) {
				ColorLibraryCard colorLibraryCard = null;
				
				if(onlyFavorite && !Manifest.COLOR_PALETTE_MANAGER.getColorLibraryCardService(list.get(i)).isFavorite()) {
					continue;
				}
				
				colorLibraryCard = new ColorLibraryCard(view, Manifest.COLOR_PALETTE_MANAGER.getColorLibraryCardService(list.get(i)));
				new ColorLibraryCardController(colorLibraryCard, ventana);
				view.getPanelPaletteList().add(colorLibraryCard, view.gbcList);
				
				view.gbcList.gridx++;
	            if(view.gbcList.gridx == 3) {
	            	view.gbcList.gridx = 0;
	            	view.gbcList.gridy++;
	            }
			}
			
			view.getPanelPaletteList().updateUI();
			view.getPanelPaletteList().repaint();
		}
	}
}