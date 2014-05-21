import javax.swing.JOptionPane;

import info.gridworld.*;
public class DungeonDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Welcome to Pokemon Mystery Dungeon!");
		PersonalityTest test= new PersonalityTest();
		Pokemon p= test.chooseCharacter();
		Hero h= new Hero(p);
		System.out.println("done");
		
	}

}
