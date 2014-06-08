import info.gridworld.actor.Actor;

import java.awt.Graphics;


public abstract class Items extends Actor {
	int healthChange;
	int staminaChange;
	int numberInBag;
	public Items(int health,int stamina){
		healthChange=health;
		staminaChange=stamina;
		numberInBag=0;
	}
	public int getHealthChange() {
		return healthChange;
	}
	public int getStaminaChange() {
		return staminaChange;
	}
	
	public void add(){// adds another item to bag
		numberInBag++;
	}
	public void remove(){
		numberInBag--;
	}
	public int getNumInBag(){
		return numberInBag;
	}
	public abstract void draw(Graphics g, int row, int col);

}
