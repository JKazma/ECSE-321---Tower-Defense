package CritterRush.controller;

import java.awt.Graphics;
import java.util.ArrayList;

import CritterRush.model.Critter;
import CritterRush.model.PlayerStats;
import CritterRush.model.ShopTower;
import CritterRush.model.Tower;
import CritterRush.model.TowerTool;

public class GameController {
	
	private ArrayList<Tower> towers=new ArrayList<Tower>();
	private ArrayList<Critter> critters=new ArrayList<Critter>();
	private PlayerStats ps;
	private TowerTool tt;
	private ShopTower toBePurchased;
	private int currentWave;
	
	public GameController(){
		ps = new PlayerStats(ICManager.iniBankAmount, ICManager.iniLife);
		tt = new TowerTool();
		currentWave = 0;
	}
	
	
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
	
	
	public boolean placeTower(){
		return tt.placeTower(toBePurchased);
		
	}
	
	public void upgradeTower(Tower t){
		
	}
	
	public void sellTower(){
		if(TowerManager.getSelectedTower() != null){
			ps.setCurrencyPoints(ps.getCurrencyPoints() + TowerManager.getSelectedTower().getRefundValue());
			TowerManager.removeTower(TowerManager.getSelectedTower());
			TowerManager.setSelectedTower(null);
		}
	}
	
	public void spawnCritterWave(){
		CritterManager.addCritter (new Critter ("Steve", currentWave));
	}
	
	public void shootCritters(){
		
	}
	
	public void moveCritters(){
		
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
	
	public int[] getWaveInfo(){
		return new int[] {ICManager.critterHealth[currentWave], ICManager.critterInitialSpeed[currentWave], ICManager.critterCount[currentWave]};
	}
	
	public int[] getNextWaveInfo(){
		return new int[] {ICManager.critterHealth[currentWave + 1], ICManager.critterInitialSpeed[currentWave + 1], ICManager.critterCount[currentWave + 1]};
	}
	
	public int[] getWaveCountInfo(){
		return new int[] {currentWave + 1, ICManager.waveCount};
	}
	
	public void draw(Graphics g){
		MapManager.getSelectedMap().draw(g);
		tt.draw(g);
		CritterManager.draw(g);
		TowerManager.draw(g);
	}
}
