import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

public abstract class Pokemon extends Actor {
	public int level;
	public int topHp;
	public int hp;
	public int attack;
	public int defense;
	public boolean enemy;
	public int x = 0;

	public Pokemon(boolean enemy, int hp, int attack, int defense, int level) {
		this.enemy = enemy;
		this.hp = hp;
		this.topHp = hp;
		this.attack = attack;
		this.defense = defense;

	}

	public Pokemon() {
	};

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

	public void move() {// need to check to not move on another Pokemon, need to reput that the spot is empty or corridor
		BoundedGrid g = (BoundedGrid) this.getGrid();
		ArrayList<Location> a = getPokemon(g);
		Location go = a.get(0);
		double max = distanceFrom(go);
		for (Location l : a) {
			double temp = distanceFrom(l);
			if (temp < max) {
				temp = max;
				go = l;
			}
		}

		int direct = this.getLocation().getDirectionToward(go);
		Location l = getLocation().getAdjacentLocation(direct);
		this.setDirection(direct);
		if(g.get(l) instanceof String)
		this.attack((Pokemon) g.get(l));

	}

	public double distanceFrom(Location loc) {
		Location here = this.getLocation();
		int a = loc.getCol() - here.getCol();
		int b = loc.getRow() - here.getRow();
		double total = (a * a) + (b * b);
		double distance = Math.sqrt(total);
		return distance;
	}

	private ArrayList<Location> getPokemon(BoundedGrid g) {
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
