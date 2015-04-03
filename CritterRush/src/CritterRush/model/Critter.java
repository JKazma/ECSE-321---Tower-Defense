package CritterRush.model;
import java.awt.Graphics;
import java.awt.Image;

import CritterRush.controller.CritterManager;
import CritterRush.controller.ICManager;

public class Critter extends GameObject{
	
	private String name;
	private int x; //set
	private int y; //set
	private int dx;
	private int dy;
	private int health;
	private int speed;
	private int initialSpeed;
	private int scoreReward; 
	private int currencyPointReward;
	private int wave;
	private boolean alive;
	private int slowDuration;
	private Cell cell;
	private Image image;
	
	
	public Critter(String name, int wave) 
	{
		this.setName(name);
		this.initialSpeed = ICManager.critterInitialSpeed[wave];
		this.health= ICManager.critterHealth[wave];
		this.speed= ICManager.critterInitialSpeed[wave];
		this.scoreReward = ICManager.critterScoreReward[wave];
		this.currencyPointReward = ICManager.critterCurrencyPointReward[wave];
		this.wave = wave;
		this.slowDuration = 0;
		this.x = 50;
		this.y = 50;
		this.image = ICManager.critterImage;
//		hide();
	}
	
	public void spawn(Cell entry) 
	{
		this.cell=entry;
		this.x = entry.getX();
		this.y = entry.getY();
		this.alive = true;
//		show();
	}

	public void despawn() {
		this.alive=false;
		hide();
	}
	public boolean isAlive() {
		return alive;
	}
	
	public boolean isDead() {
		return !alive;
	}
	
	public void reduceHealth(int damage, Critter c, int currencyPoints, int score) {
		this.health -= damage;
		if(health<=0)
		{
			isDead();
			currencyPoints+=currencyPointReward;
			score+=scoreReward;
			CritterManager.removeCritter(c);
			this.despawn();
			return;
		}
		isAlive();
	}
	
	public void reachesExit(Critter c, int lifeCount, Path path)
	{
		Cell exit=path.getExit();
		if(c.getX()==exit.getX() && c.getY()==exit.getY())
		{
			lifeCount-=1;
			this.despawn();
			CritterManager.removeCritter(c);
			if(lifeCount<=0)
			{
				//GAME OVER
			}
		}
	}
	
	//vs setSpeed
	public void slowdown(float slowFactor, int slowDuration) {
		if (this.slowDuration <= 0) {
			this.speed -= slowFactor;
		}
		this.slowDuration = slowDuration;
	}
	
//	public void moveOnPath(Path path)
//	{
//		for(int i=1;i<path.getPath().size();i++)
//		{
//			travelCritters(path);
//		}
//	}
	
	public void travelTo(Path path) {
		System.out.print("Yo");
		if(alive) {
			if (slowDuration > 0) {
				slowDuration--;
			}
			else {
				this.speed = this.initialSpeed;
			}
			
			int i=0;
			if(speed!=0 && Timer.getTime()%(11-speed)==0)
			{
				if(cell==null)
				{
					this.despawn();
					return;
				}
				
				if(x==cell.getX() && y==cell.getY())
				{
					cell=path.getPath().get(i);
					if(cell!=null)
					{
						dx=(cell.getX()-x)/ICManager.cellSize;
						dy=(cell.getY()-y)/ICManager.cellSize;						
					}
				}
				else 
				{
					x+=dx;
					y+=dy;					
				}
			}
		}
	}			
//			if(speed!=0 && Counter.getCount()%(11-speed)==0) {
//				if(waypoint==null) {
//					this.despawn();
//					return;
//				}
//				if(x==waypoint.getX() && y==waypoint.getY()) {
//					waypoint=waypoint.next;
//					if(waypoint!=null) {
//						vx=(waypoint.getX()-x)/Tile.WIDTH;
//						vy=(waypoint.getY()-y)/Tile.HEIGHT;
//					}
//				} else {
//					x+=vx;
//					y+=vy;
//				}
//			}
	
	//setters and getters
	public void setName(String name) {
		this.name = name;
	}

	public void setX(int xPos) {
		this.x = xPos;
	}

	public void setY(int yPos) {
		this.y = yPos;
	}
	
	public void setHealth(int health)
	{
		this.health=health;
	}
	
	public void setSpeed(int speed)
	{
		this.speed=speed;
	}

	public void setScoreReward(int scoreReward) {
		this.scoreReward = scoreReward;
	}

	public void setCurrencyPointReward(int currencyPointReward) {
		this.currencyPointReward = currencyPointReward;
	}
	
	public void setCell(Cell cell) {
		this.cell=cell;
	}
	
	//The following getter methods return the critter characteristics.
	public String getName()
	{
		return name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHealth() {
		return health;
	}
	
	public int getSpeed(){
		return speed;
	}

	public int getInitialSpeed() {
		return initialSpeed;
	}

	public int getScoreReward() {
		return scoreReward;
	}

	public int getCurrencyPointReward() {
		return currencyPointReward;
	}
	
	public Cell getCell() {
		return cell;
	}
	
	@Override
	public void drawStrategy(Graphics g) {
		g.drawImage(image, x, y, null);
	}
}
