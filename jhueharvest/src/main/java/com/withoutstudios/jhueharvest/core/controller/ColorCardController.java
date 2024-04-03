package com.withoutstudios.jhueharvest.core.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JColorChooser;
import javax.swing.JPanel;

import com.withoutstudios.jhueharvest.ui.library.ColorCard;
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
public class ColorCardController {
	private JPanel selected;
	private Point offSet, newPoint;
	
	private ArrayList<Component> componentList;

	public ColorCardController(ColorCard view) {
		componentList = new ArrayList<Component>();
		
		view.getBtnChangeCardColor().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changeCardColor(view);
			}
		});
		
		view.getBtnDeleteColorCard().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeCardColor(view);
			}
		});
		
		MouseAdapter mouseDragPositionColorCard = new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				view.getPanelOptions().setVisible(true);
				view.getLblHexCode().setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				view.getPanelOptions().setVisible(false);
				view.getLblHexCode().setVisible(false);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				selected = (JPanel) e.getSource();
				view.getColorLibraryCard().getPanelPaletteList().add(selected, 0);
				offSet = e.getPoint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if(selected != null) {
					
					componentList.clear();
					
					for(int i=0; i<view.getColorLibraryCard().getPanelPaletteList().getComponentCount(); i++) {
						componentList.add(view.getColorLibraryCard().getPanelPaletteList().getComponent(i));
					}
					
					Collections.sort(componentList, new Comparator<Component>() {
						
						@Override
						public int compare(Component o1, Component o2) {
							return Integer.compare(o1.getY(), o2.getY());
						}
					});
					
					view.getColorLibraryCard().getPanelPaletteList().removeAll();
					view.getColorLibraryCard().getColorLibraryCardService().getColorList().clear();
					for(int i=0; i<componentList.size(); i++) {
						view.getColorLibraryCard().getPanelPaletteList().add(componentList.get(i));
						view.getColorLibraryCard().getColorLibraryCardService().getColorList().add(componentList.get(i).getBackground());
					}
					
					Manifest.MANAGER_DATA_FILE.save(DataType.PALETTE);
				}
				
				selected = null;
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if(selected != null) {
					newPoint = selected.getLocation();
					newPoint.translate(e.getX() - offSet.x, e.getY() - offSet.y);
					selected.setLocation(selected.getX(), newPoint.y);
				}
			}
		};
		
		view.addMouseListener(mouseDragPositionColorCard);
		view.addMouseMotionListener(mouseDragPositionColorCard);
		
		MouseAdapter mouseShowAndHide = new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(selected == null) {
					view.getPanelOptions().setVisible(true);
					view.getLblHexCode().setVisible(true);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if(selected == null) {
					view.getPanelOptions().setVisible(false);
					view.getLblHexCode().setVisible(false);
				}
			}
		};
		
		view.getPanelOptions().addMouseListener(mouseShowAndHide);
		view.getBtnChangeCardColor().addMouseListener(mouseShowAndHide);
		view.getBtnDeleteColorCard().addMouseListener(mouseShowAndHide);
	}
	
	private void changeCardColor(ColorCard view) {
		Color color = JColorChooser.showDialog(view.getColorLibraryCard().getLibraryPreview().getMainFrame(), Manifest.applicationName, view.getBackground());
		
		if(color != null) {
			view.setBackground(color);
			view.getLblHexCode().setForeground(Manifest.imageDebug.getForegroundColor(view.getBackground()));
			view.getLblHexCode().setText(Manifest.imageDebug.convertColorToHTML(view.getBackground()).toUpperCase());
			
			view.getColorLibraryCard().getColorLibraryCardService().getColorList().clear();
			for(int i=0; i<view.getColorLibraryCard().getPanelPaletteList().getComponentCount(); i++) {
				view.getColorLibraryCard().getColorLibraryCardService().getColorList().add(view.getColorLibraryCard().getPanelPaletteList().getComponent(i).getBackground());
			}
			
			Manifest.MANAGER_DATA_FILE.save(DataType.PALETTE);
		}
	}
	
	private void removeCardColor(ColorCard view) {
		view.getColorLibraryCard().getPanelPaletteList().remove(view);
		
		view.getColorLibraryCard().getColorLibraryCardService().getColorList().clear();
		for(int i=0; i<view.getColorLibraryCard().getPanelPaletteList().getComponentCount(); i++) {
			view.getColorLibraryCard().getColorLibraryCardService().getColorList().add(view.getColorLibraryCard().getPanelPaletteList().getComponent(i).getBackground());
		}
		
		Manifest.MANAGER_DATA_FILE.save(DataType.PALETTE);
		
		view.getColorLibraryCard().getPanelPaletteList().updateUI();
		view.getColorLibraryCard().getPanelPaletteList().repaint();
	}
}