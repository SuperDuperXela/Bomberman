package upgrades;

import java.awt.Graphics2D;

import gamemodel.GameLogic;
import gamemodel.PlayerIf;

public class BombCountUpgrade extends AbstractUpgrade {

	/**
	 * @param x
	 * @param y
	 * @param gameLogic
	 */
	public BombCountUpgrade(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
	}

	@Override
	public void render(Graphics2D g, int size, int start) {
		// size Modifier for drawImage | 0.15 results in a 30% smaller image /
		// 15% smaller on all sides
		int sizeMod = (int) (size * 0.15);

		g.drawImage(gameLogic.getImages().get("bombCountUpgrade"), start + getX() * size + sizeMod,
				start + getY() * size + sizeMod, start + (getX() + 1) * size - sizeMod,
				start + (getY() + 1) * size - sizeMod, 0, 0, 16, 16, null);
	}

	@Override
	public void despawn() {
		gameLogic.removeUpgrade(this);
	}

	@Override
	public void newUpgrade(int x, int y, GameLogic gameLogic) {
		BombCountUpgrade upgrade = new BombCountUpgrade(x, y, gameLogic);
		gameLogic.addUpgrade(upgrade);
	}

	@Override
	public void upgradePlayer(PlayerIf player) {
		int currentMaxBombs = player.getCurrentMaxBombs();

		if (currentMaxBombs < 5) {
			player.setBombCount(player.getBombCount() + 1);
			player.setCurrentMaxBombs(currentMaxBombs + 1);
		}
	}
}