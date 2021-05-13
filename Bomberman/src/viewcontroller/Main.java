package viewcontroller;

import gamemodel.Bomberman;

public class Main {

	public static void main(String[] args) {

		Bomberman m = new Bomberman();
		m.start();

		View frame = new View(m);
		m.addView(frame);
//		 View frame2 = new View(m);

		Controller c = new Controller(m);
		frame.addController(c);
	}

}
