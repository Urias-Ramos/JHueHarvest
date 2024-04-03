package com.withoutstudios.jhueharvest.ui.library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.withoutstudios.jhueharvest.util.Manifest;

/**
 * Esta clase hace hace referencia al color que se agrega a las cartillas de la paleta de colores
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class ColorCard extends JPanel {
	private ColorLibraryCard colorLibraryCard;
	private JPanel panelOptions;
	
	private JLabel lblHexCode;
	
	private JButton btnChangeCardColor, btnDeleteColorCard;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ColorCard(ColorLibraryCard colorLibraryCard, Color color) {
		this.colorLibraryCard = colorLibraryCard;
		
		this.setOpaque(true);
		this.setBorder(new EmptyBorder(0, 5, 0, 5));
		this.setLayout(new BorderLayout(0, 0));
		this.setBackground(color);
		
		lblHexCode = new JLabel();
		lblHexCode.setOpaque(false);
		lblHexCode.setHorizontalTextPosition(JLabel.LEFT);
		lblHexCode.setFont(new Font("Arial", Font.BOLD, 14));
		lblHexCode.setVisible(false);
		lblHexCode.setPreferredSize(new Dimension(108, 48));
		lblHexCode.setForeground(Manifest.imageDebug.getForegroundColor(getBackground()));
		lblHexCode.setText(Manifest.imageDebug.convertColorToHTML(getBackground()).toUpperCase());
		
		this.add(lblHexCode, BorderLayout.CENTER);
		this.add(createPanelOptions(), BorderLayout.SOUTH);
	}
	
	private JPanel createPanelOptions() {
		panelOptions = new JPanel();
		panelOptions.setOpaque(false);
		panelOptions.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panelOptions.setVisible(false);
		
		btnChangeCardColor = new JButton();
		btnChangeCardColor.setFocusPainted(false);
		btnChangeCardColor.setOpaque(true);
		btnChangeCardColor.setPreferredSize(new Dimension(24, 24));
		btnChangeCardColor.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getResource("/icon/icon_change_color.png"), 12, 12)));
		btnChangeCardColor.putClientProperty("JButton.buttonType", "roundRect");
		
		btnDeleteColorCard = new JButton();
		btnDeleteColorCard.setFocusPainted(false);
		btnDeleteColorCard.setOpaque(true);
		btnDeleteColorCard.setPreferredSize(new Dimension(24, 24));
		btnDeleteColorCard.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getResource("/icon/icon_clear_text.png"), 12, 12)));
		btnDeleteColorCard.putClientProperty("JButton.buttonType", "roundRect");
		
		panelOptions.add(btnChangeCardColor);
		panelOptions.add(btnDeleteColorCard);
		
		return panelOptions;
	}

	public ColorLibraryCard getColorLibraryCard() {
		return colorLibraryCard;
	}

	public JPanel getPanelOptions() {
		return panelOptions;
	}

	public JLabel getLblHexCode() {
		return lblHexCode;
	}

	public JButton getBtnChangeCardColor() {
		return btnChangeCardColor;
	}

	public JButton getBtnDeleteColorCard() {
		return btnDeleteColorCard;
	}
}