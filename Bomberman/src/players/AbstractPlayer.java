package players;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import gamemodel.AbstractEntity;
import gamemodel.Bomb;
import gamemodel.GameLogic;
import upgrades.UpgradeIf;

public abstract class AbstractPlayer extends AbstractEntity implements PlayerIf {

	protected double x;

	protected double y;

	protected int bombCount;

	protected int currentMaxBombs;

	protected int bombRadius;

	protected double bombCountDownTime;

	protected int lives;

	protected int direction;

	protected double speed;

	protected int animationType;

	protected int playerNumber;

	protected int team;

	protected AbstractPlayer(double x, double y, GameLogic gameLogic, int playerNumber, int team) {
		super((int) x, (int) y, gameLogic);
		this.x = x;
		this.y = y;
		this.playerNumber = playerNumber;
		this.team = team;

		bombCount = 1;
		currentMaxBombs = 1;
		bombRadius = 1;
		bombCountDownTime = 3.0;
		lives = 1;
		direction = 0;
		speed = 1.0;
	}

	@Override
	public void render(Graphics2D g, int size, int start) {
		// TODO noch nicht fertig, nur zum Testen

		if (playerNumber == 1) {
			g.setColor(new Color(200, 20, 20)); // red
		} else if (playerNumber == 2) {
			g.setColor(new Color(20, 20, 200)); // blue
		} else if (playerNumber == 3) {
			g.setColor(new Color(20, 200, 20)); // green
		} else if (playerNumber == 4) {
			g.setColor(new Color(200, 200, 20)); // yellow
		} else {
			g.setColor(new Color(220, 220, 220));
		}
		g.fillOval((int) (start + x * size), (int) (start + y * size), size - 1, size - 1);
		g.setColor(new Color(222, 99, 22));
		g.drawOval((int) (start + x * size), (int) (start + y * size), size - 1, size - 1);

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
		gameLogic.getSoundPlayer().playBombDropSound();
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
	public void giveBackBomb() {
		bombCount++;
	}

	@Override
	public void pickUpUpgrade() {
		if (gameLogic.getUpgrades()[getX()][getY()] != null) {
			UpgradeIf upgrade = gameLogic.getUpgrades()[getX()][getY()];
			upgrade.upgradePlayer(this);
			upgrade.despawn();
			gameLogic.getSoundPlayer().playPickupSound();
		}
	}

	@Override
	public void takeDamage() {
		lives--;

		if (lives == -1) {
			// die
			gameLogic.getSoundPlayer().playDeathSound();
			gameLogic.removePlayer(this);
		}
	}

	@Override
	public void instantKill() {
		lives = -1;
		gameLogic.getSoundPlayer().playDeathSound();
		gameLogic.removePlayer(this);
	}

	@Override
	public synchronized int getLives() {
		return lives;
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
	public int getDirection() {
		return direction;
	}

	@Override
	public void setDirection(int direction) {
		this.direction = direction;
	}

	@Override
	public Map<String, String> getPlayerInformation() {

		Map<String, String> playerInformation = new HashMap<>();
		playerInformation.put("speed", speed + "");
		playerInformation.put("bombCountCurrent", bombCount + "");
		playerInformation.put("bombCountMax", currentMaxBombs + "");
		playerInformation.put("bombRadius", bombRadius + "");
		playerInformation.put("bombTimer", bombCountDownTime + "");
		playerInformation.put("lives", (lives + 1) + "");
		playerInformation.put("number", playerNumber + "");

		return playerInformation;
	}

	@Override
	public int getPlayerNumber() {
		return playerNumber;
	}

}
