package com.withoutstudios.jhueharvest.core.controller;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.withoutstudios.jhueharvest.ui.ImageViewPanel;
import com.withoutstudios.jhueharvest.util.Manifest;

/**
 * Esta clase es la que se encarga de manejar toda la iteracion de la UI con los servicios o funciones
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class ImageViewController {
	private JFrame ventana;
	private JFileChooser fileChooser;
	private ColorExtractionOptionsPanelController paletteController;

	public ImageViewController(ImageViewPanel view, ColorExtractionOptionsPanelController paletteController, JFrame ventana) {
		this.paletteController = paletteController;
		this.ventana = ventana;
		
		fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(Manifest.applicationName);
		fileChooser.setMultiSelectionEnabled(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "jpg", "jpeg", "png", "gif");
		fileChooser.setFileFilter(filter);
		
		DropTargetListener d = new DropTargetListener() {

			@Override
			public void dragEnter(DropTargetDragEvent dtde) {
				
			}

			@Override
			public void dragOver(DropTargetDragEvent dtde) {
				
			}

			@Override
			public void dropActionChanged(DropTargetDragEvent dtde) {
				
			}

			@Override
			public void dragExit(DropTargetEvent dte) {
				
			}

			@Override
			public void drop(DropTargetDropEvent dtde) {
				File file = null;
				dtde.acceptDrop(DnDConstants.ACTION_COPY);
				Transferable transferable = dtde.getTransferable();
				DataFlavor[] flavors = transferable.getTransferDataFlavors();
				for(DataFlavor flavor: flavors) {
					if(flavor.isFlavorJavaFileListType()) {
						try {
							@SuppressWarnings("unchecked")
							List<File> files = (List<File>) transferable.getTransferData(flavor);
							for(File fichero: files) {
								file = fichero.getAbsoluteFile();
							}
						} catch (UnsupportedFlavorException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				dtde.dropComplete(true);
				
				openImageFile(file, view);
			}
		};
		
		view.getImageView().setDropTarget(new DropTarget(view.getImageView(), d));
		view.getPanelDragDrop().setDropTarget(new DropTarget(view.getPanelDragDrop(), d));
		
		//Agrega el color del pixel al panelList
		view.getImageView().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(Manifest.imageDebug.image != null) {
						if(Manifest.maxTotalColorsPalette >= Manifest.imageDebug.ColorPaletteList.size() + 1) {
							Manifest.imageDebug.ColorPaletteList.add(view.getRobot().getPixelColor(e.getXOnScreen(), e.getYOnScreen()));
							paletteController.getServiceAddNewColorPalette(Manifest.imageDebug.ColorPaletteList.get(Manifest.imageDebug.ColorPaletteList.size() - 1));
						}
						else {
							JOptionPane.showMessageDialog(ventana, Manifest.IDIOME.getValue("102"), Manifest.applicationName, JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				else if((e.getButton() == MouseEvent.BUTTON3)) {
					Manifest.imageViewPanel.getPopup().updateUI();
					SwingUtilities.updateComponentTreeUI(Manifest.imageViewPanel.getPopup());
					Manifest.imageViewPanel.getPopup().pack();
					Manifest.imageViewPanel.getPopup().show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		/**
		view.getBtnOption().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				view.getPopup().updateUI();
				SwingUtilities.updateComponentTreeUI(view.getPopup());
				view.getPopup().pack();
				view.getPopup().show(view.getBtnOption(), 0, 24);
			}
		});**/
		
		view.getItemPopup()[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openFileChooser(view);
			}
		});
		
		view.getItemPopup()[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				enableAndDisableControls(false);
				view.getCard().show(view.getPanelCard(), "dragdrop");
			}
		});
		
		view.getBtnDirectory().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openFileChooser(view);
			}
		});
	}
	
	private void openFileChooser(ImageViewPanel view) {
		fileChooser.updateUI();
		SwingUtilities.updateComponentTreeUI(fileChooser);
		fileChooser.repaint();
		int optionSelected = fileChooser.showOpenDialog(ventana);
		
		if(JFileChooser.APPROVE_OPTION == optionSelected) {
			File file = fileChooser.getSelectedFile();
			openImageFile(file, view);
		}
	}
	
	private void openImageFile(File file, ImageViewPanel view) {
		if(file.isFile()) {
			BufferedImage img = null;
			try {
				img = ImageIO.read(file);
				if(img != null) {
					Manifest.imageDebug.file = file;
					
					Manifest.imageDebug.image = img;
					Manifest.imageDebug.imageAux = img;
					
					view.getCard().show(view.getPanelCard(), "imageview");
					Manifest.imageDebug.changeView(Manifest.imageDebug.imageAux);
					
					enableAndDisableControls(true);
				}
				else {
					JOptionPane.showMessageDialog(ventana, Manifest.IDIOME.getValue("103"), Manifest.applicationName, JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e) {
				enableAndDisableControls(false);
				e.printStackTrace();
			}
		}
		else {
			enableAndDisableControls(false);
		}
	}
	
	private void enableAndDisableControls(boolean enabled) {
		paletteController.getView().getPanelPaletteList().removeAll();
		paletteController.getView().getPanelPaletteList().revalidate();
		
		paletteController.getView().getBtnExtractor().setEnabled(enabled);
		paletteController.getView().getBtnDownload().setEnabled(false);
	}
}