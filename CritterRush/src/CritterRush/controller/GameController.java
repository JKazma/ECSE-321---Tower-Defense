package CritterRush.controller;

import java.awt.Graphics;
import java.util.ArrayList;

import CritterRush.model.Critter;
import CritterRush.model.FastTower;
import CritterRush.model.PlayerStats;
import CritterRush.model.ShopTower;
import CritterRush.model.SplashTower;
import CritterRush.model.EditorTools;
import CritterRush.model.Tower;
import CritterRush.model.TowerTool;
import CritterRush.model.slowTower;

public class GameController {
	
	private ArrayList<Tower> towers=new ArrayList<Tower>();
	private ArrayList<Critter> critters=new ArrayList<Critter>();
	private PlayerStats ps;
	private TowerTool tt;
	private ShopTower toBePurchased;
	
	public GameController(){
		ps = new PlayerStats(ICManager.iniBankAmount, ICManager.iniLife);
		tt = new TowerTool();
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
			ps.setCurrencyPoints(ps.getCurrencyPoints() + TowerManager.getSelectedTower().getSell());
			TowerManager.removeTower(TowerManager.getSelectedTower());
			TowerManager.setSelectedTower(null);
		}
	}
	
	public Tower getTowerAt(int x, int y){
		
	}
	
	public void spawnCritterWave(){
		
	}
	
	public void shootCritters(){
		
	}
	
	public void moveCritters(){
		
	}
	
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
		tt.draw(g);
		TowerManager.draw(g);
		CritterManager.draw(g);
	}
}
