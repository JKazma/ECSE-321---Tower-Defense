package CritterRush.model.critter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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
	private double rate;
	
	private int initialHealth;
	private int health;
	private int scoreReward; 
	private int currencyPointReward;
	private int slowDuration;
	private double speed;
	private double initialSpeed;

	private boolean alive;
	private boolean slowedDown;
	
	private Image normalSpeedImage;
	private Image slowSpeedImage;
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
		this.speed= ICManager.critterInitialSpeed[wave];
		calculateRate();
		this.scoreReward = ICManager.critterScoreReward[wave];
		this.currencyPointReward = ICManager.critterCurrencyPointReward[wave];
		this.slowDuration = 0;
		this.cellIndex = 0;
		this.gc = gc;
		this.alive = true;

		this.normalSpeedImage = ICManager.critterImage[wave];
		this.slowSpeedImage = ICManager.slowCritterI;
		
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
		if(health <= 0){
			gc.getPs().setCurrencyPoints(gc.getPs().getCurrencyPoints() + currencyPointReward);
			gc.getPs().setScore(gc.getPs().getScore() + scoreReward);
			despawn();
			playDeathSound();
		}
	}
	
	/**
	 * Update player stats when critter reaches exit.
	 */
	public void reachedExit(){
		gc.getPs().setLifeCount(gc.getPs().getLifeCount() - 1);
		despawn();
	}
	
	/**
	 * Slowdown effect with slowdown time.
	 * @param slowDuration
	 */
	public void slowdown(int slowDuration, double slowFactor) {
		this.slowDuration = slowDuration;
		this.speed = initialSpeed / slowFactor ;
		calculateRate();
		}
	
	/**
	 * Travel the critter on the map.
	 */
	public void travelTo() {
		
		if(isVisible()) {
			
			//If it stops following the path, kill critter. This shouldn't happen but just in case.
			if(x < 0 || x > ICManager.fieldSizeX || y < 0 || y > ICManager.fieldSizeY) 
				despawn();
			
			//Set the slowdown speed if the slow timer hasn't elapsed yet.
			if (slowDuration > 0) {
				slowDuration--;
				slowedDown = true;
			}
			else {
				//Reset to original speed and calculate new rate.
				speed = initialSpeed;
				if(slowedDown == true)
					calculateRate();
				slowedDown = false;
				
			}
			
			//If critter has speed.
			if(speed != 0) {
				//Reaches exit
				if(cellIndex >= MapManager.getSelectedMap().getPath().getPath().size()) {
					reachedExit();
					return;
				}
				
				//Set next cell positional variables
				int nextCellX = MapManager.getSelectedMap().getPath().getPath().get(cellIndex).getX();
				int nextCellY = MapManager.getSelectedMap().getPath().getPath().get(cellIndex).getY();
				
				//If reached next cell, increment cell index and set new direction, 
				//use a range of error to make sure the critter detects the tile.
				//Even though it shouldn't happen, if somehow the critter goes beyond the range
				//of error, we adjust the critter's path by looking for that case.
				if((x <= nextCellX + rate && x >= nextCellX - rate && y <= nextCellY + rate && y >= nextCellY - rate) 
						|| ((nextCellX - x ) * dx < 0 || (nextCellY - y ) * dy < 0)) {
					cellIndex++;
					
					//Fix x and y position
					x = nextCellX;
					y = nextCellY;
					
					//If end wasn't reached
					if(cellIndex < MapManager.getSelectedMap().getPath().getPath().size()) {
						
						//Compute new direction
						if(MapManager.getSelectedMap().getPath().getPath().get(cellIndex).getX() - x == 0) 
							dx = 0;
						else 
							dx = (MapManager.getSelectedMap().getPath().getPath().get(cellIndex).getX() - x) / Math.abs(MapManager.getSelectedMap().getPath().getPath().get(cellIndex).getX() - x);
						
						if(MapManager.getSelectedMap().getPath().getPath().get(cellIndex).getY() - y == 0) 
							dy = 0;
						else 
							dy = (MapManager.getSelectedMap().getPath().getPath().get(cellIndex).getY() - y) / Math.abs(MapManager.getSelectedMap().getPath().getPath().get(cellIndex).getY() - y);
					}
				//Move critter
				}else {
					x+=dx*rate;
					y+=dy*rate;
				}
			}
		}
	}	
	
	/**
	 * Compute the rate at which the critter should move.
	 */
	public void calculateRate(){
		rate = ICManager.cellSize/speed/ICManager.frameRate;
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
	
    /**
     * Play game music.
     */
    public void playDeathSound(){
        try{
        	File file = new File("resources/critterDeath.wav");
        	
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
               
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch(Exception ex)
        {
        }
    }
	
	@Override
	public void drawStrategy(Graphics g) {
		if (slowedDown)
			g.drawImage(slowSpeedImage, (int) x, (int) y, null);
		else
			g.drawImage(normalSpeedImage, (int) x, (int) y, null);
		g.setColor(Color.red);
		g.drawLine((int) x, (int) (y - 5), (int)(x + (ICManager.cellSize * (double) health/ (double)initialHealth)), (int) (y - 5));
	}
}
