import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	Hero h;
	ArrayList<Location> openSpaces;
	ArrayList<Location> rooms;
	ArrayList<Location> corridors;// need to place corridors/rooms after pokemon
									// move
	ActorWorld world;
	BoundedGrid grid;
	int numEnemies = 9;
	int numSteps=0;//number of steps taken by main character

	public GamePanel() {
		startGame();
		Dungeon d = new Dungeon(50, 50);
		openSpaces = d.getRoomLocs();// gets locations of the rooms/corridors
		ArrayList<ArrayList<Location>> a= d.getCorridors();
		for(ArrayList<Location> l:a){
			for(int i=0;i<l.size();i++){
				openSpaces.add(l.get(i));
			}
		}
		grid = d.getDungeon();
		// BoundedGrid grid = new BoundedGrid<>(10, 10);
		world = new ActorWorld(grid);

		this.setPreferredSize(new Dimension(500, 500));
		// grid.put(new Location(9,1), "C");
		// for(int i=0;i<3;i++){
		// for(int col=0;col<3;col++){
		// grid.put(new Location(i,col), "C");
		// }
		// }

		// ArrayList<Actor> a=grid.getNeighbors(new Location(1,1));
		// for(Actor ac:a){
		// System.out.println(ac);
		// }
		// world.add(new Location(5, 5),h);

	}

	public void startGame() {
		JOptionPane.showMessageDialog(null,
				"Welcome to Pokemon Mystery Dungeon!");
		choosePokemon();
		for (int i = 0; i < numEnemies; i++) {
			addEnemy();
		}
		world.add(openSpaces.get((int) (Math.random()*openSpaces.size())),h);
	}

	private void addEnemy() {
		// TODO Auto-generated method stub
		int pick = (int) (Math.random() * openSpaces.size());
		Location l = openSpaces.get(pick);
		String last = (String) grid.get(l);
		int choose = (int) (Math.random() * 4);
		openSpaces.remove(l);
		switch (choose) {
		case 0:
			Cyndaquil a= new Cyndaquil(true, last);
			world.add(l,a);
			
			break;
		case 1:
			world.add(l, new Mudkip(true, last));
			break;
		case 2:
			world.add(l, new Munchlax(true, last));
			break;
		case 3:
			world.add(l, new Pichu(true, last));
		}

	}

	private void choosePokemon() {
		PersonalityTest test = new PersonalityTest();
		Pokemon p = test.chooseCharacter();
		h = new Hero(p);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawDungeon(g);

	}

	private void drawDungeon(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
