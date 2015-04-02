package CritterRush.model;


import CritterRush.controller.CellTypeManager;
import CritterRush.controller.MapManager;

public class TextureTool extends Tool{
	
	private CellType a1 = CellTypeManager.path;
	private CellType a2 = CellTypeManager.scenery;
	
	
	@Override
	//Set cell to path cell.
	public void action1() {
		
		Cell c = MapManager.getEditorMap().getCellAt(x, y);
		if(c == null) return;
		c.setType(a1);
		MapManager.getEditorMap().getPath().computePath();
		
	}
	@Override
	//Set cell to scenery cell.
	public void action2() {
		
		Cell c = MapManager.getEditorMap().getCellAt(x, y);
		if(c == null) return;
		c.setType(a2);
		
	}
	@Override
	public boolean action3() {
		return true;
		
	}	
}
