package viewcontroller;

import gamemodel.Bomberman;
import gamemodel.GameLogic;

public class Main {

	public static void main(String[] args) {
		Bomberman m = new Bomberman();
		m.start();

		View frame = new View(m);
		m.addView(frame);

		Controller c = new Controller(m);
		frame.addController(c);
		
		GameLogic g = new GameLogic(m);
	}

}
