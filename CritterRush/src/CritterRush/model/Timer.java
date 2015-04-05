package CritterRush.model;

public class Timer {
	
	private static long waveEndTime = 0;
	private static long spawnTime = 0;
	
	/**
	 * Increment the timer.
	 */
	public static void increment() {
		waveEndTime++;
		spawnTime++;
	}
	
	//Getter and setters
	public static long getWaveEndTime() {
		return waveEndTime;
	}
	
	public static void setWaveEndTime(int n) {
		waveEndTime = n;
	}
	
	public static long getSpawnTime() {
		return spawnTime;
	}
	
	public static void setSpawnTime(int n) {
		spawnTime = n;
	}
}
