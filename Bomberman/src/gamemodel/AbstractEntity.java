package gamemodel;

public abstract class AbstractEntity implements EntityIf {

	private int x;

	private int y;

	protected GameLogic gameLogic;

	/**
	 * @param x
	 * @param y
	 * @param gameLogic
	 */
	protected AbstractEntity(int x, int y, GameLogic gameLogic) {
		this.x = x;
		this.y = y;
		this.gameLogic = gameLogic;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}