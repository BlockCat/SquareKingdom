package me.blockcat.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import me.blockcat.Main;

public class GameKeyListener implements KeyListener{

	private Main main;
	public HashMap<Integer, Boolean> keys = new HashMap<Integer, Boolean>();

	public GameKeyListener(Main main) {
		this.main = main;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		keys.put(event.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent event) {
		keys.put(event.getKeyCode(), false);		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isPressed(int id) {
		if (keys.containsKey(id)) {
			return keys.get(id);
		} else {
			return false;
		}
	}

}
