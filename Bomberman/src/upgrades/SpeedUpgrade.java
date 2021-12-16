package upgrades;

import java.awt.Graphics2D;

import gamemodel.GameLogic;
import gamemodel.PlayerIf;

public class SpeedUpgrade extends AbstractUpgrade {

	/**
	 * @param x
	 * @param y
	 * @param gameLogic
	 */
	public SpeedUpgrade(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
	}

	@Override
	public void render(Graphics2D g, int size, int start) {
		// size Modifier for drawImage | 0.18 results in a 36% smaller image /
		// 18% smaller on all sides
		int sizeMod = (int) (size * 0.18);

		g.drawImage(gameLogic.getImages().get("speedUpgrade"), start + getX() * size + sizeMod,
				start + getY() * size + sizeMod, start + (getX() + 1) * size - sizeMod,
				start + (getY() + 1) * size - sizeMod, 0, 0, 16, 16, null);
	}

	@Override
	public void despawn() {
		gameLogic.removeUpgrade(this);
	}

	@Override
	public void newUpgrade(int x, int y, GameLogic gameLogic) {
		SpeedUpgrade upgrade = new SpeedUpgrade(x, y, gameLogic);
		gameLogic.addUpgrade(upgrade);
	}

	@Override
	public void upgradePlayer(PlayerIf player) {
		double speed = player.getSpeed();
		// 1 / 1.3 / 1.6 / 1.9 / 2.2 / 2.5
		if (speed < 2.5) {
			player.setSpeed(speed + 0.3);
		}
	}
}