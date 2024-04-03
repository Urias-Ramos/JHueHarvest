package com.withoutstudios.jhueharvest.ui.library;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.withoutstudios.jhueharvest.ui.MainFrame;
import com.withoutstudios.jhueharvest.util.Manifest;

/**
 * Esta clase hace hace referencia a la libreria donde se guardaran las paletas de colores
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class LibraryPanel extends JPanel {
	private JPanel panelPaletteList;
	private JPanel panelPrincipal;
	
	public JPanel panel2;
	
	private CardLayout card;
	public JPanel panelCard;
	
	private JLabel lblTitle, lblCount;
	private JButton btnAddPaletteCard;
	
	public JLayeredPane jLayeredPane;
	
	public GridBagConstraints gbcList;
	
	private JTextField txtSearch;
	private JButton btnClearText, btnSearch;
	
	private JComboBox<String> filter;
	
	private ImageIcon iconClearText;
	
	private MainFrame mainFrame;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LibraryPanel(MainFrame mainFrame) {
		this.setOpaque(false);
		this.setLayout(new BorderLayout(5, 5));
		
		this.add(createUI(), BorderLayout.NORTH);
		
		card = new CardLayout();
		
		iconClearText = new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_clear_text.png"), 18, 18));
		
		panelCard = new JPanel(card);
		panelCard.add(createMainPanel(), "library");
		panelCard.add(createPanelEmpty(), "empty");
		
		card.show(panelCard, "empty");
		
		jLayeredPane = new JLayeredPane();
		jLayeredPane.add(panelCard, JLayeredPane.DEFAULT_LAYER);
		
		jLayeredPane.add(btnAddPaletteCard, JLayeredPane.PALETTE_LAYER);
		
		this.add(jLayeredPane, BorderLayout.CENTER);
	}
	
	private JPanel createUI() {
		panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(true);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JPanel panelCenter = new JPanel();
		panelCenter.setOpaque(false);
		panelCenter.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JToolBar panelOptions = new JToolBar();
		panelOptions.setOpaque(false);
		panelOptions.setFloatable(false);
		panelOptions.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		JToolBar panelSearch = new JToolBar();
		panelSearch.setOpaque(false);
		panelSearch.setFloatable(false);
		panelSearch.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		panelSearch.setBorder(new BevelBorder(BevelBorder.LOWERED));
		panelSearch.setPreferredSize(new Dimension(245, 38));
		
		lblTitle = new JLabel();
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lblTitle.setHorizontalAlignment(JLabel.LEFT);
		lblTitle.setText(Manifest.IDIOME.getValue("7001"));
		Manifest.translationManager.registerJComponent(lblTitle, "7001");
		
		lblCount = new JLabel();
		lblCount.setFont(new Font("Arial", Font.BOLD, 14));
		lblCount.setHorizontalAlignment(JLabel.LEFT);
		lblCount.setText("0/"+Manifest.TOTAL_PALETTE_LIBRARY);
		
		txtSearch = new JTextField(15);
		txtSearch.setFont(new Font("Arial", Font.PLAIN, 14));
		txtSearch.setOpaque(false);
		txtSearch.setBorder(new EmptyBorder(5, 5, 5, 5));
		txtSearch.setText("");
		txtSearch.putClientProperty("JTextField.placeholderText", Manifest.IDIOME.getValue("7002"));
		Manifest.translationManager.registerJComponent(txtSearch, "7002");
		
		btnClearText = new JButton();
		btnClearText.setPreferredSize(new Dimension(32, 32));
		btnClearText.setFocusPainted(false);
		btnClearText.setEnabled(false);
		btnClearText.setIcon(null);
		
		btnSearch = new JButton();
		btnSearch.setPreferredSize(new Dimension(32, 32));
		btnSearch.setFocusPainted(false);
		btnSearch.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_search.png"), 18, 18)));
		
		JLabel lblFilter = new JLabel();
		lblFilter.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_filter.png"), 18, 18)));
		
		filter = new JComboBox<String>();
		filter.setPreferredSize(new Dimension(150, 38));
		filter.addItem(Manifest.IDIOME.getValue("7003"));
		filter.addItem(Manifest.IDIOME.getValue("7004"));
		filter.addItem(Manifest.IDIOME.getValue("7005"));
		filter.addItem(Manifest.IDIOME.getValue("7006"));
		filter.addItem(Manifest.IDIOME.getValue("7007"));
		
		Manifest.translationManager.registerJComponent(filter, "7003");
		
		JPanel panelCenterContainer = new JPanel();
		panelCenterContainer.setOpaque(false);
		panelCenterContainer.setLayout(new BorderLayout(5, 5));
		
		panelCenterContainer.add(lblTitle, BorderLayout.CENTER);
		panelCenterContainer.add(lblCount, BorderLayout.SOUTH);
		
		panelCenter.add(panelCenterContainer);
		
		panelSearch.add(txtSearch);
		panelSearch.add(btnClearText);
		panelSearch.add(btnSearch);
		
		JToolBar panelFilter = new JToolBar();
		panelFilter.setOpaque(false);
		panelFilter.setFloatable(false);
		panelFilter.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		
		JSeparator separator = new JSeparator(JSeparator.VERTICAL);
		separator.setPreferredSize(new Dimension(2, 18));
		
		panelFilter.add(separator);
		panelFilter.add(lblFilter);
		panelFilter.add(filter);
		
		panelOptions.add(panelSearch);
		panelOptions.add(panelFilter);
		
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
		
		panelFlowImageView.add(panelOptions, gbc);
		
		panelPrincipal.add(panelCenter, BorderLayout.CENTER);
		panelPrincipal.add(panelFlowImageView, BorderLayout.EAST);
		panelPrincipal.add(new JSeparator(), BorderLayout.SOUTH);
		
		return panelPrincipal;
	}
	
	private JPanel  createMainPanel() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		panelPaletteList = new JPanel();
		panelPaletteList.setOpaque(true);
		panelPaletteList.setBackground(Color.decode("#303234"));
		
		panelPaletteList.setLayout(new GridBagLayout());

		gbcList = new GridBagConstraints();
		gbcList.gridx = 0;
		gbcList.gridy = 0;
		gbcList.anchor = GridBagConstraints.CENTER;
		gbcList.insets = new Insets(10, 10, 10, 10);
		
		JPanel panelDragDrop = new JPanel();
		panelDragDrop.setOpaque(false);
		panelDragDrop.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.YELLOW);
		panel1.setOpaque(false);
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		panelDragDrop.add(panel1, gbc);
		
		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.gridwidth = 2;
		
		panelDragDrop.add(panelPaletteList, gbc);
		
		panel2 = new JPanel();
		panel2.setOpaque(false);
		panel2.setBackground(Color.RED);
		gbc.weighty = 1.0;
		gbc.gridx = 3;
		gbc.gridy = 0;
		
		gbc.weightx = 1.0;
		panelDragDrop.add(panel2, gbc);
		
		JScrollPane scroll = new JScrollPane(panelDragDrop);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		scroll.setOpaque(false);
		
		btnAddPaletteCard = new JButton();
		btnAddPaletteCard.setOpaque(false);
		btnAddPaletteCard.setFocusPainted(false);
		btnAddPaletteCard.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_add.png"), 54, 54)));
		btnAddPaletteCard.putClientProperty("JButton.buttonType", "roundRect");
		
		panelPrincipal.add(scroll, BorderLayout.CENTER);
		
		return panelPrincipal;
	}
	
	private JPanel createPanelEmpty() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JLabel lblTitle = new JLabel();
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setVerticalAlignment(JLabel.CENTER);
		lblTitle.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_palette.png"), 450, 450)));
		
		panelPrincipal.add(lblTitle, BorderLayout.CENTER);
		
		return panelPrincipal;
	}

	public CardLayout getCard() {
		return card;
	}

	public JPanel getPanelCard() {
		return panelCard;
	}

	public JPanel getPanelPaletteList() {
		return panelPaletteList;
	}

	public JPanel getLibraryPreview() {
		return panelPrincipal;
	}
	
	public JButton getBtnAddPaletteCard() {
		return btnAddPaletteCard;
	}

	public JTextField getTxtSearch() {
		return txtSearch;
	}
	
	public JButton getBtnClearText() {
		return btnClearText;
	}
	
	public JButton getBtnSearch() {
		return btnSearch;
	}
	
	public JComboBox<String> getFilter() {
		return filter;
	}

	public JLabel getLblCount() {
		return lblCount;
	}

	public ImageIcon getIconClearText() {
		return iconClearText;
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}
}