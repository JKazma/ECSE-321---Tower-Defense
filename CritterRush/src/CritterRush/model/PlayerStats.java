package CritterRush.model;


public class PlayerStats {

	private int bankAmount;
	private int lifeCount;
	private int score;
	
	/**
	 * Constructor
	 * @param bankAmount
	 * @param lifeCount
	 */
	public PlayerStats(int bankAmount, int lifeCount){
		this.bankAmount = bankAmount;
		this.lifeCount = lifeCount;	
	}

	//Setters
	public void setCurrencyPoints(int currencyPoints) {
		this.bankAmount = currencyPoints;
	}

	public void setLifeCount(int lifeCount) {
		this.lifeCount = lifeCount;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	//Getters
	public int getCurrencyPoints() {
		return bankAmount;
	}

	public int getLifeCount() {
		return lifeCount;
	}

	public int getScore() {
		return score;
	}	
	
}
