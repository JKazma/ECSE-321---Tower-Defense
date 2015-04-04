package CritterRush.controller;

import java.awt.*;
import java.util.ArrayList;

import CritterRush.model.*;
import CritterRush.model.tower.Projectile;
public class ProjectileManager {
	private static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	public static void addProjectile(Projectile p)
	{
		projectiles.add(p);
	}
	public static void removeProjectiles(Projectile p)
	{
		projectiles.remove(p);
	}
	
	public static void removeAllProjectiles()
	{
		projectiles.clear();
	}
	
	public static void moveAllProjectiles() { 
		for(Projectile p:projectiles) {
			p.move();
		}
	}
	
	public static void checkAllCollisions() { 
		for (Projectile p : projectiles) {
			p.checkCollision();
		}
	}
	
	public static ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public static void draw(Graphics g) {
		for(Projectile p:projectiles) {
			p.draw(g);
		}
	}

}
