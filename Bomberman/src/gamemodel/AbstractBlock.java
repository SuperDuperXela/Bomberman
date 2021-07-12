package gamemodel;

public abstract class AbstractBlock extends AbstractEntity {

	/**
	 * @param x
	 * @param y
	 * @param gameLogic
	 */
	protected AbstractBlock(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
	}
}