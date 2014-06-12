import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ItemHolder extends JPanel implements ActionListener {
	// responsible for showing/clicking items and showing stats
	ArrayList<Items> items;
	GamePanel panel;
	JButton apple;
	JButton Grimy;
	JButton Oran;
	JLabel health;
	JLabel stamina;
	JLabel stage;
	Hero h;

	// Button
	public ItemHolder() {
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(200, 500));
		this.setLayout(new FlowLayout());
	
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.drawString("This is a test", 0, 0);

	}

	public void setGamePanel(GamePanel p) {
		panel = p;
		drawItems();
		
	}

	private void drawItems() {
		h=panel.getHero();
		health=new JLabel( "Current hp is "+h.getMain().hp );
		health.setFont(new Font("Serif", Font.PLAIN, 20));
		this.add(health);
		stamina= new JLabel("Current stamina is  "+h.stamina);
		stamina.setFont(new Font("Serif", Font.PLAIN, 20));
		this.add(stamina);
		stage = new JLabel("You are on level " + h.getStage());
		stage.setFont(new Font("Serif", Font.PLAIN, 20));
		this.add(stage);
		items = panel.getHero().getItems();
		apple = new JButton("Eat Apple: Quantity " + items.get(0).getNumInBag());
		Grimy = new JButton("Eat Grimyfood: Quantity "
				+ items.get(1).getNumInBag());
		Oran = new JButton("Eat Oran Berry: Quantity "
				+ items.get(2).getNumInBag());
		this.add(apple);
		this.add(Grimy);
		this.add(Oran);

		apple.addActionListener(this);
		Grimy.addActionListener(this);
		Oran.addActionListener(this);
		
		

	}

	public void redrawButtons() {
		apple.setText("Eat Apple: Quantity " + items.get(0).getNumInBag());
		Grimy.setText("Eat Grimyfood: Quantity " + items.get(1).getNumInBag());
		Oran.setText("Eat Oran Berry: Quantity " + items.get(2).getNumInBag());
		health.setText("Current hp is "+h.getMain().hp);
		stamina.setText("Current stamina is  "+h.stamina);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == (apple)) {
			panel.getHero().eatFood(new Apple());
			panel.requestFocusInWindow();
		}
		if (e.getSource() == (Grimy)) {
			panel.getHero().eatFood(new GrimyFood());
			panel.requestFocusInWindow();

		}
		if (e.getSource() == Oran) {
			panel.getHero().eatFood(new OranBerry());
			panel.requestFocusInWindow();

		}
		redrawButtons();
	}

}
