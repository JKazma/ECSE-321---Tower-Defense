package CritterRush.model.tower;
import java.awt.*;

public class SplashProjectile extends Projectile{
	int areaRadius;
	
	public SplashProjectile(Point start, Point end,int damage, Image image, int areaRadius)
	{
		super(start, end, damage, image);
		this.areaRadius = areaRadius;
		
	}
	public void checkCollision()
	{
		
	}
}
