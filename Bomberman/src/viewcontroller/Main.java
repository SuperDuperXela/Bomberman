package viewcontroller;

import gamemodel.Bomberman;
import gamemodel.GameLogic;

public class Main {

	public static void main(String[] args) {
		GameLogic gameLogic = new GameLogic(13, 11);

		Bomberman bomberman = new Bomberman(gameLogic);
		bomberman.start();
	}
}