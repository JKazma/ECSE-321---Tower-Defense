package CritterRush.model.map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import CritterRush.controller.CellTypeManager;
import CritterRush.controller.ICManager;
import CritterRush.controller.MapManager;
import CritterRush.model.GameObject;

public class Path extends GameObject{

	private ArrayList<Cell> path= new ArrayList<Cell>();
	private Cell entry;
	private Cell exit;
	private boolean validSpawns = false;
	
	/**
	 * Compute path only if valid spawns
	 * 
	 **/
	public void computePath(){
		path.clear();
		MapManager.getEditorMap().resetCells();
		validSpawns = checkSpawnsValidity();
		if (validSpawns)
			shortestPath();
	}
	
	/**
	 * Check if the map has valid spawns.
	 * 
	 **/
	public boolean checkSpawnsValidity(){
    	if (entry != null && exit != null)
    		return true;
    	else
    		return false;
	}
	
	/**
	 * Check if the path is valid.
	 * 
	 **/
	public boolean checkPathValidity(){
    	if (path.size() > 0 && entry != null && exit != null)
    		return true;
    	else
    		return false;
	}
	
	/**
	 * Compute shortest path using breadth-first search.
	 */
	private void shortestPath(){
		
		Queue<Cell> q = new LinkedList<Cell>();
		
		q.add(exit);
		exit.setVisited(true);
		
		while (!q.isEmpty()){
			//Current Cell
			Cell current = q.remove();
			
			//If the current cell is equal to final cell
			if(current.getX() == entry.getX() && current.getY() == entry.getY()){ 
			    //Back track to get the path
				for(Cell c = current; c != null; c = c.getPreviousCell()) {
			    	path.add(c);
			   }
			   break;
				}
			//For each adjacent cells to current cell,
			//If it's not null or has never been visited and is not a scenery cell, then add to queue
			for(int i = 0; i < 4; i++){
				if (current.getAdjacentCells()[i] != null && current.getAdjacentCells()[i].getVisited() == false && current.getAdjacentCells()[i].getType() != CellTypeManager.scenery && current.getAdjacentCells()[i].getType() != CellTypeManager.obstacle){
					q.add(current.getAdjacentCells()[i]);
					current.getAdjacentCells()[i].setVisited(true);
					current.getAdjacentCells()[i].setPreviousCell(current);
				}
			}
		}
	}
	
	//Getter and setter functions
	public ArrayList<Cell> getPath() {
		return path;
	}

	public void setPath(ArrayList<Cell> path) {
		this.path = path;
	}

	public Cell getEntry() {
		return entry;
	}

	public void setEntry(Cell entry) {
		this.entry = entry;
	}

	public Cell getExit() {
		return exit;
	}

	public void setExit(Cell exit) {
		this.exit = exit;
	}

	public boolean isValidSpawns() {
		return validSpawns;
	}

	public void setValidSpawns(boolean valid) {
		this.validSpawns = valid;
	}

	//Draw the path in the editor
	@Override
	public void drawStrategy(Graphics g) {
		if(this.isValidSpawns()) {
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.orange);
			g2.setStroke(new BasicStroke(ICManager.squareHighlightTickness));
			
			for(Object o:path.toArray()) {
				Cell tmp=(Cell)o;
				if(tmp != entry && tmp != exit)
					g2.drawRect(tmp.getX(), tmp.getY(), ICManager.cellSize, ICManager.cellSize);
			}
		}
		
	}

}
