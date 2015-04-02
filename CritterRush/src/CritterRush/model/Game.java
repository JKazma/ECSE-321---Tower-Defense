package CritterRush.model;

import javax.swing.JPanel;

import CritterRush.controller.ICManager;
import CritterRush.controller.TowerManager;

public class Game {
	
	private PlayerStats ps;
	
	public Game(){
		ps = new PlayerStats(ICManager.iniBankAmount, ICManager.iniLife);
	}
	
	/**
	 * Purchase a tower.
	 * @param t
	 * @return boolean
	 */
	public boolean purchaseTower(String t){
		int towerCost;
		//Purchase succeed
		if (t == "fastTower" && ICManager.fastTowerCost <= ps.getCurrencyPoints()){
			towerCost = ICManager.fastTowerCost;
			TowerManager.setShopTower(new FastTower(2000 , 2000)); 
		}else if (t == "splashTower" && ICManager.splashTowerCost <= ps.getCurrencyPoints()){
			towerCost = ICManager.splashTowerCost;
			TowerManager.setShopTower(new SplashTower(2000 , 2000)); 
		}else if (t == "slowTower" && ICManager.slowTowerCost <= ps.getCurrencyPoints()){
			towerCost = ICManager.slowTowerCost;
			TowerManager.setShopTower(new slowTower(2000 , 2000)); 
		}else if (t == "powerTower" && ICManager.powerTowerCost <= ps.getCurrencyPoints()){
			towerCost = ICManager.powerTowerCost;
			TowerManager.setShopTower(new FastTower(2000 , 2000)); 
		}
		//Purchase fail
		else{
			return false;
		}
		
		//Update bank's resources
		ps.setCurrencyPoints(ps.getCurrencyPoints() - towerCost);
		ToolBox.setCurrentTool(ToolBox.getTowerTool());
		return true;
	}
	
	
	
	
}
