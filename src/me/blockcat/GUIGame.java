package me.blockcat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

import me.blockcat.Entity.Entity;
import me.blockcat.Entity.EntityPlayer;
import me.blockcat.GUIs.GuiButton;
import me.blockcat.GUIs.GuiButtonSmall;
import me.blockcat.Obstacle.Obstacle;
import me.blockcat.Obstacle.ObstacleFinish;
import me.blockcat.Obstacle.ObstacleHurt;
import me.blockcat.Obstacle.ObstacleWall;

public class GUIGame extends Gui {

	public int lives = 4;
	public EntityPlayer player = null;
	private int level = 1;
	private Main main;	
	private List<Entity> entities = new CopyOnWriteArrayList<Entity>();
	private List<Obstacle> obstacles = new CopyOnWriteArrayList<Obstacle>();
	private Image backgroundImage = null;
	private Image barImage = null;
	private Image liveImage = null;
	private Camera camera;


	public GUIGame(Main main) {
		this.main = main;
		camera = new Camera(this);

		try {
			InputStream in1 = this.getClass().getClassLoader().getResourceAsStream("resources/images/background.png");
			InputStream in2 = this.getClass().getClassLoader().getResourceAsStream("resources/images/bar.png");
			InputStream in3 = this.getClass().getClassLoader().getResourceAsStream("resources/images/bar/live.png");
			backgroundImage = ImageIO.read(in1); 
			barImage = ImageIO.read(in2);
			liveImage = ImageIO.read(in3);
		} catch (Exception e) {

		}
	}

	@Override
	public void initiate() {
		/*for (int x = 0; x < 640/32; x++) {
			obstacles.add(new ObstacleWall(x * 32, 400));
		}
		obstacles.add(new ObstacleWall(7*32, 400-16));*/
		this.add(new GuiButtonSmall(10, 430, "Menu"));

		try {
			this.loadLevel(1);
		} catch (Exception e) {
			main.changeScreen("main", new GuiMainMenu(main), true);
		}
	}

	public boolean isSolidAt(int x, int y) {
		for (Obstacle ob : obstacles) {
			if(ob.inObject(x, y)) {
				return true;
			}
		}
		return false;
	}

	public Obstacle getSolid(int x, int y, Direction direction, boolean standing) {
		if (direction == Direction.DOWN) {
			for (Obstacle ob : obstacles) {
				if (standing) {
					if(ob.inObject(x + 1, y + 20) && ob.inObject(x + 15, y + 20)) {
						return ob;
					}
				} else {
					if(ob.inObject(x + 1, y + 20) || ob.inObject(x + 15, y + 20)) {
						return ob;
					}
				}
			}
		} else if (direction == Direction.UP) {
			for (Obstacle ob : obstacles) {
				if (standing) {
					if(ob.inObject(x + 1, y - 4) && ob.inObject(x + 15,  y - 4)) {
						return ob;
					}
				} else {
					if(ob.inObject(x + 1, y - 4) || ob.inObject(x + 15,  y - 4)) {
						return ob;
					}
				}
			}
		} else if (direction == Direction.LEFT) {
			for (Obstacle ob : obstacles) {
				if (standing) {
					if(ob.inObject(x - 4, y + 2) && ob.inObject(x - 4, y + 15)) {
						return ob;
					}
				} else {
					if(ob.inObject(x - 4, y + 6) || ob.inObject(x - 4, y + 15)) {
						return ob;
					}	
				}
			}
		} else if (direction == Direction.RIGHT) {
			for (Obstacle ob : obstacles) {
				if (standing) {
					if(ob.inObject(x + 20, y + 2) && ob.inObject(x + 20, y + 15)) {
						return ob;
					}
				} else {
					if(ob.inObject(x + 20, y + 6) || ob.inObject(x + 20, y + 15)) {
						return ob;
					}
				}
			}
		}
		return null;
	}

	public void switchLevel() {
		this.entities.clear();
		this.obstacles.clear();
		level++;
		try {
			this.loadLevel(level);
		} catch(Exception e) {
			main.changeScreen("game_over", new GuiGameOver(main, false), true);
		}
	}

	public void loadLevel(int level) throws Exception {
		try {
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("resources/level/" + level + ".level");
			Scanner scanner = new Scanner(in);
			int line = 0;
			while(scanner.hasNextLine()) {
				String scan = scanner.nextLine();
				for(int i = 0; i < scan.length(); i++) {
					switch(scan.charAt(i)) {
					case 'X':
						obstacles.add(new ObstacleWall(i * 16, line * 16));
						break;
					case 'O':
						player = new EntityPlayer(i * 16, line * 16, this);
						entities.add(player);
						break;
					case 'H':
						obstacles.add(new ObstacleHurt(i * 16, line * 16, true));
						break;
					case 'B':
						obstacles.add(new ObstacleHurt(i * 16, line * 16, false));
						break;
					case 'F':
						obstacles.add(new ObstacleFinish(i * 16, line * 16));
						break;
					}

				}
				line++;
			}
			scanner.close();
		} catch(Exception e) {
			throw new Exception();
		}
	}

	public List<Obstacle> getObstacles() {
		return obstacles;
	}
	public List<Entity> getEntities() {
		return entities;
	}


	@Override
	public void render(Graphics2D g) {
		//super.render(g);

		//g.drawImage(backgroundImage, 0, 0, null);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 640, 480);

		camera.render(g);

		g.drawImage(barImage, 0, 420, null);
		if (player != null) {
			for (int i = 0; i < lives; i ++) {
				g.fill3DRect(600 - (i * 40), 440, 24, 24, true);
			}
		}
		
		for (GuiButton element : elements) {
			element.render(g);
		}	
	}

	@Override
	protected void executeButton(int id) {
		switch(id) {
		case 0: 
			main.changeScreen("main", new GuiMainMenu(main), true);
		}

	}

	@Override
	public void update() {
		if (main.keyListener.isPressed(KeyEvent.VK_ESCAPE)) {
			main.changeScreen("main", new GuiMainMenu(main), false);
		}
		for (Entity ent : entities) {
			ent.move();
		}
		camera.update(player);
	}

	public void respawn() {
		try {
			entities.remove(player);
			player = null;			
			this.loadLevel(level);
			lives--;
			//player.setInvincible(false);
		} catch (Exception e) {

		}
	}

	public void dead() {
		main.changeScreen("game_over", new GuiGameOver(main, true), true);
	}
}
