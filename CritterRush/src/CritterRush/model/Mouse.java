package CritterRush.model;

public class Mouse{
	private static int x = 0;
	private static int y = 0;
	
	/**
	 * Set x and y of the mouse object.
	 * @param x
	 * @param y
	 */
	public static void move(int x, int y) {
		Mouse.x=x;
		Mouse.y=y;
	}
	//getter and setters.
	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;

	}	
}
