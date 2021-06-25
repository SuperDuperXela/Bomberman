package gamemodel;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.SwingUtilities;

import viewcontroller.Controller;
import viewcontroller.Observer;
import viewcontroller.View;

public class Bomberman extends Thread {

    private List<Observer> observer = new ArrayList<>();

    private AtomicBoolean stop = new AtomicBoolean(false);

    private GameLogic gameLogic;

    public Bomberman(GameLogic gameLogic) {
	this.gameLogic = gameLogic;
    }

    public void renderEntities(Graphics2D g, int size, int start) {
	gameLogic.renderEntities(g, size, start);
    }

    public void addView(Observer o) {
	observer.add(o);
	o.aktualisieren();
    }

    public void removeView(Observer o) {
	observer.remove(o);
    }

    private void aktualisiereAlle() {
	for (Observer o : observer) {
	    o.aktualisieren();
	}
    }

    private void refreshPlayerPosition() {
	List<Player> players = gameLogic.getPlayers();

	for (Player player : players) {
	    switch (player.getDirection()) {
	    case 1:
		player.move(-1, 0);
		break;
	    case 2:
		player.move(0, -1);
		break;
	    case 3:
		player.move(1, 0);
		break;
	    case 4:
		player.move(0, 1);
		break;
	    default:
		break;
	    }
	}
    }

    private void init() {
	Bomberman b = this;

	SwingUtilities.invokeLater(() -> {

	    View frame = new View(b);
	    addView(frame);

	    gameLogic.addUpgradeType(new BombCountUpgrade(0, 0, gameLogic));
	    gameLogic.addUpgradeType(new BombRadiusUpgrade(0, 0, gameLogic));
	    gameLogic.addUpgradeType(new BombTimerUpgrade(0, 0, gameLogic));
	    gameLogic.addUpgradeType(new SpeedUpgrade(0, 0, gameLogic));

	    Player pl = new Player(1, 1, gameLogic);
	    gameLogic.addPlayer(pl);

	    Controller c1 = new Controller(pl);
	    frame.addController(c1);

	    Player pl2 = new Player(gameLogic.getWidth() - 2.0, gameLogic.getHeight() - 2.0, gameLogic);
	    gameLogic.addPlayer(pl2);

	    Controller c2 = new Controller(pl2, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN,
		    KeyEvent.VK_CONTROL, KeyEvent.VK_NUMPAD0);
	    frame.addController(c2);

	    for (int i = 0; i < gameLogic.getWidth(); i++) {
		SolidBlock sboben = new SolidBlock(i, 0, gameLogic);
		gameLogic.addSolidBlock(sboben);
		SolidBlock sbunten = new SolidBlock(i, gameLogic.getHeight() - 1, gameLogic);
		gameLogic.addSolidBlock(sbunten);
	    }

	    for (int i = 1; i < gameLogic.getHeight() - 1; i++) {
		SolidBlock slinks = new SolidBlock(0, i, gameLogic);
		gameLogic.addSolidBlock(slinks);
		SolidBlock sbrechts = new SolidBlock(gameLogic.getWidth() - 1, i, gameLogic);
		gameLogic.addSolidBlock(sbrechts);
	    }

	    for (int i = 2; i < 11; i++) {
		for (int j = 2; j < 9; j++) {
		    if (i % 2 == 0 && j % 2 == 0) {
			SolidBlock sb = new SolidBlock(i, j, gameLogic);
			gameLogic.addSolidBlock(sb);
		    } else {
			BrokenBlock bb = new BrokenBlock(i, j, gameLogic);
			gameLogic.addBrokenBlock(bb);
		    }
		}
	    }
	});
    }

    @Override
    public void run() {
	init();

	try {
	    while (!stop.get()) {
		Thread.sleep(1000 / 60);
		aktualisiereAlle();
		refreshPlayerPosition();
	    }

	} catch (InterruptedException e) {
	    e.printStackTrace();
	    Thread.currentThread().interrupt();
	}

	System.exit(0);
    }

    /**
     * Setzt {@code stop} auf {@code true} und beendet damit {@code run()} im Model.
     * 
     * @author Alex
     */
    public void kill() {
	stop.set(true);
    }
}