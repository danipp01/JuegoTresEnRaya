package juego;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;


public class Cache {

	
	private HashMap<String, BufferedImage> recursos = new HashMap<String, BufferedImage>();

	private static Cache instance = null;

	private static String RESOURCES_FOLDER = "../TresEnRayas/juego/resources/";

	
	public static Cache getInstance() {
		if (instance == null) {
			instance = new Cache();
		}
		return instance;
	}

	private BufferedImage loadImage(String resourceName) {
		URL url = null;
		try {
			url = getClass().getResource(resourceName);
			return ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("La imagen no se pudo cargar: " + resourceName + " de " + url);
		}
		return null;

	}

	
	public BufferedImage getImagen(String resourceName) {
		BufferedImage img = recursos.get(resourceName);

		if (img == null) {
			img = loadImage(RESOURCES_FOLDER + resourceName);
			recursos.put(resourceName, img);
		}
		return img;
	}
}


