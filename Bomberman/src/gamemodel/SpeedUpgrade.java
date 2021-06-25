package gamemodel;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpeedUpgrade extends AbstractUpgrade {

	public SpeedUpgrade(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
	}

	@Override
	public void render(Graphics2D g, int size, int start) {
		// TODO noch nicht fertig, nur zum Testen
		g.setColor(new Color(0, 0, 200));
		g.fillRect(start + getX() * size + size / 8, start + getY() * size + size / 8, size * 3 / 4, size * 3 / 4);
		g.setColor(new Color(0, 0, 150));
		g.drawRect(start + getX() * size + size / 8, start + getY() * size + size / 8, size * 3 / 4, size * 3 / 4);
		g.setColor(Color.WHITE);
		g.drawString("SpeedUpgr", start + getX() * size + size / 4, start + getY() * size + size / 2);
	}

	@Override
	public void despawn() {
		gameLogic.removeUpgrade(this);

	}

	@Override
	public int getX() {
		return super.getX();

	}

	@Override
	public int getY() {
		return super.getY();

	}

	@Override
	public void newUpgrade(int x, int y, GameLogic gameLogic) {
		SpeedUpgrade upgrade = new SpeedUpgrade(x, y, gameLogic);
		gameLogic.addUpgrade(upgrade);
	}

	@Override
	public void upgradePlayer(PlayerIf player) {
		double speed = player.getSpeed();

		if (speed < 2.6) {
			player.setSpeed(speed + 0.3);
		}
	}

}
