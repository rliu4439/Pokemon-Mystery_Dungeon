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
	protected int followSteps = 0, recoverSteps = 0;
	protected boolean enemy;
	protected int x = 0;
	private boolean attackImg = false;
	ArrayList<Pokemon> friend;
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

	public boolean isAttackImg() {
		return attackImg;
	}

	public void setAttackImg(boolean attackImg) {
		this.attackImg = attackImg;
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
		System.out.println(p+ " current hp is "+p.getHp());
		int defense = p.getDefense();
		int attack = this.getAttack();
		int hp = p.getHp();
		int attackpoints = attack - defense;
		System.out.println("attack is " + attack + " defense is " + defense);
		if (attack - defense > 0) {
			p.setHp(hp - attackpoints);
			System.out.println(p+ " was attacked, hp is now "+p.getHp());
		} else if (attack - defense <= 0) {
			p.setHp(hp - 1);
//			System.out.println("The hp after attack is " + p.getHp());
			System.out.println(p+ " was attacked, hp is now "+p.getHp());
		}
		int direct = p.getLocation().getDirectionToward(getLocation());
		System.out.println("Direct is "+direct);
		p.setDirection(direct);
		
		return;
	}

	public void move(ArrayList<Pokemon> friendly) {
		friend=friendly;
		BoundedGrid g = (BoundedGrid) this.getGrid(); // Move randomly if hero
														// is not near
		boolean stop = false;
		ArrayList<Location> a = getPokemon(g);
		ArrayList<Location> moveTowards = new ArrayList<Location>();// gets the
																	// location
																	// of all
ArrayList<Location> locs=g.getOccupiedAdjacentLocations(getLocation());
for(Location l:locs){
	if(g.get(l) instanceof Pokemon && ((Pokemon) g.get(l)).isEnemy()==false){
		Pokemon h=(Pokemon) g.get(l);
		if(this.getLocation().getDirectionToward(h.getLocation())==0||this.getLocation().getDirectionToward(h.getLocation())==90||this.getLocation().getDirectionToward(h.getLocation())==180||this.getLocation().getDirectionToward(h.getLocation())==270){
			this.attack(h);
			System.out.println(this + " Attacking the Hero");
			System.out.println("Hero hp is " + h.hp);
			this.setAttackImg(true);
			System.out.println("attack image is "+attackImg);
			return;
		}
	}
}
	
		 if (a.size() == 0 || followSteps >= 10 || recoverSteps > 0) {
			this.setAttackImg(false);
			if (followSteps >= 10) {
				recoverSteps = 5;
				followSteps = 0;
			}
			if (recoverSteps > 0)
				recoverSteps--;
			ArrayList<Location> b = g
					.getEmptyAdjacentLocations(getLocation());
			for (int z = 0; z < b.size(); z++) {
				if (land[b.get(z).getRow()][b.get(z).getCol()].equals("W")) {
					b.remove(z);
					z--;
				}
			}
			if (b.size() > 0) {
				moveTo(b.get((int) (Math.random() * b.size())));
				// System.out.println("Moved Randomly");
			}
		}

			else if (a.size() > 0){// && followSteps < 10 && recoverSteps == 0) {
				followSteps++;
				System.out.println(this +" a size is more than 0");
				if (this.distanceFrom(a.get(0)) < 2) {
					System.out.println("Distance from hero is less than 2");
					ArrayList<Location> b = g
							.getOccupiedAdjacentLocations(getLocation());
					for (Location l : b) {
						if (g.get(l) instanceof Pokemon
								&& (((Pokemon) g.get(l)).isEnemy() == false)) {
							
							Pokemon h = (Pokemon) g.get(l);
							if(this.getLocation().getDirectionToward(h.getLocation())==0||this.getLocation().getDirectionToward(h.getLocation())==90||this.getLocation().getDirectionToward(h.getLocation())==180||this.getLocation().getDirectionToward(h.getLocation())==270){
								this.attack(h);
								System.out.println(this + " Attacking the Hero");
								System.out.println("Hero hp is " + h.hp);
								this.setAttackImg(true);
							}
							
						}
					}
				} else if (this.distanceFrom(a.get(0)) >= 2) {
					this.setAttackImg(false);
					if (!(land[getLocation().getAdjacentLocation(
							getLocation().getDirectionToward(a.get(0)))
							.getRow()][getLocation().getAdjacentLocation(
							getLocation().getDirectionToward(a.get(0)))
							.getCol()].equals("W"))
							&& !(g.get(getLocation().getAdjacentLocation(
									getLocation().getDirectionToward(a.get(0)))) instanceof Pokemon)) {
						moveTo(getLocation().getAdjacentLocation(
								getLocation().getDirectionToward(a.get(0))));
						this.setDirection(getLocation().getDirectionToward(
								a.get(0)));
						// System.out.println("Moved Directly Towards Hero");
					} else if (!(land[getLocation().getAdjacentLocation(
							getLocation().getDirectionToward(a.get(0)) + 45)
							.getRow()][getLocation().getAdjacentLocation(
							getLocation().getDirectionToward(a.get(0)) + 45)
							.getCol()].equals("W"))
							&& !(g.get(getLocation()
									.getAdjacentLocation(
											getLocation().getDirectionToward(
													a.get(0)) + 45)) instanceof Pokemon)) {
						moveTo(getLocation()
								.getAdjacentLocation(
										getLocation().getDirectionToward(
												a.get(0)) + 45));
						this.setDirection(getLocation().getDirectionToward(
								a.get(0)) + 45);
						// System.out.println("Moved Direction + 45 Towards Hero");
					} else if (!(land[getLocation().getAdjacentLocation(
							getLocation().getDirectionToward(a.get(0)) - 45)
							.getRow()][getLocation().getAdjacentLocation(
							getLocation().getDirectionToward(a.get(0)) - 45)
							.getCol()].equals("W"))
							&& !(g.get(getLocation()
									.getAdjacentLocation(
											getLocation().getDirectionToward(
													a.get(0)) - 45)) instanceof Pokemon)) {
						moveTo(getLocation()
								.getAdjacentLocation(
										getLocation().getDirectionToward(
												a.get(0)) - 45));
						this.setDirection(getLocation().getDirectionToward(
								a.get(0)) - 45);
						// System.out.println("Moved Direction - 45 Towards Hero");
					}
				}
			} 

		}
	

	public double distanceFrom(Location loc) {
		// System.out.println("In distance from");
		Location here = this.getLocation();
		int a = loc.getCol() - here.getCol();
		int b = loc.getRow() - here.getRow();
		double total = (a * a) + (b * b);
		double distance = Math.sqrt(total);
		return distance;
	}

	private ArrayList<Location> getPokemon(BoundedGrid g) {// returns all
		// System.out.println("In getPokemon"); // non-enemy Pokemon
		// TODO Auto-generated method stub
		ArrayList<Location> locs = new ArrayList<>();
		Location l = this.getLocation();
		// System.out.println("L is " + l);
		// System.out.println("current Enemy location is "+l);
		int minRow, maxRow;
		int minCol, maxCol;
		minRow = l.getRow() - 4;
		maxRow = l.getRow() + 4;
		minCol = l.getCol() - 4;
		maxCol = l.getRow() + 4;
//		System.out.println("Current loc is "+l);
//		System.out.println("hero loc is "+ friend.get(0));
//		System.out.println(" x is from "+minCol+" to "+maxCol+" y is from "+minRow+" to "+maxRow);
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
