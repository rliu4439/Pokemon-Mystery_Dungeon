import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ItemHolder extends JPanel implements ActionListener {
	// responsible for showing/clicking items and showing stats
	ArrayList<Items> items;
	GamePanel panel;
	JButton apple;
	JButton Grimy;
	JButton Oran;
//	Button 
	public ItemHolder() {
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(200, 500));
		this.setLayout(new FlowLayout());
		
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		System.out.println("drawing");
		
	}

	public void setGamePanel(GamePanel p) {
		panel = p;
		drawItems();
	}

	private void drawItems() {
		// TODO Auto-generated method stub
//		g.draw3DRect(10, 10, 50, 50, true);
		items=panel.getHero().getItems();
		apple=new JButton("Eat Apple: Quantity "+items.get(0).getNumInBag());
		Grimy = new JButton("Eat Grimyfood: Quantity "+items.get(1).getNumInBag());
		Oran= new JButton("Eat Oran Berry: Quantity "+items.get(2).getNumInBag());
		this.add(apple);
		this.add(Grimy);
		this.add(Oran);
		
		apple.addActionListener(this);
		Grimy.addActionListener(this);
		Oran.addActionListener(this);
	
//		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==(apple)){
			System.out.println("apple clicked");
		}
		if(e.getSource()==(Grimy)){
			System.out.println("grimiy clicked");
		}
		if(e.getSource()==Oran){
			System.out.println("oran Clicked");
		}
	}

}
