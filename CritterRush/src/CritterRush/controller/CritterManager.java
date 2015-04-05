package CritterRush.controller;
import java.util.ArrayList;
import java.awt.Graphics;
import CritterRush.model.critter.Critter;

public class CritterManager {
	
	private static CritterManager uniqueInstance = null;
	private static ArrayList<Critter> critters = new ArrayList<Critter>();
	
	
	/**
	 * Return unique instance of game controller
	 * @return
	 */
    public static synchronized CritterManager getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CritterManager();
        }
        return uniqueInstance;
    }
    
    
	/**
	 * Return an ArrayList of critters
	 * @return
	 */
	public static ArrayList<Critter> getCritters() {
		return critters;
	}
	
	/**
	 * Add a critter to the ArrayList.
	 * @param c
	 */
	public static void addCritter(Critter c) {
		critters.add(c);
	}
	
	/**
	 * Remove a critter from the ArrayList.
	 * @param c
	 */
	public static void removeCritter(Critter c) {
		critters.remove(c);
	}
	
	/**
	 * Clear the arrayList.
	 */
	public static void removeAllCritters() {
		critters.clear();
	}
	
	/**
	 * Order all critters to travel on the path.
	 */
	public static void travelCritters() {
			for(Critter c: critters) {
				if(c != null) c.travelTo();
			}
	}
	
	public static void draw(Graphics g) {
		for(Critter c:critters) {
			if(c!=null)	c.draw(g);
		}
	}


}