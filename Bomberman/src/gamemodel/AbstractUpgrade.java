package gamemodel;

public abstract class AbstractUpgrade extends AbstractEntity implements UpgradeIf {

	/**
	 * @param x
	 * @param y
	 * @param gameLogic
	 */
	protected AbstractUpgrade(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
	}
}