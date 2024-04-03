package com.withoutstudios.jhueharvest.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

/**
 * Esta es la clase que se encarga de previsualizar la imagen.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class ImageView extends JComponent {
	private BufferedImage image;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase, crea el JComponent con un tamaño fijo.
	 * 
	 * @param width ancho fijo a establecer
	 * @param height alto fijo a establecer
	 */
	public ImageView(int width, int height) {
		this.setMaximumSize(new Dimension(width, height));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		
		//si la imagen es null no se hacen los calculos.
		if(getImage() != null) {
			
			//se obtiene el ancho y alto de la imagen
			int originalWidth = getImage().getWidth();
	        int originalHeight = getImage().getHeight();
	        
	        //se obtiene el ancho y alto del JComponent
	        int scaledWidth = getWidth();
	        int scaledHeight = getHeight();
	        
	        //se verifica si la imagen es de un tamaño mayor al del JComponet
	        if(originalWidth > scaledWidth || originalHeight > scaledHeight) {
	        	
	        	//calculo para obtener el aspecto de la imagen y el lienzo
	        	double aspectoImage = (double) originalWidth / originalHeight;
		        double aspectoView = (double) scaledWidth / scaledHeight;
		        
		        //si la imagen tiene un area mayor al lienzo
		        if(aspectoImage > aspectoView) {
		        	//la imagen ocupara todo el ancho del lienzo y se ajustara el alto
		        	originalWidth = scaledWidth;
		        	originalHeight = (int) (originalWidth / aspectoImage);
		        }
		        else {
		        	//la imagen ocupara todo el alto del lienzo y se ajustara el ancho
		        	originalHeight = scaledHeight;
		        	originalWidth = (int) (originalHeight * aspectoImage);
		        }
	        }
	        
	        //calculo para obtener las corrdenadas x, y centreadas para dibujar la imagen.
	        int x = (scaledWidth - originalWidth) / 2;
	        int y = (scaledHeight - originalHeight) / 2;
	        
	        //se dibuja la imagen en el JComponent con las coordenadas dadas y el ancho y alto espablecido.
	        g2d.drawImage(getImage(), x, y, originalWidth, originalHeight, this);
		}
		
		//se libera el recurso grafico.
		g2d.dispose();
	}
	
	/**
	 * Obtiene BufferedImage con la imagen actual mostrada.
	 * 
	 * @return BufferedImage
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	/**
	 * Establece una nueva imagen a mostrar.
	 * 
	 * @param image nueva imagen a mostrar
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}
}