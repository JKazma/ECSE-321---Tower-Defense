package CritterRush.model;

import java.awt.*;

import CritterRush.controller.ProjectileManager;

public class Tower extends GameObject{
	protected int x;
	protected int y;
	protected int level;
	protected int range;
	protected int damage;
	protected int fireRate; //number of shots per second
	protected int buy;
	protected int refundValue;
	protected int upgradeCost;
	protected Image image;
	protected boolean hit;
	protected boolean upgraded;


	public Tower(int x, int y)
	{
		this.x = x;
		this.y = y;	
	}

	public void shoot(Critter critter)
	{
		
		double critPos = Math.pow((critter.getX()-x) + (critter.getY()-y),2);
		if(Math.sqrt(critPos)< range && critter.isAlive())
			{
				ProjectileManager.addProjectile(new Projectile (new Point(x,y), new Point(critter.getX(), critter.getY()), damage,image)); //image should be updated
			}
	}
	
	public boolean upgradedTower()
	{
		return upgraded;
	}
	
	// getters
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getLevel()
	{
		return level;
	}
	public int getRange()
	{
		return range;
	}
	public int getDamage()
	{
		return damage;
	}
	public int getFireRate()
	{
		return fireRate;
	}
	public int getBuy()
	{
		return buy;
	}
	public int getrefundValue()
	{
		return refundValue;
	}
	public int getUpgradeCost()
	{
		return upgradeCost;
	}
	public Image getImage()
	{
		return image;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	//Get Tower info in an array to be display in the GamePanel
	public int[] getInfo(){
		return new int[] {level, damage, range, fireRate, upgradeCost, refundValue};
	}
	

	@Override
	public void drawStrategy(Graphics g) {
		g.drawImage(image, x, y, null);
	}

}


