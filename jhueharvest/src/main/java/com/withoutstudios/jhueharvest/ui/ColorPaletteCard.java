package com.withoutstudios.jhueharvest.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import com.withoutstudios.jhueharvest.util.Manifest;

/**
 * Esta clase representa un JPanel que se usa para mostrar los colores obtenidos
 * por la opcion de Paletta.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class ColorPaletteCard extends JPanel implements MouseListener {
	private JLabel lblColor;
	private JLabel lblHTML, lblRGB;
	
	private JButton btnCopyColorHTML;
	
	private Color color, foregroundOriginal, foreground;
	private Clipboard clip;
	
	private JPanel panelList;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase, crea un objeto JPanel con los datos del color asociado.
	 * 
	 * @param color color que se utilizara para obtener los datos.
	 */
	public ColorPaletteCard(Color color, JPanel panelList) {
		this.color = color;
		this.panelList = panelList;
		
		//dado el color se busca el color de fuente que puede tener.
		foreground = Manifest.imageDebug.getForegroundColor(color);
		
		//se crea un vinculo con el portapapeles del sistema.
		clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		
		this.setOpaque(false);
		this.setBackground(color);
		this.setLayout(new BorderLayout(5, 5));
		this.setMaximumSize(new Dimension(300, 52));
		
		this.add(createUI(), BorderLayout.CENTER);
	}
	
	/**
	 * Este metodo crea y distribuye la UI del JPanel.
	 * 
	 * @return JPanel
	 */
	private JPanel createUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JPanel panelColor = new JPanel();
		panelColor.setOpaque(true);
		panelColor.setBackground(new Color(241, 241, 241, 75));
		panelColor.setBorder(new EmptyBorder(2, 2, 2, 2));
		
		JToolBar panelHTML = new JToolBar();
		panelHTML.setOpaque(false);
		panelHTML.setLayout(new BorderLayout(5, 5));
		panelHTML.setFloatable(false);
		
		JToolBar panelRGB = new JToolBar();
		panelRGB.setOpaque(false);
		panelRGB.setLayout(new BorderLayout(5, 5));
		panelRGB.setFloatable(false);
		
		lblColor = new JLabel();
		lblColor.setOpaque(true);
		lblColor.setBackground(color);
		lblColor.setPreferredSize(new Dimension(42, 32));
		lblColor.addMouseListener(this);
		
		JPanel panelData = new JPanel();
		panelData.setOpaque(false);
		panelData.setLayout(new BorderLayout(5, 5));
		
		lblHTML = new JLabel(""+Manifest.imageDebug.convertColorToHTML(color));
		lblHTML.setFont(new Font("Arial", Font.PLAIN, 14));
		lblHTML.setHorizontalAlignment(JLabel.LEFT);
		lblHTML.setOpaque(false);
		
		lblRGB = new JLabel("rgb("+color.getRed()+", "+color.getGreen()+", "+color.getBlue()+")");
		lblRGB.setFont(new Font("Arial", Font.ITALIC, 12));
		lblRGB.setHorizontalAlignment(JLabel.LEFT);
		lblRGB.setOpaque(false);
		
		panelHTML.add(lblHTML, BorderLayout.CENTER);
		//panelHTML.add(btnCopyColorHTML, BorderLayout.EAST);
		
		panelRGB.add(lblRGB, BorderLayout.CENTER);
		
		panelColor.add(lblColor);
		
		panelData.add(panelHTML, BorderLayout.NORTH);
		panelData.add(panelRGB, BorderLayout.CENTER);
		
		JPanel panelFlow = new JPanel();
		panelFlow.setLayout(new BorderLayout(5, 5));
		panelFlow.setOpaque(false);
		
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
		
		panelFlowImageView.add(panelOptions(), gbc);
		
		panelFlow.add(panelColor, BorderLayout.WEST);
		panelFlow.add(panelData, BorderLayout.CENTER);
		panelFlow.add(panelFlowImageView, BorderLayout.EAST);
		
		panelPrincipal.add(panelFlow, BorderLayout.CENTER);
		panelPrincipal.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);
		
		return panelPrincipal;
	}
	
	private JToolBar panelOptions() {
		JToolBar panelPrincipal = new JToolBar();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setFloatable(false);
		
		btnCopyColorHTML = new JButton();
		btnCopyColorHTML.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getResource("/icon/icon_copy.png"), 12, 12)));
		btnCopyColorHTML.setFocusPainted(false);
		btnCopyColorHTML.setPreferredSize(new Dimension(18, 18));
		btnCopyColorHTML.setOpaque(false);
		btnCopyColorHTML.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clip.setContents(new StringSelection(lblHTML.getText()), null);
			}
		});
		
		JButton btnDelete = new JButton();
		btnDelete.setFocusPainted(false);
		btnDelete.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getResource("/icon/icon_clear_text.png"), 12, 12)));
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				remove();
			}
		});
		
		panelPrincipal.add(btnCopyColorHTML);
		panelPrincipal.add(btnDelete);
		
		return panelPrincipal;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setOpaque(true);
		this.revalidate();
		this.repaint();
		
		//se obtiene el color del texto original
		foregroundOriginal = lblHTML.getForeground();
		
		//se le aplica el color de fuente asociado al color.
		lblHTML.setForeground(foreground);
		lblRGB.setForeground(foreground);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setOpaque(false);
		this.revalidate();
		this.repaint();
		
		//se le vuelve a poner el color original al texto.
		lblHTML.setForeground(foregroundOriginal);
		lblRGB.setForeground(foregroundOriginal);
	}
	
	public void remove() {
		panelList.remove(this);
		Manifest.imageDebug.ColorPaletteList.remove(color);
		panelList.updateUI();
		panelList.repaint();
	}
}