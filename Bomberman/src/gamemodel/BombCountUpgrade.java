package gamemodel;

import java.awt.Graphics2D;

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
	// TODO noch nicht fertig, nur zum Testen
	/*
	 * g.setColor(new Color(0, 0, 200)); g.fillRect(start + getX() * size + size /
	 * 8, start + getY() * size + size / 8, size * 3 / 4, size * 3 / 4);
	 * g.setColor(new Color(0, 0, 150)); g.drawRect(start + getX() * size + size /
	 * 8, start + getY() * size + size / 8, size * 3 / 4, size * 3 / 4);
	 * g.setColor(Color.WHITE); g.drawString("CountUpgr", start + getX() * size +
	 * size / 4, start + getY() * size + size / 2);
	 */

	g.drawImage(gameLogic.getImages().get("bombcount"), start + getX() * size + (int) (size * 0.1),
			start + getY() * size + (int) (size * 0.1),
			start + (getX() + 1) * size - (int) (size * 0.1), start + (getY() + 1) * size - (int) (size * 0.1), 0, 0,
			16, 16, null);
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