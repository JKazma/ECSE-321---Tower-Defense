package CritterRush.model.critter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import CritterRush.controller.GameController;
import CritterRush.controller.ICManager;
import CritterRush.controller.MapManager;
import CritterRush.model.GameObject;
import CritterRush.model.Timer;

public class Critter extends GameObject{
	
	private String name;
	private int x; //set
	private int y; //set
	private int dx;
	private int dy;
	private int initialHealth;
	private int health;
	private int speed;
	private int initialSpeed;
	private int scoreReward; 
	private int currencyPointReward;
	private boolean alive;
	private int slowDuration;
	private Image image;
	private int maxSpeed;
	private GameController gc;
	
	//Incremented variables
	private int cellIndex;
	
	
	public Critter(String name, int wave, GameController gc) 
	{
		hide();
		this.name = name;
		this.initialSpeed = ICManager.critterInitialSpeed[wave];
		this.maxSpeed = ICManager.maxSpeed;
		this.health= ICManager.critterHealth[wave];
		this.initialHealth = health;
		this.speed= ICManager.critterInitialSpeed[wave];;
		this.scoreReward = ICManager.critterScoreReward[wave];
		this.currencyPointReward = ICManager.critterCurrencyPointReward[wave];
		this.slowDuration = 0;
		this.cellIndex = 0;
		this.gc = gc;
		this.alive = true;

		this.image = ICManager.critterImage;
		
	}
	
	public void spawn() 
	{
		this.x = MapManager.getSelectedMap().getPath().getEntry().getX();
		this.y = MapManager.getSelectedMap().getPath().getEntry().getY();
		show();
	}

	public void despawn() {
		this.alive = false;
		hide();
		gc.checkCleared();
	}
	
	public void reduceHealth(int damage){
		this.health -= damage;
		if(health <= 0)
			despawn();
		
		gc.getPs().setCurrencyPoints(gc.getPs().getCurrencyPoints() + currencyPointReward);
		gc.getPs().setScore(gc.getPs().getScore() + scoreReward);
	}
	
	public void reachedExit(){
		despawn();
		gc.getPs().setLifeCount(gc.getPs().getLifeCount() - 1);
	}
	
	
	public void slowdown(int slowFactor, int slowDuration) {
			this.speed =  initialSpeed - slowFactor;
			this.slowDuration = slowDuration;
		}
		
	public void travelTo() {
		if(isVisible()) {
			if (slowDuration > 0) 
				slowDuration--;
			else 
				speed = initialSpeed;
			
			//Set the speed of the critter
			if(speed!=0 && Timer.getTravelTime()%(maxSpeed + 1 - speed) == 0) {
				if(cellIndex >= MapManager.getSelectedMap().getPath().getPath().size()) {
					reachedExit();
					return;
				}
				//If reached next cell, increment cell index and set new direction
				if(x == MapManager.getSelectedMap().getPath().getPath().get(cellIndex).getX() && y == MapManager.getSelectedMap().getPath().getPath().get(cellIndex).getY()) {
					cellIndex++;
					if(cellIndex < MapManager.getSelectedMap().getPath().getPath().size()) {
						dx = (MapManager.getSelectedMap().getPath().getPath().get(cellIndex).getX() - x)/ICManager.cellSize*2;
						dy = (MapManager.getSelectedMap().getPath().getPath().get(cellIndex).getY() - y)/ICManager.cellSize*2;
					}
				//Move critter
				}else {
					x+=dx;
					y+=dy;
				}
			}
		}
	}			
	
	//setters and getters
	public void setHealth(int health)
	{
		this.health = health;
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
	
	public int getCellIndex(){
		return cellIndex;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	@Override
	public void drawStrategy(Graphics g) {
		g.drawImage(image, x, y, null);
		g.setColor(Color.red);
		g.drawLine(x, y - 5, x + (int) (ICManager.cellSize * ((double) health/ (double)initialHealth)), y - 5);
	}
}
