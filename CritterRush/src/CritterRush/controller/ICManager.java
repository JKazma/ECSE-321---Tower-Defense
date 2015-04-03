package CritterRush.controller;

import java.awt.Image;
import javax.swing.ImageIcon;

import CritterRush.model.ShopTower;

public class ICManager {
	public static Image scenery;
	public static Image path;
	public static Image entry;
	public static Image exit;
	public static Image obstacleCell;
	
	public static Image fastTower;
	public static Image slowTower;
	public static Image splashTower;
	public static Image powerTower;
	
	public static Image critterImage;

    public static Image mapIconEmpty;
    public static ImageIcon[] maps;
    
	public static int cellSize;
	public static int fieldSizeX;
	public static int fieldSizeY;
	public static int squareHighlightTickness;
	public static int iniBankAmount;
	public static int iniLife;
	public static int waveSize;
	
	public static int[] critterHealth;
	public static int[] critterInitialSpeed;
	public static int[] critterScoreReward;
	public static int[] critterCurrencyPointReward;
	public static int[] critterCount;
	public static int	waveCount;
	
	public static ShopTower fastTowerShop;
	public static ShopTower slowTowerShop;
	public static ShopTower splashTowerShop;
	public static ShopTower powerTowerShop;
	
	public ICManager() {
			initImages();
		}
	
	public void initImages(){
		//Initialize map images
		scenery = new ImageIcon(getClass().getResource("/map/scenery.png")).getImage();
		path = new ImageIcon(getClass().getResource("/map/path.png")).getImage();
		entry = new ImageIcon(getClass().getResource("/map/entry.png")).getImage();
		exit = new ImageIcon(getClass().getResource("/map/exit.png")).getImage();
		obstacleCell = new ImageIcon(getClass().getResource("/map/obstacleCell.png")).getImage();
		mapIconEmpty = new ImageIcon(getClass().getResource("/map/empty.png")).getImage();
		
		//Initialize tower images
		fastTower = new ImageIcon(getClass().getResource("/towers/fastTower.png")).getImage();
		splashTower = new ImageIcon(getClass().getResource("/towers/splashTower.png")).getImage();
		slowTower = new ImageIcon(getClass().getResource("/towers/slowTower.png")).getImage();
		powerTower = new ImageIcon(getClass().getResource("/towers/powerTower.png")).getImage();
		
		//Initialize critter image
		critterImage = new ImageIcon(getClass().getResource("/critters/critter.png")).getImage();
		
		//Initialize ImageIcons
		maps = new ImageIcon[4];
		
		//Initialize map constants
		cellSize = 30;
		fieldSizeX = 780;
		fieldSizeY = 600;
		squareHighlightTickness = 2;
		iniBankAmount = 1500;
		iniLife = 5;
		waveSize = 10;
		
		//Initialize tower constants
		fastTowerShop = new ShopTower("fastTower", 1,1,50,1,1);
		slowTowerShop =  new ShopTower("slowTower", 2,1,50,1,1);
		splashTowerShop =  new ShopTower("splashTower", 1,3,1,1,1);
		powerTowerShop =  new ShopTower("powerTower", 1,1,1,1,5);
		
		//Initialize critter constants
		critterHealth = new int[] {50,60,70};
		critterInitialSpeed = new int[] {50,60,70};
		critterScoreReward = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		critterCurrencyPointReward = new int[] {10,20,30,40,50,60,70,80,90,100,110,120,130,140,150};
		critterCount = new int[] {30,30,30,30,30,30,30,30,30,30,30,30,30,30,30};
		waveCount = 15;
	}
}