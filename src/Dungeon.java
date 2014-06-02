import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import info.gridworld.*;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

public class Dungeon {// "R" represents Room, "W" represents wall
	private BoundedGrid dungeon;
	// private int numOfRooms;
	// private ArrayList<Integer> widthRoom = new ArrayList<Integer>();
	// private ArrayList<Integer> lengthRoom = new ArrayList<Integer>();
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<ArrayList<Location>> corridors = new ArrayList<ArrayList<Location>>();
	private ArrayList<Location> roomLocs = new ArrayList<Location>();

	public Dungeon(int width, int length) {
		dungeon = new BoundedGrid(length, width);
		for (int a = 0; a < dungeon.getNumRows(); a++)
			for (int b = 0; b < dungeon.getNumCols(); b++) {
				Location l = new Location(a, b);
				dungeon.put(l, "W");
			}

		generateDungeon();
		// dungeon = new String[length][width]; //length is number of rows,
		// width is the number of cols
		// for (int a = 0; a < dungeon.length; a++)
		// for (int b = 0; b < dungeon[0].length; b++)
		// dungeon[a][b] = "w";
	}

	public BoundedGrid getDungeon() {
		return dungeon;
	}

	private void generateDungeon() {
		initRooms();
		// placeRoom();
		initCorridors();
	}

	public ArrayList<ArrayList<Location>> getCorridors() {
		return corridors;
	}

	public ArrayList<Location> getRoomLocs() {
		return roomLocs;
	}

	private void initCorridors() {
		ArrayList<Location> locs = new ArrayList<Location>();
		ArrayList<Location> locs1 = new ArrayList<Location>();
		for (int a = 0; a < rooms.size(); a++) {
			Collections.shuffle(rooms);
			Room r1 = rooms.get(0);
			System.out.println("Y: " + r1.getY());//-Integer.MAX_VALUE
			Room r2 = rooms.get(1);
			System.out.println("Y: " + (r2.getY()));//-Integer.MAX_VALUE
			int[] d1 = r1.getDimensions();
			int[] d2 = r2.getDimensions();

			// first room, get border w/o corners
			int t = d1[1] - 1;
			for (int b = 0; b < 2; b++) {
				for (int e = d1[0]; e < d1[0] + d1[2]; e++)
					locs.add(new Location(t, e));
				t = d1[1] + d1[3] + 1;
			}
			int s = d1[0] - 1;
			for (int c = 0; c < 2; c++) {
				for (int d = d1[1]; d < d1[1] + d1[3]; d++)
					locs.add(new Location(d, s));
				s = d1[0] + d1[2] + 1;
			}

			// second room, get border w/o corners
			t = d2[1] - 1;
			for (int b = 0; b < 2; b++) {
				for (int e = d2[0]; e < d2[0] + d2[2]; e++)
					locs1.add(new Location(t, e));
				t = d2[1] + d2[3] + 1;
			}
			s = d2[0] - 1;
			for (int c = 0; c < 2; c++) {
				for (int d = d2[1]; d < d2[1] + d2[3]; d++)
					locs1.add(new Location(d, s));
				s = d2[0] + d2[2] + 1;
			}

			Collections.shuffle(locs);
			Collections.shuffle(locs1);
			corridors.add(placeCorridors(locs.get(0), locs1.get(0)));
		}
		ArrayList<Room> roomsLeft = new ArrayList<Room>();
		for (Room r : rooms)
			if (!r.corridorConnection(dungeon)) {
				roomsLeft.add(r);
				while (roomsLeft.size() > 0) {
					Collections.shuffle(rooms);
					Room r1 = rooms.get(0);
					Room r2 = rooms.get(1);
					if (roomsLeft.contains(r1))
						roomsLeft.remove(r1);
					if (roomsLeft.contains(r2))
						roomsLeft.remove(r2);
					int[] d1 = r1.getDimensions();
					int[] d2 = r2.getDimensions();

					// first room, get border w/o corners
					int t = d1[1] - 1;
					for (int b = 0; b < 2; b++) {
						for (int e = d1[0]; e < d1[0] + d1[2]; e++)
							locs.add(new Location(t, e));
						t = d1[1] + d1[3] + 1;
					}
					int s = d1[0] - 1;
					for (int c = 0; c < 2; c++) {
						for (int d = d1[1]; d < d1[1] + d1[3]; d++)
							locs.add(new Location(d, s));
						s = d1[0] + d1[2] + 1;
					}

					// second room, get border w/o corners
					t = d2[1] - 1;
					for (int b = 0; b < 2; b++) {
						for (int e = d2[0]; e < d2[0] + d2[2]; e++)
							locs1.add(new Location(t, e));
						t = d2[1] + d2[3] + 1;
					}
					s = d2[0] - 1;
					for (int c = 0; c < 2; c++) {
						for (int d = d2[1]; d < d2[1] + d2[3]; d++)
							locs1.add(new Location(d, s));
						s = d2[0] + d2[2] + 1;
					}

				}

				Collections.shuffle(locs);
				Collections.shuffle(locs1);
				corridors.add(placeCorridors(locs.get(0), locs1.get(0)));
			}
		for (Room ro : rooms) {
			int[] dim = ro.getDimensions();
			for (int a = dim[0]; a < dim[2]; a++)
				for (int b = dim[1]; b < dim[3]; b++) {
					Location loc = new Location(b, a);
					dungeon.put(loc, "R");// don't the rooms already have "R"
											// from placeRooms?
				}
		}
	}

