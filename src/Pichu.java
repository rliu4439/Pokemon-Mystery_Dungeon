import info.gridworld.grid.Location;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pichu extends Pokemon {
	// private int level = 1;
	// private int topHp=10;
	// private int hp = 10;
	// private int attack = 4;
	// private int defense = 1;
	private static Image front;
	private static Image back;
	private static Image left;
	private static Image right;
	private Image currentImage;

	public Pichu(boolean enemy, String[][] land) {
		super(enemy, 10, 4, 1, 1, land);
		getImages();
	}

	public void getImages() {
		if (front == null || back == null || left == null || right == null) {
			try {
				back = ImageIO
						.read(new File("src/image/pichu/pichu-back 2.png"));
				front = ImageIO.read(new File(
						"src/image/pichu/pichu-forward 2.png"));
				left = ImageIO
						.read(new File("src/image/pichu/pichu-left 2.png"));
				right = ImageIO.read(new File(
						"src/image/pichu/pichu-right 2.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		g.drawImage(currentImage, col,row, 60, 60, null);
		
	}
}
