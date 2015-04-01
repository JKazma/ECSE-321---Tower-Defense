package CritterRush.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import CritterRush.controller.ICManager;
import CritterRush.controller.MapManager;

public class ToolBox{
	private static Tool textureTool;
	private static Tool spawnTool;
	private static Tool towerTool;
	private static Tool currentTool;
	
	public ToolBox(){
		textureTool = new TextureTool();
		spawnTool = new SpawnTool();
		towerTool = new TowerTool();
		currentTool = textureTool;
		
	}
	
	//Update the position if the tool
	public static void update() {
		currentTool.updatePosition();
		
	}

	public static void performAction1() {
		currentTool.action1();
		
	}
	
	public static void performAction2() {
		currentTool.action2();
		
	}
	
	//Getters and setters
	public static Tool getTextureTool() {
		return textureTool;
	}

	public static Tool getSpawnTool() {
		return spawnTool;
	}

	public static Tool getCurrentTool() {
		return currentTool;
	}

	public static void setCurrentTool(Tool currentTool) {
		ToolBox.currentTool = currentTool;
	}

	public static void draw(Graphics g) {
		currentTool.draw(g);
		
		//Draw the entry and exit point highlights in Editor
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(ICManager.squareHighlightTickness));
		
		MapManager.getEditorMap().getPath().draw(g);
		if(MapManager.getEditorMap().getPath().getEntry()!=null) {
			g2.setColor(Color.ORANGE);
			g2.drawRect(MapManager.getEditorMap().getPath().getEntry().getX(), MapManager.getEditorMap().getPath().getEntry().getY(), ICManager.cellSize, ICManager.cellSize);
		}
		if(MapManager.getEditorMap().getPath().getExit()!=null) {
			g2.setColor(Color.RED);
			g2.drawRect(MapManager.getEditorMap().getPath().getExit().getX(), MapManager.getEditorMap().getPath().getExit().getY(), ICManager.cellSize, ICManager.cellSize);
		}
	}
}
