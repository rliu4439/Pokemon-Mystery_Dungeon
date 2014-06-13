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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InfoScreen extends JPanel {
	// responsible for showing/clicking items and showing stats
	GamePanel panel;
	JTextArea t1, t2, t3, t4;
	int count = 0;

	public InfoScreen(GamePanel p) {
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(200, 150));
		this.setLayout(new FlowLayout());
		JTextArea label = new JTextArea("Recent events", 1, 50);
		t1 = new JTextArea(1, 60);
		t2 = new JTextArea(1, 60);
		t3 = new JTextArea(1, 60);
		t4 = new JTextArea(1, 60);
		// textArea.setLineWrap(false);
		// textArea.setColumns(50);
		this.add(label);
		this.add(t1);
		this.add(t2);
		this.add(t3);
		this.add(t4);
		panel = p;

	}

	public void writeText(String text) {
		if (t3.getText().length() > 0)
			t4.replaceRange(
					"4.  " + t3.getText().substring(4, t3.getText().length()),
					0, t4.getText().length());
		if (t2.getText().length() > 0)
			t3.replaceRange(
					"3.  " + t2.getText().substring(4, t2.getText().length()),
					0, t3.getText().length());
		if (t1.getText().length() > 0)
			t2.replaceRange(
					"2.  " + t1.getText().substring(4, t1.getText().length()),
					0, t2.getText().length());
		t1.replaceRange("1.  " + text, 0, t1.getText().length());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setFont(new Font("Serif", Font.PLAIN, 30));
		g.setColor(Color.BLACK);
		// g.drawString("POKEMON: GOTTA CATCH THEM ALL!", 200, 0);

	}
}
