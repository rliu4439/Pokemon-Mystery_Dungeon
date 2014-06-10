import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class GamePanel extends JPanel {// change grid to land to hold locations
										// w/ strings
	Hero hero; // place pokemon and items in the gridworld but put wall, floor
				// in
				// array
	ArrayList<Location> openSpaces;
	ArrayList<Location> rooms;
	ArrayList<Location> corridors;// need to place corridors/rooms after pokemon
									// move
	ArrayList<Pokemon> enemies = new ArrayList<>();
	ActorWorld world;
	BoundedGrid grid;
	String[][] land;
	int numEnemies = 40;
	int numItems = 50;
	int floorLevel = 1;
	int numSteps = 0;// number of steps taken by main character
	private static Image wall;
	private static Image floor;
	private static Image stairs;
	ItemHolder i;

	public GamePanel(ItemHolder i2) {
		// tester t= new tester();
		i = i2;
		setUpKeyMappings();
		try {
			wall = ImageIO.read(new File("src/image/Wall.png"));
			floor = ImageIO.read(new File("src/image/Floor.png"));// reads in
			stairs = ImageIO.read(new File("src/image/Stairs.png")); // pictures
		} catch (IOException e) {
			e.printStackTrace();
		}

		boolean check = false;
		Dungeon d;

		d = new Dungeon(50, 50);// creates a dungeon
		ArrayList<Room> roo = d.getRooms();
		boolean reDo = d.checkRooms();
		while (reDo == true) {
			System.out.println("need to redo");
			System.out.println("Checking");
			d = new Dungeon(50, 50);
			reDo = d.checkRooms();

		}

		land = d.getDungeon();// / change to land
		openSpaces = openLocations();// gets locations of the rooms/corridors

		grid = new BoundedGrid<>(land.length, land[1].length);
		world = new ActorWorld(grid);

		startGame();

		ArrayList<ArrayList<Location>> a = d.getCorridors();
		for (ArrayList<Location> l : a) {
			for (int i = 0; i < l.size(); i++) {
				openSpaces.add(l.get(i));
			}
		}

		this.setPreferredSize(new Dimension(750, 750));

	}

	public Hero getHero() {
		return hero;
	}

	public void moveEnemies() {
		for (Pokemon p : enemies) {
			p.move();
		}
	}

	public ArrayList<Location> openLocations() {
		ArrayList<Location> open = new ArrayList<>();
		for (int row = 0; row < land.length; row++) {
			for (int col = 0; col < land[0].length; col++) {
				if (land[row][col].equals("W") == false) {
					open.add(new Location(row, col));
				}
			}
		}
		return open;
	}

	public void addItem() {
		int pick = (int) (Math.random() * openSpaces.size());
		// System.out.println("openspaces is size " + openSpaces.size());
		Location l = openSpaces.get(pick);
		int choose = (int) (Math.random() * 3);
		// System.out.println(choose);
		switch (choose) {
		case 0:
			Apple a = new Apple();
			world.add(l, a);
			// System.out.println("added apple");
			openSpaces.remove(l);

			break;
		case 1:
			GrimyFood f = new GrimyFood();
			world.add(l, f);
			openSpaces.remove(l);

			break;
		case 2:
			OranBerry o = new OranBerry();

			world.add(l, o);
			openSpaces.remove(l);

			break;
		}

	}

	public void startGame() {
		// JOptionPane.showMessageDialog(null,
		// "Welcome to Pokemon Mystery Dungeon!");
		// choosePokemon();
		for (int i = 0; i < numEnemies; i++) {
			addEnemy();
		}
		for (int i = 0; i < numItems; i++) {
			addItem();
		}
		Pokemon p = new Mudkip(false, land);// used for testing
		hero = new Hero(p, land);
		Location l = (openSpaces.get((int) (Math.random() * openSpaces.size())));
		world.add(openSpaces.get((int) (Math.random() * openSpaces.size())),
				hero.main);
		hero.setGrid(grid);
		addStairs();
	}

	private void addStairs() {
		int pick = (int) (Math.random() * openSpaces.size());
		Location l = openSpaces.get(pick);
		land[l.getRow()][l.getCol()] = "S";
		openSpaces.remove(l);
	}

	private void addEnemy() {
		// TODO Auto-generated method stub
		int pick = (int) (Math.random() * openSpaces.size());
		// System.out.println("openspaces is size " + openSpaces.size());
		Location l = openSpaces.get(pick);
		// System.out.println(l);
		int choose = (int) (Math.random() * 4);
		// System.out.println(choose);
		switch (choose) {
		case 0:
			Cyndaquil a = new Cyndaquil(true, land);
			world.add(l, a);
			openSpaces.remove(l);
			enemies.add(a);
			break;
		case 1:
			Mudkip m = new Mudkip(true, land);
			world.add(l, m);
			// System.out.println("Pokemon here");
			openSpaces.remove(l);
			enemies.add(m);
			break;
		case 2:
			Munchlax mu = new Munchlax(true, land);
			world.add(l, mu);
			// System.out.println("Pokemon here");
			openSpaces.remove(l);
			enemies.add(mu);
			break;
		case 3:
			Pichu p = new Pichu(true, land);
			world.add(l, p);
			openSpaces.remove(l);
			enemies.add(p);
			break;

		}

	}

	private void choosePokemon() {
		PersonalityTest test = new PersonalityTest(this.land);
		Pokemon p = test.chooseCharacter();
		hero = new Hero(p, land);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawDungeon(g);

	}

	// } else {
	// // System.out.println("got here");
	// g.drawImage(stairs, r * 60, c * 60, 60, 60, null);
	// }

	// if (grid.get(new Location(r, c)) instanceof Pokemon) {
	// // System.out.println("Pokemon here");
	// Pokemon p = (Pokemon) grid.get(new Location(r, c));
	// g.drawImage(floor, r * 60, c * 60, 60, 60, null);
	// p.draw(g, r * 60, c * 60);
	// g.draw3DRect(r * 60, c * 60, 60, 60, true);
	//
	// }
	//
	// else if (grid.get(new Location(r, c)) instanceof Items) {
	// Items i = (Items) grid.get(new Location(r, c));
	// g.drawImage(floor, r * 60, c * 60, 60, 60, null);
	// i.draw(g, r * 60, c * 60);
	// g.draw3DRect(r * 60, c * 60, 60, 60, true);
	//
	// }
	private void drawDungeon(Graphics g) {
		// TODO Auto-generated method stub]
		// System.out.println("Drawing dungeon");
		Location current = hero.main.getLocation();
		int row = current.getRow();
		int col = current.getCol();
		System.out.println(current);
		int counterr = 0;
		int counterc = 0;
		for (int r = row - 4; r < row + 5; r++) {
			for (int c = col - 4; c < col + 5; c++) {
				if (land[r][c].equals("C") || land[r][c].equals("R")) {
					System.out.println("Looking at " + r + " " + c);
					g.drawImage(floor, counterc * 60, counterr * 60, 60, 60,
							null);
					// g.draw3DRect(r * 10, c * 10, 10, 10, true);
				} else if (land[r][c].equals("W")) {

					g.drawImage(wall, counterc * 60, counterr * 60, 60, 60,
							null);
					// g.draw3DRect(r * 10, c * 10, 10, 10, true);
				} else {
					// System.out.println("got here");
					g.drawImage(stairs, counterr * 60, counterc * 60, 60, 60,
							null);
				}

				if (grid.get(new Location(r, c)) instanceof Pokemon) {
					// System.out.println("Pokemon here");
					Pokemon p = (Pokemon) grid.get(new Location(r, c));
					g.drawImage(floor, counterr * 60, counterc * 60, 60, 60,
							null);
					p.draw(g, counterr * 60, counterc * 60);
					// g.draw3DRect(r * 60, c * 60, 60, 60, true);

				}
				counterc++;
			}
			counterr++;
			counterc = 0;
			// System.out.println();
		}

	}

	private void setUpKeyMappings() {
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
		this.getActionMap().put("moveRight", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				hero.moveRight();
				System.out.println("moving right");
				repaint();
			}

		});
		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
		this.getActionMap().put("moveLeft", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("moving left");
				hero.moveLeft();
				repaint();
			}
		});
		this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "moveUp");
		this.getActionMap().put("moveUp", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("moving up");
				hero.moveUp();
				repaint();
			}

		});

		this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "moveBack");

		this.getActionMap().put("moveBack", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hero.moveBack();
				System.out.println("moving down");
				repaint();
			}
		});

	}

}
