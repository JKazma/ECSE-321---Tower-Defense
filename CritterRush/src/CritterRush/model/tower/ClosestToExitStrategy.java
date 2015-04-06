package CritterRush.model.tower;

import java.util.ArrayList;

import CritterRush.controller.ICManager;
import CritterRush.model.critter.Critter;

public class ClosestToExitStrategy implements ITargetStrategy{

	public void shootTarget(ArrayList<Critter> critters, Tower t) {
		//Check if tower is loaded. The fire rate is bounded by the frame rate.
		if(t.getTime() >= ICManager.frameRate / t.getFireRate()) {
			
			double critPos;
			Critter temp = null;
				
			//For each critter in range, find closest to exit
			for(Critter c: critters){
				critPos = Math.pow((c.getX() - t.getX()), 2) + Math.pow ((c.getY() - t.getY()),2);
				if(Math.sqrt(critPos) < t.getRange() && c.isAlive() && c.isVisible()){
					t.setTime(0);
					if(temp == null) 
						temp = c;
					
					if(c.getCellIndex() > temp.getCellIndex()){
						temp = c;
					}
				}
			}
			//Create projectile
			if(temp != null) {
				t.addProjectile(temp);
			}
		}
		else
			t.setTime (t.getTime() + 1);
		

	}
}
