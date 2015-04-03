package CritterRush.model.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import CritterRush.controller.ICManager;
import CritterRush.model.Mouse;

public abstract class Tool {
	protected static int x;
	protected static int y;
	protected Color color=Color.white;
	protected EditorTools e;
	
	//Update the tool position depending on the cell width and height.
	public void updatePosition() {
		x=(Mouse.getX()/ICManager.cellSize)*ICManager.cellSize;
		y=(Mouse.getY()/ICManager.cellSize)*ICManager.cellSize;
	}
	public abstract void action1();
	
	public abstract void action2();
	
	//Draw square highligher
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.setStroke(new BasicStroke(ICManager.squareHighlightTickness));
		if (x < ICManager.fieldSizeX) g2.drawRect(x, y, ICManager.cellSize, ICManager.cellSize);
	}
}