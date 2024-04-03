package com.withoutstudios.jhueharvest.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import com.withoutstudios.jhueharvest.util.Manifest;

/**
 * Esta clase hace referencia al panel de la vista de configuracion
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class PanelSetting extends JPanel {
	private JToggleButton btnWitheTheme, btnBlackTheme;
	
	private JComboBox<String> comboLenguaje;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelSetting() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout(5, 5));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JPanel panelConten = new JPanel();
		panelConten.setOpaque(false);
		panelConten.setLayout(new BorderLayout(5, 5));
		
		panelConten.add(panelTheme(), BorderLayout.CENTER);
		
		panelPrincipal.add(panelLenguaje(), BorderLayout.NORTH);
		panelPrincipal.add(panelConten, BorderLayout.CENTER);
		
		this.add(panelTitle(), BorderLayout.NORTH);
		this.add(panelPrincipal, BorderLayout.CENTER);
	}
	
	private JPanel panelTitle() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JLabel lblTitle = new JLabel();
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lblTitle.setHorizontalAlignment(JLabel.LEFT);
		lblTitle.setText(Manifest.IDIOME.getValue("6005"));
		Manifest.translationManager.registerJComponent(lblTitle, "6005");
		
		panelPrincipal.add(lblTitle, BorderLayout.CENTER);
		
		return panelPrincipal;
	}
	
	private JPanel panelLenguaje() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(0, 5));
		
		JPanel panelContenedor = new JPanel();
		panelContenedor.setOpaque(false);
		panelContenedor.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
		
		JLabel lblEtiqueta = new JLabel();
		lblEtiqueta.setPreferredSize(new Dimension(90, 18));
		lblEtiqueta.setFont(new Font("Arial", Font.BOLD, 12));
		lblEtiqueta.setText(Manifest.IDIOME.getValue("8001"));
		Manifest.translationManager.registerJComponent(lblEtiqueta, "8001");
		
		comboLenguaje = new JComboBox<String>();
		comboLenguaje.addItem("Español");
		comboLenguaje.addItem("English");
		comboLenguaje.addItem("Portugues");
		comboLenguaje.addItem("France");
		
		panelContenedor.add(lblEtiqueta);
		panelContenedor.add(comboLenguaje);
		
		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		
		panelPrincipal.add(panelContenedor, BorderLayout.CENTER);
		panelPrincipal.add(separator, BorderLayout.SOUTH);
		
		return panelPrincipal;
	}
	
	private JPanel panelTheme() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JToolBar panelTheme = new JToolBar();
		panelTheme.setOpaque(false);
		panelTheme.setFloatable(false);
		panelTheme.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblEtiqueta = new JLabel();
		lblEtiqueta.setPreferredSize(new Dimension(100, 32));
		lblEtiqueta.setFont(new Font("Arial", Font.BOLD, 12));
		lblEtiqueta.setText(Manifest.IDIOME.getValue("8002"));
		Manifest.translationManager.registerJComponent(lblEtiqueta, "8002");
		
		ButtonGroup group = new ButtonGroup();
		
		btnWitheTheme = new JToggleButton();
		btnWitheTheme.setPreferredSize(new Dimension(70, 70));
		btnWitheTheme.setFocusPainted(false);
		btnWitheTheme.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_light_mode.png"), 60, 60)));
		
		btnBlackTheme = new JToggleButton();
		btnBlackTheme.setPreferredSize(new Dimension(70, 70));
		btnBlackTheme.setFocusPainted(false);
		btnBlackTheme.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_dark_mode.png"), 60, 60)));
		btnBlackTheme.setSelected(true);
		
		group.add(btnWitheTheme);
		group.add(btnBlackTheme);
		
		panelTheme.add(btnWitheTheme);
		panelTheme.add(btnBlackTheme);
		
		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		
		panelPrincipal.add(lblEtiqueta, BorderLayout.NORTH);
		panelPrincipal.add(panelTheme, BorderLayout.CENTER);
		panelPrincipal.add(separator, BorderLayout.SOUTH);
		
		return panelPrincipal;
	}

	public JComboBox<String> getComboLenguaje() {
		return comboLenguaje;
	}

	public JToggleButton getBtnWitheTheme() {
		return btnWitheTheme;
	}

	public JToggleButton getBtnBlackTheme() {
		return btnBlackTheme;
	}
}