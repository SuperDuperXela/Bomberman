package gamemodel;

import java.awt.Color;
import java.awt.Graphics2D;

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

	public void think() {
		// TODO
		if (gameLogic.getBombs()[getX()][getY()] != null || gameLogic.getBombs()[getX()][getY() + 1] != null) {
			setDirection(2);
		} else
			setDirection(0);
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
