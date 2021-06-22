package gamemodel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import viewcontroller.Controller;

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
    public void move(double x, double y) {
	// TODO how to handle blocks and bombs at the edges of blocks
	if (direction == 1) {
	    for (AbstractBlock block : gameLogic.getAllBlocks()) {
		if ((block.getX() == getX()) && (block.getY() == getY())) {
		    return;
		}
	    }
	} else if (direction == 2) {
	    for (AbstractBlock block : gameLogic.getAllBlocks()) {
		if ((block.getX() == getX()) && (block.getY() == getY())) {
		    return;
		}
	    }
	} else if (direction == 3) {
	    for (AbstractBlock block : gameLogic.getAllBlocks()) {
		if ((block.getX() == getX() + 1) && (block.getY() == getY())) {
		    return;
		}
	    }
	} else if (direction == 4) {
	    for (AbstractBlock block : gameLogic.getAllBlocks()) {
		if ((block.getX() == getX()) && (block.getY() == getY() + 1)) {
		    return;
		}
	    }
	}
	this.x += x * 0.05 * speed;
	this.y += y * 0.05 * speed;
	System.out.println(this.x + ", " + this.y);
    }

    @Override
    public void placeBomb() {
	if (bombCount <= 0) {
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
	for (UpgradeIf upgrade : gameLogic.getUpgrades()) {
	    if (upgrade.getX() == getX() && upgrade.getY() == getY()) {
		upgrade.upgradePlayer(this);
		upgrade.despawn();
		return;
	    }
	}
    }

    @Override
    public void takeDamage() {
	lives--;
	if (lives == -1) {
	    // die
	    gameLogic.removePlayer(this);
	    return;
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
}
