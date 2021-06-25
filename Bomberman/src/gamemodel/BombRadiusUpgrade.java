package gamemodel;

import java.awt.Color;
import java.awt.Graphics2D;

public class BombRadiusUpgrade extends AbstractUpgrade {

    public BombRadiusUpgrade(int x, int y, GameLogic gameLogic) {
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
	g.drawString("RadiusUpgr", start + getX() * size + size / 4, start + getY() * size + size / 2);
    }

    @Override
    public void despawn() {
	gameLogic.removeUpgrade(this);
    }

    @Override
    public void newUpgrade(int x, int y, GameLogic gameLogic) {
	BombRadiusUpgrade upgrade = new BombRadiusUpgrade(x, y, gameLogic);
	gameLogic.addUpgrade(upgrade);
    }

    @Override
    public void upgradePlayer(PlayerIf player) {
	int bombRadius = player.getBombRadius();

	if (bombRadius < 5) {
	    player.setBombRadius(bombRadius + 1);
	}
    }
}