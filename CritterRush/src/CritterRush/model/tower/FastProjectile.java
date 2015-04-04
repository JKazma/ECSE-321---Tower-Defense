package CritterRush.model.tower;

import CritterRush.controller.ICManager;
import CritterRush.model.critter.Critter;


public class FastProjectile extends Projectile {
	public FastProjectile(int x, int y, int damage, Critter c) {
		super(x, y, damage, c);
		image = ICManager.projectileImage;

	}

	@Override
	protected void doDamage(Critter c) {
		c.reduceHealth(damage);
	}
}
