package com.withoutstudios.jhueharvest.core.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;

import com.withoutstudios.jhueharvest.ui.library.ColorCard;
import com.withoutstudios.jhueharvest.ui.library.ColorLibraryCard;
import com.withoutstudios.jhueharvest.util.Manifest;
import com.withoutstudios.jhueharvest.util.enumerators.DataType;

/**
 * Esta clase es la que se encarga de manejar toda la iteracion de la UI con los servicios o funciones
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class ColorLibraryCardController {
	private BufferedImage bufferdImagePalette;

	public ColorLibraryCardController(ColorLibraryCard view, JFrame ventana) {
		
		view.getBtnAddColorPalette().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(ventana, Manifest.applicationName, Color.ORANGE);
				if(color != null) {
					addColor(view, color);
				}
			}
		});
		
		view.getBtnDeletePalette().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int opc = JOptionPane.showConfirmDialog(ventana, "Estas seguro que deseas eliminarlo", Manifest.applicationName, JOptionPane.OK_CANCEL_OPTION);
				
				if(opc == JOptionPane.OK_OPTION) {
					view.getLibraryPreview().getPanelPaletteList().remove(view);
					view.getLibraryPreview().getPanelPaletteList().updateUI();
					view.getLibraryPreview().getPanelPaletteList().repaint();
					
					Manifest.COLOR_PALETTE_MANAGER.remove(view.getColorLibraryCardService());
					Manifest.MANAGER_DATA_FILE.save(DataType.PALETTE);
				}
			}
		});
		
		view.getBtnExportPalette().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if((e.getButton() == MouseEvent.BUTTON1)) {
					view.getPopupMenu().updateUI();
					SwingUtilities.updateComponentTreeUI(view.getPopupMenu());
					view.getPopupMenu().pack();
					view.getPopupMenu().show(e.getComponent(), e.getComponent().getX() - 56, e.getComponent().getY() + 20);
				}
			}
		});
		
		view.getItemExportImage().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bufferdImagePalette = Manifest.imageDebug.paletteToImage(view.getColorLibraryCardService().getColorList(), view.getColorLibraryCardService().getName(), 1600, 1200);
				
				Manifest.MANAGER_DATA_FILE.loadDataFile(DataType.EXPORT);
				File file = new File(new File("").getAbsolutePath()+"\\library\\"+view.getColorLibraryCardService().getName()+".png");
				
				Manifest.imageDebug.downloadImage(bufferdImagePalette, file, "palette");
				bufferdImagePalette = null;
				
				JOptionPane.showMessageDialog(ventana, Manifest.IDIOME.getValue("5007"), Manifest.applicationName, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		view.getItemExportPDF().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bufferdImagePalette = Manifest.imageDebug.paletteToImage(view.getColorLibraryCardService().getColorList(), view.getColorLibraryCardService().getName(), 800, 600);
				
				Manifest.MANAGER_DATA_FILE.loadDataFile(DataType.EXPORT);
				File file = new File(new File("").getAbsolutePath()+"\\library\\"+view.getColorLibraryCardService().getName()+".pdf");
				
				exportPDF(ventana, bufferdImagePalette, file);
				bufferdImagePalette = null;
				
				JOptionPane.showMessageDialog(ventana, Manifest.IDIOME.getValue("5007"), Manifest.applicationName, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		view.getBtnFavorite().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.getColorLibraryCardService().setFavorite(view.getBtnFavorite().isSelected());
				Manifest.MANAGER_DATA_FILE.save(DataType.PALETTE);
			}
		});
		
		view.getPanelPaletteList().addContainerListener(new ContainerListener() {
			
			@Override
			public void componentAdded(ContainerEvent e) {
				changeView(view);
			}

			@Override
			public void componentRemoved(ContainerEvent e) {
				changeView(view);
			}
		});
		
		view.getTxtName().addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent e) {
				view.getTxtName().setEditable(true);
				view.getTxtName().setOpaque(true);
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				view.getTxtName().setText(view.getColorLibraryCardService().getName());
				view.getTxtName().setEditable(false);
				view.getTxtName().setOpaque(false);
			}
		});
		
		view.getTxtName().addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					view.getTxtName().setText(view.getTxtName().getText().toUpperCase());
					view.getColorLibraryCardService().setName(view.getTxtName().getText());
					Manifest.MANAGER_DATA_FILE.save(DataType.PALETTE);
					view.getTxtName().setEditable(false);
					view.getTxtName().setOpaque(false);
				}
			}
		});
		
		changeView(view);
	}
	
	private void changeView(ColorLibraryCard view) {
		
		if(view.getPanelPaletteList().getComponentCount() > 0) {
			view.getCard().show(view.getPanelCard(), "palette");
		}
		else {
			view.getCard().show(view.getPanelCard(), "empty");
		}
		
		view.getLblTotalColorPalette().setText(view.getPanelPaletteList().getComponentCount()+"/"+Manifest.MAX_TOTAL_PALETTE_CARD_LIBRARY);
		
		if(view.getPanelPaletteList().getComponentCount() < Manifest.MAX_TOTAL_PALETTE_CARD_LIBRARY) {
			view.getBtnAddColorPalette().setEnabled(true);
		}
		else {
			view.getBtnAddColorPalette().setEnabled(false);
		}
	}
	
	private void addColor(ColorLibraryCard view, Color color) {
		view.getColorLibraryCardService().getColorList().add(color);
		
		ColorCard colorCard = new ColorCard(view, color);
		
		new ColorCardController(colorCard);
		
		Manifest.MANAGER_DATA_FILE.save(DataType.PALETTE);
		
		view.getPanelPaletteList().add(colorCard);
		view.getPanelPaletteList().updateUI();
		view.getPanelPaletteList().repaint();
	}
	
	private void exportPDF(JFrame ventana, BufferedImage bufferedImage, File file) {
		try {
			PDDocument document = new PDDocument();
			PDPage page = new PDPage(new PDRectangle(800, 600));
			document.addPage(page);
			
			float x = (page.getMediaBox().getWidth() - bufferedImage.getWidth()) / 2;
			float y = (page.getMediaBox().getHeight() - bufferedImage.getHeight()) / 2;
			
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			contentStream.drawImage(LosslessFactory.createFromImage(document, bufferedImage), x, y);
			contentStream.close();
			
			document.save(file);
			document.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(ventana, "¡Error Export!", Manifest.applicationName, JOptionPane.ERROR_MESSAGE);
		}
	}
}