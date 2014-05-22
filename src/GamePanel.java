import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	Hero h;

	public GamePanel() {
		startGame();
		Grid<Actor> grid = new BoundedGrid<Actor>(100,100);
        ActorWorld world = new ActorWorld(grid);
		this.setPreferredSize(new Dimension(500, 500));
		world.add(new Location(5, 5),h);
		
		
	}

	public void startGame() {
		JOptionPane.showMessageDialog(null,
				"Welcome to Pokemon Mystery Dungeon!");
		choosePokemon();
		
	}

	private void choosePokemon() {
		// TODO Auto-generated method stub
		PersonalityTest test = new PersonalityTest();
		Pokemon p = test.chooseCharacter();
		h = new Hero(p);

	}
}
