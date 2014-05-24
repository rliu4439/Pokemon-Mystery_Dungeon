import info.gridworld.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Room {
	private int x;
	private int y;
	private int width;
	private int length;

	public Room(int x, int y, int width, int length) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.length = length;
	}

	public int[] getDimensions() {
		int[] d = new int[] { x, y, width, length };
		return d;
	}

	public boolean corridorConnection(Grid d) {
		int t = y - 1;
		for (int b = 0; b < 2; b++) {
			for (int a = x; a < x + width; a++) {
				Location l = new Location(t, a);
				if (d.get(l).equals("C"))
					return true;
			}
			t = y + length + 1;
		}
		int s = x - 1;
		for (int c = 0; c < 2; c++) {

			for (int e = y; e < y + length; e++) {
				Location l = new Location(e, s);
				if (d.get(l).equals("C"))

					for (int f = y - 1; f < y + length + 1; f++) {
						Location ll = new Location(f, s);
						if (d.get(ll).equals("C"))

							return true;
					}
				s = x + width + 1;
			}
		}
		return false;
	}

	// clear radius of 5
	public boolean overlap(Grid d) {
		// Grid g = getGrid();
		int[] dimensions = getDimensions();
		for (int a = x - 5; a < x + width + 5; a++) {
			for (int b = y - 5; b < y + length + 5; y++) {
				if (a > -1 && a < d.getNumCols() && b > -1
						&& b > d.getNumRows()) {
					Location l = new Location(a, b);
					if (d.get(l) instanceof Room)
						return true;
				}
			}
		}
		return false;
	}

	public int getX() {
		return x;
	}

	
	public int getY() {
		return y;
	}

	
	
}
