package CritterRush.model;

public class Timer {
	
	private static long travelTime = 0;
	private static long spawnTime = 0;
	
	/**
	 * Increment the timer.
	 */
	public static void increment() {
		travelTime++;
		spawnTime++;
	}
	
	//Getter and setters
	public static long getTravelTime() {
		return travelTime;
	}
	
	public static long getSpawnTime() {
		return spawnTime;
	}
	
	public static void setSpawnTime(int n) {
		spawnTime = n;
	}
}
