package CritterRush.controller;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ICManager {
	public static Image scenery;
	public static Image path;
	public static Image entry;
	public static Image exit;

    public static Image mapIconEmpty;
    public static ImageIcon[] maps;
    
	public static int cellSize;
	public static int fieldSizeX;
	public static int fieldSizeY;
	public static int squareHighlightTickness;
	public static int iniBankAmount;
	public static int iniLife;
	
	public ICManager() {
			initImages();
		}
	
	public void initImages(){
		//Initialize images
		scenery = new ImageIcon(getClass().getResource("/scenery.png")).getImage();
		path = new ImageIcon(getClass().getResource("/path.png")).getImage();
		entry = new ImageIcon(getClass().getResource("/entry.png")).getImage();
		exit = new ImageIcon(getClass().getResource("/exit.png")).getImage();
		mapIconEmpty = new ImageIcon(getClass().getResource("/empty.png")).getImage();
		
		//Initialize ImageIcons
		maps = new ImageIcon[4];
		
		//Initialize constants
		cellSize = 30;
		fieldSizeX = 780;
		fieldSizeY = 600;
		squareHighlightTickness = 4;
		iniBankAmount = 1500;
		iniLife = 5;
	}
}