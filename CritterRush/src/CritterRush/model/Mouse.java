package CritterRush.model;

public class Mouse{
	private static int x = 0;
	private static int y = 0;
	
	
	public static void move(int x, int y) {
		Mouse.x=x;
		Mouse.y=y;
	}

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;

	}	
}
