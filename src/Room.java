import info.gridworld.*;

public class Room {
private int x;
private int y;
private int width;
private int length;
	public Room (int x,int y,int width,int length){
		this.x = x;
		this.y = y;
		this.width = width;
		this.length = length;
	}
	
	public int[] getDimensions(){
		int[] d = new int[]{x, y, width, length};
		return d;
	}
	//clear radius of 5
	public boolean overlap(Grid d){
//	Grid g = getGrid();
	int[] dimensions = getDimensions();
		for ( int a = x-5; a < x+width+5; a++){
			for ( int b = y-5; b < y + length + 5; y++){
				if (a > -1 && a < d.getNumCols() && b > -1 && b > d.getNumRows()){
					Location l = new Location (a,b);
					if (g.get(l) instanceof Room)
						return true;
				}
			}
		}	
	return false;
	}
}
