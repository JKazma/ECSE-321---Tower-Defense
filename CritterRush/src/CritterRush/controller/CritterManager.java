package CritterRush.controller;
import java.util.ArrayList;
import java.awt.Graphics;
import CritterRush.model.critter.Critter;

public class CritterManager {

	private static ArrayList<Critter> critters = new ArrayList<Critter>();
	
	public static ArrayList<Critter> getCritters() {
		return critters;
	}
	
	public static void addCritter(Critter c) {
		critters.add(c);
	}
	
	public static void removeCritter(Critter c) {
		critters.remove(c);
	}
	
	public static void removeAllCritters() {
		critters.clear();
	}
	
	public static void travelCritters() {
		try{
			for(Critter c:CritterManager.getCritters()) {
				if(c != null) c.travelTo();
			}
		}catch(java.util.ConcurrentModificationException e){
			//Do Nothing
		}
	}
	
	public static void draw(Graphics g) {
		for(Critter c:critters) {
			if(c!=null)	c.draw(g);
		}
	}


}