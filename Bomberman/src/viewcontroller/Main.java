package viewcontroller;

import gamemodel.Bomberman;
import gamemodel.GameLogic;
import gamemodel.Player;

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
		
		test.move(0.5, 0.5);
		
		System.out.println("test: " + test.getX() + "\n" + test.getY());
	}

}
