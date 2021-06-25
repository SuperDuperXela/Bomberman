package gamemodel;

import java.awt.Color;
import java.awt.Graphics2D;

public class BrokenBlock extends AbstractBlock {

	public BrokenBlock(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
	}

	@Override
	public void render(Graphics2D g, int size, int start) {
		// TODO noch nicht fertig, nur zum Testen
		g.setColor(new Color(180, 80, 80));
		g.fillRect(start + getX() * size, start + getY() * size, size - 1, size - 1);
//	g.setColor(Color.WHITE);
//	g.drawString("broken", start + getX() * size + size / 4, start + getY() * size + size / 2);
	}

	@Override
	public int getX() {
		return super.getX();

	}

	@Override
	public int getY() {
		return super.getY();

	}

	public void destroy() {
		// Chances that a Upgrade spawns
		if (Math.random() >= 0.5) {
			gameLogic.spawnUpgrade(getX(), getY());
		}

		gameLogic.removeBrokenBlock(this);
	}

}
