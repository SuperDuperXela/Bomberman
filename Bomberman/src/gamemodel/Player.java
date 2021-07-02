package gamemodel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

public class Player extends AbstractEntity implements PlayerIf {

	private int bombCount = 1;

	private int currentMaxBombs = 1;

	private double x;

	private double y;

	private int animationType = 0;

	private double speed = 1.0;

	private int bombRadius = 1;

	private double bombCountDownTime = 3.0;

	private int lives = 1;

	private int direction = 0;

	public Player(double x, double y, GameLogic gameLogic) {
		super((int) x, (int) y, gameLogic);
		this.x = x;
		this.y = y;
	}

	@Override
	public void move(int x, int y) {
		double tolerance = 0.25;

		boolean blockorbomb = ((gameLogic.getSolidBlocks()[getX() + x][getY() + y] != null)
				|| (gameLogic.getBrokenBlocks()[getX() + x][getY() + y] != null)
				|| (gameLogic.getBombs()[getX() + x][getY() + y] != null));

		if (((((direction == 1) && (getX() - this.x > 0)) || ((direction == 3) && (getX() - this.x < 0)))
				&& ((blockorbomb) || ((getY() - this.y > tolerance) || (getY() - this.y < -tolerance))))
				|| ((((direction == 2) && (getY() - this.y > 0)) || ((direction == 4) && (getY() - this.y < 0)))
						&& ((blockorbomb) || ((getX() - this.x > tolerance) || (getX() - this.x < -tolerance))))) {
			return;
		}

		this.x += x * 0.05 * speed;
		this.y += y * 0.05 * speed;
	}

	@Override
	public void placeBomb() {
		if (bombCount <= 0) {
			return;
		}

		if (gameLogic.getBombs()[getX()][getY()] != null) {
			return;
		}

		bombCount--;
		Bomb bomb = new Bomb(getX(), getY(), gameLogic, this);
		gameLogic.addBomb(bomb);
		bomb.startCountdown();
	}

	@Override
	public void render(Graphics2D g, int size, int start) {
		// TODO noch nicht fertig, nur zum Testen
		g.setColor(new Color(222, 220, 220));
		g.fillOval((int) (start + x * size), (int) (start + y * size), size - 1, size - 1);
		g.setColor(new Color(222, 99, 22));
		g.drawOval((int) (start + x * size), (int) (start + y * size), size - 1, size - 1);
//		g.setFont(new Font("Ariel", 1, 16));
		g.drawString("Player", (int) (start + x * size) + size / 4, (int) (start + y * size) + size / 2);
	}

	@Override
	public int getX() {
		return (int) Math.round(x);
	}

	@Override
	public int getY() {
		return (int) Math.round(y);
	}

	@Override
	public double getBombCountDownTime() {
		return bombCountDownTime;
	}

	@Override
	public void setBombCountDownTime(double bombCountDownTime) {
		this.bombCountDownTime = bombCountDownTime;
	}

	@Override
	public int getBombRadius() {
		return bombRadius;
	}

	@Override
	public void setBombRadius(int bombRadius) {
		this.bombRadius = bombRadius;
	}

	@Override
	public int getBombCount() {
		return bombCount;
	}

	@Override
	public void setBombCount(int bombCount) {
		this.bombCount = bombCount;
	}

	@Override
	public double getSpeed() {
		return speed;
	}

	@Override
	public void setSpeed(double speed) {
		this.speed = speed;

	}

	@Override
	public int getCurrentMaxBombs() {
		return currentMaxBombs;
	}

	@Override
	public void setCurrentMaxBombs(int currentMaxBombs) {
		this.currentMaxBombs = currentMaxBombs;
	}

	@Override
	public int getLives() {
		return lives;
	}

	@Override
	public void giveBackBomb() {
		bombCount++;
	}

	@Override
	public void pickUpUpgrade() {
		if (gameLogic.getUpgrades()[getX()][getY()] != null) {
			UpgradeIf upgrade = gameLogic.getUpgrades()[getX()][getY()];
			upgrade.upgradePlayer(this);
			upgrade.despawn();
		}
	}

	@Override
	public void takeDamage() {
		lives--;

		if (lives == -1) {
			// die
			gameLogic.removePlayer(this);
		}
	}

	@Override
	public int getDirection() {
		return direction;
	}

	@Override
	public void setDirection(int direction) {
		this.direction = direction;
	}

	@Override
	public Map<String, String> getPlayerInformation() {
		Map<String, String> playerInformation = new HashMap<String, String>();
		playerInformation.put("speed", speed + "");
		playerInformation.put("bombCountCurrent", bombCount + "");
		playerInformation.put("bombCountMax", currentMaxBombs + "");
		playerInformation.put("bombRadius", bombRadius + "");
		playerInformation.put("bombTimer", bombCountDownTime + "");
		playerInformation.put("lives", lives + "");

		return playerInformation;
	}
}