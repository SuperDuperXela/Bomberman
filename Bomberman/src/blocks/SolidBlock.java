package blocks;

import java.awt.Graphics2D;

import gamemodel.GameLogic;

public class SolidBlock extends AbstractBlock {

	/**
	 * @param x
	 * @param y
	 * @param gameLogic
	 */
	public SolidBlock(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
	}

	@Override
	public void render(Graphics2D g, int size, int start) {
		g.drawImage(gameLogic.getImage("solidBlock"), start + getX() * size, start + getY() * size,
				start + (getX() + 1) * size, start + (getY() + 1) * size, 0, 0, 48, 48, null);
	}
}