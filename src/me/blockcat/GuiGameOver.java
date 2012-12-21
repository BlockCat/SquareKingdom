package me.blockcat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import me.blockcat.GUIs.GuiButton;

public class GuiGameOver extends GUI {

	private Main main;

	public GuiGameOver(Main main) {
		this.main = main;
	}

	@Override
	public void initiate() {
		this.add(new GuiButton(160, 240, "Main menu"));
		this.add(new GuiButton(160, 300, "Quit!"));
	}

	@Override
	public void render(Graphics2D g) {
		/** background */
		g.setColor(new Color(52, 52, 52));
		g.fillRect(0, 0, main.WIDTH, main.HEIGHT);
		
		g.setColor(Color.WHITE);
		g.setFont(Main.gameFont.deriveFont(60.0F));
		g.drawString("Game Over!", 160, 200);
		
		/** draw the rest. */
		for (GuiButton element : elements) {
			element.render(g);
		}
	}

	@Override
	protected void executeButton(int id) {
		switch(id) {
		case 0: 
			main.changeScreen("main", new GUIMainMenu(main), true);
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
