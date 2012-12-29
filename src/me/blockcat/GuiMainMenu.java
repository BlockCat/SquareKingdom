package me.blockcat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.imageio.ImageIO;

import me.blockcat.GUIs.GuiButton;

public class GuiMainMenu extends Gui {
	
	private Main main;
	private Image title = null;

	public GuiMainMenu (Main main) {
		this.main = main;
	}

	@Override
	public void initiate() {
		this.add(new GuiButton(160, 300, "Start game!"));
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
		super.render(g);
		/** draw the rest. */
		g.drawImage(title , 0, 0, null);
	}

	@Override
	protected void executeButton(int id) {
		switch(id) {
		case 0: 
			System.out.println("started game!");
			main.changeScreen("tutorial", new GuiTutorial(main), true);
			break;
		case 1:
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
