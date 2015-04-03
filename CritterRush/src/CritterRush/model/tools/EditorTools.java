package CritterRush.model.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import CritterRush.controller.ICManager;
import CritterRush.controller.MapManager;

public class EditorTools{
	private static Tool textureTool;
	private static Tool spawnTool;
	private static Tool obstacleTool;
	//private static Tool towerTool;
	private static Tool currentTool;
	
	public EditorTools(){
		textureTool = new TextureTool();
		spawnTool = new SpawnTool();
		obstacleTool = new ObstacleTool();
	//	towerTool = new TowerTool();
		currentTool = null;
		
	}
	
	/**
	 * Update the tool position which is controlled by the mouse.
	 */
	public static void update() {
		if(currentTool != null)
			currentTool.updatePosition();
		
	}

	//Left mouse
	public static void performAction1() {
		if(currentTool != null)
			currentTool.action1();
		
	}
	
	//Right mouse
	public static void performAction2() {
		if(currentTool != null)
			currentTool.action2();
		
	}
	
	//Getters and setters
	public static Tool getTextureTool() {
		return textureTool;
	}

	public static Tool getSpawnTool() {
		return spawnTool;
	}
	
	public static Tool getObstacleTool() {
		return obstacleTool;
	}

	/*public static Tool getTowerTool() {
		return towerTool;
	}*/
	
	public static Tool getCurrentTool() {
		return currentTool;
	}

	public static void setCurrentTool(Tool currentTool) {
		EditorTools.currentTool = currentTool;
	}

	/**
	 * Draw the tool.
	 * @param g
	 */
	public static void draw(Graphics g) {
		if(currentTool != null)
			currentTool.draw(g);
		
		//Draw the entry and exit point highlights in Editor
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(ICManager.squareHighlightTickness));
		
		MapManager.getEditorMap().getPath().draw(g);
		if(MapManager.getEditorMap().getPath().getEntry()!=null) {
			g2.setColor(Color.cyan);
			g2.drawRect(MapManager.getEditorMap().getPath().getEntry().getX(), MapManager.getEditorMap().getPath().getEntry().getY(), ICManager.cellSize, ICManager.cellSize);
		}
		if(MapManager.getEditorMap().getPath().getExit()!=null) {
			g2.setColor(Color.RED);
			g2.drawRect(MapManager.getEditorMap().getPath().getExit().getX(), MapManager.getEditorMap().getPath().getExit().getY(), ICManager.cellSize, ICManager.cellSize);
		}
	}
}
