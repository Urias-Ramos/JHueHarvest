package com.withoutstudios.jhueharvest.core.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.withoutstudios.jhueharvest.ui.MainFrame;
import com.withoutstudios.jhueharvest.util.Manifest;
import com.withoutstudios.jhueharvest.util.enumerators.DataType;
import com.withoutstudios.jhueharvest.util.enumerators.ViewType;

/**
 * Esta clase es la que se encarga de manejar toda la iteracion de la UI con los servicios o funciones
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class MainFrameController {
	private ViewType viewType;

	public MainFrameController(MainFrame view) {
		viewType = ViewType.EXTRACTOR_IMAGE;
		
		lookAndFeel(1, view);
		
		view.getBtnChangeView().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(viewType == ViewType.EXTRACTOR_IMAGE) {
					viewType = ViewType.COLOR_PALETTE;
				}
				else if(viewType == ViewType.COLOR_PALETTE) {
					viewType = ViewType.EXTRACTOR_IMAGE;
				}
				
				switch(viewType) {
				case EXTRACTOR_IMAGE:
					view.getCard().show(view.getPanelCard(), "dashboard");
					break;
				case COLOR_PALETTE:
					view.getCard().show(view.getPanelCard(), "library");
					break;
				case ABOUT:
					view.getCard().show(view.getPanelCard(), "about");
					break;
					default:
				}
			}
		});
		
		view.getBtnOption().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if((e.getButton() == MouseEvent.BUTTON1)) {
					view.getPopupMenuSetting().updateUI();
					SwingUtilities.updateComponentTreeUI(view.getPopupMenuSetting());
					view.getPopupMenuSetting().pack();
					view.getPopupMenuSetting().show(e.getComponent(), e.getComponent().getX() - 44, e.getComponent().getY() + 26);
				}
			}
			
		});
		
		view.getItemSetting().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getCard().show(view.getPanelCard(), "setting");
			}
		});
		
		view.getBtnAbout().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getCard().show(view.getPanelCard(), "about");
			}
		});
		
		view.getPanelSetting().getComboLenguaje().addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					Manifest.IDIOME.changedIdiome(view.getPanelSetting().getComboLenguaje().getSelectedIndex());
					Manifest.MANAGER_DATA_FILE.getApplicationPreferences().setIdiomeIndex(view.getPanelSetting().getComboLenguaje().getSelectedIndex());
					Manifest.MANAGER_DATA_FILE.save(DataType.PROFILE);
				}
			}
		});
		
		view.getPanelSetting().getBtnWitheTheme().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lookAndFeel(0, view);
				Manifest.MANAGER_DATA_FILE.getApplicationPreferences().setThemeIndex(0);
				Manifest.MANAGER_DATA_FILE.save(DataType.PROFILE);
			}
		});
		
		view.getPanelSetting().getBtnBlackTheme().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lookAndFeel(1, view);
				Manifest.MANAGER_DATA_FILE.getApplicationPreferences().setThemeIndex(1);
				Manifest.MANAGER_DATA_FILE.save(DataType.PROFILE);
			}
		});
		
		view.getPanelSetting().getComboLenguaje().setSelectedIndex(Manifest.MANAGER_DATA_FILE.getApplicationPreferences().getIdiomeIndex());
		
		if(Manifest.MANAGER_DATA_FILE.getApplicationPreferences().getThemeIndex() == 0) {
			view.getPanelSetting().getBtnWitheTheme().setSelected(true);
			view.getPanelSetting().getBtnBlackTheme().setSelected(false);
			lookAndFeel(0, view);
		}
		else {
			view.getPanelSetting().getBtnBlackTheme().setSelected(true);
			view.getPanelSetting().getBtnWitheTheme().setSelected(false);
			lookAndFeel(1, view);
		}
	}
	
	private void lookAndFeel(int theme, MainFrame view) {
		try {
			switch(theme) {
			case 0:
				FlatLightLaf.setup();
				UIManager.setLookAndFeel(new FlatLightLaf());
				UIManager.put("TitlePane.unifiedBackground", false);
				SwingUtilities.updateComponentTreeUI(view);
				FlatLightLaf.revalidateAndRepaintAllFramesAndDialogs();
				Manifest.THEME_BACKGROUND = Color.WHITE;
				break;
			case 1:
				FlatDarkLaf.setup();
				UIManager.setLookAndFeel(new FlatDarkLaf());
				SwingUtilities.updateComponentTreeUI(view);
				UIManager.put("TitlePane.unifiedBackground", false);
				UIManager.put("TitlePane.centerTitle", true);
				FlatDarkLaf.repaintAllFramesAndDialogs();
				Manifest.THEME_BACKGROUND = Color.decode("#303234");
				break;
			}
			
			if(view.getColorExtractionOptionsPanel() != null) {
				view.getColorExtractionOptionsPanel().setBackground(Manifest.THEME_BACKGROUND);
				view.getColorExtractionOptionsPanel().getPanelPaletteList().setBackground(Manifest.THEME_BACKGROUND);
				view.getLibraryPanel().getPanelPaletteList().setBackground(Manifest.THEME_BACKGROUND);
			}
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(view, "¡Required Windows 10!", Manifest.applicationName, JOptionPane.ERROR_MESSAGE);
		}
	}
}