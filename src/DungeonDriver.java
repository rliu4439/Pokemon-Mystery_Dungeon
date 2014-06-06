import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import info.gridworld.*;

public class DungeonDriver {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e1) {
				
			}
		}
		
		JFrame jf = new JFrame("Pokemon: Mystery Dungeon 1.0");
		SwingUtilities.updateComponentTreeUI(jf);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.add(new GamePanel());
		jf.pack();
		jf.setVisible(true);
		
	}


}
