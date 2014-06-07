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
	private int numberofRooms;

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

	public String[][] getDungeon() {
		String[][] retu = new String[dungeon.getNumRows()][dungeon.getNumCols()];
		for (int row = 0; row < dungeon.getNumRows(); row++) {
			for (int col = 0; col < dungeon.getNumCols(); col++) {
				retu[row][col] = (String) dungeon.get(new Location(row, col));
			}
		}
		return retu;
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
		System.out.println("going into initCorridors");
		ArrayList<Location> locs = new ArrayList<Location>();
		ArrayList<Location> locs1 = new ArrayList<Location>();
		for (int a = 0; a < rooms.size(); a++) {
			Collections.shuffle(rooms);
			Room r1 = rooms.get(0);
			System.out.println("Y: " + r1.getY());
			Room r2 = rooms.get(1);
			System.out.println("Y: " + (r2.getY()));
			int[] d1 = r1.getDimensions();
			int[] d2 = r2.getDimensions();

			// first room, get inner border
			int t = d1[1];// remove negative one? seems to work
			for (int b = 0; b < 2; b++) {
				for (int e = d1[0]; e < d1[0] + d1[2]; e++)
					locs.add(new Location(t, e));
				t = d1[1] + d1[3];
			}
			int s = d1[0];
			for (int c = 0; c < 2; c++) {
				for (int d = d1[1]; d < d1[1] + d1[3]; d++)
					locs.add(new Location(d, s));
				s = d1[0] + d1[2];
			}

			// second room, get inner border
			t = d2[1];
			for (int b = 0; b < 2; b++) {
				for (int e = d2[0]; e < d2[0] + d2[2]; e++)
					locs1.add(new Location(t, e));
				t = d2[1] + d2[3];
			}
			s = d2[0];
			for (int c = 0; c < 2; c++) {
				for (int d = d2[1]; d < d2[1] + d2[3]; d++)
					locs1.add(new Location(d, s));
				s = d2[0] + d2[2];
			}

			Collections.shuffle(locs);
			Collections.shuffle(locs1);
			corridors.add(placeCorridors(locs.get(0), locs1.get(0)));
		}
		ArrayList<Room> roomsLeft = new ArrayList<Room>();
		for (Room r : rooms)
			if (!r.corridorConnection(dungeon))
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

			// first room, get inner border
			int t = d1[1];
			for (int b = 0; b < 2; b++) {
				for (int e = d1[0]; e < d1[0] + d1[2]; e++)
					locs.add(new Location(t, e));
				t = d1[1] + d1[3];
			}
			int s = d1[0];
			for (int c = 0; c < 2; c++) {
				for (int d = d1[1]; d < d1[1] + d1[3]; d++)
					locs.add(new Location(d, s));
				s = d1[0] + d1[2];
			}

			// second room, get inner border
			t = d2[1];
			for (int b = 0; b < 2; b++) {
				for (int e = d2[0]; e < d2[0] + d2[2]; e++)
					locs1.add(new Location(t, e));
				t = d2[1] + d2[3];
			}
			s = d2[0];
			for (int c = 0; c < 2; c++) {
				for (int d = d2[1]; d < d2[1] + d2[3]; d++)
					locs1.add(new Location(d, s));
				s = d2[0] + d2[2];
			}

		}

		Collections.shuffle(locs);
		Collections.shuffle(locs1);
		corridors.add(placeCorridors(locs.get(0), locs1.get(0)));

		for (Room ro : rooms) {
			int[] dim = ro.getDimensions();
			for (int a = dim[0]; a < dim[2]; a++)
				for (int b = dim[1]; b < dim[3]; b++) {
					Location loc = new Location(b, a);
					dungeon.put(loc, "R");
				}
		}
	}

	private ArrayList<Location> placeCorridors(Location l, Location l1) {
		System.out.println("going into placecorridors");
		ArrayList<Location> spaces = new ArrayList<Location>();
		spaces.add(l);
		while (!l.equals(l1)) {
			int point = l.getDirectionToward(l1);// gets direction from room 1
													// to room 2
			ArrayList<Location> locs = dungeon.getEmptyAdjacentLocations(l);
			ArrayList<Location> temp = dungeon.getOccupiedAdjacentLocations(l);
			for (Location a : temp) {
				locs.add(a);
			}
			ArrayList<Location> head = new ArrayList<Location>();
			for (Location loc : locs) {
				int bam = l.getDirectionToward(loc);
				// if (dungeon.get(loc) instanceof String &&
				// !dungeon.get(loc).equals("R")){
				if (point % 10 == 0// if direction to room 2 divisible
						&& (Math.abs(point - bam) == 90 || point - bam == 0))// by
																				// 10
																				// and
																				// less
																				// than
																				// 90
																				// degrees
																				// away
																				// from
																				// point
					head.add(loc);
				else if (point % 10 != 0 && (Math.abs(point - bam)) == 45) {
					head.add(loc);

				}
				// }
			}
			Location chosen = null;
			Collections.shuffle(head);
			if (point % 10 != 0)// when adding locs, possible to have none that
								// are divisible by 10
				chosen = head.get(0);
			else {
				Random rand = new Random();
				int chance = rand.nextInt(10) + 1; // 70% straight, 15% side
				// Location temp;
				for (int i = 0; i < head.size(); i++) {// for (Location lo :
														// head) {
					if (chance < 8
							&& l.getDirectionToward(head.get(i)) == point) {
						chosen = head.get(i);
						break;
					} else if (chance >= 8
							&& l.getDirectionToward(head.get(i)) == point) {
						head.remove(head.get(i));
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
		System.out.println("going into placeroom");
		Random rand = new Random();
		Room r = null;
		int b = 0;
		for (int a = 0; a < 1000000; a++) {
			int x = rand.nextInt(dungeon.getNumCols() - width - 4) + 3;
			int y = rand.nextInt(dungeon.getNumRows() - length - 4) + 3;
			// System.out.println(dungeon.getNumCols() + " "
			// + dungeon.getNumRows() + " " + x + " " + y);
			r = new Room(x, y, width, length);

			b = a;
			if (!r.overlap(dungeon)) {

				break;
			}

		}

		System.out.println("Chance to build room completed");

		if (b != 1000000) {
			rooms.add(r);
			numberofRooms++;
			for (int a = r.getX(); a < width + r.getX(); a++) {
				// System.out.println("place room y :" + r.getY());
				// System.out.println("x is "+r.getX());
				for (int c = r.getY(); c < length + r.getY(); c++) {
					Location loc = new Location(c, a);
					// System.out.println("x is "+a+" y is "+c);
					dungeon.put(loc, "R");

					roomLocs.add(loc);
				}
			}
		}
		for (int row = 0; row < dungeon.getNumRows(); row++) {
			for (int col = 0; col < dungeon.getNumCols(); col++) {
				System.out.print(dungeon.get(new Location(row, col)));
			}
			System.out.println();
		}
		System.out.println("done");
	}

	private void initRooms() {
		System.out.println("Going into init Rooms");
		Random rand = new Random();
		int a = dungeon.getNumRows() * dungeon.getNumCols();
		int numOfRooms = rand.nextInt(a / 1000) + 4;
		numberofRooms = 0;
		while (numberofRooms < numOfRooms) {
			int w = (int) (Math.random() * 12 + 8); // width
			int l = (int) (Math.random() * 12 + 8); // length
			placeRoom(w, l);
		}

	}
}
