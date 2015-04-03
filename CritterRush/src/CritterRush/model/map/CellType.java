package CritterRush.model.map;

import java.awt.Image;

public class CellType {
	private Image image;
	private boolean isScenery;
	private boolean isPath;
	
	
	public CellType(Image image, boolean isScenery, boolean isPath){
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