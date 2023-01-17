package gamemodel;

public class Player extends AbstractPlayer implements PlayerIf {

	/**
	 * @param x
	 * @param y
	 * @param gameLogic
	 * @param number
	 */
	public Player(double x, double y, GameLogic gameLogic, int playerNumber, int team) {
		super((int) x, (int) y, gameLogic, playerNumber, team);

	}

}