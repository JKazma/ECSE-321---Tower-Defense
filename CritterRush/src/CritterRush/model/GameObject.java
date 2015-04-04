package CritterRush.model;

import java.awt.Graphics;

public abstract class GameObject {
	private boolean visible=true;
	
	/**
	 * Perform drawing strategy
	 * @param g
	 */
	public void draw(Graphics g) {
		if(isVisible()) {
			drawStrategy(g);
		}
	}
	
	/**
	 * Drawing strategy specified in children class
	 * @param g
	 */
	public abstract void drawStrategy(Graphics g);
	
	/**
	 * Show object.
	 */
	public void show() {
		setVisible(true);
	}
	
	/**
	 * Hide object.
	 */
	public void hide() {
		setVisible(false);
	}
	
	//Getters and setters
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
