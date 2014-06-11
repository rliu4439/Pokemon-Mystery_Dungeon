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
		System.out.println("Attcking hero");
		int defense = p.getDefense();
		int attack = this.getAttack();
		int hp = p.getHp();
		if (attack - defense > 0) {
			p.setHp(hp - (attack - defense));
		}
		return;
	}
//	if (a.size() == 0) {
//
//		ArrayList<Location> l = new ArrayList<Location>();
//		ArrayList<Location> lo = g.getValidAdjacentLocations(getLocation());
//		for (Location loc : lo){
//			if (loc.getDirectionToward(getLocation()) % 10 == 0){
//				l.add(loc);}}
//		Collections.shuffle(l);
//		for (int c = 0; c < l.size(); c++) {
//			Location go = l.get(c);
//			if (g.isValid(go)
//					&& land[go.getRow()][go.getCol()].equals("W") == false
//					&& (g.get(go) instanceof Pokemon == false)) {
//				this.moveTo(go);
//				System.out.println("Moved from " + begin + " to "
//						+ this.getLocation());
//				this.setDirection(this.getLocation().getDirectionToward(go));
//				break;
//			}
//		}
//	} else {
	public void move(ArrayList<Pokemon> friendly) {
		BoundedGrid g = (BoundedGrid) this.getGrid();	//Move randomly if hero is not near
		boolean stop = false;
		ArrayList<Location> a = getPokemon(g);
		ArrayList<Location> moveTowards = new ArrayList<Location>();// gets the location of all
			// pokemon that are not enemy so
																				// it can attack them
		if (a.size()>0){
			if (this.distanceFrom(a.get(0)) < 2){
			ArrayList<Location>	b = g.getOccupiedAdjacentLocations(getLocation());
				for (Location l:b){
					if (g.get(l) instanceof Hero){	
						Hero h=(Hero) g.get(l);
						this.attack(h.getMain());
						System.out.println("Attacking the Hero");
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
////		System.out.println("In move");
//		BoundedGrid g = (BoundedGrid) this.getGrid();
//		// boolean stop = false;
////		Location begin = this.getLocation();
////		ArrayList<Location> a = getPokemon(g);// gets the location of all
//												// pokemon that are not enemy
////												// within a certain distance
//
//			Location moveto = friendly.get(0).getLocation();
//			int direct = this.getLocation().getDirectionToward(moveto);
//			this.setDirection(direct);
//			Location temp = this.getLocation().getAdjacentLocation(direct);
//			
//			if (g.isValid(temp)// checks that location is valid, not wall, not pokemon, then move
//					&& land[temp.getRow()][temp.getCol()].equals("W") == false
//					&& (g.get(moveto) instanceof Pokemon == false)) {
//				this.moveTo(temp);
//				return;
//			} else if (g.isValid(temp)
//					&& land[temp.getRow()][temp.getCol()].equals("W") == true
//					&& (g.get(moveto) instanceof Pokemon == false)) {
//				ArrayList<Location> lo = g.getValidAdjacentLocations(getLocation());
//				ArrayList<Location> l = new ArrayList<Location>();
//				for (Location loc : lo)
//					if (loc.getDirectionToward(getLocation()) % 10 == 0)
//						l.add(loc);
//				for (int c = 0; c < l.size(); c++) {
//					Location go = l.get(c);
//					if (g.isValid(go)
//							&& land[go.getRow()][go.getCol()].equals("W") == false
//							&& (g.get(go) instanceof Pokemon == false)) {
//						System.out.println("got here");
//						this.moveTo(go);
////						System.out.println("Moved from " + begin + " to "
////								+ this.getLocation());
//						return;
//					}
//				}
//			}
//			 else if (g.isValid(temp)
//						&& land[temp.getRow()][temp.getCol()].equals("W") == false
//						&& (g.get(temp) instanceof Pokemon == true)) {
//				 System.out.println("About to attack");
//				 
//			 }
//			 else{
//				 System.out.println(" never moved ");
//				 System.out.println(land[temp.getRow()][temp.getCol()].equals("W") == true);
//				 System.out.print(" ");
//				 boolean b=g.get(moveto) instanceof Pokemon == false;
//				 System.out.println("The move location is "+b);//+" "
////							+ land[temp.getRow()][temp.getCol()].equals("W") == true+ " "+
////							(g.get(moveto) instanceof Pokemon == false));
//			 }
			
//		}
	}

	// while (a.size() > 0 && stop == false) {// check for the closest
	// Location go = a.get(0);
	// double max = distanceFrom(go);
	// for (Location l : a) {
	// if (l.equals(this.getLocation()) == false) {
	// double temp = distanceFrom(l);// finds closest pokemon
	// if (temp < max) {
	// temp = max;
	// go = l;// checks for closest pokemon to attack
	// }
	// }
	//
	// }
	//
	// int direct = this.getLocation().getDirectionToward(go);
	// Location l = this.getLocation().getAdjacentLocation(direct);
	// if (g.isValid(l)
	// && (land[l.getRow()][l.getCol()].equals("W")) == false) {// if
	// // the
	// // new
	// // location
	// // is
	// // valid
	// // and
	// // it
	// // isn't
	// // a
	// // wall,
	// // move
	// // toward
	// // it
	// this.setDirection(direct);
	// Location current = this.getLocation();
	// if (g.get(l) == (null)) {
	// this.moveTo(l);
	//
	// stop = true;
	// System.out.println("Moved from " + begin + " to "
	// + this.getLocation());
	// } else if (g.get(l) instanceof Pokemon) {
	// // this.attack((Pokemon) g.get(l));
	// stop = true;
	// System.out
	// .println("Didn't move, attacking hero. Past loc is equal to begin? "
	// + begin.equals(this.getLocation()));
	//
	// }
	// } else {
	// System.out.println("removing location");
	// a.remove(l);// if the location is not valid, then look for a new
	// // spot
	// }
	// }
	//
	// if (stop == false) {// if you never moved, move to a random location
	// Random ran = new Random();
	// ArrayList<Location> l = g.getValidAdjacentLocations(getLocation());
	// Location current = this.getLocation();
	// int choose = ran.nextInt(l.size());
	// Location go = l.get(choose);
	// if (g.isValid(go)
	// && land[go.getRow()][go.getCol()].equals("W") == false) {
	// this.moveTo(go);
	// System.out.println("Moved from " + begin + " to "
	// + this.getLocation());

	// }else{
	// l.remove(go);
	// boolean s=false;
	// choose = ran.nextInt(l.size());
	// go = l.get(choose);
	// while(s==false){
	// if(g.isValid(go) &&
	// land[go.getRow()][go.getCol()].equals("W")==false){
	// this.moveTo(go);
	// System.out.println("Moved from "+begin+ " to "+
	// this.getLocation());
	//
	// }
	// }
	// }

	//
	// }
	// System.out.println("After moving, my location is valid? "
	// + getLocation());

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
