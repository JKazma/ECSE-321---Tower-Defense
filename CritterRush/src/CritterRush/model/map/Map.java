package CritterRush.model.map;

import java.awt.Graphics;

import CritterRush.controller.CellTypeManager;
import CritterRush.controller.ICManager;
import CritterRush.model.GameObject;

public class Map extends GameObject{
	private Cell[][] cells;
	private int sizeX, sizeY;
	private Path path;
	
	/**
	 * Constructor
	 * @param name
	 * @param sizeX
	 * @param sizeY
	 * @param cellType
	 */
	public Map (int sizeX, int sizeY, CellType cellType){
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.path=new Path();
		initializeCells(cellType);
	}
	
	/**
	 * Initialize all the cells to a specified cell type.
	 * @param cellType
	 */
    private void initializeCells(CellType cellType){
    	this.cells = new Cell[sizeX][sizeY];
        //Initialize cells.
        for(int j = 0; j < sizeY; j++){
            for(int i = 0; i < sizeX; i++){
            	this.cells[i][j] = new Cell(i*ICManager.cellSize,j*ICManager.cellSize, ICManager.cellSize, ICManager.cellSize, cellType);
            }
        }
        //Set adjacent cells values for each cell.
        for(int j = 0; j < sizeY; j++){
            for(int i = 0; i < sizeX; i++){
                if(j < sizeY-1) cells[i][j].setSouthCell(cells[i][j+1]);
                if(i < sizeX-1) cells[i][j].setEastCell(cells[i+1][j]);
                if(j > 0) cells[i][j].setNorthCell(cells[i][j-1]);
                if(i > 0) cells[i][j].setWestCell(cells[i-1][j]);
            }
        }
    }
    /**
     * Check the map validity.
     * @return
     */
    public boolean checkValidity(){
    	return path.checkPathValidity();
    }
    
    /**
     * Reset the cells for the shortest path computation by clearing 
     * the data used for a breadth-first search algorithm.
     */
    public void resetCells(){
        for(int j = 0; j < sizeY; j++){
            for(int i = 0; i < sizeX; i++){
            	cells[i][j].setVisited(false);
            	cells[i][j].setPreviousCell(null);
            }
        }
    }
	
    
	//Getters
	public int getSceneryCellsCount(){
		int count = 0;
        for(int j = 0; j < sizeY; j++){
            for(int i = 0; i < sizeX; i++){
            	if(cells[i][j].getType() == CellTypeManager.scenery)
            		count++;
            }
        }
        return count;
	}
	
	/**
	 * Get cell at a specific x and y in pixels
	 * @param x
	 * @param y
	 * @return
	 */
	public Cell getCellAt(int x, int y){
		if (x < ICManager.fieldSizeX && y < ICManager.fieldSizeY && x >= 0 && y >= 0) return cells[x/ICManager.cellSize][y/ICManager.cellSize];
		else return null;
	} 
	
	public Path getPath(){
		return path;
	}
	
	public int getSizeX(){
		return sizeX;
	}
	
	public int getSizeY(){
		return sizeY;
	}
	
	public void drawStrategy(Graphics g) {
		//Draw the cells images
		for(int j=0; j<sizeY; j++) {
			for(int i=0; i<sizeX; i++) {
				cells[i][j].draw(g);
			}
		}
	}
}
