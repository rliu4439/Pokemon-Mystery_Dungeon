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
	ArrayList<Pokemon> friendly = new ArrayList<>();
	ActorWorld world;
	BoundedGrid grid;
	String[][] land;
	int numEnemies = 5;
	int numItems = 10;
	int floorLevel = 1;
	int numSteps = 0;// number of steps taken by main character
	private static Image wall;
	private static Image floor;
	private static Image stairs;
	private static Image error;
	ItemHolder itemHolder;
	InfoScreen info;
	boolean firstStage = true;
	
	public ItemHolder getItemHolder() {
		return itemHolder;
	}

	public void setItemHolder(ItemHolder itemHolder) {
		this.itemHolder = itemHolder;
	}

	public GamePanel(ItemHolder i2) {
		itemHolder = i2;
		setUpKeyMappings();
		try {
			wall = ImageIO.read(new File("src/image/Wall.png"));
			floor = ImageIO.read(new File("src/image/Floor.png"));// reads in
			stairs = ImageIO.read(new File("src/image/Stairs.png")); // pictures
			error = ImageIO.read(new File("src/image/error.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		Pokemon p = new Mudkip(false, land, this);// used for testing
//		hero = new Hero(p, land, this);
startGame();
//		nextLevel();

		this.setPreferredSize(new Dimension(600, 600));

	}

	public void nextLevel() {
		if (!firstStage)
			this.getInfo().writeText("You moved to the next stage!");
		else
			firstStage = false;
		boolean check = false;
		Dungeon d;

		d = new Dungeon(60,60);// creates a dungeon
		ArrayList<Room> roo = d.getRooms();
		boolean reDo = d.checkRooms();
		while (reDo == true) {
			d = new Dungeon(50, 50);
			reDo = d.checkRooms();

		}

		land = d.getDungeon();// / change to land
		openSpaces = openLocations();// gets locations of the rooms/corridors
		hero.setLand(this.land);
		grid = new BoundedGrid<>(land.length, land[1].length);
		world = new ActorWorld(grid);

		int counter = 0;
		for (int i = 0; i < numEnemies*floorLevel; i++) {
			addEnemy();
			counter++;
		}
		for (int i = 0; i < numItems+floorLevel; i++) {
			addItem();
		}

		Location l = (openSpaces.get((int) (Math.random() * openSpaces.size())));
		world.add(openSpaces.get((int) (Math.random() * openSpaces.size())),
				hero.main);
		hero.setGrid(grid);
		hero.setPanel(this);

		addStairs();

		ArrayList<ArrayList<Location>> a = d.getCorridors();
		for (ArrayList<Location> lo : a) {
			for (int i = 0; i < lo.size(); i++) {
				openSpaces.add(lo.get(i));
			}
		}
		repaint();
	}

	public void startGame() {
		 JOptionPane.showMessageDialog(null,
		 "Welcome to Pokemon Mystery Dungeon!");
			if (!firstStage)
				this.getInfo().writeText("You moved to the next stage!");
			else
				firstStage = false;
			boolean check = false;
			Dungeon d;

			d = new Dungeon(60,60);// creates a dungeon
			ArrayList<Room> roo = d.getRooms();
			boolean reDo = d.checkRooms();
			while (reDo == true) {
				d = new Dungeon(50, 50);
				reDo = d.checkRooms();

			}

			land = d.getDungeon();// / change to land
			openSpaces = openLocations();// gets locations of the rooms/corridors
			grid = new BoundedGrid<>(land.length, land[1].length);
			world = new ActorWorld(grid);

			int counter = 0;
			for (int i = 0; i < numEnemies*floorLevel; i++) {
				addEnemy();
				counter++;
			}
			for (int i = 0; i < numItems+floorLevel; i++) {
				addItem();
			}
			PersonalityTest t= new PersonalityTest(land);
			Pokemon z=t.chooseCharacter(this);
			hero= new Hero(z,land,this);
			Location l = (openSpaces.get((int) (Math.random() * openSpaces.size())));
			world.add(openSpaces.get((int) (Math.random() * openSpaces.size())),
					hero.main);
			hero.setGrid(grid);
			hero.setPanel(this);

			addStairs();

			ArrayList<ArrayList<Location>> a = d.getCorridors();
			for (ArrayList<Location> lo : a) {
				for (int i = 0; i < lo.size(); i++) {
					openSpaces.add(lo.get(i));
				}
			}
			repaint();

	}

	private void addStairs() {
		boolean pick = true;

		while (pick == true) {
			int pickNum = (int) (Math.random() * openSpaces.size());
			Location l = openSpaces.get(pickNum);
			if (land[l.getRow()][l.getCol()].equals("R")) {
				land[l.getRow()][l.getCol()] = "S";
				openSpaces.remove(l);
				pick = false;
			}

		}

	}

	private void addEnemy() {
		int pick = (int) (Math.random() * openSpaces.size());
		// System.out.println("openspaces is size " + openSpaces.size());
		Location l = openSpaces.get(pick);
		// System.out.println(l);
		int choose = (int) (Math.random() * 4);
		// System.out.println(choose);
		switch (choose) {
		case 0:
			Cyndaquil a = new Cyndaquil(true, land, this);
			world.add(l, a);
			openSpaces.remove(l);
			enemies.add(a);
			break;
		case 1:
			Mudkip m = new Mudkip(true, land, this);
			world.add(l, m);
			// System.out.println("Pokemon here");
			openSpaces.remove(l);
			enemies.add(m);
			break;
		case 2:
			Munchlax mu = new Munchlax(true, land, this);
			world.add(l, mu);
			// System.out.println("Pokemon here");
			openSpaces.remove(l);
			enemies.add(mu);
			break;
		case 3:
			Pichu p = new Pichu(true, land, this);
			world.add(l, p);
			openSpaces.remove(l);
			enemies.add(p);
			break;

		}

	}

	public Hero getHero() {
		return hero;
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

	public void moveEnemies(ArrayList<Pokemon> friendly) {

		for (Pokemon p : enemies) {
			p.move(friendly);
		}
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getHp() <= 0 && enemies.get(i).isEnemy() == true) {
				enemies.get(i).removeSelfFromGrid();
				getInfo().writeText(enemies.get(i).getName()+" has been defeated. You gained 5 XP points!");
				System.out.println("removed " + enemies.get(i));
				enemies.remove(enemies.get(i));
				hero.increaseXP();

			}
		}
	}

//	private void choosePokemon() {
//		PersonalityTest test = new PersonalityTest(this.land);
//		Pokemon p = test.chooseCharacter();
//		hero = new Hero(p, land, this);
//
//	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawDungeon(g);

	}

	private void drawDungeon(Graphics g) {
		// System.out.println("At draw Dungeon");
		Location current = hero.main.getLocation();
		// System.out.println("current is "+current);
		int row = current.getRow();
		int col = current.getCol();
		// System.out.println(current);
		int counterr = 0;
		int counterc = 0;
		for (int r = row - 4; r < row + 5; r++) {// gets 4 above and 4 below
													// hero
			for (int c = col - 4; c < col + 5; c++) {
				// System.out.println("counter r is "
				// +counterr+" counter c is "+counterc);
				if (land[r][c].equals("C") || land[r][c].equals("R")) {
					// System.out.println("Looking at " + r + " " + c);
					g.drawImage(floor, counterc * 60, counterr * 60, 60, 60,
							null);
					// g.draw3DRect(r * 10, c * 10, 10, 10, true);
				} else if (land[r][c].equals("W")) {
					// System.out.println("Looking at " + r + " " + c);
					g.drawImage(wall, counterc * 60, counterr * 60, 60, 60,
							null);
					// g.draw3DRect(r * 10, c * 10, 10, 10, true);
				} else if (land[r][c].equals("S")) {
					// System.out.println("got here");
					// System.out.println("Location of stairs is " + r + " " +
					// c);
					g.drawImage(stairs, counterc * 60, counterr * 60, 60, 60,
							null);
				} else {
					g.drawImage(error, counterc * 60, counterr * 60, 60, 60,
							null);
				}

				if (grid.get(new Location(r, c)) instanceof Pokemon) {
					// System.out.println("Pokemon here");
					Pokemon p = (Pokemon) grid.get(new Location(r, c));
					// g.drawImage(floor, counterc * 60, counterr * 60, 60, 60,
					// null);
					p.draw(g, counterc * 60, counterr * 60);
					// g.draw3DRect(r * 60, c * 60, 60, 60, true);

				} else if (grid.get(new Location(r, c)) instanceof Items) {

					// System.out.println("Found an item at " + r + " " + c);
					Items i = (Items) grid.get(new Location(r, c));
					// g.drawImage(floor, counterc * 60, counterr * 60, 60, 60,
					// null);
					i.draw(g, counterc * 60, counterr * 60);
					// System.out.println("Drawimg at "+counterr+" "+counterc);

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
				moveEnemies(friendly);
//				hero.checkStatus();
				repaint();
				hero.checkStatus();
			}

		});
		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
		this.getActionMap().put("moveLeft", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.println("moving left");
				hero.moveLeft();
				moveEnemies(friendly);
//				hero.checkStatus();
				repaint();
				hero.checkStatus();
			}
		});
		this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "moveUp");
		this.getActionMap().put("moveUp", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// System.out.println("moving up");
				hero.moveUp();
				moveEnemies(friendly);
//				hero.checkStatus();
				repaint();
				hero.checkStatus();
			}

		});

		this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "moveBack");

		this.getActionMap().put("moveBack", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hero.moveBack();
				// System.out.println("moving down");
				moveEnemies(friendly);
				// System.out.println("Finished moving enemies");
//				hero.checkStatus();
				repaint();
				hero.checkStatus();
			}
		});

		this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "attack");
		this.getActionMap().put("attack", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (hero.main.getGrid().get(
						hero.getMain().getLocation()
								.getAdjacentLocation(hero.main.getDirection())) instanceof Pokemon)
					hero.main.attack((Pokemon) hero.main.getGrid().get(
							hero.main.getLocation().getAdjacentLocation(
									hero.main.getDirection())));
				// System.out.println("moving right");
				moveEnemies(friendly);
			
				repaint();
				hero.checkStatus();
			}

		});
	}

	public InfoScreen getInfo() {
		return info;
	}

	public void setInfo(InfoScreen info) {
		this.info = info;
	}

}
