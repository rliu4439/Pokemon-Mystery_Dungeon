import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

public class tester {
	public tester() {
		BoundedGrid g = new BoundedGrid<>(10, 10);
		Scanner in = null;
		try {
			in = new Scanner(new File("test.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int row=0;row<10;row++){
			for(int col=0;col<10;col++){
				String n=in.next();
				g.put(new Location(row,col), n);
			}
		}
		
	}
}
