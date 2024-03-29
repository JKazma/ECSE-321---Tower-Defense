package CritterRush.model.tower;

public class ShopTower {
	private int initialCost, level, range, damage;
	private double fireRate;
	private String type;
	
	/**
	 * Constructor
	 * @param type
	 * @param initialCost
	 * @param range
	 * @param damage
	 * @param fireRate
	 * @param level
	 */
	public ShopTower(String type, int initialCost, int range, int damage, int fireRate, int level){
		this.type = type;
		this.initialCost = initialCost;
		this.level = level;
		this.range = range;
		this.damage = damage;
		this.fireRate = ((float) fireRate)/10;
	}
	
	//Getters
	public int getInitialCost() {
		return initialCost;
	}

	public String getType() {
		return type;
	}

	public int getLevel() {
		return level;
	}

	public int getRange() {
		return range;
	}

	public int getDamage() {
		return damage;
	}

	public double getFireRate() {
		return fireRate;
	}	
	
	/**
	 * Get tower info to be displayed in the GUI
	 * @return
	 */
	public String[] getInfo(){
		return new String[] {"Type: " + type, 
				"Level: " + String.valueOf(level), 
				"Damage: " + String.valueOf(damage), 
				"Range: " + String.valueOf(range), 
				"Fire Rate: " + String.valueOf(((double)((int)(fireRate*100))) /100), 
				"Buy Cost: " + String.valueOf(initialCost)};
	}
	
	
	
	
}
