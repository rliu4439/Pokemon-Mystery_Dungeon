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
	protected String lastSpot;// String is the type of the last spot eg.
								// corridor or room
	private int dir; // dir = direction, stick with definitions as defined by
						// Location Class

	public Pokemon(boolean enemy, int hp, int attack, int defense, int level,
			String last) {
		this.enemy = enemy;
		this.hp = hp;
		this.topHp = hp;
		this.attack = attack;
		this.defense = defense;
		this.lastSpot = last;

	}

	public Pokemon() {
	};
	public void draw(Graphics g, int row, int col){
		
	}
	
	public String getLastSpot(){
		return lastSpot;
	}

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
	}

	public void move() {// need to check to not move on another Pokemon, need to
						// reput that the spot is empty or corridor
		BoundedGrid g = (BoundedGrid) this.getGrid();
		boolean stop = false;
		ArrayList<Location> a = getPokemon(g);
		while (a.size() > 0 || stop == true) {
			Location go = a.get(0);
			double max = distanceFrom(go);
			for (Location l : a) {
				double temp = distanceFrom(l);// finds closest pokemon
				if (temp < max) {
					temp = max;
					go = l;
				}
			}

			int direct = this.getLocation().getDirectionToward(go);
			Location l = getLocation().getAdjacentLocation(direct);
			if (g.isValid(l) && (g.get(l).equals("W")) == false) {
				this.setDirection(direct);
				Location current = this.getLocation();
				if (g.get(l) instanceof String) {// if next location to move to
													// is
													// empty, then move and save
													// what it was(eg
													// room/corridor)
					String temp = (String) g.get(l);
					this.moveTo(l);
					g.put(current, lastSpot);
					lastSpot = temp;
					stop = true;
				} else {
					this.attack((Pokemon) g.get(l));
					stop = true;

				}
			} else {
				a.remove(l);
			}
		}
		if (stop == false) {// never moved
			Random ran = new Random();
			ArrayList<Location> l = g.getValidAdjacentLocations(getLocation());
			for (int i = 0; i < l.size(); i++) {
				if (g.get(l.get(i)) instanceof String) {
					if (g.get(l.get(i)).equals("W")) {
						l.remove(i);
					}
				}
			}
			Location current = this.getLocation();
			int choose = ran.nextInt(l.size());
			Location go = l.get(choose);
			String temp = (String) g.get(go);
			this.moveTo(go);
			g.put(current, lastSpot);
			lastSpot = temp;
		}

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
