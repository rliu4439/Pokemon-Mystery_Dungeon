import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ItemHolder extends JPanel implements ActionListener {
	// responsible for showing/clicking items and showing stats
	ArrayList<Items> items;
	GamePanel panel;
	Button apple;
	Button Grimy;
	Button Oran;
//	Button 
	public ItemHolder() {
		this.setOpaque(true);
		this.setBackground(Color.BLUE);
		this.setPreferredSize(new Dimension(200, 500));
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawItems(g);
		
	}

	public void setGamePanel(GamePanel p) {
		panel = p;
	}

	private void drawItems(Graphics g) {
		// TODO Auto-generated method stub
		items=panel.getHero().getItems();
		apple=new Button("Eat Apple: Quantity "+items.get(0));
		Grimy = new Button("Eat Grimyfood: Quantity "+items.get(1));
		Oran= new Button("Eat Oran Berry: Quantity "+items.get(2));
		apple.addActionListener(this);
		Grimy.addActionListener(this);
		Oran.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(apple)){
			System.out.println(":hellefjf");
		}
	}

}
