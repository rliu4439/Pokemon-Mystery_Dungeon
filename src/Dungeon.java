import java.util.ArrayList;
import java.util.Random;


public class Dungeon {
	private String[][] dungeon;
	private int numOfRooms;
//	private ArrayList<Integer> widthRoom = new ArrayList<Integer>();
//	private ArrayList<Integer> lengthRoom = new ArrayList<Integer>();
	private Arraylist<Room> rooms = new ArrayList<Room>();
	public Dungeon (int width, int length){
		
		
		generateDungeon();
		dungeon = new String[length][width]; //length is number of rows, width is the number of cols
		for (int a = 0; a < dungeon.length; a++)
			for (int b = 0; b < dungeon[0].length; b++)
				dungeon[a][b] = "w";
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
		boolean check = false;
		while (!check){
			int x = rand.nextInt(dungeon[0].length - width);
			int y = rand.nextInt(dungeon.length - length);
			for (Room r : rooms)
				
		}
		rooms.add(new Room(x, y, width, length));
		for (int a = x; a < width; a++)
			for (int b = y; b < length; b++)
				dungeon[b][a] = "e";
	}

	private void placeCorridors() {
		
	}

	private void initRooms() {

		int a = dungeon.length*dungeon[0].length;
		numOfRooms = a/10;
		for(int b=0; b<numOfRooms;b++){
			int hi = (int) (Math.random()*3+6); //width
			int bye = (int) (Math.random()*3+6); //length
			placeRoom(hi, bye);

		}
	}
}
