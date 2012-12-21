package me.blockcat.GUIs;

import java.awt.Image;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class GuiCentral {
	
	private static HashMap<String, Image> images = new HashMap<String, Image>();
	
	public static Image getImage(String url) {
		if (images.containsKey(url)) {
			return images.get(url);
		} else {
			try {
				Image image = ImageIO.read(GuiCentral.class.getClassLoader().getResourceAsStream(url));
				images.put(url, image);
				return image;
			} catch (Exception e) {
				return null;
			}
		}
	}

}
