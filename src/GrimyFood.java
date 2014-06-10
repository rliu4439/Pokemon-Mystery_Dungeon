import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GrimyFood extends Items {
	private static Image img;

	public GrimyFood() {
		super(-5, 10);
		try {
			if (img == null) {

				img = ImageIO.read(new File(
						"src/image/GrimyFood.png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g, int row, int col) {
		// TODO Auto-generated method stub
		g.drawImage(img, col, row, 60, 60, null);
	}
}
