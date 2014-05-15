import java.util.ArrayList;


public class Dungeon {
private String[][] dungeon;
private int numOfRooms;
private ArrayList<Integer> widthRoom = new ArrayList<Integer>();
private ArrayList<Integer> lengthRoom = new ArrayList<Integer>();
	public Dungeon (int width, int length){
		
		
		generateDungeon();
		dungeon = new String[length][width]; //length is number of rows, width is the number of cols
	}
	
	private void generateDungeon(){
	initRooms();
	placeRooms();
	initCorridors();
	placeCorridors();
	}

	private void initCorridors() {
		
	}

	private void placeRooms() {
		
	}

	private void placeCorridors() {
		
	}

	private void initRooms() {

		int a = dungeon.length*dungeon[0].length;
		numOfRooms = (int) ((a/10)+.5);
		for(int b=0; b<numOfRooms;b++){
			int hi = (int) (Math.random()*3+6); //width
			int bye = (int) (Math.random()*3+6); //length

		}
	}
}
