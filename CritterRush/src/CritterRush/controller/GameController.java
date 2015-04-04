package CritterRush.controller;

import java.awt.Graphics;

import CritterRush.model.PlayerStats;
import CritterRush.model.Timer;
import CritterRush.model.critter.Critter;
import CritterRush.model.tools.TowerTool;
import CritterRush.model.tower.ShopTower;
import CritterRush.model.tower.Tower;

public class GameController {
	
	private PlayerStats ps;
	private TowerTool tt;
	private ShopTower toBePurchased;
	private int currentWave;
	private boolean playerReady;
	private int critterIndex;
	private IObserver o;
	
	public GameController(){
		ps = new PlayerStats(ICManager.iniBankAmount, ICManager.iniLife);
		tt = new TowerTool();
		
		currentWave = 0;
		playerReady = false;
		critterIndex = 0;
		
		TowerManager.removeAllTowers();
		CritterManager.removeAllCritters();
		ProjectileManager.removeAllProjectiles();
		
		TowerManager.setSelectedTower(null);
		
		new Timer();
	}
	
	/**
	 * Purchase tower of specified type
	 * @return String
	 */
	public String purchaseTower(ShopTower type){
		//Purchase succeed, check if enough money and room on map.
		if (type.getInitialCost() <= ps.getCurrencyPoints()){
			if(TowerManager.getTowers().size() < MapManager.getSelectedMap().getSceneryCellsCount()){
				//Update bank's resources
				ps.setCurrencyPoints(ps.getCurrencyPoints() - type.getInitialCost());
				toBePurchased = type;
				tt.enabled(true);
				
				//Place tower
				placeTower();
				return "success";
			}else {
				return "noRoom";
			}

		}
		//Purchase fail
		else{
			return "lowResource";
		}

	}
	/**
	 * Place the purchased tower
	 * @return boolean
	 */
	public boolean placeTower(){
		return tt.placeTower(toBePurchased);	
	}
	
	/**
	 * Upgrade selected tower in TowerManager.
	 * @return String
	 */
	public String upgradeTower(){
		if (TowerManager.getSelectedTower().getUpgradeCost() <= ps.getCurrencyPoints()){
			if(TowerManager.getSelectedTower().getUpgradable()){
				
				TowerManager.getSelectedTower().upgradeTower();
				return "success";
			}
			else{
				return "maxLevel";
			}
		}
		else{
			return "lowResource";
		}
	}
	
	/**
	 * Sell selected tower.
	 */
	public void sellTower(){
		//Remove references to tower and update player stats
		if(TowerManager.getSelectedTower() != null){
			ps.setCurrencyPoints(ps.getCurrencyPoints() + TowerManager.getSelectedTower().getRefundValue());
			TowerManager.removeTower(TowerManager.getSelectedTower());
			TowerManager.setSelectedTower(null);
		}
	}
	
	/**
	 * Spawn next wave only if current wave has been cleared.
	 */
	public void spawnCritterWave(){
		if(playerReady)
			createWave();
		
			//Spawn critters at a specific spawn rate
			if(Timer.getSpawnTime() > ICManager.spawnRate && critterIndex < CritterManager.getCritters().size()){
				CritterManager.getCritters().get(critterIndex).spawn();
				Timer.setSpawnTime(0);
				critterIndex++;
			}
	}
	
	/**
	 * Generate wave to be spawned.
	 */
	public void createWave(){
		playerReady = false;
		//Populate arraylist of critters
		for (int i = 0; i < ICManager.critterCount[currentWave]; i++){
			CritterManager.addCritter(new Critter("Critter", currentWave, this));
		}
	}
	
	public void updateEntities(){
			Timer.increment();
			CritterManager.travelCritters();
			TowerManager.shootCritters();
			ProjectileManager.moveAllProjectiles();
			ProjectileManager.checkAllCollisions();
	}
	
	
	
	/**
	 * Check if the wave is cleared.
	 */
	public void checkCleared(){
		boolean waveCleared = true;
		for(Critter c : CritterManager.getCritters()) {
			if(c.isAlive()) waveCleared = false;
		}
		if(waveCleared){
			//Game won
			if(currentWave + 1 == ICManager.waveCount){
				gameWon();
			}
			//Game continue
			else{
				currentWave++;
				o.update("waveComplete");
			}
		}
	}
	
	/**
	 * Notify observer that the game was won.
	 */
	public void gameWon(){
		o.update("gameWon");
	}
	
	/**
	 * Return the current wave and next wave info
	 * @return
	 */
	public String[] getWaveInfo(){
		
		//If next wave exists
		if(currentWave + 1 < ICManager.waveCount){
			return new String[] {"", "WAVE INFO", 
			"Health: " + String.valueOf(ICManager.critterHealth[currentWave]) + " ==> " + String.valueOf(ICManager.critterHealth[currentWave + 1]), 
			"Speed: " + String.valueOf(ICManager.critterInitialSpeed[currentWave]) + " ==> " + String.valueOf(ICManager.critterInitialSpeed[currentWave + 1]),
			"Amount: " + String.valueOf(ICManager.critterCount[currentWave]) + " ==> " + String.valueOf(ICManager.critterCount[currentWave + 1]),
			"", 
			"Current Wave: " + String.valueOf(currentWave + 1) + " / " + String.valueOf(ICManager.waveCount)};
		}
		
		//If it doesn't
		else{
			return new String[] {"", "WAVE INFO", 
			"Health: " + String.valueOf(ICManager.critterHealth[currentWave]) + " ==> Done", 
			"Speed: " + String.valueOf(ICManager.critterInitialSpeed[currentWave]) + " ==> Done",
			"Amount: " + String.valueOf(ICManager.critterCount[currentWave]) + " ==> Done",
			"", 
			"Current Wave: " + String.valueOf(currentWave + 1) + " / " + String.valueOf(ICManager.waveCount)};
		}
				
	}

	public void setObserver (IObserver o) {
		this.o = o;
	}
	
	public void setReady(boolean b){
		playerReady = b;
	}
	
	//Getter and setters
	public PlayerStats getPs() {
		return ps;
	}

	public TowerTool getTt() {
		return tt;
	}


	public void setTt(TowerTool tt) {
		this.tt = tt;
	}

	public Tower getSelectedTower() {
		return TowerManager.getSelectedTower();
	}


	public void setSelectedTower(Tower selectedTower) {
		TowerManager.setSelectedTower(selectedTower);
	}

	public void draw(Graphics g){
		MapManager.getSelectedMap().draw(g);
		CritterManager.draw(g);
		TowerManager.draw(g);
		ProjectileManager.draw(g);
		tt.draw(g);
	}
}
