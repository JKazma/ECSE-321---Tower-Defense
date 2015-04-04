package CritterRush.model.tower;

import CritterRush.controller.ICManager;
import CritterRush.model.critter.Critter;

public class SupremeProjectile extends Projectile{
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param damage
	 * @param c
	 */
	public SupremeProjectile(int x, int y, int damage, Critter c) {
		super(x, y, damage, c);
		image = ICManager.supremeProjImage;
	}
	
	/**
	 * Inflict damage to critter.
	 * @param c
	 */
	@Override
	protected void doDamage(Critter c) {
		c.reduceHealth(damage);
	}
}
