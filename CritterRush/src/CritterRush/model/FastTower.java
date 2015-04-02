package CritterRush.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import CritterRush.controller.ICManager;
import CritterRush.controller.ProjectileManager;

public class FastTower extends Tower {
	public FastTower(int x, int y)
	{
		super(x,y);
		this.level = 1;
		this.range = 5;
		this.damage = 3;
		this.fireRate = 2;
		this.buy = 100;
		this.refundValue = 500;
		this.upgradeCost = 100;
		this.image = ICManager.fastTower;
	}
	public void shoot(Critter critter)
	{
		
		double critPos = Math.pow((critter.getX()-x) + (critter.getY()-y),2);	
		if(Math.sqrt(critPos)< range && critter.isAlive())
			{
				ProjectileManager.addProjectile(new FastProjectile (new Point(this.getX(),this.getY()), new Point(critter.getX(),critter.getY()), damage,image)); //image should be updated
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
			int newRateOfFire=getFireRate()+2;
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
	}



}



