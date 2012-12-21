package me.blockcat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.imageio.ImageIO;

import me.blockcat.GUIs.GuiButton;

public class GUIMainMenu extends GUI {
	
	private Main main;
	private Image title = null;

	public GUIMainMenu (Main main) {
		this.main = main;
	}

	@Override
	public void initiate() {
		this.add(new GuiButton(160, 240, "Start game!"));
		this.add(new GuiButton(160, 300, "Help!"));
		this.add(new GuiButton(160, 360, "Quit!"));
		try {
			URL url = this.getClass().getClassLoader().getResource("resources/images/Title.png");
			Image im = ImageIO.read(url);
			title = im;
		} catch (Exception e) {
			
		}
	}

	@Override
	public void render(Graphics2D g) {
		/** background */
		g.setColor(new Color(52, 52, 52));
		g.fillRect(0, 0, main.WIDTH, main.HEIGHT);
		
		/** draw the rest. */
		g.drawImage(title , 0, 0, null);
		for (GuiButton element : elements) {
			element.render(g);
		}
	}

	@Override
	protected void executeButton(int id) {
		switch(id) {
		case 0: 
			System.out.println("started game!");
			main.changeScreen("game", new GUIGame(main));
			break;
		case 1:
			System.out.println("Help!");
			break;
		case 2:
			System.out.println("quit!");
			main.stop();
		}
	}

	@Override
	public void update() {
		if (main.keyListener.isPressed(KeyEvent.VK_ESCAPE)) {
			//main.stop();
		}
	}


}
