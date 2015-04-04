package CritterRush.model.tower;

import java.awt.Point;

import CritterRush.controller.ICManager;
import CritterRush.controller.ProjectileManager;
import CritterRush.model.critter.Critter;

public class SupremeTower extends Tower {
	public SupremeTower(int x, int y)
	{
		super(x,y);
		this.level = 1;
		this.initialCost = ICManager.supremeTowerAttributes[0];
		this.range = ICManager.supremeTowerAttributes[1];
		this.damage = ICManager.supremeTowerAttributes[2];
		this.fireRate = ICManager.supremeTowerAttributes[3] / 10; //The firerate in the constants is multiplied by 10 to allow a range from 0.1 to infinity
		this.maxLevel = ICManager.supremeTowerAttributes[4];
		this.totalCost = initialCost;
		this.refundValue = (int)(totalCost * 0.7);
		this.upgradeCost = (int)(totalCost * 0.5);
		
		this.image = ICManager.supremeTower;
		this.upgradable = false;
	}
	
	protected void addProjectile(Critter c){
		ProjectileManager.addProjectile(new SupremeProjectile (this.getX() + (ICManager.cellSize / 2),
				this.getY() + (ICManager.cellSize / 2), damage, c));	
	}
}



