package CritterRush.model.tower;

import java.awt.*;
import java.util.ArrayList;

import CritterRush.controller.ICManager;
import CritterRush.model.GameObject;
import CritterRush.model.critter.Critter;

public abstract class Tower extends GameObject{
	
	protected String type;
	protected int x;
	protected int y;
	protected int initialCost;
	protected int level;
	protected int range;
	protected int damage;
	protected double fireRate; //number of shots per second
	protected int refundValue;
	protected int upgradeCost;
	protected int totalCost;
	protected int maxLevel;
	
	protected int time; //Used for the fire rate
	
	protected double upgRangeFactor, upgDamageFactor, upgUpgradeFactor, upgRefundFactor;
	
	protected Image image;
	protected boolean hit;
	protected boolean upgradable;
	
	


	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	public Tower(int x, int y){
		this.x = x;
		this.y = y;
		this.level = 1;
		upgRangeFactor = 1.08;
		upgDamageFactor = 1.2;
		upgUpgradeFactor = 1.4;
		upgRefundFactor = 0.7;
		
		time = 0;
	}
	
	/**
	 * Shoot critters using a closest to exit strategy.
	 * @param critters
	 */
	public void shootCritters(ArrayList <Critter> critters){
		
		//Check if tower is loaded. The fire rate is bounded by the frame rate.
		if(time >= ICManager.frameRate / fireRate) {
			time = 0;
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
		else
			time ++;
	}
	
	/**
	 * Add projectile to the projectileManager
	 * @param c
	 */
	protected abstract void addProjectile(Critter c);
	
	/**
	 * Upgrade tower if max level was not reached.
	 */
	public void upgradeTower(){
		range = (int)(range * upgRangeFactor);
		damage = (int) (damage + damage * upgradeCost/totalCost * upgDamageFactor);
		totalCost = totalCost + upgradeCost;
		upgradeCost = (int)(upgradeCost * upgUpgradeFactor);
		refundValue = (int)(totalCost * upgRefundFactor);
		level = level + 1;
		
		//Disable upgradable if max level reached.
		if(level == maxLevel)
			upgradable  = false;
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
	public double getFireRate()
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
	
	/**
	 * Get Tower current info and next level info in an array to be display in the GUI.
	 * @return
	 */
	public String[] getInfo(){
		int upgRange = (int)(range * upgRangeFactor);
		int upgdDamage = (int) (damage + damage * upgradeCost/totalCost * upgDamageFactor);
		int upgTotalCost = totalCost + upgradeCost;
		int upgUpgradeCost = (int)(upgradeCost * upgUpgradeFactor);
		int upgRefundValue = (int)(upgTotalCost * upgRefundFactor);
		int upgLevel = level + 1;
		
		//If max level not reached
		if (level < maxLevel)
			return new String[] {"Type: " + type, 
				"Level: " + String.valueOf(level) + " ==> " + String.valueOf(upgLevel), 
				"Damage: " + String.valueOf(damage) + " ==> " + String.valueOf(upgdDamage), 
				"Range: " + String.valueOf(range) + " ==> " + String.valueOf(upgRange), 
				"Fire Rate: " + String.valueOf( ((double)((int)(fireRate*100))) /100 ) + " ==> " + String.valueOf(((double)((int)(fireRate*100))) /100), 
				"Up Cost: " + String.valueOf(upgradeCost) + " ==> " + String.valueOf(upgUpgradeCost ), 
				"Refund: " + String.valueOf(refundValue) + " ==> " + String.valueOf(upgRefundValue)};
		
		//Max level reached
		else
			return new String[] {"Type: " + type, 
				"Level: " + String.valueOf(level) + " ==> Max", 
				"Damage: " + String.valueOf(damage) + " ==> Max", 
				"Range: " + String.valueOf(range) + " ==> Max", 
				"Fire Rate: " + String.valueOf( ((double)((int)(fireRate*100))) /100 ) + " ==> Max", 
				"Up Cost: " + String.valueOf(upgradeCost) + " ==> Max", 
				"Refund: " + String.valueOf(refundValue) + " ==> Max"};
	}
	

	@Override
	public void drawStrategy(Graphics g) {
		g.drawImage(image, x, y, null);
	}

}


