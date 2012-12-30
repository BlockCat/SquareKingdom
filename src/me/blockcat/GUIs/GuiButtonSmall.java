package me.blockcat.GUIs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import me.blockcat.Main;

public class GuiButtonSmall extends GuiButton {

	
	private int width = 77;
	private int height = 40;	
	private int id;
	private Color color = Color.WHITE;
	
	public GuiButtonSmall(int x, int y, String message) {
		super(x, y, message);
	}
	
	public boolean inButton(int x, int y) {
		
		if (x >= this.x && x <= this.x + this.width) {
			if (y >= this.y && y <= this.y + this.height) {
				return true;
			}
		}
		
		return false;
	}
	
	public void render(Graphics2D g) {
		
		g.drawImage(GuiCentral.getImage("resources/images/SK_Button_Small.png"), x, y, null);
		
		g.setColor(Color.GREEN);
		g.setFont(Main.gameFont);
		g.drawString(message, x + 10, y + 25);
	}
	
	public int getID() {
		return id;
	}

	public void setID(int count) {
		this.id = count;
	}
	
	
		
}
