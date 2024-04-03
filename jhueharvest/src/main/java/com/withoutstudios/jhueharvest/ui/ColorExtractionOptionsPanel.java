package com.withoutstudios.jhueharvest.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.withoutstudios.jhueharvest.util.Manifest;

/**
 * Esta clase representa un JPanel con toda la UI de la opcion de Paleta.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class ColorExtractionOptionsPanel extends JPanel {
	private JSpinner txtPaletteCount;
	private JComboBox<String> comboExtractionMethod;
	
	private JPanel panelPaletteList;
	
	private JButton btnExtractor;
	private JButton btnDownload;
	
	private JPanel panelCard;
	private CardLayout card;
	
	private JScrollPane scroll;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase.
	 */
	public ColorExtractionOptionsPanel() {
		this.setOpaque(true);
		this.setLayout(new BorderLayout(5, 5));
		this.setMinimumSize(new Dimension(340, 0));
		this.setMaximumSize(new Dimension(340, 0));
		this.setPreferredSize(new Dimension(340, 0));
		
		this.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED), new EmptyBorder(10, 10, 10, 10)));
		
		this.add(createUI(), BorderLayout.CENTER);
	}
	
	/**
	 * Este metodo crea un JPanel con toda la UI distribuida.
	 * 
	 * @return JPanel
	 */
	private JPanel createUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		panelPrincipal.add(panelControl(), BorderLayout.CENTER);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo crea un JPanel con toda la UI distribuida.
	 * 
	 * @return JPanel
	 */
	private JPanel panelControl() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		panelPrincipal.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JPanel panelGrid = new JPanel();
		panelGrid.setOpaque(false);
		panelGrid.setLayout(new GridLayout(2, 1, 5, 5));
		
		JPanel panelPrueba = new JPanel();
		panelPrueba.setOpaque(false);
		panelPrueba.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelPrueba.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JPanel panelContenedor1 = new JPanel();
		panelContenedor1.setOpaque(false);
		panelContenedor1.setLayout(new BorderLayout(5, 5));
		panelContenedor1.setPreferredSize(new Dimension(310, 45));
		
		JPanel panelTitle = new JPanel();
		panelTitle.setOpaque(false);
		panelTitle.setLayout(new BorderLayout(5, 5));
		
		JToolBar panelControl = new JToolBar();
		panelControl.setOpaque(false);
		panelControl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelControl.setFloatable(false);
		
		JLabel lblTitle = new JLabel();
		lblTitle.setHorizontalAlignment(JLabel.LEFT);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
		lblTitle.setText(Manifest.IDIOME.getValue("5001"));
		Manifest.translationManager.registerJComponent(lblTitle, "5001");
		
		JLabel lblDescripcion = new JLabel();
		lblDescripcion.setHorizontalAlignment(JLabel.LEFT);
		lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDescripcion.setText(Manifest.IDIOME.getValue("5002"));
		Manifest.translationManager.registerJComponent(lblDescripcion, "5002");
		
		SpinnerNumberModel numberModel = new SpinnerNumberModel(6, 1, 64, 1);
		
		txtPaletteCount = new JSpinner(numberModel);
		txtPaletteCount.setOpaque(false);
		txtPaletteCount.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPaletteCount.setPreferredSize(new Dimension(96, 26));
		
		JComponent editor = txtPaletteCount.getEditor();
		if(editor instanceof JSpinner.DefaultEditor) {
			JFormattedTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
			textField.setEditable(true);
		}
		
		panelControl.add(txtPaletteCount);
		
		panelTitle.add(lblTitle, BorderLayout.NORTH);
		panelTitle.add(lblDescripcion, BorderLayout.CENTER);
		
		panelContenedor1.add(panelTitle, BorderLayout.CENTER);
		panelContenedor1.add(panelControl, BorderLayout.EAST);
		panelContenedor1.add(new JSeparator(), BorderLayout.SOUTH);
		
		panelPrueba.add(panelContenedor1);
		
		panelGrid.add(panelPrueba);
		
		//inicio
		
		JPanel panelPrueba2 = new JPanel();
		panelPrueba2.setOpaque(false);
		panelPrueba2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelPrueba2.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JPanel panelContenedor2 = new JPanel();
		panelContenedor2.setOpaque(false);
		panelContenedor2.setLayout(new BorderLayout(5, 5));
		panelContenedor2.setPreferredSize(new Dimension(310, 45));
		
		JPanel panelTitle2 = new JPanel();
		panelTitle2.setOpaque(false);
		panelTitle2.setLayout(new BorderLayout(5, 5));
		
		JPanel panelControl2 = new JPanel();
		panelControl2.setOpaque(false);
		panelControl2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lblTitle2 = new JLabel();
		lblTitle2.setHorizontalAlignment(JLabel.LEFT);
		lblTitle2.setFont(new Font("Arial", Font.BOLD, 14));
		lblTitle2.setText(Manifest.IDIOME.getValue("5003"));
		Manifest.translationManager.registerJComponent(lblTitle2, "5003");
		
		JLabel lblDescripcion2 = new JLabel();
		lblDescripcion2.setHorizontalAlignment(JLabel.LEFT);
		lblDescripcion2.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDescripcion2.setText(Manifest.IDIOME.getValue("5004"));
		Manifest.translationManager.registerJComponent(lblDescripcion2, "5004");
		
		comboExtractionMethod = new JComboBox<String>();
		comboExtractionMethod.addItem("Normal");
		comboExtractionMethod.addItem("Pixel");
		comboExtractionMethod.setFont(new Font("Arial", Font.PLAIN, 14));
		comboExtractionMethod.setPreferredSize(new Dimension(96, 26));
		
		panelControl2.add(comboExtractionMethod);
		
		panelTitle2.add(lblTitle2, BorderLayout.NORTH);
		panelTitle2.add(lblDescripcion2, BorderLayout.CENTER);
		
		panelContenedor2.add(panelTitle2, BorderLayout.CENTER);
		panelContenedor2.add(panelControl2, BorderLayout.EAST);
		panelContenedor2.add(new JSeparator(), BorderLayout.SOUTH);
		
		panelPrueba2.add(panelContenedor2);
		
		panelGrid.add(panelPrueba2);
		
		JPanel panelContenedor = new JPanel();
		panelContenedor.setOpaque(false);
		panelContenedor.setLayout(new BorderLayout(5, 5));
		
		JPanel panelContenedor3 = new JPanel();
		panelContenedor3.setOpaque(false);
		panelContenedor3.setLayout(new BorderLayout(5, 5));
		
		JPanel panelFlowOpption = new JPanel();
		panelFlowOpption.setOpaque(false);
		panelFlowOpption.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnExtractor = new JButton();
		btnExtractor.setEnabled(false);
		btnExtractor.setFocusPainted(false);
		btnExtractor.setBorderPainted(true);
		btnExtractor.setBackground(Color.decode("#5972a4"));
		btnExtractor.setForeground(Color.WHITE);
		btnExtractor.setFont(new Font("Arial", Font.BOLD, 16));
		btnExtractor.setPreferredSize(new Dimension(200, 38));
		btnExtractor.putClientProperty("JButton.buttonType", "roundRect");
		btnExtractor.setText(Manifest.IDIOME.getValue("5005"));
		Manifest.translationManager.registerJComponent(btnExtractor, "5005");
		
		panelFlowOpption.add(btnExtractor);
		
		panelContenedor3.add(panelFlowOpption, BorderLayout.CENTER);
		panelContenedor3.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);
		
		//fin
		
		card = new CardLayout();
		
		panelCard = new JPanel(card);
		panelCard.setOpaque(false);
		
		panelPaletteList = new JPanel();
		panelPaletteList.setOpaque(false);
		panelPaletteList.setLayout(new BoxLayout(panelPaletteList, BoxLayout.Y_AXIS));
		panelPaletteList.setOpaque(true);
		panelPaletteList.setBackground(Manifest.THEME_BACKGROUND);
		
		JLabel lblDragDrop = new JLabel();
		lblDragDrop.setHorizontalAlignment(JLabel.CENTER);
		lblDragDrop.setPreferredSize(new Dimension(315, 315));
		lblDragDrop.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/empty_list_color.png"), 315, 315)));
		lblDragDrop.setVerticalTextPosition(JLabel.BOTTOM);
		lblDragDrop.setHorizontalTextPosition(JLabel.CENTER);
		
		scroll = new JScrollPane(panelPaletteList);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		scroll.setOpaque(true);
		scroll.setBackground(Color.decode("#303234"));
		
		panelCard.add(scroll, "paletteList");
		panelCard.add(lblDragDrop, "empty");
		
		card.show(panelCard, "empty");
		
		btnDownload = new JButton();
		btnDownload.setBackground(Color.decode("#7EAD66"));
		btnDownload.setForeground(Color.WHITE);
		btnDownload.setFont(new Font("Arial", Font.BOLD, 16));
		btnDownload.setPreferredSize(new Dimension(200, 38));
		btnDownload.putClientProperty("JButton.buttonType", "roundRect");
		btnDownload.setFocusPainted(false);
		btnDownload.setBorderPainted(true);
		btnDownload.setEnabled(false);
		//btnDownload.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_save.png"), 16, 16)));
		btnDownload.setText(Manifest.IDIOME.getValue("1"));
		Manifest.translationManager.registerJComponent(btnDownload, "1");
		
		JPanel panelSouth = new JPanel();
		panelSouth.setOpaque(false);
		panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelSouth1 = new JPanel();
		panelSouth1.setOpaque(false);
		panelSouth1.setLayout(new BorderLayout(0, 0));
		
		panelContenedor.add(panelGrid, BorderLayout.CENTER);
		panelContenedor.add(panelContenedor3, BorderLayout.SOUTH);
		
		panelSouth.add(btnDownload);
		//panelSouth.add(btnSavePalette);
		
		panelSouth1.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.NORTH);
		panelSouth1.add(panelSouth, BorderLayout.CENTER);
		
		panelPrincipal.add(panelContenedor, BorderLayout.NORTH);
		panelPrincipal.add(panelCard, BorderLayout.CENTER);
		panelPrincipal.add(panelSouth1, BorderLayout.SOUTH);
		
		return panelPrincipal;
	}
	
	/**
	 * Obtiene un JSpinner asociado.
	 * 
	 * @return JSpinner
	 */
	public JSpinner getTxtPaletteCount() {
		return txtPaletteCount;
	}
	
	/**
	 * Obtiene un JComboBox asociado.
	 * 
	 * @return JComboBox
	 */
	public JComboBox<String> getComboExtractionMethod() {
		return comboExtractionMethod;
	}
	
	/**
	 * Obtiene un JPanel asociado.
	 * 
	 * @return JPanel
	 */
	public JPanel getPanelPaletteList() {
		return panelPaletteList;
	}

	/**
	 * Obtiene un JButton asociado.
	 * 
	 * @return JButton
	 */
	public JButton getBtnExtractor() {
		return btnExtractor;
	}
	
	/**
	 * Obtiene un JButton asociado.
	 * 
	 * @return JButton
	 */
	public JButton getBtnDownload() {
		return btnDownload;
	}
	
	public JScrollPane getScroll() {
		return scroll;
	}

	public JPanel getPanelCard() {
		return panelCard;
	}

	public CardLayout getCard() {
		return card;
	}
}