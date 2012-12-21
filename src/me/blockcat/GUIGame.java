package me.blockcat;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import me.blockcat.Entity.Entity;
import me.blockcat.Entity.EntityPlayer;
import me.blockcat.Obstacle.Obstacle;
import me.blockcat.Obstacle.ObstacleWall;

public class GUIGame extends GUI {

	public Entity player = null;
	private Main main;
	private List<Entity> entities = new CopyOnWriteArrayList<Entity>();
	private List<Obstacle> obstacles = new CopyOnWriteArrayList<Obstacle>();
	private Camera camera;

	public GUIGame(Main main) {
		this.main = main;
		camera = new Camera(this);
	}

	@Override
	public void initiate() {
		/*for (int x = 0; x < 640/32; x++) {
			obstacles.add(new ObstacleWall(x * 32, 400));
		}
		obstacles.add(new ObstacleWall(7*32, 400-16));*/
		this.loadLevel(1);
	}

	public boolean isSolidAt(int x, int y) {
		for (Obstacle ob : obstacles) {
			if(ob.inObject(x, y)) {
				return true;
			}
		}
		return false;
	}
	

	public Obstacle getSolid(int x, int y, Direction direction) {
		if (direction == Direction.DOWN) {
			for (Obstacle ob : obstacles) {
				if(ob.inObject(x + 1, y + 20) || ob.inObject(x + 15, y + 20)) {
					return ob;
				}
			}
		} else if (direction == Direction.UP) {
			for (Obstacle ob : obstacles) {
				if(ob.inObject(x + 1, y - 4) || ob.inObject(x + 15,  y - 4)) {
					return ob;
				}
			}
		} else if (direction == Direction.LEFT) {
			for (Obstacle ob : obstacles) {
				if(ob.inObject(x - 4, y + 1) || ob.inObject(x - 4, y + 15)) {
					return ob;
				}
			}
		} else if (direction == Direction.RIGHT) {
			for (Obstacle ob : obstacles) {
				if(ob.inObject(x + 20, y + 1) || ob.inObject(x + 20, y + 15)) {
					return ob;
				}
			}
		}
		return null;
	}

	public void loadLevel(int level) {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("resources/level/" + level + ".level");
		Scanner scanner = new Scanner(in);
		int line = 0;
		while(scanner.hasNextLine()) {
			String scan = scanner.nextLine();
			for(int i = 0; i < scan.length(); i++) {
				if (scan.charAt(i) == 'X') {
					obstacles.add(new ObstacleWall(i * 16, line * 16));
				} else if (scan.charAt(i) == 'O') {
					System.out.println("player created at: " + i*16 + ":" + line*16);
					player = new EntityPlayer(i * 16, line * 16, this);
					entities.add(player);
				}
			}
			line++;
		}
		scanner.close();
	}
	
	public List<Obstacle> getObstacles() {
		return obstacles;
	}
	public List<Entity> getEntities() {
		return entities;
	}


	@Override
	public void render(Graphics2D g) {
		camera.renderFore(g);
	}

	@Override
	protected void executeButton(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		if (main.keyListener.isPressed(KeyEvent.VK_ESCAPE)) {
			main.changeScreen("main", new GUIMainMenu(main));
		}
		for (Entity ent : entities) {
			ent.move();
		}
		camera.update(player);
	}





}
