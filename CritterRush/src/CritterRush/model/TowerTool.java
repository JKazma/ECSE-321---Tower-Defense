package CritterRush.model;

import CritterRush.controller.CellTypeManager;
import CritterRush.controller.MapManager;
import CritterRush.controller.TowerManager;

public class TowerTool extends Tool{
	
	
	@Override
	public void action1() {

	}

	@Override
	public void action2() {
		// TODO Auto-generated method stub
		 
	}

	@Override
	public boolean action3() {
		//Add tower only if there are no tower and a scenery cell
		Cell c = MapManager.getSelectedMap().getCellAt(x, y);
		Tower t = TowerManager.getTowerAt(x, y);
		if(c != null) {
			if(c.getType() == CellTypeManager.scenery && t == null) {
				//Add Tower and set the x and y position
				TowerManager.getShopTower().setX(x);
				TowerManager.getShopTower().setY(y);
				TowerManager.addTower(TowerManager.getShopTower());
				TowerManager.setShopTower(null);
				ToolBox.setCurrentTool(null);
				return true;
			}
		}
		return false;
		
	}

}
