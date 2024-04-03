package com.withoutstudios.jhueharvest.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Esta clase contiene todos los metodos que seran usados por el programa para la edicion de la imagen.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2024-03-30
 * 
 */
public class ImageDebug {
	public File file;
	public BufferedImage image;
	public BufferedImage imageAux;
	
	public ArrayList<Color> ColorPaletteList;
	
	/**
	 * Constructor de la clase.
	 */
	public ImageDebug() {
		ColorPaletteList = new ArrayList<Color>();
	}
	
	/**
	 * Este metodo devuelve el ancho de un texto en pixeles
	 * 
	 * @param g objetro Graphics de origen
	 * @param text texto del que se quiere saber el ancho
	 * @return ancho del texto en pixeles
	 */
	public int getAnchoTextoPixel(Graphics g, String text) {
		return g.getFontMetrics().stringWidth(text);
	}
	
	/**
	 * Este metodo devuelve el alto de un texto en pixeles
	 * 
	 * @param g objetro Graphics de origen
	 * @param text texto del que se quiere saber el alto
	 * @return alto del texto en pixeles
	 */
	public int getAltoTextoPixel(Graphics g, String text) {
		return (int) g.getFontMetrics().getLineMetrics(text, g).getHeight();
	}
	
	/**
	 * Este metodo se encarga de actualizar la vista de la imagen por otra.
	 * 
	 * @param imageOriginal nueva imagen a mostrar
	 */
	public void changeView(BufferedImage imageOriginal) {
		if(imageOriginal != null) {
			Manifest.imageViewPanel.getImageView().setImage(getCloneImage(imageOriginal));
			Manifest.imageViewPanel.getImageView().repaint();
		}
	}
	
	/**
	 * Este metodo clona el BufferedImage dado, para obtener un nuevo BufferedImage independiente.
	 * 
	 * @param imageOriginal BufferedImage a clonar
	 * @return BufferedImage
	 */
	public BufferedImage getCloneImage(BufferedImage imageOriginal) {
		BufferedImage sub = imageOriginal.getSubimage(0, 0, imageOriginal.getWidth(), imageOriginal.getHeight());
		return new BufferedImage(sub.getColorModel(), sub.copyData(null), sub.getColorModel().isAlphaPremultiplied(), null);
	}
	
	/**
	 * Este metodo se encarga de obtener la paleta de colores, usando el enfoque de Cuantizacion.
	 * 
	 * @param imageOriginal imagen a ser evaluada
	 * @param totalColors total de colores que debe encontrar
	 * @return lista con los colores
	 */
	public ArrayList<Color> getPaletteQuantization(BufferedImage imageOriginal, int totalColors) {
		ArrayList<Color> colorList = new ArrayList<Color>();
		int width = imageOriginal.getWidth();
        int height = imageOriginal.getHeight();
        
        //distancia para conciderar si son colores distintos
        double minDistance = 50.0;
        
        for(int y=0; y<height; y++) {
        	for(int x=0; x<width; x++) {
        		Color pixelColor = new Color(imageOriginal.getRGB(x, y));
        		
        		int n = imageOriginal.getRGB(x, y);
                int alpha = (n >> 24) & 0xFF;
                
                //se ignoran los pixeles transparentes
                if(alpha != 0) {
                	boolean isUniqueColor = true;
                	for(Color uniqueColor: colorList) {
                		double distance = getColorDistance(pixelColor, uniqueColor);
                		if(distance < minDistance) {
                			isUniqueColor = false;
                            break;
                		}
                	}
                	
                	if(colorList.size() < totalColors) {
                		if(isUniqueColor) {
                			colorList.add(pixelColor);
                		}
                	}
                	else {
                		break;
                	}
                }
            }
        	
        	if(colorList.size() >= totalColors) {
        		break;
        	}
        }
		
		return colorList;
	}
	
	/**
	 * Este metodo se encarga de obtener la paleta de colores, usando el enfoque de colores predominantes. 
	 * 
	 * @param imageOriginal imagen a ser evaluada
	 * @param totalColors total de colores que debe encontrar
	 * @return lista con los colores
	 */
	public ArrayList<Color> getDominantColorPalette(BufferedImage imageOriginal, int totalColors) {
		HashMap<Color, Integer> colorCountMap = new HashMap<>();
		
		int width = imageOriginal.getWidth();
        int height = imageOriginal.getHeight();
        
        for(int y=0; y<height; y++) {
        	for(int x=0; x<width; x++) {
        		Color colorValue = new Color(imageOriginal.getRGB(x, y));
                int n = imageOriginal.getRGB(x, y);
                int alpha = (n >> 24) & 0xFF;
                
                //se ignoran los pixeles transparentes
                if(alpha != 0) {
                	colorCountMap.put(colorValue, colorCountMap.getOrDefault(colorValue, 0) + 1);
                }
        	}
        }
        
        //se ordenan de mayor a menor la lista de colores dado la cantidad de apariciones
        ArrayList<Map.Entry<Color, Integer>> sortedColorList = new ArrayList<>(colorCountMap.entrySet());
        sortedColorList.sort(Map.Entry.<Color, Integer>comparingByValue().reversed());
        
        int x = 1;
        ArrayList<Color> colorList = new ArrayList<>();
        for(Map.Entry<Color, Integer> entry : sortedColorList) {
        	Color color = entry.getKey();
            
        	if(!colorList.contains(color)) {
        		colorList.add(color);
        	}
        	
        	if(x == totalColors) {
        		break;
        	}
        	x++;
        }
        
        return colorList;
	}
	
