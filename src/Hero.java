import java.util.ArrayList;


public class Hero  {
	ArrayList<Items> items= new ArrayList<>();
	int stamina=100;
	Pokemon main;
	public Hero(Pokemon p){
		main=p;
	
	}
	public boolean checkHP(){// if hp below 0, game over/restart
		if(main.getHp()<0){
			return false;
		}
			return true;
	}
	
	public void eatFood(Items i){
		addHP(i.getHealthChange());
		addStamina(i.getStaminaChange());
		
	}

	private void addHP(int healthChange) {
		// TODO Auto-generated method stub
		main.setHp(main.getHp()+healthChange);
		if(healthChange>main.topHp){
			main.setHp(main.topHp);
		}
	}

	private void addStamina(int staminaChange) {
		// TODO Auto-generated method stub
		stamina+=staminaChange;
		if(stamina>100){
			stamina=100;
		}
	}
}
