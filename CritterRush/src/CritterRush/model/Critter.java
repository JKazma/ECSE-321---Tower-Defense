package CritterRush.model;

import java.awt.Graphics;

public abstract class Critter {
	
	//Critter variables
	protected String name;
	protected int x;
	protected int y;
	protected int health;
	protected int speed;
	protected int initialSpeed;
	protected int resistance; 
	protected int scoreReward; 
	protected int currencyPointReward; //change in UML
	protected boolean alive;
	protected int slowDuration;
	protected int wave=1;
	
	
	public abstract void draw(Graphics g);

	//The following is the constructor of the Critter class which sets the name of the Tower. 
	public Critter(String name) 
	{
		this.setName(name);
		this.initialSpeed=speed;
		this.slowDuration=0;
		//hide();
	}

	public void waveUp(int wave)
	{
		health=15+wave*2;
		speed=2+wave/2;
		resistance=3+wave/2;
		scoreReward+=10;
		currencyPointReward+=10;		
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public boolean isDead() {
		return !alive;
	}
	
	public void critterKilled(int currencyPoints, int score)
	{
		if(isDead())
		{
			currencyPoints+=currencyPointReward;
			score+=scoreReward;
		}
	}
	
	public void reduceHealth(int damage) {
		this.health -= damage;
		if(health<=0)
		{
			alive=false;
		}
		alive=true;
	}
	
	public void reachesExit(Critter c, int exit, int currencyPoints, int lifeCount)
	{
		if(true/*c reaches exit before dying*/)
		{
			currencyPoints-=currencyPointReward;
			//lifeCount-=1;
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
	
	public void travelTo() {
		if(alive) {
			if (slowDuration > 0) {
				slowDuration--;
			}
			else {
				this.speed = this.initialSpeed;
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
		}
	}
	
	//The following setter methods set the critter characteristics.
	public void setName(String name) {
		this.name = name;
	}

	public void setxPos(int x) {
		this.x = x;
	}

	public void setyPos(int y) {
		this.y = y;
	}
	
	public void setHealth(int health)
	{
		this.health=health;
	}
	
	public void setSpeed(int speed)
	{
		this.speed=speed;
	}

	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	public void setScoreReward(int scoreReward) {
		this.scoreReward = scoreReward;
	}

	public void setCurrencyPointReward(int currencyPointReward) {
		this.currencyPointReward = currencyPointReward;
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

	public int getResistance() {
		return resistance;
	}

	public int getScoreReward() {
		return scoreReward;
	}

	public int getCurrencyPointReward() {
		return currencyPointReward;
	}
}
