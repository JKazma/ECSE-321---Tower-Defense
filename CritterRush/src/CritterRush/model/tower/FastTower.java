package CritterRush.model.tower;

import CritterRush.controller.ICManager;
import CritterRush.controller.ProjectileManager;
import CritterRush.model.critter.Critter;

public class FastTower extends Tower {
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	public FastTower(int x, int y)
	{
		super(x,y);
		type = "Fast Tower";
		this.initialCost = ICManager.fastTowerAttributes[0];
		this.range = ICManager.fastTowerAttributes[1];
		this.damage = ICManager.fastTowerAttributes[2];
		this.fireRate = ICManager.fastTowerAttributes[3] / 10; //The firerate in the constants is multiplied by 10 to allow a range from 0.1 to infinity
		this.maxLevel = ICManager.fastTowerAttributes[4];
		this.totalCost = initialCost;
		this.refundValue = (int)(totalCost * upgRefundFactor);
		this.upgradeCost = (int)(totalCost * upgUpgradeFactor);
		
		this.image = ICManager.fastTower;
		this.upgradable = true;
	}
	
	/**
	 * Create a projectile of type fastTower.
	 */
	protected void addProjectile(Critter c){
		ProjectileManager.addProjectile(new FastProjectile (this.getX() + (ICManager.cellSize / 2),
				this.getY() + (ICManager.cellSize / 2), damage, c));	
	}

}



