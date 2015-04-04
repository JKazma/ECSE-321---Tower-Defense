package CritterRush.controller;

import java.awt.Image;
import javax.swing.ImageIcon;

import CritterRush.model.tower.ShopTower;

public class ICManager {
	//Map variables
	public static Image scenery;
	public static Image path;
	public static Image entry;
	public static Image exit;
	public static Image obstacleCell;
    public static Image mapIconEmpty;
    public static ImageIcon[] maps;
    public static int squareHighlightTickness;
	
	public static int cellSize;
	public static int fieldSizeX;
	public static int fieldSizeY;
	
	//Tower variables
	public static Image fastTower;
	public static Image slowTower;
	public static Image splashTower;
	public static Image supremeTower;
	
	public static Image fastProjImage;
	public static Image slowProjImage;
	public static Image supremeProjImage;
	
	public static ShopTower fastTowerShop;
	public static ShopTower slowTowerShop;
	public static ShopTower splashTowerShop;
	public static ShopTower supremeTowerShop;
	
	public static int[] fastTowerAttributes;
	public static int[] splashTowerAttributes;
	public static int[] slowTowerAttributes;
	public static int[] supremeTowerAttributes;
	
	public static int slowestFireRate;
	public static int projectileSpeed;
	public static int slowDuration;
	
	//Critter variables
	public static Image critterImage;
	
	public static int[] critterHealth;
	public static double[] critterInitialSpeed;
	public static int[] critterScoreReward;
	public static int[] critterCurrencyPointReward;
	public static int[] critterCount;
	
	public static double[] possibleSpeed;
	public static int waveCount;
	public static int spawnRate;

	//Game variables
	public static int iniBankAmount;
	public static int iniLife;
	public static int frameRate;
	
	
	
	public ICManager() {
			initVariables();
		}
	
	
	public void initVariables(){
		//Initialize map variables
		scenery = new ImageIcon(getClass().getResource("/map/scenery.png")).getImage();
		path = new ImageIcon(getClass().getResource("/map/path.png")).getImage();
		entry = new ImageIcon(getClass().getResource("/map/entry.png")).getImage();
		exit = new ImageIcon(getClass().getResource("/map/exit.png")).getImage();
		obstacleCell = new ImageIcon(getClass().getResource("/map/obstacleCell.png")).getImage();
		mapIconEmpty = new ImageIcon(getClass().getResource("/map/empty.png")).getImage();
		maps = new ImageIcon[4];
		maps[0] = new ImageIcon(getClass().getResource("/map/defaultMap.png"));
		
		cellSize = 30;
		fieldSizeX = 780;
		fieldSizeY = 600;
		squareHighlightTickness = 2;
		
		//Initialize tower variables
		fastTower = new ImageIcon(getClass().getResource("/towers/fastTower.png")).getImage();
		splashTower = new ImageIcon(getClass().getResource("/towers/splashTower.png")).getImage();
		slowTower = new ImageIcon(getClass().getResource("/towers/slowTower.png")).getImage();
		supremeTower = new ImageIcon(getClass().getResource("/towers/supremeTower.png")).getImage();
		
		fastProjImage = new ImageIcon(getClass().getResource("/towers/fastTowerProjectile.png")).getImage();
		slowProjImage = new ImageIcon(getClass().getResource("/towers/slowTowerProjectile.png")).getImage();
		supremeProjImage = new ImageIcon(getClass().getResource("/towers/supremeTowerProjectile.png")).getImage();
		
		//order: type, initialCost, range, damage, fireRate (times 10), maxLevel
		fastTowerShop = new ShopTower("Fast Tower", 300, 150, 5, 50, 6);
		slowTowerShop =  new ShopTower("Slow Tower", 300,150, 5, 50, 6);
		splashTowerShop =  new ShopTower("Splash Tower", 300,150,5,50,6);
		supremeTowerShop =  new ShopTower("Supreme Tower", 1,1,1,1,5);
		
		//Order: initialCost, range, damage, fireRate (times 10), maxLevel
		fastTowerAttributes = new int[] {300,150,5,50,6};
		slowTowerAttributes = new int[] {300,150,5,50,6};
		splashTowerAttributes = new int[] {300,150,5,50,6};
		supremeTowerAttributes = new int[] {300,150,5,50,6};
		
		slowestFireRate = 5;	//2 seconds for a shot
		projectileSpeed = 10;
		slowDuration = 120;		//2 seconds
		
		//Initialize critter variables
		critterImage = new ImageIcon(getClass().getResource("/critters/critter.png")).getImage();
		
		//Critter speed can only have one of the following values: 1.0, 1.5, 2.0, 2.5, 3.0, 5.0, 6.0
		critterInitialSpeed = new double[] {3.75,10,70};
		critterHealth = new int[] {80,60,70};
		critterScoreReward = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		critterCurrencyPointReward = new int[] {10,20,30,40,50,60,70,80,90,100,110,120,130,140,150};
		critterCount = new int[] {2,3,30,30,30,30,30,30,30,30,30,30,30,30,30};
		
		waveCount = 1;
		possibleSpeed = new double[] {1.0, 1.5, 2.0, 2.5, 3.0, 3.75, 5.0, 6.0};
		spawnRate = 60; //1 critter per second
		
		//Initialize game variables
		iniBankAmount = 1500;
		iniLife = 5;
		frameRate = 60;
	}
}