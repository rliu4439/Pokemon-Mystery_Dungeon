import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
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
//		System.out.println("Hero hp is "+friendly.get(0).hp);
//		System.out.println("top hp is "+friendly.get(0).topHp);
		System.out.println("Attcking hero");
		int defense = p.getDefense();
		int attack = this.getAttack();
		int hp = p.getHp();
		if (attack - defense > 0) {
			int attackpoints=attack-defense;
			System.out.println("attack points is "+attackpoints);
			p.setHp(hp-attackpoints);
			
		}
		return;
	}

	public void move(ArrayList<Pokemon> friendly) {
		BoundedGrid g = (BoundedGrid) this.getGrid();	//Move randomly if hero is not near
		boolean stop = false;
		ArrayList<Location> a = getPokemon(g);
		ArrayList<Location> moveTowards = new ArrayList<Location>();// gets the location of all
			// pokemon that are not enemy so
//		System.out.println("Hero hp is "+friendly.get(0).hp);
//		System.out.println("top hp is "+friendly.get(0).topHp);
// it can attack them
		if (a.size()>0){
			if (this.distanceFrom(a.get(0)) < 2){
			ArrayList<Location>	b = g.getOccupiedAdjacentLocations(getLocation());
				for (Location l:b){
					if (g.get(l) instanceof Pokemon && (((Pokemon) g.get(l)).isEnemy()==false)){	
						Pokemon h= (Pokemon) g.get(l);
						this.attack(h);
						System.out.println(this +" Attacking the Hero");
						System.out.println("Hero hp is "+h.hp);
					}
				}
			}
			else if (this.distanceFrom(a.get(0))>=2){
				if (!(land[getLocation().getAdjacentLocation(getLocation().getDirectionToward(a.get(0))).getRow()][getLocation().getAdjacentLocation(getLocation().getDirectionToward(a.get(0))).getCol()].equals("W")) && !(g.get(getLocation().getAdjacentLocation(getLocation().getDirectionToward(a.get(0)))) instanceof Pokemon)){
					moveTo(getLocation().getAdjacentLocation(getLocation().getDirectionToward(a.get(0))));
//					System.out.println("Moved Directly Towards Hero");
				}
				else if (!(land[getLocation().getAdjacentLocation(getLocation().getDirectionToward(a.get(0))+45).getRow()][getLocation().getAdjacentLocation(getLocation().getDirectionToward(a.get(0))+45).getCol()].equals("W")) && !(g.get(getLocation().getAdjacentLocation(getLocation().getDirectionToward(a.get(0))+45)) instanceof Pokemon)){
					moveTo(getLocation().getAdjacentLocation(getLocation().getDirectionToward(a.get(0))+45));
//					System.out.println("Moved Direction + 45 Towards Hero");
				}
				else if (!(land[getLocation().getAdjacentLocation(getLocation().getDirectionToward(a.get(0))-45).getRow()][getLocation().getAdjacentLocation(getLocation().getDirectionToward(a.get(0))-45).getCol()].equals("W")) && !(g.get(getLocation().getAdjacentLocation(getLocation().getDirectionToward(a.get(0))-45)) instanceof Pokemon)){
					moveTo(getLocation().getAdjacentLocation(getLocation().getDirectionToward(a.get(0))-45));
//					System.out.println("Moved Direction - 45 Towards Hero");
				}
			}
		}
		else if (a.size()==0){
			ArrayList<Location>	b = g.getEmptyAdjacentLocations(getLocation());
			for (int z = 0; z< b.size(); z++){
				if (land [b.get(z).getRow()][b.get(z).getCol()].equals("W")){
					b.remove(z);
					z--;
				}
			}
			if (b.size()>0){
				moveTo(b.get((int)(Math.random()*b.size())));
//				System.out.println("Moved Randomly");
			}
		}

	}

	
	

	public double distanceFrom(Location loc) {
//		System.out.println("In distance from");
		Location here = this.getLocation();
		int a = loc.getCol() - here.getCol();
		int b = loc.getRow() - here.getRow();
		double total = (a * a) + (b * b);
		double distance = Math.sqrt(total);
		return distance;
	}

	private ArrayList<Location> getPokemon(BoundedGrid g) {// returns all
//		System.out.println("In getPokemon"); // non-enemy Pokemon
		// TODO Auto-generated method stub
		ArrayList<Location> locs = new ArrayList<>();
		Location l = this.getLocation();
//		System.out.println("L is " + l);
		// System.out.println("current Enemy location is "+l);
		int minRow, maxRow;
		int minCol, maxCol;
		minRow = l.getRow() - 4;
		maxRow = l.getRow() + 4;
		minCol = l.getCol() - 4;
		maxCol = l.getRow() + 4;
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
