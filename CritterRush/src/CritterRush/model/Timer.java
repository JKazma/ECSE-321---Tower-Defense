package CritterRush.model;

public class Timer {
	
	private static long time=0;
	
	public static void increment() {
		time++;
	}
	
	public static long getTime() {
		return time;
	}
}
