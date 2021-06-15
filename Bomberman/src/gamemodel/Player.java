package gamemodel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import viewcontroller.Controller;

public class Player extends AbstractEntity implements PlayerIf {

	private int bombCount = 1;

	private double x;

	private double y;

	private int animationType = 0;

	private double speed = 1.0;

	private int bombRadius = 1;

	private double bombCountDownTime = 3.0;
	
	private int lives = 1;

	private Controller controller;

	private GameLogic gameLogic;

	public Player(Controller controller, double x, double y, GameLogic gameLogic) {
		super((int) x, (int) y, gameLogic);
		this.gameLogic = gameLogic;
		this.controller = controller;
		this.x = x;
		this.y = y;
	}

	@Override
	public void move(double x, double y) {
		//TODO how to handle speed
		this.x += x;
		this.y += y;
	}

	/**
	 * bombCount-- timer ablaufen lassen bombCount++
	 */
	@Override
	public void placeBomb() {
		if(bombCount <= 0) {
			return;
		}
		bombCount--;
		Bomb bomb = new Bomb(getX(), getY(), gameLogic, this);
		gameLogic.addBomb(bomb);
		bomb.startCountdown();
	}

	@Override
	public void render(Graphics2D g, int size, int start) {
		// TODO noch nicht fertig, nur zum testen
		g.setColor(new Color(222, 220, 220));
		g.fillOval((int) (start + x * size), (int) (start + y * size), size - 1, size - 1);
		g.setColor(new Color(222, 99, 22));
		g.drawOval((int) (start + x * size), (int) (start + y * size), size - 1, size - 1);
		g.setFont(new Font("Ariel", 1, 16));
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
	
	public double getBombCountDownTime() {
		return bombCountDownTime;
	}
	
	public void setBombCountDownTime(double bombCountDownTime) {
		this.bombCountDownTime = bombCountDownTime;
	}
	
	public int getBombRadius() {
		return bombRadius;
	}
	
	public void setBombRadius(int bombRadius) {
		this.bombRadius = bombRadius;
	}
	
	public int getBombCount() {
		return bombCount;
	}
	
	public void setBombCount(int bombCount) {
		this.bombCount = bombCount;
	}
	
	public void giveBackBomb() {
		bombCount++;
	}

	public void pickUpUpgrade() {
		for (UpgradeIf upgrade : gameLogic.getUpgrades()) {
			// TODO is player on same field as upgrade
			// yes -> which upgrade
			switch (upgrade.getClass().getName()) {
				case "gamemodel.BombCountUpgrade":
					upgradeBombCount();
					break;
				case "gamemodel.BombRadiusUpgrade":
					upgradeRadius();
					break;
				case "gamemodel.BombTimerUpgrade":
					upgradeTimer();
					break;
				case "gamemodel.SpeedUpgrade":
					upgradeSpeed();
					break;
				default:
					break;
			}
		}
	}
	
	public void takeDamage() {
		if (lives == 0) {
			//die
			return;
		}
		lives--;
	}
	
	private void upgradeBombCount() {
		if(bombCount < 5) {
			bombCount++;
		}
	}
	
	private void upgradeRadius() {
		if(bombRadius < 5) {
			bombRadius++;
		}
	}
	
	private void upgradeTimer() {
		if(bombCountDownTime > 2) {
			bombCountDownTime -= 0.2;
		}
	}
	
	private void upgradeSpeed() {
		if(speed < 2) {
			speed += 0.2;
		}
	}
}
