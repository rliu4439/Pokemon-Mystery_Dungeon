import info.gridworld.grid.Location;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Munchlax extends Pokemon {
	// private int level = 1;
	// private int hp = 16;
	// private int topHp=16; // max hp at current level
	// private int attack = 1;
	// private int defense = 3;
	private static Image front;
	private static Image back;
	private static Image left;
	private static Image right;
	private Image currentImage;
	private static Image attack;
	public Munchlax(boolean enemy,String[][] land) {
		super(enemy, 16, 2, 3, 1,land);
		getImages();
	}

	public void getImages(){
		if (front==null || back==null|| left==null|| right==null){
			try {
				back = ImageIO.read(new File("src/image/munchlax/munchlax-back.png"));
				front = ImageIO.read(new File("src/image/munchlax/munchlax-front.png"));
				left = ImageIO.read(new File("src/image/munchlax/munchlax-left.png"));
				right = ImageIO.read(new File("src/image/munchlax/munchlax-right.png"));
				attack = ImageIO.read(new File("src/image/munchlax/attack.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void draw(Graphics g, int col, int row) {
		// TODO Auto-generated method stub
		if(super.isAttackImg()==true){
			this.currentImage=attack;
		}else{
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
		
		g.drawImage(currentImage, col,row, 60, 60, null);	}
}
