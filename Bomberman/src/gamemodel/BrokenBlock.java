package gamemodel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.concurrent.ThreadLocalRandom;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BrokenBlock extends AbstractBlock {

	/**
	 * @param x
	 * @param y
	 * @param gameLogic
	 */
	public BrokenBlock(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
	}

	public void destroy() {
		// Chances that an upgrade spawns
		if (ThreadLocalRandom.current().nextInt(0, 100) >= 50) {
			gameLogic.spawnUpgrade(getX(), getY());
		}

		gameLogic.removeBrokenBlock(this);
	}

	@Override
	public void render(Graphics2D g, int size, int start) {
//	 g.setColor(new Color(180, 80, 80));
//	 g.fillRect(start + getX() * size, start + getY() * size, size - 1, size - 1);

		g.drawImage(gameLogic.getImages().get("brokenBlock"), start + getX() * size, start + getY() * size, start + (getX() + 1) * size,
				start + (getY() + 1) * size, 0, 0, 48, 48, null);

	}
}