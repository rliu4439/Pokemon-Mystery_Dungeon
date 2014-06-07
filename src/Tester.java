import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tester extends JPanel {// change grid to land to hold locations
										// w/ strings
	Hero h; // place pokemon and items in the gridworld but put wall, floor in
			// array
	ArrayList<Location> openSpaces;
	ArrayList<Location> rooms;
	ArrayList<Location> corridors;// need to place corridors/rooms after pokemon
									// move
	ActorWorld world;
	BoundedGrid grid;
	String[][] land;
	int numEnemies = 9;
	int numSteps = 0;// number of steps taken by main character
	Image wall;
	Image floor;

	public Tester() {
		this.setOpaque(true);
        this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(200, 500));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawItems(g);

	}

	private void drawItems(Graphics g) {
		// TODO Auto-generated method stub]
	}

}
