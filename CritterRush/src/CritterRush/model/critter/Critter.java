package CritterRush.model.critter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import CritterRush.controller.GameController;
import CritterRush.controller.ICManager;
import CritterRush.controller.MapManager;
import CritterRush.model.GameObject;

public class Critter extends GameObject{
	
	private String name;
	private double x;
	private double y;
	private double dx;
	private double dy;
	private int initialHealth;
	private int health;
	private double speed;
	private double initialSpeed;
	private int scoreReward; 
	private int currencyPointReward;
	private boolean alive;
	private int slowDuration;
	private Image image;
	private GameController gc;
	
	//Incremented variables
	private int cellIndex;
	
	/**
	 * Constructor
	 * @param name
	 * @param wave
	 * @param gc
	 */
	public Critter(String name, int wave, GameController gc) 
	{
		hide();
		this.name = name;
		this.initialSpeed = ICManager.critterInitialSpeed[wave];
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
	
	/**
	 * Display critter on screen.
	 */
	public void spawn() 
	{
		this.x = MapManager.getSelectedMap().getPath().getEntry().getX();
		this.y = MapManager.getSelectedMap().getPath().getEntry().getY();
		show();
	}
	
	/**
	 * Remove critter from screen and check if the wave is cleared.
	 */
	public void despawn() {
		this.alive = false;
		hide();
		gc.checkCleared();
	}
	
	/**
	 * Reduce critter's health.
	 * @param damage
	 */
	public void reduceHealth(int damage){
		this.health -= damage;
		
		//If dead, update player stats.
		if(health <= 0)
			despawn();
		
		gc.getPs().setCurrencyPoints(gc.getPs().getCurrencyPoints() + currencyPointReward);
		gc.getPs().setScore(gc.getPs().getScore() + scoreReward);
	}
	
	/**
	 * Update player stats when critter reaches exit.
	 */
	public void reachedExit(){
		despawn();
		gc.getPs().setLifeCount(gc.getPs().getLifeCount() - 1);
	}
	
	/**
	 * Slowdown effect with slowdown time.
	 * @param slowDuration
	 */
	public void slowdown(int slowDuration) {
			for(int i = 0; i< ICManager.possibleSpeed.length; i++) 
				if(initialSpeed == ICManager.possibleSpeed[i]){
					speed =  ICManager.possibleSpeed[i - 1];
					this.slowDuration = slowDuration;
					break;
				} 
		}
	/**
	 * Travel the critter on the map.
	 */
	public void travelTo() {
		if(isVisible()) {
			//Set the slowdown speed if the slow timer hasn't elapsed yet.
			if (slowDuration > 0) 
				slowDuration--;
			else 
				speed = initialSpeed;
			
			//Set the speed of the critter && Timer.getTravelTime()%(maxSpeed + 1 - speed) == 0
			if(speed!=0) {
				if(cellIndex >= MapManager.getSelectedMap().getPath().getPath().size()) {
					reachedExit();
					return;
				}
				//If reached next cell, increment cell index and set new direction
				if(x == MapManager.getSelectedMap().getPath().getPath().get(cellIndex).getX() && y == MapManager.getSelectedMap().getPath().getPath().get(cellIndex).getY()) {
					cellIndex++;
					if(cellIndex < MapManager.getSelectedMap().getPath().getPath().size()) {
						dx = (MapManager.getSelectedMap().getPath().getPath().get(cellIndex).getX() - x)/ICManager.cellSize*speed;
						dy = (MapManager.getSelectedMap().getPath().getPath().get(cellIndex).getY() - y)/ICManager.cellSize*speed;
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

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getHealth() {
		return health;
	}
	
	public double getSpeed(){
		return speed;
	}

	public double getInitialSpeed() {
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
		g.drawImage(image, (int) x, (int) y, null);
		g.setColor(Color.red);
		g.drawLine((int) x, (int) (y - 5), (int)(x + (ICManager.cellSize * (double) health/ (double)initialHealth)), (int) (y - 5));
	}
}
