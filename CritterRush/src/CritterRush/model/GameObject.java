package CritterRush.model;

import java.awt.Graphics;

public abstract class GameObject {
	private boolean visible=true;
	
	//If visible, do draw strategy
	public void draw(Graphics g) {
		if(isVisible()) {
			drawStrategy(g);
		}
	}
	
	//Drawing logic specified in children classes
	public abstract void drawStrategy(Graphics g);
	
	//show object
	public void show() {
		setVisible(true);
	}
	
	//hide object
	public void hide() {
		setVisible(false);
	}

	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
