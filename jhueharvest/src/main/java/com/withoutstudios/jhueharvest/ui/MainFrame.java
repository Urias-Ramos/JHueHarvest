package com.withoutstudios.jhueharvest.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import com.withoutstudios.jhueharvest.core.controller.ColorExtractionOptionsPanelController;
import com.withoutstudios.jhueharvest.core.controller.ImageViewController;
import com.withoutstudios.jhueharvest.core.controller.LibraryColorController;
import com.withoutstudios.jhueharvest.core.controller.MainFrameController;
import com.withoutstudios.jhueharvest.ui.library.LibraryPanel;
import com.withoutstudios.jhueharvest.util.Manifest;

/**
 * Esta clase es el JFrame, aqui se crean todas las vistas de la aplicacion.<br><br>
 * 
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class MainFrame extends JFrame {
	private JButton btnChangeView, btnOption;
	private JPopupMenu popupMenuSetting;
	private JMenuItem itemSetting, btnAbout;
	
	private LibraryPanel libraryPanel;
	private LibraryColorController libraryColorController;
	
	private ColorExtractionOptionsPanel colorExtractionOptionsPanel;
	
	private PanelSetting panelSetting;
	
	private CardLayout card;
	private JPanel panelCard;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MainFrame() {
		this.setLayout(new BorderLayout(5, 5));
		
		this.setJMenuBar(createTitleBar());
		this.add(createUI(), BorderLayout.CENTER);
		
		new MainFrameController(this);
	}
	
	private JPanel createUI() {
		card = new CardLayout();
		
		panelCard = new JPanel(card);
		panelCard.setOpaque(false);
		
		libraryPanel = new LibraryPanel(this);
		libraryColorController = new LibraryColorController(this, libraryPanel);
		
		colorExtractionOptionsPanel = new ColorExtractionOptionsPanel();
		ColorExtractionOptionsPanelController imageColorPaletteController = new ColorExtractionOptionsPanelController(colorExtractionOptionsPanel, libraryPanel, this);
		
		new ImageViewController(Manifest.imageViewPanel, imageColorPaletteController, this);
		
		panelSetting = new PanelSetting();
		PanelAbout panelAbout = new PanelAbout();
		
		JPanel panelDashboard = new JPanel();
		panelDashboard.setOpaque(false);
		panelDashboard.setLayout(new BorderLayout(5, 5));
		
		panelDashboard.add(Manifest.imageViewPanel, BorderLayout.CENTER);
		panelDashboard.add(colorExtractionOptionsPanel, BorderLayout.EAST);
		
		panelCard.add(panelDashboard, "dashboard");
		panelCard.add(libraryPanel, "library");
		panelCard.add(panelSetting, "setting");
		panelCard.add(panelAbout, "about");
		
		card.show(panelCard, "dashboard");
		
		createPopupMenu();
		
		return panelCard;
	}
	
	private JMenuBar createTitleBar() {
		JToolBar panelPrincipal = new JToolBar();
		panelPrincipal.setFloatable(false);
		panelPrincipal.setOpaque(false);
		
		btnChangeView = new JButton();
		btnChangeView.setOpaque(false);
		btnChangeView.setFocusPainted(false);
		btnChangeView.setBorderPainted(false);
		btnChangeView.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_change.png"), 18, 18)));
		btnChangeView.setPreferredSize(new Dimension(44, 30));
		btnChangeView.setMinimumSize(new Dimension(44, 30));
		btnChangeView.setMaximumSize(new Dimension(44, 30));
		
		btnOption = new JButton();
		btnOption.setOpaque(false);
		btnOption.setFocusPainted(false);
		btnOption.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_menu.png"), 18, 18)));
		btnOption.setBorderPainted(false);
		btnOption.setPreferredSize(new Dimension(44, 30));
		btnOption.setMinimumSize(new Dimension(44, 30));
		btnOption.setMaximumSize(new Dimension(44, 30));
		
		panelPrincipal.add(btnChangeView);
		panelPrincipal.add(btnOption);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(panelPrincipal);
		
		return menuBar;
	}
	
	private void createPopupMenu() {
		popupMenuSetting = new JPopupMenu();
		
		itemSetting = new JMenuItem();
		itemSetting.setText(Manifest.IDIOME.getValue("6005"));
		Manifest.translationManager.registerJComponent(itemSetting, "6005");
		
		btnAbout = new JMenuItem();
		btnAbout.setText(Manifest.IDIOME.getValue("6006"));
		Manifest.translationManager.registerJComponent(btnAbout, "6006");
		
		popupMenuSetting.add(itemSetting);
		popupMenuSetting.add(btnAbout);
	}

	public JButton getBtnChangeView() {
		return btnChangeView;
	}

	public JButton getBtnOption() {
		return btnOption;
	}

	public JPopupMenu getPopupMenuSetting() {
		return popupMenuSetting;
	}

	public JMenuItem getItemSetting() {
		return itemSetting;
	}

	public JMenuItem getBtnAbout() {
		return btnAbout;
	}

	public LibraryPanel getLibraryPanel() {
		return libraryPanel;
	}

	public LibraryColorController getLibraryColorController() {
		return libraryColorController;
	}

	public ColorExtractionOptionsPanel getColorExtractionOptionsPanel() {
		return colorExtractionOptionsPanel;
	}

	public PanelSetting getPanelSetting() {
		return panelSetting;
	}

	public CardLayout getCard() {
		return card;
	}

	public JPanel getPanelCard() {
		return panelCard;
	}
}