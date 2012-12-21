package me.blockcat;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import me.blockcat.GUIs.GuiButton;

public abstract class GUI {
	
	protected List<GuiButton> elements = new ArrayList<GuiButton>();
	private int count = 0;
	
	public abstract void initiate();
	public abstract void render(Graphics2D g);
	
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