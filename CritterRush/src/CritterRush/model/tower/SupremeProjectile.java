package CritterRush.model.tower;

import CritterRush.controller.ICManager;
import CritterRush.model.critter.Critter;

public class SupremeProjectile extends Projectile{
	
	public SupremeProjectile(int x, int y, int damage, Critter c) {
		super(x, y, damage, c);
		image = ICManager.projectileImage;
		
	}

	@Override
	protected void doDamage(Critter c) {
		c.reduceHealth(damage);
	}
}
