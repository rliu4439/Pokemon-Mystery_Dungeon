import info.gridworld.grid.Location;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mudkip extends Pokemon{
//private int level = 1;
//private int hp = 14;
//private int attack = 2;
//private int defense = 3
	Image front;
	Image back;
	Image left;
	Image right;
	Image currentImage;
	public Mudkip(boolean enemy,String last){
		
		super (enemy,14,2,3,1,last);
		try {
			back = ImageIO.read(new File("src/image/pichu/mudkip-backward.png"));
			front = ImageIO.read(new File("src/image/pichu/mudkip-forward.png"));
			left = ImageIO.read(new File("src/image/pichu/mudkip-left.png"));
			right = ImageIO.read(new File("src/image/pichu/mudkip-right.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void draw(Graphics g) {// draws pokemon according to their direction
		int direction = this.getDirection();
		if (direction == Location.EAST) {
			this.currentImage = right;
		} else if (direction == Location.WEST) {
			currentImage = left;
		} else if (direction == Location.NORTH) {
			currentImage = back;
		} else {
			currentImage = front;
		}
	}
}
