package CritterRush.model.tower;

import CritterRush.controller.ICManager;
import CritterRush.model.critter.Critter;


public class FastProjectile extends Projectile {
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param damage
	 * @param c
	 */
	public FastProjectile(int x, int y, int damage, Critter c) {
		super(x, y, damage, c);
		image = ICManager.fastProjImage;

	}
	
	/**
	 * Inflict damage to critter.
	 */
	@Override
	protected void doDamage(Critter c) {
		c.reduceHealth(damage);
	}
}
