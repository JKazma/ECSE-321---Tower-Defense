package CritterRush.controller;

import java.awt.Image;
import javax.swing.ImageIcon;

import CritterRush.model.tower.ShopTower;

public class ICManager {
	
	private static ICManager uniqueInstance = null;
	
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
	
	public static int projectileSpeed;
	public static int slowDuration;
	public static double slowFactor;
	
	//Critter variables
	public static Image normalCritterI;
	public static Image fastCritterI;
	public static Image strongCritterI;
	public static Image slowCritterI;
	public static Image[] critterImage;
	
	public static int[] critterHealth;
	public static double[] critterInitialSpeed;
	public static int[] critterScoreReward;
	public static int[] critterCurrencyPointReward;
	public static int[] critterCount;
	public static int[] spawnRate;
	
	public static int waveCount;


	//Game variables
	public static int iniBankAmount;
	public static int iniLife;
	public static int frameRate;
	
	
	
	private ICManager() {
			initVariables();
		}
	/**
	 * Return unique instance of game controller
	 * @return
	 */
    public static synchronized ICManager getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ICManager();
        }
        return uniqueInstance;
    }
    
    
    /**
     * Initialize variables
     */
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
		fastTowerShop = new ShopTower("Fast Tower", 300,85,6,35,1);
		slowTowerShop =  new ShopTower("Slow Tower", 350,70,6,20,1);
		splashTowerShop =  new ShopTower("Splash Tower", 500,60,10,10,1);
		supremeTowerShop =  new ShopTower("Supreme Tower", 3200,110,40,50,1);
		
		//Order: initialCost, range, damage, fireRate (times 10), maxLevel
		fastTowerAttributes = new int[] {300,85,6,35,4};
		slowTowerAttributes = new int[] {350,70,6,20,4};
		splashTowerAttributes = new int[] {500,60,10,10,3};
		supremeTowerAttributes = new int[] {3200,110,40,50,2};
		
		projectileSpeed = 10;
		slowDuration = 180;		//60 -> 1 second, higher is longer
		slowFactor = 0.65;
		
		//Initialize critter variables
		normalCritterI = new ImageIcon(getClass().getResource("/critters/normalCritter.png")).getImage();
		fastCritterI = new ImageIcon(getClass().getResource("/critters/fastCritter.png")).getImage();
		strongCritterI = new ImageIcon(getClass().getResource("/critters/strongCritter.png")).getImage();
		slowCritterI = new ImageIcon(getClass().getResource("/critters/slowCritter.png")).getImage();
		
		//Critter speed can only have one of the following values: 0.75, 1.0, 1.5, 2.0, 3.0, 3.75, 5.0, 6.0
		//Because the speed system works only with values respecting this formula: cellSize / speed = n where n is an int
		critterInitialSpeed = new double[] {0.25, 0.25, 0.30, 0.18, 0.18, 0.5, 0.25, 0.25, 0.30, 0.18, 0.18, 0.5, 0.25, 0.25, 0.45};
		critterHealth = new int[] {30, 30, 130, 50, 50, 600, 130, 150, 650, 175, 195, 2200, 250, 280, 4000};
		critterCurrencyPointReward = new int[] {35, 35, 110, 35, 35, 450, 90, 90, 200, 60, 60, 800, 130, 150, 2500};
		critterScoreReward = new int[] {50, 40, 100, 50, 35, 250, 75, 60, 150, 65, 50, 500, 100, 80, 1500};
		critterCount = new int[] {10, 15, 5, 20, 25, 2, 10, 15, 5, 20, 25, 2, 10, 15, 1};
		//60 -> 1 critter per second, lower is faster. 
		spawnRate = new int[] {60, 60, 100, 35, 30, 200, 60, 60, 100, 35, 30, 200, 60, 60, 60};
		critterImage = new Image[] {normalCritterI, normalCritterI, strongCritterI, fastCritterI, fastCritterI, strongCritterI, 
				normalCritterI, normalCritterI, strongCritterI, fastCritterI, fastCritterI, strongCritterI, normalCritterI, 
				normalCritterI, strongCritterI};
		
		waveCount = 15;
		
		//Initialize game variables
		iniBankAmount = 700;
		iniLife = 5;
		frameRate = 60;
	}
}