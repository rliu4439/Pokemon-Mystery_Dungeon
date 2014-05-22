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
	
	public boolean corridorConnection(Grid g){
	Grid d = g.getGrid();
	int t = y-1;
		for (int b = 0; b < 2; b++){
			for ( int a = x-1; a < x+width+1; a++){
				Location l = new Location(t,a);
				if (d.get(l).equalsTo("C")
					return true;
			}
			t= y+length+1
		}
	int s = x-1;
		for( int c = 0; c < 2; c++){
			for (int d = y-1; d<y+length+1; d++){
				Location l = new Location (d,s);
				if (d.get(l)equalsTo("C")
					return true;
			}
			s= x + width +1;
		}
		return false;
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
