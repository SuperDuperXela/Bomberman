package gamemodel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

public class Explosion extends AbstractEntity {

	/**
	 * @param x
	 * @param y
	 * @param gameLogic
	 */
	public Explosion(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
	}

	public void remove() {
		Explosion newExplosion = this;
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				gameLogic.removeExplosion(newExplosion);
			}

		}, (long) 500);
	}

	@Override
	public void render(Graphics2D g, int size, int start) {
		// TODO Auto-generated method stub
		g.setColor(Color.ORANGE);
		g.fillRect(start + getX() * size, start + getY() * size, size - 1, size - 1);
	}

}
