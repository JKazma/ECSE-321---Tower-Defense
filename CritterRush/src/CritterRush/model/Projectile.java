package CritterRush.model;

import java.awt.*;

public class Projectile extends GameObject
{
	protected Point start;
	protected Point end;
	protected double x;
	protected double y;
	protected float vx;
	protected float vy;
	protected Image image;
	protected int damage;

	public Projectile(Point start, Point end, int damage, Image image)
	{
		this.start = start;
		x = start.getX();
		y = start.getY();	
		this.end = end;
		this.damage = damage;
		this.image = image;
	}
	
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public float getVX()
	{
		return vx;
	}
	public float getVY()
	{
		return vy;
	}
	public int getDamage()
	{
		return damage;
	}
	public void move()
	{
		
	}
	public void checkCollision()
	{
		
	}
	@Override
	public void drawStrategy(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
	