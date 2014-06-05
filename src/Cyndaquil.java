import info.gridworld.grid.Location;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cyndaquil extends Pokemon {
	// private int level = 1;
	// private int hp = 12;
	// private int attack = 3;
	// private int defense = 2;
	Image front;
	Image back;
	Image left;
	Image right;
	Image currentImage;

	public Cyndaquil(boolean enemy, String last) {
		super(enemy, 12, 3, 2, 1, last);
		try {
			back = ImageIO.read(new File("src/image/pichu/pichu-back.png"));
			front = ImageIO.read(new File("src/image/pichu/pichu-forward.png"));
			left = ImageIO.read(new File("src/image/pichu/pichu-left.png"));
			right = ImageIO.read(new File("src/image/pichu/pichu-right.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Override
	public void draw(Graphics g, int row, int col) {
		// TODO Auto-generated method stub
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
