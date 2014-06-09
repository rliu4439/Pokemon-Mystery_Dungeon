import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.sun.org.apache.xpath.internal.operations.Or;

public class Hero extends Pokemon {
	ArrayList<Items> items = new ArrayList<>();// list containing items, in
												// order of apple,
												// grimyfood, then oranBerry,
												// increment numberinbag to add
												// item
	int stamina = 100;
	int steps = 0;
	Pokemon main;
	BoundedGrid grid;
	String[][] land;

	public Hero(Pokemon p, String[][]la) {
		main = p;
		land = this.getLand();
		main.setEnemy(false);
		items.add(new Apple());
		items.add(new GrimyFood());
		items.add(new OranBerry());
		this.land=la;
	}
	public void setGrid(BoundedGrid g){
		grid=g;
	}

	public void eatFood(Items i) {
		Items a = null;
		if (i instanceof Apple && items.get(0).getNumInBag() > 0) {
			System.out.println("This was an apple");
			a = (Apple) i;
			items.get(0).remove();

		} else if (i instanceof GrimyFood && items.get(1).getNumInBag() > 0) {
			a = (GrimyFood) i;
			items.get(1).remove();
		} else if (i instanceof OranBerry && items.get(2).getNumInBag() > 0) {
			a = (OranBerry) i;
			items.get(2).remove();
		} else {
			return;
		}
		addHP(a.getHealthChange());
		addStamina(a.getStaminaChange());
		System.out.println("There are " + items.get(0).getNumInBag()
				+ " apples left");

	}

	public ArrayList<Items> getItems() {
		return items;
	}

	private void addHP(int healthChange) {
		// TODO Auto-generated method stub
		main.setHp(main.getHp() + healthChange);
		if (healthChange > main.topHp) {
			main.setHp(main.topHp);
		}
	}

	public void checkStatus() {
		if (hp <= 0) {
			System.out.println("Game over");
		} else if (stamina < 0) {
			hp--;
		} else if (stamina > 0 && hp < main.topHp) {
			hp++;
		}
		Location current=main.getLocation();
		int row=current.getRow();
		int col= current.getCol();
		if(land[row][col].equals("S")){// what to do if stairs
			
		}
		
	}

	private void addStamina(int staminaChange) {
		// TODO Auto-generated method stub
		stamina += staminaChange;
		if (stamina > 100) {
			stamina = 100;
		}
	}

	public void staminaLoss() {
		stamina--;
	}

	@Override
	public void draw(Graphics g, int row, int col) {
		// TODO Auto-generated method stub
		System.out.println(main.getDirection());
		main.draw(g, row, col);

		System.out.println("Direction is "+main.getDirection());
//		g.setColor(Color.MAGENTA);
//		g.draw3DRect(col, row, 10, 10, false);
	}

	public void moveRight() {
		// TODO Auto-generated method stub
		main.setDirection(90);
		System.out.println("Direction is now "+main.getDirection());
		Location current = main.getLocation();
		int row=current.getRow()+1;
		int col=current.getCol();
		Object a=grid.get(new Location(row, col));
		System.out.println("Current Location is "+main.getLocation());
		if (land[row][col].equals("W") == false) {
			System.out.println(a);
			if (a==null || a instanceof Items) {
				if(a instanceof Items){
					if (a instanceof Apple){
						items.get(0).add();
					}
					else if(a instanceof GrimyFood){
						items.get(1).add();
					}
					else if( a instanceof OranBerry){
						items.get(2).add();
					}
				}
				main.moveTo(new Location(row,col));
				System.out.println("Now the current location is "+ main.getLocation());
			}
		}
		checkStatus();
	}

	public void moveLeft() {
		// TODO Auto-generated method stub
		main.setDirection(270);
		System.out.println("Direction is now "+main.getDirection());
		Location current = main.getLocation();
		int row=current.getRow()-1;
		int col=current.getCol();
		Object a=grid.get(new Location(row, col));
		System.out.println("Current Location is "+main.getLocation());
		if (land[row][col].equals("W") == false) {
			System.out.println(a);
			if (a==null || a instanceof Items) {
				if(a instanceof Items){
					if (a instanceof Apple){
						items.get(0).add();
					}
					else if(a instanceof GrimyFood){
						items.get(1).add();
					}
					else if( a instanceof OranBerry){
						items.get(2).add();
					}
				}
				main.moveTo(new Location(row,col));
				System.out.println("Now the current location is "+ main.getLocation());
			}
		}
		checkStatus();

		}

	

	public void moveUp() {
		main.setDirection(0);
		System.out.println("Direction is now "+main.getDirection());

		Location current = main.getLocation();
		int row=current.getRow();
		int col=current.getCol()-1;
		Object a=grid.get(new Location(row, col));
		System.out.println("Current Location is "+main.getLocation());
		if (land[row][col].equals("W") == false) {
			System.out.println(a);
			if (a==null || a instanceof Items) {
				if(a instanceof Items){
					if (a instanceof Apple){
						items.get(0).add();
					}
					else if(a instanceof GrimyFood){
						items.get(1).add();
					}
					else if( a instanceof OranBerry){
						items.get(2).add();
					}
				}
				main.moveTo(new Location(row,col));
				System.out.println("Now the current location is "+ main.getLocation());
			}
		}
		checkStatus();


	}

	public void moveBack() {
		// TODO Auto-generated method stub
//		for (int row = 0; row < land.length; row++) {
//			System.out.print(row);
//			for (int col = 0; col < land[0].length; col++) {
//				System.out.print(land[row][col]);
//			}
//			System.out.println();
//		}
		main.setDirection(180);
		System.out.println("Direction is now "+main.getDirection());

		Location current = main.getLocation();
//		System.out.println("Current Location is "+current.getRow()+" "+current.getCol());
		int row=current.getRow();
		int col=current.getCol()+1;
		Object a=grid.get(new Location(row, col));
		System.out.println("Current Location is "+main.getLocation());
		if (land[row][col].equals("W") == false) {
			System.out.println(a);
			if (a==null || a instanceof Items) {
				if(a instanceof Items){
					if (a instanceof Apple){
						items.get(0).add();
					}
					else if(a instanceof GrimyFood){
						items.get(1).add();
					}
					else if( a instanceof OranBerry){
						items.get(2).add();
					}
				}
				 main.moveTo(new Location(row,col));
				System.out.println("Now the current location is "+ main.getLocation());
			}
		}
		checkStatus();


	}
}
