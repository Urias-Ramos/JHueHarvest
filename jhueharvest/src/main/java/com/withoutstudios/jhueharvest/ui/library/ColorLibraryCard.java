package com.withoutstudios.jhueharvest.ui.library;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.withoutstudios.jhueharvest.core.controller.ColorCardController;
import com.withoutstudios.jhueharvest.core.service.ColorLibraryCardService;
import com.withoutstudios.jhueharvest.util.Manifest;

/**
 * Esta clase hace hace referencia a la cartilla donde se agregaran los colores de la paleta
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class ColorLibraryCard extends JPanel {
	private JPanel panelPaletteList;
	
	private LibraryPanel libraryPreview;
	private ColorLibraryCardService colorLibraryCardService;
	
	private JButton btnDeletePalette, btnAddColorPalette, btnExportPalette;
	private JLabel lblTotalColorPalette;
	private JTextField txtPaletteName;
	
	private JToggleButton btnFavorite;
	
	private CardLayout card;
	private JPanel panelCard;
	
	private JPopupMenu popupMenu;
	private JMenuItem itemExportPDF, itemExportImage;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ColorLibraryCard(LibraryPanel libraryPreview, ColorLibraryCardService colorLibraryCardService) {
		this.libraryPreview = libraryPreview;
		this.colorLibraryCardService = colorLibraryCardService;
		
		this.setOpaque(false);
		this.setLayout(new BorderLayout(5, 5));
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		Dimension dimension = new Dimension(275, 375);
		
		this.setMaximumSize(dimension);
		this.setMinimumSize(dimension);
		this.setPreferredSize(dimension);
		
		this.add(createUI(), BorderLayout.CENTER);
		this.setBackground(Color.decode("#303234"));
	}
	
	private JPanel createUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		card = new CardLayout();
		
		panelCard = new JPanel(card);
		panelCard.setOpaque(false);
		
		panelCard.add(createMainPanel(), "palette");
		panelCard.add(createPanelEmpty(), "empty");
		
		card.show(panelCard, "empty");
		
		panelPrincipal.add(panelCard, BorderLayout.CENTER);
		panelPrincipal.add(createTitlePanel(), BorderLayout.SOUTH);
		
		return panelPrincipal;
	}
	
	private JPanel createTitlePanel() {
		JPanel panelTitle = new JPanel();
		panelTitle.setOpaque(false);
		panelTitle.setLayout(new BorderLayout(5, 5));
		
		txtPaletteName = new JTextField(25);
		txtPaletteName.setOpaque(false);
		txtPaletteName.setFont(new Font("Arial", Font.BOLD, 16));
		txtPaletteName.setText(colorLibraryCardService.getName());
		txtPaletteName.setEditable(false);
		txtPaletteName.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JToolBar panelOptions = new JToolBar();
		panelOptions.setOpaque(false);
		panelOptions.setFloatable(false);
		
		btnDeletePalette = new JButton();
		btnDeletePalette.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_eraser.png"), 16, 16)));
		
		btnAddColorPalette = new JButton();
		btnAddColorPalette.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_add_color.png"), 16, 16)));
		
		btnFavorite = new JToggleButton();
		btnFavorite.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_favorite_off.png"), 16, 16)));
		btnFavorite.setSelectedIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_favorite_on.png"), 16, 16)));
		btnFavorite.setDisabledSelectedIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_favorite_off.png"), 16, 16)));
		btnFavorite.setSelected(colorLibraryCardService.isFavorite());
		
		btnExportPalette = new JButton();
		btnExportPalette.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/exportar.png"), 16, 16)));
		
		itemExportPDF = new JMenuItem("PDF");
		itemExportPDF.setFocusPainted(false);
		itemExportPDF.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/pdf.png"), 16, 16)));
		
		itemExportImage = new JMenuItem("Image");
		itemExportImage.setFocusPainted(false);
		itemExportImage.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/image.png"), 16, 16)));
		
		popupMenu = new JPopupMenu();
		popupMenu.add(itemExportPDF);
		popupMenu.add(itemExportImage);
		
		lblTotalColorPalette = new JLabel();
		lblTotalColorPalette.setPreferredSize(new Dimension(38, 32));
		lblTotalColorPalette.setFont(new Font("Arial", Font.BOLD, 14));
		lblTotalColorPalette.setText(colorLibraryCardService.getColorList().size()+"/12");
		lblTotalColorPalette.setHorizontalAlignment(JLabel.CENTER);
		
		if(colorLibraryCardService.getColorList().size() < 12) {
			btnAddColorPalette.setEnabled(true);
		}
		else {
			btnAddColorPalette.setEnabled(false);
		}
		
		JSeparator separator = new JSeparator(JSeparator.VERTICAL);
		separator.setPreferredSize(new Dimension(8, 16));
		
		panelOptions.add(new JSeparator(JSeparator.VERTICAL));
		panelOptions.add(btnAddColorPalette);
		panelOptions.add(btnDeletePalette);
		panelOptions.add(btnExportPalette);
		panelOptions.add(btnFavorite);
		panelOptions.add(separator);
		panelOptions.add(lblTotalColorPalette);
		
		panelTitle.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		panelTitle.add(txtPaletteName, BorderLayout.CENTER);
		panelTitle.add(panelOptions, BorderLayout.EAST);
		
		return panelTitle;
	}
	
	private JPanel createMainPanel() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		panelPaletteList = new JPanel();
		panelPaletteList.setOpaque(false);
		//panelPaletteList.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panelPaletteList.setLayout(new BoxLayout(panelPaletteList, BoxLayout.Y_AXIS));
		panelPaletteList.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		
		ColorCard[] lblColor = new ColorCard[colorLibraryCardService.getColorList().size()];
		for(int i=0; i<lblColor.length; i++) {
			lblColor[i] = new ColorCard(this, colorLibraryCardService.getColorList().get(i));
			
			new ColorCardController(lblColor[i]);
			
			panelPaletteList.add(lblColor[i]);
		}
		
		panelPrincipal.add(panelPaletteList, BorderLayout.CENTER);
		
		return panelPrincipal;
	}
	
	private JPanel createPanelEmpty() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		JLabel lblEmpty = new JLabel();
		lblEmpty.setHorizontalAlignment(JLabel.CENTER);
		lblEmpty.setVerticalAlignment(JLabel.CENTER);
		lblEmpty.setPreferredSize(new Dimension(225, 225));
		lblEmpty.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/empty_palette.png"), 215, 215)));
		
		panelPrincipal.add(lblEmpty, gbc);
		
		return panelPrincipal;
	}
	
	public JPanel getPanelPaletteList() {
		return panelPaletteList;
	}

	public LibraryPanel getLibraryPreview() {
		return libraryPreview;
	}

	public JButton getBtnDeletePalette() {
		return btnDeletePalette;
	}

	public JButton getBtnAddColorPalette() {
		return btnAddColorPalette;
	}

	public JButton getBtnExportPalette() {
		return btnExportPalette;
	}

	public JPopupMenu getPopupMenu() {
		return popupMenu;
	}

	public JMenuItem getItemExportPDF() {
		return itemExportPDF;
	}

	public JMenuItem getItemExportImage() {
		return itemExportImage;
	}

	public JLabel getLblTotalColorPalette() {
		return lblTotalColorPalette;
	}

	public ColorLibraryCardService getColorLibraryCardService() {
		return colorLibraryCardService;
	}
	
	public JToggleButton getBtnFavorite() {
		return btnFavorite;
	}
	
	public JTextField getTxtName() {
		return txtPaletteName;
	}

	public CardLayout getCard() {
		return card;
	}

	public JPanel getPanelCard() {
		return panelCard;
	}
}