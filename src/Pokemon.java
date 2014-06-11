import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import info.gridworld.actor.Actor;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

public abstract class Pokemon extends Actor {
	protected int level;
	protected int topHp;
	protected int hp;
	protected int attack;
	protected int defense;
	protected boolean enemy;
	protected int x = 0;
	private String[][] land;// String is the type of the last spot eg.
							// corridor or room
	private int dir; // dir = direction, stick with definitions as defined by
						// Location Class

	public Pokemon(boolean enemy, int hp, int attack, int defense, int level,
			String[][] land) {
		this.enemy = enemy;
		this.hp = hp;
		this.topHp = hp;
		this.attack = attack;
		this.defense = defense;

		this.land = land;

	}

	public String[][] getLand() {
		return land;
	}


	public Pokemon() {
	};

	public abstract void draw(Graphics g, int row, int col);

	public int getLevel() {
		return level;
	}

	public void levelUp() {
		level++;
		topHp += 5;
		attack += 2;
		defense += 2;
		hp = topHp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setEnemy(boolean b) {
		enemy = b;
	}

	public boolean isEnemy() {
		return enemy;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void attack(Pokemon p) {
		int defense = p.getDefense();
		int attack = this.getAttack();
		int hp = p.getHp();
		if (attack - defense > 0) {
			p.setHp(hp - (attack - defense));
		}
		return;
	}

	public void move() {
		BoundedGrid g = (BoundedGrid) this.getGrid();
		boolean stop = false;
		Location begin=this.getLocation();
		ArrayList<Location> a = getPokemon(g);// gets the location of all
												// pokemon that are not enemy so
												// it can attack them
		while (a.size() > 0 || stop == true) {//while there are spaces to go to or the pokemon moved
			Location go = a.get(0);
			double max = distanceFrom(go);
			for (Location l : a) {
				if (l.equals(this.getLocation()) == false) {
					double temp = distanceFrom(l);// finds closest pokemon
					if (temp < max) {
						temp = max;
						go = l;// checks for closest pokemon to attack
					}
				}

			}

			int direct = this.getLocation().getDirectionToward(go);
			Location l = this.getLocation().getAdjacentLocation(direct);
			if (g.isValid(l) && (land[l.getRow()][l.getCol()].equals("W")) == false) {// if the new location is valid and it isn't a wall, move toward it
				this.setDirection(direct);
				Location current = this.getLocation();
				if (g.get(l)==(null)) {
					this.moveTo(l);
					stop=true;
					System.out.println("Moved from "+begin+ " to "+ this.getLocation());
				} else if(g.get(l)instanceof Pokemon){
					this.attack((Pokemon) g.get(l));
					stop = true;
					System.out.println("Didn't move, attacking hero. Past loc is equal to begin? "+ begin.equals(this.getLocation()));

				}
			} else {
				a.remove(l);// if the location is not valid, then look for a new spot
			}
		}
		
		if (stop == false) {// if you never moved, move to a random location
			Random ran = new Random();
			ArrayList<Location> l = g.getValidAdjacentLocations(getLocation());
			Location current = this.getLocation();
			int choose = ran.nextInt(l.size());
			Location go = l.get(choose);
			if(g.isValid(go) && land[go.getRow()][go.getCol()].equals("W")==false){
				this.moveTo(go);
				System.out.println("Moved from "+begin+ " to "+ this.getLocation());

			}
		
		}
		System.out.println("After moving, my location is valid? "+g.isValid(getLocation()));

	}

	public double distanceFrom(Location loc) {
		Location here = this.getLocation();
		int a = loc.getCol() - here.getCol();
		int b = loc.getRow() - here.getRow();
		double total = (a * a) + (b * b);
		double distance = Math.sqrt(total);
		return distance;
	}

	private ArrayList<Location> getPokemon(BoundedGrid g) {// returns all
															// non-enemy Pokemon
		// TODO Auto-generated method stub
		ArrayList<Location> locs = new ArrayList<>();
		Location l = this.getLocation();
//		System.out.println("current Enemy location is "+l);
		int minRow, maxRow;
		int minCol, maxCol;
		minRow = l.getRow() - 2;
		maxRow = l.getRow() + 2;
		minCol = l.getCol() - 2;
		maxCol = l.getRow() + 2;
		if (minRow < 0) {
			minRow = 0;
		}
		if (maxRow >= g.getNumRows()) {
			maxRow = g.getNumRows() - 1;
		}
		if (minCol < 0) {
			minCol = 0;
		}
		if (maxCol >= g.getNumCols()) {
			maxCol = g.getNumCols() - 1;
		}
		for (int row = minRow; row <= maxRow; row++) {
			for (int col = minCol; col <= maxCol; col++) {
				Object o = g.get(new Location(row, col));
				if (o instanceof Pokemon) {
					Pokemon p = (Pokemon) o;
					if (p.isEnemy() == false) {
						locs.add(p.getLocation());
					}

				}
			}
		}
		return locs;
	}
}
