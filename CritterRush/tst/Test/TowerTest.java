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
import CritterRush.model.tower.Tower;

import junit.framework.TestCase;

public class TowerTest extends TestCase {
	
	GameController gc;
	Critter critter1;
	Tower tower1;
 
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
	     
    	tower1 = new FastTower(60, 240);
	    critter1 = new Critter("bob",1,gc);
	    CritterManager.addCritter(critter1);
	     
 
    }
    
    public void testShootCritter(){
    	tower1.setTime(5000);	//Recharge tower
    	tower1.shootCritters(CritterManager.getCritters());
    	assertEquals(0, ProjectileManager.getProjectiles().size());
    	
    	tower1.setTime(5000);	//Recharge tower
    	critter1.spawn();
    	tower1.shootCritters(CritterManager.getCritters());
    	assertEquals(1, ProjectileManager.getProjectiles().size());
    }
    
    public void testUpgrade(){
    	int oldLevel = tower1.getLevel();
    	int oldDamage = tower1.getDamage();
    	int oldRange = tower1.getRange();
    	int oldUpgradeCost = tower1.getUpgradeCost();
    	int oldRefundValue = tower1.getRefundValue();
    	
    	tower1.upgradeTower();
    	
    	assertTrue(oldLevel < tower1.getLevel());
    	assertTrue(oldDamage < tower1.getDamage());
    	assertTrue(oldRange < tower1.getRange());
    	assertTrue(oldUpgradeCost < tower1.getUpgradeCost());
    	assertTrue(oldRefundValue < tower1.getRefundValue());
    	
    	for(int i = 0; i <= tower1.getMaxLevel(); i++){
    		tower1.upgradeTower();
    		if (tower1.getLevel() < tower1.getMaxLevel())
    			assertTrue(tower1.getUpgradable());
    		else
    			assertFalse(tower1.getUpgradable());
    	}
    }
}
