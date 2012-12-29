package me.blockcat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import me.blockcat.GUIs.GuiButton;

public abstract class Gui {
	
	public final int WIDTH = 640;
	public final int HEIGHT = 480;
	private int count = 0;
	protected List<GuiButton> elements = new ArrayList<GuiButton>();
	
	public abstract void initiate();
	public void render(Graphics2D g) {
		/** background */
		g.setColor(new Color(52, 52, 52));
		g.fillRect(0, 0, 640, 480);
		
		for (GuiButton element : elements) {
			element.render(g);
		}		
	}
	
	public void add(GuiButton element) {
		elements.add(element);
		element.setID(count);
		count++;
	}
	public void mouseClick(int x, int y) {
		for (GuiButton button : elements) {
			if (button.inButton(x, y)) {
				executeButton(button.getID());
			}
		}
	}
	protected abstract void executeButton(int id);
	
	public abstract void update();
}
