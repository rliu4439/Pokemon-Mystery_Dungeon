import java.util.Scanner;

import javax.swing.JOptionPane;

public class PersonalityTest {
	private String[][] land;

	public PersonalityTest(String[][] land) {
		this.land = land;
	}

	public Pokemon chooseCharacter(GamePanel panel) {
		 int counter = 0;
		 boolean stop=false;
		 while(stop==false){
			 Object[] possibilities = { "A.Red", "B.Green", "C.Blue", "D.Black"};
				String s = (String) JOptionPane.showInputDialog(null,
						"We shall start with a Personality test \n" + "What is your favorite color?",
						"Customized Dialog", JOptionPane.PLAIN_MESSAGE,

						null, possibilities, "Personality Test");
				if ((s != null) && (s.length() > 0)) {
				   if(s.equalsIgnoreCase("A.Red")){
					   counter++;
					   stop=true;
				   }
				   else if(s.equalsIgnoreCase("B.Green")){
					   counter++;
					   counter++;
					   stop=true;
				   }
				   else if(s.equalsIgnoreCase("C.Blue")){
					   counter+=3;
					   stop=true;
				   }else{
					   counter+=4;
					   stop=true;
				   }
		 }
		
				
		   }
		 stop=false;
		 while(stop==false){
			 Object[] possibilities = { "A. Don't buy anything and save your money.","B.Ask for a bigger discount.", "C. Go in and buy "};
				String s = (String) JOptionPane.showInputDialog(null,
						"If you were at a store and saw a sale, what would you do?",
						"Customized Dialog", JOptionPane.PLAIN_MESSAGE,

						null, possibilities, "Personality Test");
				if ((s != null) && (s.length() > 0)) {
					   if(s.equalsIgnoreCase("A. Don't buy anything and save your money.")){
						   counter++;
						   stop=true;
					   }
					   else if(s.equalsIgnoreCase("B.Ask for a bigger discount.")){
						   counter++;
						   counter++;
						   stop=true;
					   }
					  else{
						   counter+=3;
						   stop=true;
					   }
			 }
		 }
		 
		 while(stop==false){
			 Object[] possibilities = { "A. Laugh at them ","B.Help them ", "C. Run away "};
				String s = (String) JOptionPane.showInputDialog(null,
						"If someone was hurt, what would you do?",
						"Customized Dialog", JOptionPane.PLAIN_MESSAGE,

						null, possibilities, "Personality Test");
				if ((s != null) && (s.length() > 0)) {
					   if(s.equalsIgnoreCase("A. Laugh at them ")){
						   counter++;
						   stop=true;
					   }
					   else if(s.equalsIgnoreCase("B.Help them ")){
						   counter++;
						   counter++;
						   stop=true;
					   }
					  else{
						   counter+=3;
						   stop=true;
					   }
			 }
		 }
			
		
		 if (counter <=4) {
		 Pokemon a = new Pichu(false, land,panel);
		 JOptionPane.showMessageDialog(null, "You would be a Pichu!");
		 return a;
		 } else if (counter >=5 && counter<=6) {
		 JOptionPane.showMessageDialog(null, "You would be a Munchlax!");
		 Pokemon a = new Munchlax(false, land,panel);
		 return a;
		 } else if (counter >=7 && counter <=8) {
		 Pokemon a = new Mudkip(false, land,panel);
		 JOptionPane.showMessageDialog(null, "You would be a Mudkip!");
		 return a;
		 } else if (counter>8) {
		 Pokemon a = new Cyndaquil(false, land,panel);
		 JOptionPane.showMessageDialog(null, "You would be a Cyndaquil!");
		 return a;
		 }

		return null;

	}
}
