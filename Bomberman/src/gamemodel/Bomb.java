package gamemodel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends AbstractEntity implements EntityIf {

    private Player initPlayer;

    private List<Bomb> unexplodedBombs = new ArrayList<>();

    private List<PlayerIf> undamagedPlayers = new ArrayList<>();

    public Bomb(int x, int y, GameLogic gameLogic, Player player) {
	super(x, y, gameLogic);
	this.initPlayer = player;
    }

    public void explode() {
	if (gameLogic.getBombs()[getX()][getY()] != this) {
	    return;
	}

	this.gameLogic.removeBomb(this);
	initPlayer.giveBackBomb();

	explodeDirection(1, 0);
	explodeDirection(0, 1);
	explodeDirection(-1, 0);
	explodeDirection(0, -1);

	for (Bomb bomb : unexplodedBombs) {
	    bomb.explode();
	}

	for (PlayerIf undamagedPlayer : undamagedPlayers) {
	    undamagedPlayer.takeDamage();
	}

    }

    public void explodeDirection(int xChange, int yChange) {
	for (int i = 0; i <= initPlayer.getBombRadius(); i++) {
	    if (gameLogic.getSolidBlocks()[getX() + i * xChange][getY() + i * yChange] != null) {
		break;
	    }

	    if (gameLogic.getBrokenBlocks()[getX() + i * xChange][getY() + i * yChange] != null) {
		gameLogic.getBrokenBlocks()[getX() + i * xChange][getY() + i * yChange].destroy();
		break;
	    }

	    if (gameLogic.getBombs()[getX() + i * xChange][getY() + i * yChange] != null) {
		unexplodedBombs.add(gameLogic.getBombs()[getX() + i * xChange][getY() + i * yChange]);
	    }

	    for (Player player : gameLogic.getPlayers()) {
		if (player.getX() == getX() + i * xChange && player.getY() == getY() + i * yChange) {
		    undamagedPlayers.add(player);
		}
	    }
	}
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
	// TODO noch nicht fertig, nur zum Testen
	g.setColor(new Color(50, 50, 220));
	g.fillOval(start + getX() * size, start + getY() * size, size - 1, size - 1);
	g.setColor(new Color(255, 136, 0));
	g.drawOval(start + getX() * size, start + getY() * size, size - 1, size - 1);
//	g.setFont(new Font("Ariel", 1, 16));
	g.drawString("Bomb", (start + getX() * size) + size / 4, (start + getY() * size) + size / 2);
    }
}