package me.blockcat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.io.InputStream;
import java.util.HashMap;

import javax.swing.JFrame;

import me.blockcat.listeners.GameKeyListener;
import me.blockcat.listeners.GameMouseListener;

public class Main extends JFrame implements Runnable{
	
	public static Font gameFont = null;
	public static Main main;
	public final int WIDTH = 640;
	public final int HEIGHT = 480;
	public Gui currentScreen = null;
	public GameKeyListener keyListener;
	public GameMouseListener mouseListener;
	private int FPS = 0;
	private boolean running = false;
	private HashMap<String, Gui> screens = new HashMap<String, Gui>();
	
	private Thread painter = new Thread(new Runnable() {

		@Override
		public void run() {
			
			Graphics2D g = null;
			Graphics2D bufferGraphics = null;
			BufferStrategy strategy= getBufferStrategy();

			try {
				g = (Graphics2D) strategy.getDrawGraphics();
				bufferGraphics = (Graphics2D) g.create();
				bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				if (currentScreen != null) {
					currentScreen.render(bufferGraphics);
				}
				
				drawFPS(bufferGraphics);
				
				g = bufferGraphics;
				
			} finally {
				bufferGraphics.dispose();
				g.dispose();
			}
			
			strategy.show();
			Toolkit.getDefaultToolkit().sync();				
		}
		
	});
	private Thread updater = new Thread(new Runnable() {

		@Override
		public void run() {
			currentScreen.update();
		}
		
	});
	public static void main(String[] args) {
		Main main = new Main();
		
		Thread t = new Thread(main);
		t.start();
	}

	public Main() {

		/** Initiate screen */
		this.setTitle("SK");
		this.setSize(640, 480);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		this.createBufferStrategy(2);
		
		Main.main = this;
		
		keyListener = new GameKeyListener(this);
		mouseListener = new GameMouseListener(this);
		
		this.addKeyListener(keyListener);
		this.addMouseListener(mouseListener);
		this.changeScreen("main", new GuiMainMenu(this), true);

		/** running = true */
		this.running = true;
		
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("resources/font.ttf");
		try {
			Font f = Font.createFont(Font.TRUETYPE_FONT, is);
			gameFont = f.deriveFont(14.0F);
		} catch (Exception e) {
			e.printStackTrace();
			gameFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
		}

	}

	@Override
	public void run() {

		int FPS = 0;
		long lastFps = 0;

		//Target FPS
		long lastLoop = System.currentTimeMillis();
		final long OPTIMAL_TIME = 1000000000 / 60; 
		
		

		while(running) {

			long current = System.currentTimeMillis();
			long updateTime = current - lastLoop;
			lastLoop = current;			

			lastFps += updateTime;
			FPS++;
			if(lastFps >= 1000){
				this.FPS = FPS;
				lastFps = 0;
				FPS = 0;
			}

			//this.updateGame();
			//this.paintGame();
			updater.run();
			painter.run();

			try {
				Thread.sleep((lastLoop - System.currentTimeMillis() + OPTIMAL_TIME) /1000000 );
			} catch (Exception e) {

			}
		}
	}
	
	public void stop(){
        System.exit(0);
    }
	
	public void changeScreen(String name, Gui screen, boolean newScreen) {
		if (newScreen) {
			currentScreen = screen;
			currentScreen.initiate();
			screens.put(name, screen);
			return;
		}
		if (screens.containsKey(name)) {
			currentScreen = screens.get(name);
			return;
		} else {
			currentScreen = screen;
			currentScreen.initiate();
			screens.put(name, screen);
		}
	}
	
	public void mouseClick(int x, int y) {
		currentScreen.mouseClick(x, y);
	}
	
	public void drawFPS(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(Main.gameFont.deriveFont(20.0F));
		g.drawString("" + this.FPS + "", 600, 20);
	}

}
