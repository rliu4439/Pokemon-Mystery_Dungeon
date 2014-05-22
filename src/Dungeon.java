import java.util.ArrayList;
import java.util.Random;
import info.gridworld.*;

public class Dungeon {
	private BoundedGrid dungeon;
//	private int numOfRooms;
//	private ArrayList<Integer> widthRoom = new ArrayList<Integer>();
//	private ArrayList<Integer> lengthRoom = new ArrayList<Integer>();
	private Arraylist<Room> rooms = new ArrayList<Room>();
	public Dungeon (int width, int length){
		dungeon = new BoundedGrid(length, width);
		for (int a = 0; a < dungeon.getNumRows(); a++)
			for (int b = 0; b < dungeon.getNumCols(); b++){
				Location l = new Location(a, b);
				dungeon.put(l, "W");
			}
		
		generateDungeon();
//		dungeon = new String[length][width]; //length is number of rows, width is the number of cols
//		for (int a = 0; a < dungeon.length; a++)
//			for (int b = 0; b < dungeon[0].length; b++)
//				dungeon[a][b] = "w";
	}
	
	private void generateDungeon(){
	initRooms();
//	placeRoom();
	initCorridors();
	placeCorridors();
	}

	private void initCorridors() {
		private boolean check = false;
		private ArrayList<Location> locs = new ArrayList<Location>();
		for (int a = 0; a < rooms.size(); a++){
			Collections.shuffle(rooms);
			Room r1 = rooms.get(0);
			Room r2 = rooms.get(1);
			
		}
		for (Room r: rooms)
			if (!r.corridorConnection(dungeon))
				break;
		while (!check)	
		
	}

	private void placeRoom(int width, int length) {
		Random rand = new Random();
		Room r;
		for (int a = 0; a < 1000; a++){
			int x = rand.nextInt(dungeon.getNumCols() - width);
			int y = rand.nextInt(dungeon.getNumRows() - length);
			r = new Room(x, y, width, length);
			if (!r.overlap())
				break;
		}
		rooms.add(r);
		for (int a = x; a < width; a++)
			for (int b = y; b < length; b++){
				Location loc = new Location(b, a);
				dungeon.put(loc, "R");
			}	
	}

	private void placeCorridors() {
		
	}

	private void initRooms() {
		Random rand = new Random();
		int a = dungeon.getNumRows() * dungeon.getNumCols();
		numOfRooms = rand.nextInt(a/1000) + 2;
		for(int b=0; b<numOfRooms;b++){
			int w = (int) (Math.random()*5+5); //width
			int l = (int) (Math.random()*5+5); //length
			placeRoom(w, l);

		}
	}
}
