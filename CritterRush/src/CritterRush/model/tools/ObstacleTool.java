package CritterRush.model.tools;


import java.awt.Color;

import CritterRush.controller.CellTypeManager;
import CritterRush.controller.MapManager;
import CritterRush.model.map.Cell;
import CritterRush.model.map.CellType;

public class ObstacleTool extends Tool{
	
	private CellType a1 = CellTypeManager.obstacle;
	
	public ObstacleTool() {
		color=Color.black;
	}
	
	
	/**
	 * Set cell type to obstacle.
	 */
	@Override
	public void leftAction() {
		
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
	public void rightAction() {
		//Nothing.
	}
}
