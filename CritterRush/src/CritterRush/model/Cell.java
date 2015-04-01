package CritterRush.model;

//import org.newdawn.slick.Graphics;
import java.awt.Graphics;

public class Cell extends GameObject{
	private int x, y, width, height;
	private Cell[] adjacentCells = {null, null, null, null};		//0 -> north, 1 -> east, 2 -> south, 3 -> west
	private Cell previousCell;
	private CellType type;
	private boolean visited;
	
	public Cell(int x, int y, int width, int height, CellType type){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
	}
	//Setters
	public void setType(CellType type) {
		this.type=type;
	}
	
	public void setNorthCell(Cell north){
		adjacentCells[0] = north;
	}
	
	public void setEastCell(Cell east){
		adjacentCells[1] = east;
	}
	
	public void setSouthCell(Cell south){
		adjacentCells[2] = south;
	}
	
	public void setWestCell(Cell west){
		adjacentCells[3] = west;
	}
	
	//Getters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Cell[] getAdjacentCells() {
		return adjacentCells;
	}

	public Cell getPreviousCell() {
		return previousCell;
	}
	
	public void setPreviousCell(Cell current) {
		previousCell = current;
		
	}

	public CellType getType() {
		return type;
	}
	
	public boolean getVisited(){
		return visited;
	}
	
	public void setVisited(boolean b){
		visited = b;
	}
	
	
	public void drawStrategy(Graphics g) {
		g.drawImage(type.getImage(), x, y, null);
	}

}