	/**
	 * Este metodo devuelve el valor HTML de un color
	 * 
	 * @param color Color al cual se le obtendra el valor HTML
	 * @return cadena de texto con el valor HTML
	 */
	public String convertColorToHTML(Color color) {
		String red = Integer.toHexString(color.getRed());
        String green = Integer.toHexString(color.getGreen());
        String blue = Integer.toHexString(color.getBlue());
        
        //se verifica que los valores sean de 2 digitos
        if (red.length() == 1) {
            red = "0" + red;
        }
        if (green.length() == 1) {
            green = "0" + green;
        }
        if (blue.length() == 1) {
            blue = "0" + blue;
        }
        
        return "#" + red + green + blue;
	}
	
	/**
	 * Este metodo se utiliza para obtener el Color que debe tener un texto dado un Color.
	 * 
	 * @param color color a verificar
	 * @return Color que debe tener el texto
	 */
	public Color getForegroundColor(Color color) {
		if(isLightColor(color)) {
			return Color.BLACK;
		}
		else if(isDarkColor(color)) {
			return Color.WHITE;
		}
		
		//por si existe un error se aplica un color intermedio
		return Color.decode("#999999");
	}
	
	/**
	 * Este metodo se utiliza para saber si un color es claro.
	 * 
	 * @param color color a verificar
	 * @return true si es un color claro, false de lo contrario
	 */
	private boolean isLightColor(Color color) {
		//se Calcula el brillo relativo del color (valor entre 0 y 255)
        double brightness = (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255;
        
        // Verificar si el color es muy claro (brillo >= 0.5)
        return brightness >= 0.5;
	}
	
	/**
	 * Este metodo se utiliza para saber si un color es oscuro.
	 * 
	 * @param color color a verificar
	 * @return true si es un color oscuro, false de lo contrario
	 */
	private boolean isDarkColor(Color color) {
		//se calcula el brillo relativo del color (valor entre 0 y 255)
        double brightness = (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255;
        
        //Verificar si el color es muy oscuro (brillo < 0.5)
        return brightness < 0.5;
	}

	/**
	 * Este metodo se utiliza para calcular la distancia entre dos colores.
	 * 
	 * @param color1 Color 1 a verificar.
	 * @param color2 Color 2 a verificar.
	 * @return devuelve la distancia que existen entre ambos
	 */
	private double getColorDistance(Color color1, Color color2) {
		int redDiff = color1.getRed() - color2.getRed();
        int greenDiff = color1.getGreen() - color2.getGreen();
        int blueDiff = color1.getBlue() - color2.getBlue();
        
        return Math.sqrt(redDiff * redDiff + greenDiff * greenDiff + blueDiff * blueDiff);
	}
	
	/**
	 * Este metodo se utiliza para ajustar una imagen, dado el ancho y alto establecido.
	 * 
	 * @param url direccion de la imagen
	 * @param width nuevo ancho a establecer
	 * @param height nuevo alto a establecer
	 * @return devuelve un Image con la imagen ajustada.
	 */
	public Image getAdjustedImage(URL url, int width, int height) {
		ImageIcon imgIcon = new ImageIcon(url);
		
		if(width == -1 && height == -1) {
			width = imgIcon.getIconWidth();
			height = imgIcon.getIconHeight();
		}
		
		Image imagen = imgIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		return imagen;
	}
	
	/**
	 * Este metodo se encarga de rotar la imagen.
	 * 
	 * @param angle angulo que se desea aplicar
	 * @param imageOriginal Imagen que le debera aplicar los cambios
	 * @param enableTransparency si es true la imagen tendra transparencia, de lo contario no
	 * @param backgroundColor si la imagen no tiene transparencia se le aplicara un color
	 * @return devuelve el BufferedImage con los cambios aplicados
	 */
	public BufferedImage rotateImage(double angle, BufferedImage imageOriginal, boolean enableTransparency, Color backgroundColor) {
		double rotationAngle = Math.toRadians(angle);

  	    double sin = Math.abs(Math.sin(rotationAngle));
  	    double cos = Math.abs(Math.cos(rotationAngle));

  	    int w = (int) Math.floor((imageOriginal.getWidth() * cos) + (imageOriginal.getHeight() * sin));
  	    int h = (int) Math.floor((imageOriginal.getWidth() * sin) + (imageOriginal.getHeight() * cos));

  	    BufferedImage imageCopy;
  	    if (enableTransparency) {
  	        imageCopy = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
  	    } else {
  	        imageCopy = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
  	    }

  	    Graphics2D g2d = imageCopy.createGraphics();

  	    if (!enableTransparency) {
  	        g2d.setColor(backgroundColor);
  	        g2d.fillRect(0, 0, w, h);
  	    }

  	    int x = (w - imageOriginal.getWidth()) / 2;
  	    int y = (h - imageOriginal.getHeight()) / 2;

  	    AffineTransform transform = new AffineTransform();
  	    transform.rotate(rotationAngle, w / 2, h / 2);
  	    transform.translate(x, y);
  	    g2d.setTransform(transform);

  	    g2d.drawImage(imageOriginal, 0, 0, null);
  	    g2d.dispose();

  	    return imageCopy;
	}
	
	/**
	 * Este metodo se encarga de guardar un BufferedImage como imagen en la ruta especifica.
	 * 
	 * @param image imagen a guardar
	 * @param file ruta de guardado
	 * @param abrev abreviatura que se le agrega al nombre de la imagen, para no modificar la imagen original.
	 */
	public void downloadImage(BufferedImage imageOriginal, File file, String abrev) {
		try {
    		String name = "";
    		int lastIndex = file.getName().lastIndexOf(".");
    		if(lastIndex == -1) {
    			name = file.getName()+"("+abrev+")";
    		}
    		else {
    			name = file.getName().substring(0, lastIndex) + "("+abrev+")" + file.getName().substring(lastIndex);
    		}
    		
			ImageIO.write(imageOriginal, "png", new File(file.getParent()+"\\"+name));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Este metodo crea una imagen con la lista de colores de una Paleta.
	 * 
	 * @param colorList Lista de colores de la paleta
	 * @param namePalette nombre de la paleta de color
	 * @param imageWidth ancho que tendra la imagen
	 * @param imageHeight alto que tendra la imagen
	 * @return Objeto BufferedImage que luego podra ser guardado como una imagen el dispositivo
	 */
	public BufferedImage paletteToImage(ArrayList<Color> colorList, String namePalette, int imageWidth, int imageHeight) {
		 BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		 Graphics2D g2d = image.createGraphics();
		 
		 g2d.setColor(Color.WHITE);
		 g2d.fillRect(0, 0, imageWidth, imageHeight);
		 
		 int colorWidth = imageWidth / colorList.size();
		 String hexCode = "";
		 for(int i=0; i<colorList.size(); i++) {
			 g2d.setColor(colorList.get(i));
			 g2d.fillRect(i * colorWidth, 0, imageWidth, imageHeight - 75);
			 
			 g2d.setColor(Color.BLACK);
			 g2d.drawRect(i * colorWidth, 0, imageWidth + 10, imageHeight - 75);
			 
			 g2d.setColor(Manifest.imageDebug.getForegroundColor(colorList.get(i)));
			 g2d.setFont(new Font("Arial", Font.BOLD, 48));
			 
			 hexCode = Manifest.imageDebug.convertColorToHTML(colorList.get(i)).toUpperCase();
			 
			 g2d.drawImage(Manifest.imageDebug.rotateImage(270, textToImage(g2d, hexCode), true, Color.WHITE), (i * colorWidth) + 10, 10, null);
		 }
		 
		 g2d.setColor(Color.BLACK);
		 g2d.setFont(new Font("Arial", Font.BOLD, 32));
		 
		 int x = (imageWidth - 10) - Manifest.imageDebug.getAnchoTextoPixel(g2d, Manifest.applicationName);
		 int y = imageHeight - (Manifest.imageDebug.getAltoTextoPixel(g2d, Manifest.applicationName)) + 10;
		 g2d.drawString(Manifest.applicationName, x, y);
		 
		 g2d.setColor(Color.GRAY);
		 g2d.setFont(new Font("Arial", Font.PLAIN, 32));
		 
		 g2d.drawString(namePalette, 10, y);
		 
		 try {
			 InputStream logoStream = getClass().getResourceAsStream("/icon/icon_app.png");
			 BufferedImage logoImage = ImageIO.read(logoStream);
			 
			 g2d.drawImage(logoImage, x - 74, imageHeight - 72, 64, 64, null);
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
		 
		return image;
	}
	
	/**
	 * Este metodo permite convertir un texto en una imagen
	 * 
	 * @param g objeto Graphics original con el tamaño y color de fuente ya establecido
	 * @param text texto a convertir en imagen
	 * @return Objeto BufferedImage con el texto en una imagen transparente
	 */
	public BufferedImage textToImage(Graphics g, String text) {
		int width = Manifest.imageDebug.getAnchoTextoPixel(g, text) + 10;
		int height = Manifest.imageDebug.getAltoTextoPixel(g, text) + 10;
		
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		
		g2d.setColor(g.getColor());
		g2d.setFont(g.getFont());
		
		g2d.drawString(text, 5, height - (height / 4));
		
		return bufferedImage;
	}
}