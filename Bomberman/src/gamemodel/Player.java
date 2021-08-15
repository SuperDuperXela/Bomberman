package gamemodel;

public class Player extends AbstractPlayer implements PlayerIf {

	/**
	 * @param x
	 * @param y
	 * @param gameLogic
	 * @param number
	 */
	public Player(double x, double y, GameLogic gameLogic, int number) {
		super((int) x, (int) y, gameLogic, number);
		this.x = x;
		this.y = y;
		this.number = number;


	}

}