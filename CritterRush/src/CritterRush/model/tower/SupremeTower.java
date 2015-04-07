package CritterRush.model.tower;

import CritterRush.controller.ICManager;
import CritterRush.controller.ProjectileManager;
import CritterRush.model.critter.Critter;

public class SupremeTower extends Tower {
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	public SupremeTower(int x, int y)
	{
		super(x,y);
		type = "Supreme Tower";
		this.initialCost = ICManager.supremeTowerAttributes[0];
		this.range = ICManager.supremeTowerAttributes[1];
		this.damage = ICManager.supremeTowerAttributes[2];
		this.fireRate = ICManager.supremeTowerAttributes[3] / 10; //The firerate in the constants is multiplied by 10 to allow a range from 0.1 to infinity
		this.maxLevel = ICManager.supremeTowerAttributes[4];
		this.totalCost = initialCost;
		this.refundValue = (int)(totalCost * upgRefundFactor);
		this.upgradeCost = (int)(totalCost * upgUpgradeFactor);
		
		this.image = ICManager.supremeTower;
		this.upgradable = true;
	}
	
	/**
	 * Add projectile of type supreme to the projectileManager.
	 * @param c
	 */
	protected void addProjectile(Critter c){
		ProjectileManager.addProjectile(new SupremeProjectile (this.getX() + (ICManager.cellSize / 2),
				this.getY() + (ICManager.cellSize / 2), damage, c));	
	}
}



