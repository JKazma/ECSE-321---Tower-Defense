package CritterRush.model.tower;

import CritterRush.controller.ICManager;
import CritterRush.controller.ProjectileManager;
import CritterRush.model.critter.Critter;

public class SlowTower extends Tower {
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	public SlowTower(int x, int y)
	{
		super(x,y);
		type = "Slow Tower";
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
	
	/**
	 * Add a slowProjectile to the projectile manager.
	 * @param c
	 */
	protected void addProjectile(Critter c){
		ProjectileManager.addProjectile(new SlowProjectile (this.getX()+ (ICManager.cellSize / 2), this.getY()+ (ICManager.cellSize / 2), damage, c));
	}
}
