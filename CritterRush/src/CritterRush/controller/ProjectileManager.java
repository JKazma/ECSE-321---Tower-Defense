package CritterRush.controller;

import java.awt.*;
import java.util.ArrayList;

import CritterRush.model.tower.Projectile;

public class ProjectileManager {
	
	private static ProjectileManager uniqueInstance = null;
	
	private static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	/**
	 * Return unique instance of game controller
	 * @return
	 */
    public static synchronized ProjectileManager getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ProjectileManager();
        }
        return uniqueInstance;
    }
	
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
	
	public static void removeProjectile(Projectile p)
	{
		projectiles.remove(p);
	}
	
	public static void moveAllProjectiles() { 
		for(Projectile p:projectiles) {
				p.move();
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
