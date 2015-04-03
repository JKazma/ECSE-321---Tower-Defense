package CritterRush.model.tower;

import java.awt.Point;

import CritterRush.controller.ICManager;
import CritterRush.controller.ProjectileManager;
import CritterRush.model.critter.Critter;

public class SplashTower extends Tower{
	int areaRadius;
	public SplashTower(int x, int y)
	{
		super(x,y);
		this.level = 1;
		this.initialCost = ICManager.splashTowerAttributes[0];
		this.range = ICManager.splashTowerAttributes[1];
		this.damage = ICManager.splashTowerAttributes[2];
		this.fireRate = ICManager.splashTowerAttributes[3] / 10; //The firerate in the constants is multiplied by 10 to allow a range from 0.1 to infinity
		this.maxLevel = ICManager.splashTowerAttributes[4];
		this.totalCost = initialCost;
		this.refundValue = (int)(totalCost * 0.7);
		this.upgradeCost = (int)(totalCost * 0.5);
		
		this.image = ICManager.splashTower;
		this.upgradable = true;
	}
	
	protected void addProjectile(Critter c){
		ProjectileManager.addProjectile(new SplashProjectile (new Point(this.getX(),this.getY()), new Point(c.getX(),c.getY()), damage, image)); //image should be updated	
	}
}



