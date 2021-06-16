package viewcontroller;

import gamemodel.BombCountUpgrade;
import gamemodel.BombRadiusUpgrade;
import gamemodel.BombTimerUpgrade;
import gamemodel.Bomberman;
import gamemodel.BrokenBlock;
import gamemodel.GameLogic;
import gamemodel.Player;
import gamemodel.SolidBlock;
import gamemodel.SpeedUpgrade;

public class Main {

	public static void main(String[] args) {

		GameLogic g = new GameLogic();

		Bomberman m = new Bomberman(g);
		m.start();

		

	}

}
