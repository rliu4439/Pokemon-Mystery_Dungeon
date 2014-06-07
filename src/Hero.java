import java.awt.Graphics;
import java.util.ArrayList;

public class Hero extends Pokemon {
	ArrayList<Items> items = new ArrayList<>();// list containing items, in
												// order of apple,
												// grimyfood, then oranBerry,
												// increment numberinbag to add
												// item
	int stamina = 100;
	int steps = 0;
	Pokemon main;

	public Hero(Pokemon p) {
		main = p;
		main.setEnemy(false);
		items.add(new Apple());
		items.add(new GrimyFood());
		items.add(new OranBerry());

	}

	public void eatFood(Items i) {
		addHP(i.getHealthChange());
		addStamina(i.getStaminaChange());

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
		main.draw(g, row, col);
	}

}
