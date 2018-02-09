public class Robot {
	private String name;
	private Point position;
	private int keyCount = 0;
	private boolean hasTeleporter = false;
	
	public Robot(String name, Point startPos) {
		this.name = name;
		this.position = startPos;
	}
}
