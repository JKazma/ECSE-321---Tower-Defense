package CritterRush.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import CritterRush.controller.ICManager;

public abstract class Tool {
	protected static int x;
	protected static int y;
	protected boolean grid=true;
	protected Color color=Color.white;
	protected ToolBox e;
	
	public void Tool(ToolBox e){
		this.e = e;
	}
	
	//Update the tool position depending on the cell width and height.
	public void updatePosition() {
		if(grid) {
			x=(Mouse.getX()/ICManager.cellSize)*ICManager.cellSize;
			y=(Mouse.getY()/ICManager.cellSize)*ICManager.cellSize;
		} else {
			x=Mouse.getX();
			y=Mouse.getY();
		}
	}
	//Left click
	public abstract void action1();
	
	//Right click
	public abstract void action2();
	
	//Draw square highligher
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.setStroke(new BasicStroke(ICManager.squareHighlightTickness));
		if (x < ICManager.fieldSizeX) g2.drawRect(x, y, ICManager.cellSize, ICManager.cellSize);
	}
}