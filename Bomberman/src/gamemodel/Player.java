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

		bombCount = 1;
		currentMaxBombs = 1;
		bombRadius = 1;
		bombCountDownTime = 3.0;
		lives = 1;
		direction = 0;
		speed = 1.0;

	}

}