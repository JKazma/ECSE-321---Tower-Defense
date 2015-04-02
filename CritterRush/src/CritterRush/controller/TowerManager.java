package CritterRush.controller;

import java.awt.*;
import java.util.ArrayList;

import CritterRush.model.*;

public class TowerManager {
	private static ArrayList<Tower> towers = new ArrayList<Tower>();
	private static Tower selectedTower;
	

	public static void addTower(Tower t)
	{
		towers.add(t);
	}
	public static void removeTower(Tower t)
	{
		towers.remove(t);
	}
	//for area damage
	public static void doAreaDamage()
	{
		
	}
	public static ArrayList<Tower> getTowers()
	{
		return towers;
	}
	public static Tower getTowerAt(int x, int y) {
		for(Tower t: towers) {
			if(t.getX()==x && t.getY()==y) {
				return t;
			}
		}
		return null;
	}
	
	public static Tower getSelectedTower() {
		return selectedTower;
	}
	public static void setSelectedTower(Tower selectedTower) {
		TowerManager.selectedTower = selectedTower;
	}
	
	public static void draw(Graphics g) {
		for(Tower t:towers) {
			t.draw(g);
		}
		
		if(selectedTower != null){
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.red);
			g2.setStroke(new BasicStroke(ICManager.squareHighlightTickness));
			g2.drawRect(selectedTower.getX(), selectedTower.getY(), ICManager.cellSize, ICManager.cellSize);	
		}

	}
}
