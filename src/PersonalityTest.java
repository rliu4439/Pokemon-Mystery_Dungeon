import java.util.Scanner;

import javax.swing.JOptionPane;

public class PersonalityTest {
	public PersonalityTest() {
	}

	public Pokemon chooseCharacter() {
		int counter = 0;
		Scanner in = new Scanner(System.in);
		boolean gotInput = true;
		System.out.println("What is your favorite color? Enter the letter");
		System.out.println("A.Red");
		System.out.println("B.Green");
		System.out.println("C.Blue");
		System.out.println("D.Black");
		String input = in.next();
		input = input.toLowerCase();
		if (input.equals("a")) {

		} else if (input.equals("b")) {
			counter++;
		} else if (input.equals("c")) {
			counter = +2;
		} else if (input.equals("d")) {
			counter = +3;
		} else {
			gotInput = false;
			System.out.println("got input has been changed to "+gotInput);
		}
		System.out.println("Got input is " + gotInput);
		while (gotInput == false) {
			System.out.println("Reenter answer");
			System.out.println("What is your favorite color? Enter the letter");
			System.out.println("A.Red");
			System.out.println("B.Green");
			System.out.println("C.Blue");
			System.out.println("D.Black");
			input = in.next();
			input = input.toLowerCase();
			if (input.equals("a")) {
				gotInput=true;
			} else if (input.equals("b")) {
				counter++;
				gotInput = true;
			} else if (input.equals("c")) {
				counter = +2;
				gotInput = true;
			} else if (input.equals("d")) {
				counter = +3;
				gotInput = true;
			} else {
				gotInput = false;
			}
		}
		gotInput = true;

		
		 System.out
		 .println("If you were at a store and saw a sale, what would you do?");
		 System.out.println("A. Don't buy anything and save your money.");
		 System.out.println("B.Ask for a bigger discount.");
		 System.out.println("C. Go in and buy ");
		 input = in.next();
		 input = input.toLowerCase();
		 if (input.equals("a")) {
		
		 } else if (input.equals("b")) {
		 counter++;
		 } else if (input.equals("c")) {
		 counter = +2;
		 } else if (input.equals("d")) {
		 counter = +3;
		 } else {
		 gotInput = false;
		 }
		 System.out.println("Got input is " + gotInput);
		
		 while (gotInput == false) {
		 System.out.println("Reenter answer");
		 System.out
		 .println("If you were at a store and saw a sale, what would you do?");
		 System.out.println("A. Don't buy anything and save your money.");
		 System.out.println("B.Ask for a bigger discount.");
		 System.out.println("C. Go in and buy ");
		 input = in.next();
		 input = input.toLowerCase();
		 if (input.equals("a")) {
		gotInput=true;
		 } else if (input.equals("b")) {
		 counter++;
		 gotInput = true;
		 } else if (input.equals("c")) {
		 counter = +2;
		 gotInput = true;
		 } else {
		 gotInput = false;
		 }
		 }
		System.out.println("counter is " + counter);
		if (counter == 0) {
			Pokemon a = new Pichu();
			JOptionPane.showMessageDialog(null, "You would be a Pichu!");
			return a;
		}
		if (counter == 1 || counter == 4) {
			Pokemon a = new Munchlax();
			JOptionPane.showMessageDialog(null, "You would be a Munchlax!");
			return a;
		}
		if (counter == 2 || counter == 3) {
			Pokemon a = new Mudkip();
			JOptionPane.showMessageDialog(null, "You would be a Mudkip!");
			return a;
		}
		if (counter == 5) {
			Pokemon a = new Cyndaquil();
			JOptionPane.showMessageDialog(null, "You would be a Cyndaquil!");
			return a;
		}

		return null;

	}
}
