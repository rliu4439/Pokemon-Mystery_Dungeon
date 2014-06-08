import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			try {
				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e1) {

			}
		}

		JFrame jf = new JFrame("Pokemon: Mystery Dungeon 1.0");
		jf.setLayout(new BorderLayout());
		SwingUtilities.updateComponentTreeUI(jf);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		ItemHolder i = new ItemHolder();
		GamePanel g = new GamePanel(i);
		i.setGamePanel(g);
		jf.add(g,BorderLayout.LINE_START);
//		jf.add(i,BorderLayout.LINE_END);
		Info a= new Info(g);
//		jf.add(a,BorderLayout.PAGE_END);
		jf.pack();
		jf.setVisible(true);

	}

}
