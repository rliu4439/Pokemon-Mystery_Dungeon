public abstract class Pokemon {
	public int level;
	public int topHp;
	public int hp;
	public int attack;
	public int defense;
	public int x=0;

	public Pokemon() {
		
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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
	

}
