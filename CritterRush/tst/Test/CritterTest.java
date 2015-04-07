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

import junit.framework.TestCase;

public class CritterTest extends TestCase {
 
	GameController gc;
	Critter critter1;
 
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
	     
	     
	     critter1 = new Critter("bob",1,gc);
 
    }
    
    public void testSpawn(){
    	critter1.spawn();
    	assertEquals((double)MapManager.getMap(1).getPath().getEntry().getX(), critter1.getX());
    	assertEquals((double)MapManager.getMap(1).getPath().getEntry().getY(), critter1.getY());
    }
    
    public void testDespawn(){
    	critter1.reachedExit();
    	assertEquals(false, critter1.isAlive());
    	assertEquals(false, critter1.isVisible());
    }
    
    public void testKill(){
    	critter1.reduceHealth(critter1.getHealth());
    	assertEquals(0, critter1.getHealth());
    	assertEquals(false, critter1.isAlive());
    	assertEquals(false, critter1.isVisible());
    }
    
}