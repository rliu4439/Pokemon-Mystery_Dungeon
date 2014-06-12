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
	private static Image front;
	private static Image back;
	private static Image left;
	private static Image right;
	private Image currentImage;
	private Image attack;

	public Cyndaquil(boolean enemy, String[][] land) {
		super(enemy, 12, 3, 2, 1, land);
		getImages();
	}

	public void getImages() {
		if (front == null || back == null || left == null || right == null) {
			try {
				back = ImageIO.read(new File(
						"src/image/cyndaquil/cyndaquil-back.png"));
				front = ImageIO.read(new File(
						"src/image/cyndaquil/cyndaquil-front.png"));
				left = ImageIO.read(new File(
						"src/image/cyndaquil/cyndaquil-left.png"));
				right = ImageIO.read(new File(
						"src/image/cyndaquil/cyndaquil-right.png"));
				attack = ImageIO
						.read(new File("src/image/cyndaquil/attack.png"));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void draw(Graphics g, int col, int row) {
		// TODO Auto-generated method stub
//		if (super.isAttackImg() == true) {
//			this.currentImage = attack;
//		} else {
			int direction = this.getDirection();
			// System.out.println("Current is "+direction);
			if (direction == 90) {
				this.currentImage = right;
				// System.out.println("Changed to right");
			} else if (direction == 270) {
				// System.out.println("Changed to left");
				currentImage = left;
			} else if (direction == 0) {
				currentImage = back;
				// System.out.println("Changed to back");
			} else {
				currentImage = front;
				// System.out.println("Changed to front");
//			}
		}

		g.drawImage(currentImage, col, row, 60, 60, null);
	}
}
