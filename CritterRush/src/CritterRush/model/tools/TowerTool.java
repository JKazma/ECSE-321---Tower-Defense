package CritterRush.model.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import CritterRush.controller.CellTypeManager;
import CritterRush.controller.ICManager;
import CritterRush.controller.MapManager;
import CritterRush.controller.TowerManager;
import CritterRush.model.map.Cell;
import CritterRush.model.tower.FastTower;
import CritterRush.model.tower.ShopTower;
import CritterRush.model.tower.SlowTower;
import CritterRush.model.tower.SplashTower;
import CritterRush.model.tower.SupremeTower;
import CritterRush.model.tower.Tower;

public class TowerTool extends Tool{
	
	private boolean enabled;
	private ShopTower st;
	
	public TowerTool(){
		enabled = false;
	}
	
	@Override
	public void action1() {
		// TODO Auto-generated method stub
	}

	@Override
	public void action2() {
		// TODO Auto-generated method stub
		 
	}


	public boolean placeTower(ShopTower tower) {
		st = tower;
		//Add tower only if there are no tower and a scenery cell
		Cell c = MapManager.getSelectedMap().getCellAt(x, y);
		Tower t = TowerManager.getTowerAt(x, y);
		if(c != null) {
			if(c.getType() == CellTypeManager.scenery && t == null && enabled) {
				
				//Add Tower new tower
				if (tower.getType() == ICManager.fastTowerShop.getType())
					TowerManager.addTower(new FastTower(x, y));
				else if (tower.getType() == ICManager.splashTowerShop.getType())
					TowerManager.addTower(new SplashTower(x, y));
				else if (tower.getType() == ICManager.slowTowerShop.getType())
					TowerManager.addTower(new SlowTower(x, y));
				else if (tower.getType() == ICManager.supremeTowerShop.getType())
					TowerManager.addTower(new SupremeTower(x, y));
				
				//Disable tool
				enabled(false);
				return true;
			}
		}
		return false;
		
	}

	public void enabled(boolean b) {
		enabled = b;
	}
	
	@Override
	public void draw(Graphics g){
		if(enabled){
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(color);
			g2.setStroke(new BasicStroke(ICManager.squareHighlightTickness));
			if (x < ICManager.fieldSizeX) g2.drawRect(x, y, ICManager.cellSize, ICManager.cellSize);
			
			//Draw tower circle
			if(st != null){
				g2.setColor(Color.white);
				g.drawOval(x + ICManager.cellSize / 2  - st.getRange(),y + ICManager.cellSize / 2 - st.getRange(), st.getRange()*2,st.getRange()*2);
				
			}
		}
	}
}
