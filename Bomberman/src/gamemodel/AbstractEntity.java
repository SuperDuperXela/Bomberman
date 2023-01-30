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

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}
}