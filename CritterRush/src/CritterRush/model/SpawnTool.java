package CritterRush.model;

import java.awt.Color;

import CritterRush.controller.CellTypeManager;
import CritterRush.controller.MapManager;


public class SpawnTool extends Tool{
	
	private CellType a1 = CellTypeManager.entry;
	private CellType a2 = CellTypeManager.exit;
	private CellType normalPath = CellTypeManager.path;
	
	public SpawnTool() {
		color=Color.magenta;
	}
	
	@Override
	public void action1() {
		Cell c = MapManager.getEditorMap().getCellAt(x, y);
		if(c != null) {
			if(c.getType() == CellTypeManager.path && c != MapManager.getEditorMap().getPath().getExit()) {
				//Reset the image of the previous entry cell
				if(MapManager.getEditorMap().getPath().getEntry() != null)
					MapManager.getEditorMap().getPath().getEntry().setType(normalPath);
				
				//set the new entry cell
				MapManager.getEditorMap().getPath().setEntry(c);
				
				//set texture and compute path
				c.setType(a1);
				MapManager.getEditorMap().getPath().computePath();
			}
		}
	}


	@Override
	public void action2() {
		Cell c = MapManager.getEditorMap().getCellAt(x, y);
		if(c != null) {
			if(c.getType() == CellTypeManager.path && c != MapManager.getEditorMap().getPath().getEntry()) {
				//Reset the image of the previous exit cell
				if(MapManager.getEditorMap().getPath().getExit() != null)
					MapManager.getEditorMap().getPath().getExit().setType(normalPath);
				
				//set the new exit cell
				MapManager.getEditorMap().getPath().setExit(c);
				
				//set texture and compute path
				c.setType(a2);
				MapManager.getEditorMap().getPath().computePath();
			}
		} 
	}
}
