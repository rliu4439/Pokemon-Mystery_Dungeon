import java.util.ArrayList;
import java.util.Random;
import info.gridworld.*;

public class Dungeon {
	private BoundedGrid dungeon;
//	private int numOfRooms;
//	private ArrayList<Integer> widthRoom = new ArrayList<Integer>();
//	private ArrayList<Integer> lengthRoom = new ArrayList<Integer>();
	private Arraylist<Room> rooms = new ArrayList<Room>();
	private ArrayList<ArrayList<Location>> corridors =  ArrayList<ArrayList<Location>>();
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
		private ArrayList<Location> locs1 = new ArrayList<Location>();
		for (int a = 0; a < rooms.size(); a++){
			Collections.shuffle(rooms);
			Room r1 = rooms.get(0);
			Room r2 = rooms.get(1);
			int[] d1 = r1.getDimensions();
			int[] d2 = r2.getDimensions();
			
			//first room, get border w/o corners
			int t = d1[2] - 1;
			for (int b = 0; b < 2; b++){
				for ( int a = d1[1]; a < d1[1]+width; a++)
					locs.add(new Location(t,a));
				t= d1[2] + length + 1
			}
			int s = d1[1]-1;
			for( int c = 0; c < 2; c++){
				for (int d = d1[2]; d<d1[2]+length; d++)
					locs.add(new Location(d, s);
				s= d1[1] + width + 1;
			}
			
			//second room, get border w/o corners
			t = d2[2] - 1;
			for (int b = 0; b < 2; b++){
				for ( int a = d2[1]; a < d2[1]+width; a++)
					locs1.add(new Location(t,a));
				t= d2[2] + length + 1
			}
			s = d2[1]-1;
			for( int c = 0; c < 2; c++){
				for (int d = d2[2]; d<d2[2]+length; d++)
					locs1.add(new Location(d, s);
				s= d2[1] + width + 1;
			}
			
			Collections.shuffle(locs);
			Collections.shuffle(locs1);
			placeCorridors(locs.get(0), locs1.get(0));
		}
		for (Room r: rooms)
			if (!r.corridorConnection(dungeon))
				break;
		while (!check)	
		
	}
	
	private ArrayList<Location> placeCorridors(Location l, l1) {
		while (!l.equals(l1)){
			int point = l.getDirectionToward(l1);
			ArrayList<Location> locs = dungeon.getEmptyAdjacentLocations(l);
			locs.add(dungeon.getOccupiedAdjacentLocations(l))
			ArrayList<Location> head = new ArrayList<Location>();
			for (Location loc : locs){
				int bam = this.getLocation().getDirectionToward(loc);
				if (Math.abs(point - bam) == 90 || point - bam == 0)
					head.add(loc);
			}
			dungeon.p
   		 	return head;
		}
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