	private ArrayList<Location> placeCorridors(Location l, Location l1) {
		ArrayList<Location> spaces = new ArrayList<Location>();
		spaces.add(l);
		while (!l.equals(l1)) {
			int point = l.getDirectionToward(l1);//gets direction from room 1 to room 2
			ArrayList<Location> locs = dungeon.getEmptyAdjacentLocations(l);
			locs.addAll(dungeon.getOccupiedAdjacentLocations(l));
			ArrayList<Location> head = new ArrayList<Location>();
			for (Location loc : locs) {
				int bam = l.getDirectionToward(loc);
				// if (dungeon.get(loc) instanceof String &&
				// !dungeon.get(loc).equals("R")){
				if (point % 10 == 0
						&& (Math.abs(point - bam) == 90 || point - bam == 0))
					head.add(loc);
				else if (point % 10 != 0 && (Math.abs(point - bam)) == 45) {
					head.add(loc);

				}
				// }
			}
			Location chosen = null;
			Collections.shuffle(head);
			if (point % 10 != 0)
				chosen = head.get(0);
			else {
				Random rand = new Random();
				int chance = rand.nextInt(10) + 1; // 70% straight, 15% side
				// Location temp;
				for (Location lo : head) {
					if (chance < 8 && l.getDirectionToward(lo) == point) {
						chosen = lo;
						break;
					} else if (chance >= 8 && l.getDirectionToward(lo) == point) {
						head.remove(lo);
						chosen = head.get(0);
						break;
					}
				}
			}
			dungeon.put(chosen, "C");
			l = chosen;
			spaces.add(l);
		}
		return spaces;
	}

	private void placeRoom(int width, int length) {
		Random rand = new Random();
		Room r = null;
		int b = 0;
		for (int a = 0; a < 1000; a++) {
			int x = rand.nextInt(dungeon.getNumCols() - width);
			int y = rand.nextInt(dungeon.getNumRows() - length);
			System.out.println(dungeon.getNumCols() + " " + dungeon.getNumRows() + " " + x + " " + y);
			r = new Room(x, y, width, length);
			
			b=a;
			System.out.println("place room y :"+r.getY());
			if (!r.overlap(dungeon)){
				
				break;}

		}
		if (b!=1000)
			rooms.add(r);
		
		for (int a = r.getX(); a < width; a++)
			for (int c = r.getY(); c < length; c++) {
				Location loc = new Location(c, a);
				dungeon.put(loc, "R");
				roomLocs.add(loc);
			}
	}

	private void initRooms() {
		Random rand = new Random();
		int a = dungeon.getNumRows() * dungeon.getNumCols();
		int numOfRooms = rand.nextInt(a / 1000) + 2;
		for (int b = 0; b < numOfRooms; b++) {
			int w = (int) (Math.random() * 5 + 5); // width
			int l = (int) (Math.random() * 5 + 5); // length
			placeRoom(w, l);

		}
	}
}
