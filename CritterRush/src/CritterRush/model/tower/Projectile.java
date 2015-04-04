package CritterRush.model.tower;

import java.awt.*;

import CritterRush.controller.*;
import CritterRush.model.GameObject;
import CritterRush.model.critter.Critter;

public abstract class Projectile extends GameObject{
	
	//constants
	protected int x;
	protected int y;
	protected int damage;
	protected double dx;
	protected double dy;
	protected double speed;
	protected boolean alive;
	protected Rectangle projectileShape;
	protected Image image;

	protected Critter c;
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param damage
	 * @param c
	 */
	public Projectile(int x, int y, int damage, Critter c){
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.speed = ICManager.projectileSpeed;
		this.c = c;
		appear();
	}
	
	/**
	 * Constructor
	 */
	public Projectile(){
		this.speed = ICManager.projectileSpeed;
	}
	
	/**
	 * Calculate the angle at which the projectile should move at.
	 */
	public void angleProjectile() {
		dx = c.getX() + (ICManager.cellSize / 2) - x;
		dy = c.getY() + (ICManager.cellSize / 2) - y;
		
		double angle = Math.atan2(dy, dx);
		
		dx = Math.cos(angle);
		dy = Math.sin(angle);
		
	}
	
	/**
	 * Move the projectile taking into account the calculated angle.
	 */
	public void move(){		
		if(alive){	
			
			//recompute the angle, the delta x and y
			angleProjectile();
			x += dx * speed;
			y += dy * speed;
			
			//If it misses its target, clear when out of bounds
			if(x < 0 || x > ICManager.fieldSizeX || y < 0 || y > ICManager.fieldSizeY) 
				this.disappear();
			
		}
	}
	
	/**
	 * Check for collision with critters
	 */
	public void checkCollision(){
			if(c.isAlive() && this.alive){
				
				//Create rectangles for the projectile and the critter to detect collisions when they intersect
				Rectangle critterShape = new Rectangle((int) c.getX(), (int) c.getY(), ICManager.cellSize, ICManager.cellSize);
				Rectangle projectile = new Rectangle(x,y,image.getWidth(null),image.getHeight(null)); 
				if(critterShape.intersects(projectile)) 
				{
					//Inflict damage and kill projectile.
					disappear();
					doDamage(c);
				}
			}
			else
				disappear();
		}
	
	/**
	 * Inflict damage to critter.
	 * @param c
	 */
	protected abstract void doDamage(Critter c);
	
	
	//Getter and setters
	public boolean isAlive() {
		return alive;
	}
	
	public void appear(){
		this.alive = true;
		show();
	}
	
	public void disappear(){
		this.alive = false;
		hide();
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public double getDX(){
		return dx;
	}
	
	public double getDY(){
		return dy;
	}
	
	public int getDamage(){
		return damage;
	}
	
	@Override
	public void drawStrategy(Graphics g) {
		if(alive){
			g.drawImage(image,x,y,null);
		}
	}
}
	