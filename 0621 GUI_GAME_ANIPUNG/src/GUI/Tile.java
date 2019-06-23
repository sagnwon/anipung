package GUI;

public class Tile {
	private static Tile getTile = null;
	public static Tile getInstance() {
		if (getTile == null) {
			getTile = new Tile();
		}
		return getTile;
	}
	private Tile() {}
	
}
