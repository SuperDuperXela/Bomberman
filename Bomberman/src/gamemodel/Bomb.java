package gamemodel;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import sounds.SoundPlayer;

public class Bomb extends AbstractEntity implements EntityIf {

    private PlayerIf initPlayer;

    private List<Bomb> unexplodedBombs = new ArrayList<>();

    private List<PlayerIf> undamagedPlayers = new ArrayList<>();

    /**
     * @param x
     * @param y
     * @param gameLogic
     * @param player
     */
    public Bomb(int x, int y, GameLogic gameLogic, PlayerIf player) {
	super(x, y, gameLogic);
	this.initPlayer = player;
    }

    public void explode() {
	if (gameLogic.getBombs()[getX()][getY()] != this) {
	    return;
	}

	this.gameLogic.removeBomb(this);
	initPlayer.giveBackBomb();

	explodeDirection(0, 0, Directions.CENTRAL);
	explodeDirection(1, 0, Directions.RIGHT);
	explodeDirection(0, 1, Directions.DOWN);
	explodeDirection(-1, 0, Directions.LEFT);
	explodeDirection(0, -1, Directions.UP);

	gameLogic.getSoundPlayer().playExplosionSound();

	for (Bomb bomb : unexplodedBombs) {
	    bomb.explode();
	}

	for (PlayerIf undamagedPlayer : undamagedPlayers) {
	    undamagedPlayer.takeDamage();
	}

    }

    public void explodeDirection(int xChange, int yChange, Directions direction) {
	int radius = initPlayer.getBombRadius();

	if (direction == Directions.CENTRAL) {
	    radius = 0;
	    calculateExplosion(xChange, yChange, direction, 0);
	}

	for (int i = 1; i <= radius; i++) {
	    if (calculateExplosion(xChange, yChange, direction, i)) {
		return;
	    }
	}
    }

    public boolean calculateExplosion(int xChange, int yChange, Directions direction, int i) {
	if (gameLogic.getSolidBlocks()[getX() + i * xChange][getY() + i * yChange] != null) {
	    return true;
	}

	Explosion explosion = new Explosion(getX() + i * xChange, getY() + i * yChange, gameLogic, direction);
	gameLogic.addExplosion(explosion);
	explosion.remove();

	if (gameLogic.getBrokenBlocks()[getX() + i * xChange][getY() + i * yChange] != null) {
	    gameLogic.getBrokenBlocks()[getX() + i * xChange][getY() + i * yChange].destroy();
	    return true;
	}

	if (gameLogic.getBombs()[getX() + i * xChange][getY() + i * yChange] != null) {
	    unexplodedBombs.add(gameLogic.getBombs()[getX() + i * xChange][getY() + i * yChange]);
	}

	for (PlayerIf player : gameLogic.getPlayers()) {
	    if (player.getX() == getX() + i * xChange && player.getY() == getY() + i * yChange) {
		undamagedPlayers.add(player);
	    }
	}
	return false;
    }

    public void startCountdown() {
	double countDownTime = initPlayer.getBombCountDownTime();

	Timer timer = new Timer();
	timer.schedule(new TimerTask() {

	    @Override
	    public void run() {
		explode();
	    }

	}, (long) countDownTime * 1000);

    }

    @Override
    public void render(Graphics2D g, int size, int start) {
	g.drawImage(gameLogic.getImages().get("bomb"), start + getX() * size, start + getY() * size,
		start + (getX() + 1) * size, start + (getY() + 1) * size, 0, 0, 16, 16, null);
    }
}