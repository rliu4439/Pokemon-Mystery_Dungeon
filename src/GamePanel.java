import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	Hero h;

	public GamePanel() {
		startGame();
	}

	public void startGame() {
		JOptionPane.showMessageDialog(null,
				"Welcome to Pokemon Mystery Dungeon!");
		choosePokemon();
		
	}

	private void choosePokemon() {
		// TODO Auto-generated method stub
		PersonalityTest test = new PersonalityTest();
		Pokemon p = test.chooseCharacter();
		h = new Hero(p);

	}
}
