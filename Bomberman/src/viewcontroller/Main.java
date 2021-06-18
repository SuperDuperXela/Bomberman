package viewcontroller;

import gamemodel.Bomberman;
import gamemodel.GameLogic;

public class Main {

    public static void main(String[] args) {

	GameLogic g = new GameLogic();

	Bomberman m = new Bomberman(g);
	m.start();

    }

}
