package gamemodel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

public class Hologram extends AbstractEntity {

	private HologramTypes hologramtype;

	public Hologram(int x, int y, GameLogic gameLogic, HologramTypes hologramtype) {
		super(x, y, gameLogic);
		this.hologramtype = hologramtype;
	}

	public void remove() {
		gameLogic.removeHologram(this);
	}

	public void removeDelay(long delayMilliSeconds) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				remove();
			}

		}, delayMilliSeconds);
	}

	@Override
	public void render(Graphics2D g, int size, int start) {
		switch (hologramtype) {
		case ALERT:
			g.setColor(new Color(200, 0, 0)); // red
			break;
		case CAUTION:
			g.setColor(new Color(224, 179, 31)); // orange
			break;
		default:
			break;
		}
		g.fillRect(start + getX() * size + size / 6, start + getY() * size + size / 6, 4 * size / 6, 4 * size / 6);
	}

}
