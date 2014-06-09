import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info.gridworld.actor.Actor;


public class Stairs extends Actor {

	private static Image img;
	
	public Stairs(){
		getImage();
	}

	private void getImage() {
		// TODO Auto-generated method stub
		if(img==null){
			try {
				img = ImageIO.read(new File("src/image/Stairs.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	public void draw(Graphics g,int row,int col){
		
	}
}
