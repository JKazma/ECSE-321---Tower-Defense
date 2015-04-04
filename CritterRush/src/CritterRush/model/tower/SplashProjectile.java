package CritterRush.model.tower;

import java.awt.*;
import java.util.ArrayList;

import CritterRush.controller.CritterManager;
import CritterRush.controller.ICManager;
import CritterRush.model.critter.Critter;

public class SplashProjectile extends Projectile{
	
	int finalRadius;
	int currentRadius;
	ArrayList<Critter> critters;
	
	public SplashProjectile(int x, int y, int damage, int radius, ArrayList<Critter> critters){
		this.x = x;
		this.y = y;	
		this.damage = damage;
		this.speed = ICManager.projectileSpeed;
		this.critters = critters;
		this.finalRadius = radius;
		currentRadius = 0;
		appear();
	}
	@Override
	public void move(){
		if(alive){	
			//Grow the circle
			if(currentRadius < finalRadius) 
				currentRadius += speed;
			if (currentRadius > finalRadius)
				currentRadius = finalRadius;
		}
	}
	
	@Override
	public void checkCollision(){
		for(Critter c: critters){
			if(c.isAlive() && c.isVisible() && this.isAlive() && currentRadius >= finalRadius){
				doDamage(c);
			}
		}
		if(currentRadius >= finalRadius)
			disappear();
	}
	@Override
	protected void doDamage(Critter c) {
		c.reduceHealth(damage);
	}
	
	@Override
	public void drawStrategy(Graphics g) {
		//Draw tower circle
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.orange);
		g2.setStroke(new BasicStroke(ICManager.squareHighlightTickness));
		g.drawOval(x - currentRadius, y - currentRadius, currentRadius*2, currentRadius*2);
	}
}
