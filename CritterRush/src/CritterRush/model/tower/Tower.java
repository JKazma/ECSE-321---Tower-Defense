package CritterRush.model.tower;

import java.awt.*;
import java.util.ArrayList;

import CritterRush.controller.ICManager;
import CritterRush.controller.ProjectileManager;
import CritterRush.model.GameObject;
import CritterRush.model.Timer;
import CritterRush.model.critter.Critter;

public abstract class Tower extends GameObject{
	protected int x;
	protected int y;
	protected int level;
	protected int range;
	protected int damage;
	protected float fireRate; //number of shots per second
	protected int initialCost;
	protected int refundValue;
	protected int upgradeCost;
	protected int maxLevel;
	protected int totalCost;
	protected Image image;
	protected boolean hit;
	protected boolean upgradable;


	public Tower(int x, int y){
		this.x = x;
		this.y = y;	
	}
	
	
	public void shootCritters(ArrayList <Critter> critters){
		
		//Check if tower is loaded.
		if(Timer.getTravelTime()%(ICManager.frameRate / fireRate) == 0) {
			double critPos;
			Critter temp = null;
			
			//For each critter in range, find closest to exit
			for(Critter c: critters){
				critPos = Math.pow((c.getX() - x), 2) + Math.pow ((c.getY() - y),2);
				
				if(Math.sqrt(critPos) < range && c.isAlive() && c.isVisible()){
					if(temp == null) temp = c;
					
					if(c.getCellIndex() > temp.getCellIndex()){
						temp = c;
					}
				}
			}
			//Create projectile
			if(temp != null) {
				addProjectile(temp);
			}
		}
	}
	
	protected abstract void addProjectile(Critter c);
	
	/**
	 * Upgrade tower if max level was not reached.
	 */
	public void upgradeTower(){
		level = level + 1;
		range = (int)(range * 1.1);
		damage = (int) (damage * 1.5);
		totalCost = totalCost + upgradeCost;
		refundValue = (int)(totalCost * 0.7);
		upgradeCost = (int)(totalCost * 0.5);
		
		//Disable upgradable if max level reached.
		if(level == maxLevel)
			upgradable  = false;
	}
//	public abstract void upgradeTower();
	
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
	public float getFireRate()
	{
		return fireRate;
	}
	public int getinitialCost()
	{
		return initialCost;
	}
	public int getRefundValue()
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
	
	public boolean getUpgradable(){
		return upgradable;
	}
	
	public int getMaxLevel(){
		return maxLevel;
	}
	
	//Get Tower info in an array to be display in the GamePanel
	public int[] getInfo(){
		return new int[] {level, damage, range, (int)(fireRate * 10), upgradeCost, refundValue};
	}
	

	@Override
	public void drawStrategy(Graphics g) {
		g.drawImage(image, x, y, null);
	}

}


