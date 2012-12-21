package me.blockcat.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import me.blockcat.Main;

public class GameMouseListener implements MouseListener{
	
	private Main main = null;
	
	public GameMouseListener(Main main) {
		this.main = main;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getButton() == MouseEvent.BUTTON1 && main != null) {
			main.mouseClick(event.getX(), event.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
