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
	
	public boolean overlap(){
	
	}
	
	
}
