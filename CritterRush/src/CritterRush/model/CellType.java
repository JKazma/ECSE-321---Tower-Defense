package CritterRush.model;

//import org.newdawn.slick.Image;

import java.awt.Image;
import org.newdawn.slick.SlickException;

public class CellType {
	private Image image;
	private boolean isScenery;
	private boolean isPath;
	
	
	public CellType(Image image, boolean isScenery, boolean isPath) throws SlickException {
		this.image=image;
		this.isScenery = isScenery;
		this.isPath=isPath;
	}
	
	//Getters
	public Image getImage() {
		return image;
	}
	
	public boolean isScenery() {
		return isScenery;
	}

	public boolean isPath() {
		return isPath;
	}
}