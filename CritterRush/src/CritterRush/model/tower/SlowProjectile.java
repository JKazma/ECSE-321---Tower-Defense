package CritterRush.model.tower;
import java.awt.*;

import CritterRush.controller.ICManager;
import CritterRush.model.critter.Critter;

public class SlowProjectile extends Projectile{
	private int slowDuration;
	
	public SlowProjectile(int x, int y, int damage, Critter c) {
		super(x, y, damage, c);
		image = ICManager.projectileImage;
		slowDuration = ICManager.slowDuration;
		
	}

	@Override
	protected void doDamage(Critter c) {
		c.reduceHealth(damage);
		c.slowdown(1, slowDuration);
	}
}
