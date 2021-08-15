package gamemodel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class BotPlayer extends AbstractPlayer implements PlayerIf {

	public BotPlayer(double x, double y, GameLogic gameLogic, int number) {
		super((int) x, (int) y, gameLogic, number);
		this.x = x;
		this.y = y;
		this.number = number;

	}

	public synchronized void think() {
		// TODO

		while (getLives() > -1) {

			Bomb[][] allbombs = gameLogic.getBombs();
			for (Bomb[] bombrow : allbombs) {
				for (Bomb bomb : bombrow) {
					if (bomb != null && isInBombRange(bomb, getX(), getY())) {
						moveTo(searchSavePlace());

//						System.out.println("gefahr");
					}

				}
			}

			if (gameLogic.getUpgrades()[getX()][getY()] != null) {
				pickUpUpgrade();
			}
		}
	}

	private boolean isInBombRange(Bomb bomb, int x, int y) {
		return (((x >= bomb.getX() - bomb.getBombRadius() && x <= bomb.getX() + bomb.getBombRadius()) // check x-range
				&& bomb.getY() == y) // and check exact y
				|| (x == bomb.getX() // check exact x
						&& (y >= bomb.getY() - bomb.getBombRadius() && y <= bomb.getY() + bomb.getBombRadius())) // and
																													// y-range
		);
	}

	private Point searchSavePlace() {
		// TODO
		return new Point(0, 0);
	}

	private void moveTo(Point point) {
		// TODO
	}

	@Override
	public void render(Graphics2D g, int size, int start) {
		// TODO nicht fertig halt, bzw in AbstractPLayer regeln

		g.setColor(new Color(150, 150, 150));
		g.fillOval((int) (start + x * size), (int) (start + y * size), size - 1, size - 1);

		g.setColor(new Color(222, 99, 22));
		g.drawOval((int) (start + x * size), (int) (start + y * size), size - 1, size - 1);

		g.drawString("Bot", (int) (start + x * size) + size / 2, (int) (start + y * size) + size / 2);

	}

}
