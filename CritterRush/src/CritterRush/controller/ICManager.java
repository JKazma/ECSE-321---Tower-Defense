package CritterRush.controller;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ICManager {
	public static Image scenery;
	public static Image path;
	public static Image entry;
	public static Image exit;
	public static Image fastTower;
	public static Image slowTower;
	public static Image splashTower;
	public static Image powerTower;

    public static Image mapIconEmpty;
    public static ImageIcon[] maps;
    
	public static int cellSize;
	public static int fieldSizeX;
	public static int fieldSizeY;
	public static int squareHighlightTickness;
	public static int iniBankAmount;
	public static int iniLife;
	
	public static int fastTowerCost;
	public static int slowTowerCost;
	public static int splashTowerCost;
	public static int powerTowerCost;
	
	public ICManager() {
			initImages();
		}
	
	public void initImages(){
		//Initialize map images
		scenery = new ImageIcon(getClass().getResource("/scenery.png")).getImage();
		path = new ImageIcon(getClass().getResource("/path.png")).getImage();
		entry = new ImageIcon(getClass().getResource("/entry.png")).getImage();
		exit = new ImageIcon(getClass().getResource("/exit.png")).getImage();
		mapIconEmpty = new ImageIcon(getClass().getResource("/empty.png")).getImage();
		
		//Initialize tower images
		fastTower = new ImageIcon(getClass().getResource("/towers/fastTower.png")).getImage();
		splashTower = new ImageIcon(getClass().getResource("/towers/splashTower.png")).getImage();
		slowTower = new ImageIcon(getClass().getResource("/towers/slowTower.png")).getImage();
		powerTower = new ImageIcon(getClass().getResource("/towers/powerTower.png")).getImage();
		
		//Initialize ImageIcons
		maps = new ImageIcon[4];
		
		//Initialize map constants
		cellSize = 30;
		fieldSizeX = 780;
		fieldSizeY = 600;
		squareHighlightTickness = 2;
		iniBankAmount = 1500;
		iniLife = 5;
		
		//Initialize tower constants
		fastTowerCost = 500;
		slowTowerCost = 500;
		splashTowerCost = 500;
		powerTowerCost = 500;
	}
}