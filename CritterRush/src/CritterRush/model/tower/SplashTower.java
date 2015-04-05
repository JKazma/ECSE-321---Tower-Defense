package CritterRush.model.tower;

import java.util.ArrayList;

import CritterRush.controller.ICManager;
import CritterRush.controller.ProjectileManager;
import CritterRush.model.critter.Critter;

public class SplashTower extends Tower{
	int areaRadius;
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	public SplashTower(int x, int y)
	{
		super(x,y);
		type = "Splash Tower";
		this.initialCost = ICManager.splashTowerAttributes[0];
		this.range = ICManager.splashTowerAttributes[1];
		this.damage = ICManager.splashTowerAttributes[2];
		this.fireRate = ICManager.splashTowerAttributes[3] / 10; //The firerate in the constants is multiplied by 10 to allow a range from 0.1 to infinity
		this.maxLevel = ICManager.splashTowerAttributes[4];
		this.totalCost = initialCost;
		this.refundValue = (int)(totalCost * upgRefundFactor);
		this.upgradeCost = (int)(totalCost * upgUpgradeFactor);
		
		this.image = ICManager.splashTower;
		this.upgradable = true;
	}
	
	/**
	 * Shoot all critters in range.
	 */
	@Override
	public void shootCritters(ArrayList <Critter> critters){
		
		//Check if tower is loaded.
		if(time >= ICManager.frameRate / fireRate) {
			double critPos;
			ArrayList<Critter> crittersInRange = new ArrayList<Critter>();
			
			//For each critter in range, add to list
			for(Critter c: critters){
				critPos = Math.pow((c.getX() - x), 2) + Math.pow ((c.getY() - y),2);
				if(Math.sqrt(critPos) < range && c.isAlive() && c.isVisible()){
						time = 0;
						crittersInRange.add(c);
					}
				}
			//Create projectile
			if(crittersInRange.size() != 0)
				addProjectile(crittersInRange);
		}
		else
			time++;
	}
	
	/**
	 * Add a splashTower projectile to the projectileManager.
	 * @param critters
	 */
	protected void addProjectile(ArrayList <Critter> critters){
		ProjectileManager.addProjectile(new SplashProjectile (this.getX()+ (ICManager.cellSize / 2),
				this.getY() + (ICManager.cellSize / 2), damage, range, critters));
	}

	@Override
	protected void addProjectile(Critter c) {
		// TODO Auto-generated method stub
		
	}
}



