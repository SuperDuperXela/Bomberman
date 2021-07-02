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
		List<PlayerIf> players = gameLogic.getPlayers();

		for (PlayerIf player : players) {
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
				gameLogic.addSolidBlock(new SolidBlock(i, 0, gameLogic));
				gameLogic.addSolidBlock(new SolidBlock(i, gameLogic.getHeight() - 1, gameLogic));
				if (i > 3 && i < gameLogic.getWidth() - 4) {
					gameLogic.addBrokenBlock(new BrokenBlock(i, 1, gameLogic));
					gameLogic.addBrokenBlock(new BrokenBlock(i, gameLogic.getHeight() - 2, gameLogic));
				}
			}

			for (int i = 1; i < gameLogic.getHeight() - 1; i++) {
				gameLogic.addSolidBlock(new SolidBlock(0, i, gameLogic));
				gameLogic.addSolidBlock(new SolidBlock(gameLogic.getWidth() - 1, i, gameLogic));
				if (i > 3 && i < gameLogic.getHeight() - 4) {
					gameLogic.addBrokenBlock(new BrokenBlock(1, i, gameLogic));
					gameLogic.addBrokenBlock(new BrokenBlock(gameLogic.getWidth() - 2, i, gameLogic));
				}
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

		long time = 0;
		while (!stop.get()) {
			try {
				Thread.sleep(Math.max((1000 / 60 - time), 0));
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}

			time = System.currentTimeMillis();
			aktualisiereAlle();
			refreshPlayerPosition();
			time -= System.currentTimeMillis();
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

	public List<PlayerIf> getPlayers() {
		return gameLogic.getPlayers();
	}
}