package gamemodel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

public class Explosion extends AbstractEntity {

	Directions direction;

	/**
	 * @param x
	 * @param y
	 * @param gameLogic
	 * @param direction
	 */
	public Explosion(int x, int y, GameLogic gameLogic, Directions direction) {
		super(x, y, gameLogic);
		this.direction = direction;
	}

	public void remove() {
		Explosion newExplosion = this;
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				gameLogic.removeExplosion(newExplosion);
			}

		}, (long) 400);
	}

	@Override
	public void render(Graphics2D g, int size, int start) {

		int xOffset = 0;
		int yOffset = 0;

		switch (getDirection()) {
		case CENTRAL:
			break;

		case RIGHT:
			xOffset = -size / 6;
			break;

		case DOWN:
			yOffset = -size / 6;
			break;

		case LEFT:
			xOffset = size / 6;
			break;

		case UP:
			yOffset = size / 6;
			break;

		default:
			break;
		}

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("media/images/explosion" + getDirection() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		int xStart = start + getX() * size + xOffset - size / 12;
		int yStart = start + getY() * size + yOffset - size / 12;
		int drawSize = size * 7 / 6;
		g.drawImage(image, //
				xStart, yStart, //
				drawSize, drawSize, //
				null);
	}

	public Directions getDirection() {
		return direction;
	}

}
