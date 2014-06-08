import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Apple extends Items {
	private static Image img;

	public Apple() {
		super(0, 50);
		try {
			if (img == null) {
				img = ImageIO.read(new File(
						"src/image/cyndaquil/cyndaquil-back.png"));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g, int row, int col) {
		// TODO Auto-generated method stub
		g.drawImage(img, row, col, 10, 10, null);
	}

}
