package CritterRush.controller;

import java.awt.*;
import java.util.ArrayList;

import CritterRush.model.tower.Tower;

public class TowerManager {
	
	private static TowerManager uniqueInstance = null;
	
	private static ArrayList<Tower> towers = new ArrayList<Tower>();
	private static Tower selectedTower;
	
	/**
	 * Return unique instance of game controller
	 * @return
	 */
    public static synchronized TowerManager getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new TowerManager();
        }
        return uniqueInstance;
    }

    /**
     * Add tower to ArrayList
     * @param t
     */
	public static void addTower(Tower t)
	{
		towers.add(t);
	}
	
	/**
	 * Remove Tower from ArrayList
	 * @param t
	 */
	public static void removeTower(Tower t)
	{
		towers.remove(t);
	}
	
	/**
	 * Clear ArrayList
	 */
	public static void removeAllTowers()
	{
		towers.clear();
	}
	
	/**
	 * Notify towers to shoot all critters in range
	 */
	public static void shootCritters(){
		for(Tower t : towers) 
			t.shootCritters(CritterManager.getCritters());
	}
	
	/**
	 * Get Towers.
	 * @return
	 */
	public static ArrayList<Tower> getTowers()
	{
		return towers;
	}
	
	/**
	 * Get Tower at a specific point on map.
	 * @param x
	 * @param y
	 * @return
	 */
	public static Tower getTowerAt(int x, int y) {
		for(Tower t: towers) {
			if(t.getX()==x && t.getY()==y) {
				return t;
			}
		}
		return null;
	}
	
	/**
	 * Get selected Tower.
	 * @return
	 */
	public static Tower getSelectedTower() {
		return selectedTower;
	}
	
	/**
	 * Set Selected Tower
	 * @param selectedTower
	 */
	public static void setSelectedTower(Tower selectedTower) {
		TowerManager.selectedTower = selectedTower;
	}
	
	/**
	 * Draw towers and selected tower range.
	 * @param g
	 */
	public static void draw(Graphics g) {
		for(Tower t:towers) {
			t.draw(g);
		}
		
		if(selectedTower != null){
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.orange);
			g2.setStroke(new BasicStroke(ICManager.squareHighlightTickness));
			g2.drawRect(selectedTower.getX(), selectedTower.getY(), ICManager.cellSize, ICManager.cellSize);	
			
			//Draw tower range circle
			if(selectedTower != null){
				g2.setColor(Color.white);
				g.drawOval(selectedTower.getX() + ICManager.cellSize / 2 - selectedTower.getRange(),selectedTower.getY() + ICManager.cellSize / 2 - selectedTower.getRange(), selectedTower.getRange()*2,selectedTower.getRange()*2);
			}
		}

	}
}
