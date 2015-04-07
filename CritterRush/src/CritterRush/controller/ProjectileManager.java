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
	
    /**
     * Add a projectile to the ArrayList
     * @param p
     */
	public static void addProjectile(Projectile p)
	{
		projectiles.add(p);
	}
	
	/**
	 * Clear the ArrayList
	 */
	public static void removeAllProjectiles()
	{
		projectiles.clear();
	}
	
	/**
	 * Remove object p from ArrayList
	 * @param p
	 */
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
	
	/**
	 * Get Projetiles
	 * @return
	 */
	public static ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	/**
	 * Draw projectiles
	 * @param g
	 */
	public static void draw(Graphics g) {
		for(Projectile p:projectiles) {
			p.draw(g);
		}
	}

}
