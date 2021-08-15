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

		bombCount = 1;
		currentMaxBombs = 1;
		bombRadius = 1;
		bombCountDownTime = 3.0;
		lives = 1;
		direction = 0;
		speed = 1.0;
	}

	public synchronized void think() {
		// TODO

		while (getLives() > -1) {

			Bomb[][] allbombs = gameLogic.getBombs();
			for (Bomb[] bombrow : allbombs) {
				for (Bomb bomb : bombrow) {
					if (bomb != null // check if there is a bomb first
							&& (((getX() >= bomb.getX() - bomb.getBombRadius()
									&& getX() <= bomb.getX() + bomb.getBombRadius()) // check x-range
									&& bomb.getY() == getY()) // and check exact y
									|| (getX() == bomb.getX() // check exact x
											&& (getY() >= bomb.getY() - bomb.getBombRadius()
													&& getY() <= bomb.getY() + bomb.getBombRadius())) // and y-range
							)) {
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

	private Point searchSavePlace() {
		// TODO
		return new Point(0, 0);
	}
	
	private void moveTo(Point point) {
		//TODO
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
