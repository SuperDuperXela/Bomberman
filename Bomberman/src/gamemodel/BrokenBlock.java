package gamemodel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.concurrent.ThreadLocalRandom;

public class BrokenBlock extends AbstractBlock {

	public BrokenBlock(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
	}

	@Override
	public void render(Graphics2D g, int size, int start) {
		// TODO noch nicht fertig, nur zum Testen
		g.setColor(new Color(180, 80, 80));
		g.fillRect(start + getX() * size, start + getY() * size, size - 1, size - 1);
	}

	public void destroy() {
		// Chances that an upgrade spawns
		if (ThreadLocalRandom.current().nextInt(0, 100) >= 50) {
			gameLogic.spawnUpgrade(getX(), getY());
		}

		gameLogic.removeBrokenBlock(this);
	}
}