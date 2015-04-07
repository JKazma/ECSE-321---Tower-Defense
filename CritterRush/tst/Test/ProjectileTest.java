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
import CritterRush.model.tower.FastProjectile;
import CritterRush.model.tower.Projectile;

import junit.framework.TestCase;

public class ProjectileTest extends TestCase {
	
	GameController gc;
	Critter critter;
	Projectile projectile;
 
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
    	
	    critter = new Critter("bob",1,gc);
	    projectile = new FastProjectile(0, 270, 15, critter);	//Entry cell position
	     
 
    }
    
    public void testCollision(){
    	int critterInitialHealth = critter.getHealth();
    	
    	critter.spawn();
    	projectile.spawn();
    	
    	projectile.checkCollision();
    	assertFalse(projectile.isAlive());
    	assertFalse(projectile.isVisible());
    	
    	assertTrue(critterInitialHealth > critter.getHealth());
    	
    }

}
