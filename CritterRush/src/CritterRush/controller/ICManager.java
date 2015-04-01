package CritterRush.controller;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ICManager {
	public static Image scenery;
	public static Image path;
	public static Image entry;
	public static Image exit;
	public static int cellSize;
	public static int fieldSizeX;
	public static int fieldSizeY;
	public static int squareHighlightTickness;
    public static Image mapIconEmpty;
    public static ImageIcon[] maps;
	
	public ICManager() {
			initImages();
		}
	
	public void initImages(){
		scenery = new ImageIcon(getClass().getResource("/scenery.png")).getImage();
		path = new ImageIcon(getClass().getResource("/path.png")).getImage();
		entry = new ImageIcon(getClass().getResource("/entry.png")).getImage();
		exit = new ImageIcon(getClass().getResource("/exit.png")).getImage();
		mapIconEmpty = new ImageIcon(getClass().getResource("/empty.png")).getImage();
		maps = new ImageIcon[4];
		cellSize = 30;
		fieldSizeX = 780;
		fieldSizeY = 600;
		squareHighlightTickness = 4;
	}
}