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

		int a = dungeon.getNumRows() * dungeon.getNumCols();
		numOfRooms = a/1000 + 2;
		for(int b=0; b<numOfRooms;b++){
			int w = (int) (Math.random()*5+5); //width
			int l = (int) (Math.random()*5+5); //length
			placeRoom(w, l);

		}
	}
}
