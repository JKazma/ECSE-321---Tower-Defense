package CritterRush.controller;

import java.awt.*;
import java.util.ArrayList;

import CritterRush.model.tower.Tower;

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
	
	public static void removeAllTowers()
	{
		towers.clear();
	}
	
	public static void shootCritters(){
		for(Tower t : towers) {
					t.shootCritters(CritterManager.getCritters());
		}
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
			
			//Draw tower circle
			if(selectedTower != null){
				g2.setColor(Color.white);
				g.drawOval(selectedTower.getX() + ICManager.cellSize / 2 - selectedTower.getRange(),selectedTower.getY() + ICManager.cellSize / 2 - selectedTower.getRange(), selectedTower.getRange()*2,selectedTower.getRange()*2);
			}
		}

	}
}
