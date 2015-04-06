package CritterRush.model.tower;

import CritterRush.controller.ICManager;
import CritterRush.model.critter.Critter;

public class SlowProjectile extends Projectile{
	private int slowDuration;
	private double slowFactor;
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param damage
	 * @param c
	 */
	public SlowProjectile(int x, int y, int damage, Critter c) {
		super(x, y, damage, c);
		image = ICManager.slowProjImage;
		slowDuration = ICManager.slowDuration;
		slowFactor = ICManager.slowFactor;
		
	}
	
	/**
	 * Inflict damage to critter and slow down critter.
	 * @param c
	 */
	@Override
	protected void doDamage(Critter c) {
		c.reduceHealth(damage);
		c.slowdown(slowDuration, slowFactor);
	}
}
