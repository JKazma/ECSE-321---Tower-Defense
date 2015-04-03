package CritterRush.model.tower;

import java.awt.Point;
import java.util.ArrayList;

import javax.xml.transform.Templates;

import CritterRush.controller.ICManager;
import CritterRush.controller.ProjectileManager;
import CritterRush.model.Timer;
import CritterRush.model.critter.Critter;

public class FastTower extends Tower {
	public FastTower(int x, int y)
	{
		super(x,y);
		this.level = 1;
		this.initialCost = ICManager.fastTowerAttributes[0];
		this.range = ICManager.fastTowerAttributes[1];
		this.damage = ICManager.fastTowerAttributes[2];
		this.fireRate = ICManager.fastTowerAttributes[3] / 10; //The firerate in the constants is multiplied by 10 to allow a range from 0.1 to infinity
		this.maxLevel = ICManager.fastTowerAttributes[4];
		this.totalCost = initialCost;
		this.refundValue = (int)(totalCost * 0.7);
		this.upgradeCost = (int)(totalCost * 0.5);
		
		this.image = ICManager.fastTower;
		this.upgradable = true;
	}
	
	/*
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
	}*/
	
	protected void addProjectile(Critter c){
		ProjectileManager.addProjectile(new FastProjectile (new Point(this.getX(),this.getY()), new Point(c.getX(),c.getY()), damage, image)); //image should be updated	
	}

}



