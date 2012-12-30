package me.blockcat;

import java.awt.Color;
import java.awt.Graphics2D;

import me.blockcat.GUIs.GuiButton;

public class GuiTutorial extends Gui {

	private Main main;

	public GuiTutorial(Main main) {
		this.main = main;
	}

	@Override
	public void initiate() {
		this.add(new GuiButton(160, 300, "Start!"));
		this.add(new GuiButton(160, 360, "Back!"));
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		this.showMessage(g, "Tutorial:", 60.0F, 30, 60);
		this.showMessage(g, "Use: A & D to move.", 20.0F, 30, 120);
		this.showMessage(g, "Use the Spacebar to jump.", 20.0F, 30, 150);
		this.showMessage(g, "<--- is the finish of each level.", 20.0F, 55, 180);
		this.showMessage(g, "<--- You die from this block.", 20.0F, 55, 210);
		g.setColor(Color.YELLOW);
		g.fillRect(30, 165, 20, 20);
		g.setColor(Color.RED);
		g.fillRect(30, 195, 20, 20);
	}

	private void showMessage(Graphics2D g, String msg, float size, int x, int y) {
		g.setColor(Color.WHITE);
		g.setFont(Main.gameFont.deriveFont(size));
		g.drawString(msg, x, y);
	}

	@Override
	protected void executeButton(int id) {
		switch (id) {
		case 0:
			main.changeScreen("game", new GUIGame(main), true);
			break;
		case 1:
			main.changeScreen("main", new GuiMainMenu(main), true);
		}

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
