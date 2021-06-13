package viewcontroller;

import gamemodel.Bomberman;
import gamemodel.GameLogic;
import gamemodel.Player;
import gamemodel.SolidBlock;

public class Main {

	public static void main(String[] args) {
		Bomberman m = new Bomberman();
		m.start();

		View frame = new View(m);
		m.addView(frame);

		Controller c = new Controller(m);
		frame.addController(c);
		
		GameLogic g = new GameLogic(m);
		
		Player test = new Player(c, 5, 5, g);
		
		System.out.println(test.getX() + ", " + test.getY());
		
		test.move(1.5, 1.5);
		
		System.out.println(test.getX() + ", " + test.getY());
		
		test.move(1.5, 1.5);
		
		System.out.println(test.getX() + ", " + test.getY());
	}

}
