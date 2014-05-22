
public class Items {
int healthChange;
int staminaChange;
int numberInBag;
public Items(int health,int stamina){
	healthChange=health;
	staminaChange=stamina;
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

}
