package com.withoutstudios.jhueharvest.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.withoutstudios.jhueharvest.util.Manifest;

/**
 * Esta clase hace hace referencia al panel de la vista de Acerca de
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class PanelAbout extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelAbout() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout(5, 5));
		
		this.add(createUI(), BorderLayout.CENTER);
	}
	
	private JPanel createUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panelContenedor = new JPanel();
		panelContenedor.setOpaque(false);
		panelContenedor.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		panelContenedor.add(panelCenter(), gbc);
		
		panelPrincipal.add(panelContenedor, BorderLayout.CENTER);
		
		return panelPrincipal;
	}
	
	private JPanel panelCenter() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JLabel lblIcon = new JLabel(Manifest.applicationName);
		lblIcon.setHorizontalAlignment(JLabel.CENTER);
		lblIcon.setVerticalTextPosition(JLabel.BOTTOM);
		lblIcon.setHorizontalTextPosition(JLabel.CENTER);
		lblIcon.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_app.png"), 100, 100)));
		lblIcon.setFont(new Font("Arial", Font.BOLD, 22));
		
		JLabel lblText = new JLabel();
		lblText.setFont(new Font("Arial", Font.PLAIN, 12));
		lblText.setHorizontalAlignment(JLabel.CENTER);
		lblText.setHorizontalTextPosition(JLabel.CENTER);
		lblText.setText("<HTML><CENTER><B>By Urias Ramos</B><BR><BR>"
				+ "Color Extractor.<BR><BR>"
				+ "Version: 2024-03 (0.0.1)</HTML>");
		
		panelPrincipal.add(lblIcon, BorderLayout.NORTH);
		panelPrincipal.add(lblText, BorderLayout.CENTER);
		
		return panelPrincipal;
	}
}