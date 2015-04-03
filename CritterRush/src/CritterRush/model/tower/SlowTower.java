package CritterRush.model.tower;


import java.awt.Point;

import CritterRush.controller.ICManager;
import CritterRush.controller.ProjectileManager;
import CritterRush.model.critter.Critter;

public class SlowTower extends Tower {

	public SlowTower(int x, int y)
	{
		super(x,y);
		this.level = 1;
		this.initialCost = ICManager.slowTowerAttributes[0];
		this.range = ICManager.slowTowerAttributes[1];
		this.damage = ICManager.slowTowerAttributes[2];
		this.fireRate = ICManager.slowTowerAttributes[3] / 10; //The firerate in the constants is multiplied by 10 to allow a range from 0.1 to infinity
		this.maxLevel = ICManager.slowTowerAttributes[4];
		this.totalCost = initialCost;
		this.refundValue = (int)(totalCost * 0.7);
		this.upgradeCost = (int)(totalCost * 0.5);
		
		this.image = ICManager.slowTower;
		this.upgradable = true;
	}
	
	protected void addProjectile(Critter c){
		ProjectileManager.addProjectile(new SlowProjectile (new Point(this.getX(),this.getY()), new Point(c.getX(),c.getY()), damage, image)); //image should be updated	
	}
	
	/*public void shoot(Critter critter)
	{
		
		double critPos = Math.pow((critter.getX()-x) + (critter.getY()-y),2);
		if(Math.sqrt(critPos)< range && critter.isAlive())
			{
				ProjectileManager.addProjectile(new SlowProjectile (new Point(this.getX(),this.getY()), new Point(critter.getX(),critter.getY()), damage,image)); //image should be updated
			}
	}
	
	
	public boolean shootCritter(int rangeOfCritter,int healthOfCritter)
	{
		while(rangeOfCritter<=getRange() && healthOfCritter>0)
		{
			healthOfCritter-=(getDamage()*getFireRate());
			hit=true;
		}
		
		if(healthOfCritter<=0)
		{
			return true;
		}
		
		else if(rangeOfCritter>getRange())
		{
			
			hit=false; //changes the value of the boolean hit to false which will be used in the attackCritter method
		}
		
		return false;
	}
	*/
	/*
	public boolean upgradeTower(int currencyPoints)
	{
		int increasingCost=getBuy();
		if(currencyPoints>=getUpgradeCost() && getLevel()<5)
		{
			currencyPoints-=getUpgradeCost();
			increasingCost= getBuy()+getUpgradeCost();
			int newUpgradeCost=getUpgradeCost()+50;
			this.upgradeCost = newUpgradeCost;
			this.refundValue = (increasingCost/2);
			int newRange=getRange()+25;
			this.range = newRange;
			int newDamage=getDamage()+75;
			this.damage =(newDamage);
			int newRateOfFire= getFireRate()+2;
			this.fireRate = (newRateOfFire);
			int newLevel=getLevel()+1;
			this.level = newLevel;
			hit=true; //changes the value of the boolean hit to true which will be used in the attackCritter method
			upgraded=true; //changes the value of the boolean upgrade to true which will be used in the upgradedTower method
			return true;
		}
		else if(currencyPoints<getUpgradeCost() || getLevel()>4)
		{
			upgraded=false; //changes the value of the boolean upgrade to false which will be used in the upgradedTower method
		
		}
		return false;
	}*/
	
	
	public void slow(/*critter speed)*/)
	{
		while (hit == true) 
		{
			//speed -= 2;
		}
	}
}
