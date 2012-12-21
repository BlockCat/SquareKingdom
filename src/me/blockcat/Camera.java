package me.blockcat;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import me.blockcat.Entity.Entity;
import me.blockcat.Obstacle.Obstacle;

public class Camera {

	private GUIGame game = null;
	private final int WIDTH = 640;
	private final int HEIGHT = 480;
	private int x = 200;
	private int y = 200;
	
	private List<Obstacle> viewableObs = new CopyOnWriteArrayList<Obstacle>();
	private List<Entity> viewableEnts = new CopyOnWriteArrayList<Entity>();


	public Camera(GUIGame guiGame) {
		this.game = guiGame;
	}

	private synchronized List<Obstacle> getViewableObstacles() {
		List<Obstacle> list = new ArrayList<Obstacle>();
		/*for (Obstacle obs : game.getObstacles()) {
			if (obs.getX() >= this.x - 20 && obs.getX() <= this.x + WIDTH) {
				if (obs.getY() >= y - 20 && obs.getY() <= y + HEIGHT) {
					list.add(obs);
				}
			}
		}*/
		return game.getObstacles();
		//return list;
	}

	private synchronized List<Entity> getViewableEntities() {
		List<Entity> list = new ArrayList<Entity>();
		/*for (Entity ent : game.getEntities()) {
			if (ent.getX() >= x - 20 && ent.getX() <= x + WIDTH) {
				if (ent.getY() >= y - 20 && ent.getY() <= y + HEIGHT) {
					list.add(ent);					
				}
			}
		}*/
		return game.getEntities();
		//return list;
	}

	public synchronized void update(Entity player) {
		if (player != null) {
			x = (player.getX() - (WIDTH / 2)) * -1;
			y = (player.getY() - (HEIGHT / 2)) * -1;
		}
		
		viewableObs = this.getViewableObstacles();
		viewableEnts  = this.getViewableEntities();
	}

	public synchronized void renderFore(Graphics2D g) {
		
		Iterator<Obstacle> it = this.viewableObs.iterator();
		while(it.hasNext()) {
			Obstacle obs = it.next();
			obs.render(x + obs.getX() , y + obs.getY(), g);
		}
		Iterator<Entity> entIt = this.viewableEnts.iterator();
		while(entIt.hasNext()) {
			Entity ent = entIt.next();
			ent.render(x + ent.getX(), y + ent.getY(), g);
		}
		/*for (Obstacle obs : this.viewableObs) {
			obs.render(x + obs.getX() , y + obs.getY(), g);
		}*/
		/*for (Entity ent : this.viewableEnts) {
			ent.render(x + ent.getX(), y + ent.getY(), g);
		}*/
	}

}
