package Test;

import org.junit.Before;

import CritterRush.controller.CellTypeManager;
import CritterRush.controller.CritterManager;
import CritterRush.controller.GameController;
import CritterRush.controller.ICManager;
import CritterRush.controller.MapManager;
import CritterRush.controller.ProjectileManager;
import CritterRush.controller.TowerManager;
import CritterRush.model.critter.Critter;
import CritterRush.model.tower.FastTower;
import CritterRush.model.tower.ShopTower;
import CritterRush.model.tower.Tower;

import junit.framework.TestCase;

public class GameControllerTest extends TestCase {
	
	GameController gc;
	Tower tower1;
	ShopTower shopTower1;
 
    @Before
    public void setUp(){
    	
    	//Initialize game and managers
    	gc = new GameController();
    	
        ICManager.getUniqueInstance();
        CellTypeManager.getUniqueInstance();
        MapManager.getUniqueInstance();
        CritterManager.getUniqueInstance();
        TowerManager.getUniqueInstance();
        ProjectileManager.getUniqueInstance();
	     
    	//Get default map
    	MapManager.setSelectedMap(MapManager.getMap(1));
	     
    	shopTower1 = ICManager.fastTowerShop;
    	tower1 = new FastTower(60, 240);
	     
 
    }
    
    public void testPurchaseTower(){
    	gc.getPs().setCurrencyPoints(0);
    	assertEquals("lowResource", gc.purchaseTower(shopTower1));
    	
    	gc.getPs().setCurrencyPoints(1500000);
    	assertEquals("success", gc.purchaseTower(shopTower1));
    	
    }
    
    public void testSellTower(){
    	TowerManager.setSelectedTower(tower1);
    	int oldRessourceCount = gc.getPs().getCurrencyPoints();
    	gc.sellTower();
    	
    	assertNull(TowerManager.getSelectedTower());
    	assertTrue(oldRessourceCount < gc.getPs().getCurrencyPoints());
    }
    
    public void testSpawnWave(){
    	gc.setPlayerStartWave(true);
    	gc.spawnCritterWave();
    	
    	assertEquals(ICManager.critterCount[0], CritterManager.getCritters().size());
    }
    
    public void testWaveCleared(){
    	for(Critter c:CritterManager.getCritters())
    		c.despawn();
    	gc.checkCleared();
    	assertTrue(gc.getWaveCleared());
    }
    
    
}
