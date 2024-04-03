package com.withoutstudios.jhueharvest.ui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Robot;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.withoutstudios.jhueharvest.util.Manifest;

/**
 * Esta clase representa un JPanel con todo el apartado UI del area de vision de la imagen.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class ImageViewPanel extends JPanel {
	private ImageView imageView;
	private JButton btnOption, btnDirectory;
	
	private Robot robot;
	
	private CardLayout card;
	private JPanel panelCard;
	
	private JPanel panelDragDrop;
	private JPopupMenu popup;
	private JMenuItem[] itemPopup;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase.
	 */
	public ImageViewPanel() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout(5, 5));
		setBackground(Color.decode("#303234"));
		
		popup = new JPopupMenu();
		
		itemPopup = new JMenuItem[2];
		for(int i=0; i<itemPopup.length; i++) {
			itemPopup[i] = new JMenuItem();
			itemPopup[i].setFocusPainted(false);
			
			popup.add(itemPopup[i]);
		}
		
		itemPopup[0].setText(Manifest.IDIOME.getValue("6001"));
		Manifest.translationManager.registerJComponent(itemPopup[0], "6001");
		
		itemPopup[1].setText(Manifest.IDIOME.getValue("6002"));
		Manifest.translationManager.registerJComponent(itemPopup[1], "6002");
		
		card = new CardLayout();
		panelCard = new JPanel(card);
		panelCard.setOpaque(false);
		panelCard.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED), new EmptyBorder(0, 0, 0, 0)));
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		JPanel panelFlowImageView = new JPanel();
		panelFlowImageView.setOpaque(false);
		panelFlowImageView.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		imageView = new ImageView(975, 350);
		
		panelFlowImageView.add(imageView, gbc);
		
		panelCard.add(panelDragAndDrop(), "dragdrop");
		panelCard.add(imageView, "imageview");
		
		card.show(panelCard, "dragdrop");
		
		this.add(panelCard, BorderLayout.CENTER);
	}
	
	/**
	 * Este metodo crea el panel donde se podra arrastar y soltar un fichero de imagen para abrir.
	 * 
	 * @return JPanel
	 */
	private JPanel panelDragAndDrop() {
		panelDragDrop = new JPanel();
		panelDragDrop.setOpaque(false);
		panelDragDrop.setLayout(new GridBagLayout());
		
		JPanel panelContentido = new JPanel();
		panelContentido.setOpaque(false);
		panelContentido.setLayout(new BorderLayout(5, 5));
		
		JPanel panelDirectory = new JPanel();
		panelDirectory.setOpaque(false);
		panelDirectory.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		JLabel lblDragDrop = new JLabel();
		lblDragDrop.setHorizontalAlignment(JLabel.CENTER);
		lblDragDrop.setPreferredSize(new Dimension(325, 375));
		lblDragDrop.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/drag_drop_image_file.png"), 315, 315)));
		lblDragDrop.setVerticalTextPosition(JLabel.BOTTOM);
		lblDragDrop.setHorizontalTextPosition(JLabel.CENTER);
		lblDragDrop.setText(Manifest.IDIOME.getValue("6003"));
		lblDragDrop.setFont(new Font("Arial", Font.PLAIN, 14));
		Manifest.translationManager.registerJComponent(lblDragDrop, "6003");
		
		btnDirectory = new JButton();
		btnDirectory.setFont(new Font("Arial", Font.BOLD, 14));
		btnDirectory.setBackground(Color.decode("#18cefb"));
		btnDirectory.setForeground(Color.BLACK);
		btnDirectory.setPreferredSize(new Dimension(250, 38));
		btnDirectory.putClientProperty("JButton.buttonType", "roundRect");
		btnDirectory.setText(Manifest.IDIOME.getValue("6004"));
		Manifest.translationManager.registerJComponent(btnDirectory, "6004");
		
		panelDirectory.add(btnDirectory);
		
		panelContentido.add(lblDragDrop, BorderLayout.CENTER);
		panelContentido.add(panelDirectory, BorderLayout.SOUTH);
		
		panelDragDrop.add(panelContentido, gbc);
		
		return panelDragDrop;
	}
	
	/**
	 * Obtiene CardLayout asociado.
	 * 
	 * @return CardLayout
	 */
	public CardLayout getCard() {
		return card;
	}
	
	/**
	 * Obtiene JPanel asociado.
	 * 
	 * @return JPanel
	 */
	public JPanel getPanelCard() {
		return panelCard;
	}
	
	/**
	 * Obtiene JPanel asociado.
	 * 
	 * @return JPanel
	 */
	public JPanel getPanelDragDrop() {
		return panelDragDrop;
	}
	
	/**
	 * Obtiene ImageView asociado.
	 * 
	 * @return ImageView
	 */
	public ImageView getImageView() {
		return imageView;
	}
	
	/**
	 * Obtiene JButton asociado.
	 * 
	 * @return JButton
	 */
	public JButton getBtnOption() {
		return btnOption;
	}
	
	/**
	 * Obtiene JButton asociado.
	 * 
	 * @return JButton
	 */
	public JButton getBtnDirectory() {
		return btnDirectory;
	}
	
	/**
	 * Obtiene JPopupMenu asociado.
	 * 
	 * @return JPopupMenu
	 */
	public JPopupMenu getPopup() {
		return popup;
	}
	
	/**
	 * Obtiene JMenuItem[] asociado.
	 * 
	 * @return JMenuItem[]
	 */
	public JMenuItem[] getItemPopup() {
		return itemPopup;
	}
	
	/**
	 * Obtiene Robot asociado.
	 * 
	 * @return Robot
	 */
	public Robot getRobot() {
		return robot;
	}
}