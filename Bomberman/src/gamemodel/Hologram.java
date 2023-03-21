package gamemodel;

import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

public class Hologram extends AbstractEntity {

	private HologramTypes hologramType;
	private boolean visible = true;

	public Hologram(int x, int y, GameLogic gameLogic, HologramTypes hologramtype, boolean visible) {
		super(x, y, gameLogic);
		this.hologramType = hologramtype;
		this.visible = visible;
	}

	public Hologram(int x, int y, GameLogic gameLogic, HologramTypes hologramtype) {
		super(x, y, gameLogic);
		this.hologramType = hologramtype;
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

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public void render(Graphics2D g, int size, int start) {
		if (visible) {
			g.drawImage(gameLogic.getImage("hologram" + hologramType.getHologramType()), //
					start + getX() * size, start + getY() * size, size, size, null);
		}
	}

}
