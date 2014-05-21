import java.util.ArrayList;


public class Hero  {
	ArrayList<Items> items= new ArrayList<>();
	int stamina=100;
	Pokemon main;
	public Hero(Pokemon p){
		main=p;
	
	}
	
	public void eatFood(Items i){
		main.setHp(main.getHp()+i.getHealthChange());
		addStamina(i.getStaminaChange());
		
	}

	private void addStamina(int staminaChange) {
		// TODO Auto-generated method stub
		stamina+=staminaChange;
		if(stamina>100){
			stamina=100;
		}
	}
}
