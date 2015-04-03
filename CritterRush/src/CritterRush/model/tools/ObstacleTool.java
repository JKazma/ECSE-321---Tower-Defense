package CritterRush.model.tools;


import java.awt.Color;

import CritterRush.controller.CellTypeManager;
import CritterRush.controller.MapManager;
import CritterRush.model.map.Cell;
import CritterRush.model.map.CellType;

public class ObstacleTool extends Tool{
	
	private CellType a1 = CellTypeManager.obstacle;
	private CellType a2 = CellTypeManager.scenery;
	
	public ObstacleTool() {
		color=Color.black;
	}
	
	
	@Override
	//Set cell to path cell.
	public void action1() {
		
		Cell c = MapManager.getEditorMap().getCellAt(x, y);
		if(c == null) return;
		
		//Reset entry and exit cells if passing scenery over it.
		if(c == MapManager.getEditorMap().getPath().getEntry())
			MapManager.getEditorMap().getPath().setEntry(null);
		else if (c == MapManager.getEditorMap().getPath().getExit())
			MapManager.getEditorMap().getPath().setExit(null);
		
		//set texture and compute path
		c.setType(a1);
		MapManager.getEditorMap().getPath().computePath();
		
	}
	@Override
	//Set cell to scenery cell.
	public void action2() {
		//Nothing.
		
	}
}
