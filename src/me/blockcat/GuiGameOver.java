package me.blockcat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import me.blockcat.GUIs.GuiButton;

public class GuiGameOver extends Gui {

	private Main main;
	private boolean dead = false;

	public GuiGameOver(Main main, boolean dead) {
		this.main = main;
		this.dead = dead;
	}

	@Override
	public void initiate() {
		this.add(new GuiButton(160, 240, "Main menu"));
		this.add(new GuiButton(160, 300, "Quit!"));
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);

		if (dead) {
			g.setColor(Color.WHITE);
			g.setFont(Main.gameFont.deriveFont(60.0F));
			g.drawString("Game Over!", 160, 200);
		} else {
			g.setColor(Color.WHITE);
			g.setFont(Main.gameFont.deriveFont(60.0F));
			g.drawString("You have won!", 160, 200);
		}

	}
	@Override
	protected void executeButton(int id) {
		switch(id) {
		case 0: 
			main.changeScreen("main", new GuiMainMenu(main), true);
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
