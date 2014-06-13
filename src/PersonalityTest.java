import java.util.Scanner;

import javax.swing.JOptionPane;

public class PersonalityTest {
	private String[][] land;

	public PersonalityTest(String[][] land) {
		this.land = land;
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
				gotInput = true;
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
			counter++;
			counter++;
		} else {
			gotInput = false;
		}

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
				gotInput = true;
			} else if (input.equals("b")) {
				counter++;
				gotInput = true;
			} else if (input.equals("c")) {
				counter++;
				counter++;
				gotInput = true;
			} else {
				gotInput = false;
			}
		}
		in.close();
//		if (counter == 0) {
//			Pokemon a = new Pichu(false, land);
//			JOptionPane.showMessageDialog(null, "You would be a Pichu!");
//			return a;
//		} else if (counter == 1 || counter == 4) {
//			JOptionPane.showMessageDialog(null, "You would be a Munchlax!");
//			Pokemon a = new Munchlax(false, land);
//			return a;
//		} else if (counter == 2 || counter == 3) {
//			Pokemon a = new Mudkip(false, land);
//			JOptionPane.showMessageDialog(null, "You would be a Mudkip!");
//			return a;
//		} else if (counter == 5) {
//			Pokemon a = new Cyndaquil(false, land);
//			JOptionPane.showMessageDialog(null, "You would be a Cyndaquil!");
//			return a;
//		}

		return null;

	}
}
