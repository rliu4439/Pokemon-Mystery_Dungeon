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
	ActorWorld world;
	BoundedGrid grid;
	String[][] land;
	int numEnemies = 40;
	int numSteps = 0;// number of steps taken by main character
	private static Image wall;
	private static Image floor;
	ItemHolder i;

	public GamePanel(ItemHolder i2) {
		i = i2;
		try {
			wall = ImageIO.read(new File("src/image/Wall.png"));
			floor = ImageIO.read(new File("src/image/Floor.png"));// reads in
																	// pictures
		} catch (IOException e) {
			e.printStackTrace();
		}
		Pokemon p = new Mudkip(false, land);
		hero = new Hero(p);
		Dungeon d = new Dungeon(50, 50);// creates a dungeon
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

		this.setPreferredSize(new Dimension(500, 500));
		// using next line for testing

	}

	public Hero getHero() {
		return hero;
	}

	public ArrayList<Location> openLocations() {
		ArrayList<Location> open= new ArrayList<>();
		for(int row=0;row<land.length;row++){
			for(int col=0; col<land[0].length;col++){
				if(land[row][col].equals("W")==false){
					open.add(new Location(row, col));
				}
			}
		}
		return open;
	}

	public void startGame() {
		JOptionPane.showMessageDialog(null,
				"Welcome to Pokemon Mystery Dungeon!");
		// choosePokemon();
		for (int i = 0; i < numEnemies; i++) {
			addEnemy();
		}
		Location l = (openSpaces.get((int) (Math.random() * openSpaces.size())));
		world.add(openSpaces.get((int) (Math.random() * openSpaces.size())),
				hero);
	}

	private void addEnemy() {
		// TODO Auto-generated method stub
		int pick = (int) (Math.random() * openSpaces.size());
		System.out.println("openspaces is size " + openSpaces.size());
		Location l = openSpaces.get(pick);
		System.out.println(l);
		int choose = (int) (Math.random() * 4);
		System.out.println(choose);
		switch (choose) {
		case 0:
			Cyndaquil a = new Cyndaquil(true, land);
			world.add(l, a);
			System.out.println("Pokemon here");
			openSpaces.remove(l);

			break;
		case 1:
			world.add(l, new Mudkip(true, land));
			System.out.println("Pokemon here");
			openSpaces.remove(l);

			break;
		case 2:
			world.add(l, new Munchlax(true, land));
			System.out.println("Pokemon here");
			openSpaces.remove(l);

			break;
		case 3:
			world.add(l, new Pichu(true, land));
			System.out.println("Pokemon here");
			openSpaces.remove(l);
			break;

		}

	}

	private void choosePokemon() {
		PersonalityTest test = new PersonalityTest(this.land);
		Pokemon p = test.chooseCharacter();
		hero = new Hero(p);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawDungeon(g);

	}

	private void drawDungeon(Graphics g) {
		// TODO Auto-generated method stub]
		System.out.println("Drawing dungeon");

		for (int row = 0; row < land.length; row++) {
			for (int col = 0; col < land[0].length; col++) {
				System.out.print(land[row][col]);
				if (land[row][col].equals("C") || land[row][col].equals("R")) {
					g.drawImage(floor, row * 10, col * 10, 10, 10, null);

				} else {

					g.drawImage(wall, row * 10, col * 10, 10, 10, null);

				}

				if (grid.get(new Location(row, col)) instanceof Pokemon) {
					// System.out.println("Pokemon here");
					Pokemon p = (Pokemon) grid.get(new Location(row, col));
					g.drawImage(floor, row*10, col*10, 10, 10, null);
					p.draw(g, row*10, col*10);
				}

				else if (grid.get(new Location(row, col)) instanceof Items) {
					Items i = (Items) grid.get(new Location(row, col));
					g.drawImage(floor, row*10, col*10, 10, 10, null);
					i.draw(g, row*10, col*10);
				}
			}
			System.out.println();
		}

	}

	private void setUpKeyMappings() {
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
		this.getActionMap().put("moveRight", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				hero.moveRight();
			}

		});
		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
		this.getActionMap().put("moveLeft", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				hero.moveLeft();
			}
		});
		this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "moveUp");
		this.getActionMap().put("moveUp", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				hero.moveUp();
			}

		});

		this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "MoveBack");

		this.getActionMap().put("moveBack", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hero.moveBack();
			}
		});

	}

}
