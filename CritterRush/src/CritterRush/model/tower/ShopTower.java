package CritterRush.model.tower;

public class ShopTower {
	private int initialCost, level, range, damage, fireRate;
	private String type;
	
	public ShopTower(String type, int initialCost, int range, int damage, int fireRate, int level){
		this.type = type;
		this.initialCost = initialCost;
		this.level = level;
		this.range = range;
		this.damage = damage;
		this.fireRate = fireRate;
	}

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

	public int getFireRate() {
		return fireRate;
	}	
	
	//Get Tower info in an array to be display in the GamePanel
	public int[] getInfo(){
		return new int[] {level, damage, range, fireRate, initialCost};
	}
	
	
	
	
}
