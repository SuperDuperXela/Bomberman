package gamemodel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import viewcontroller.Controller;
import viewcontroller.ObserverIf;
import viewcontroller.View;

public class Bomberman extends Thread {

	private List<ObserverIf> observer = new ArrayList<>();

	private AtomicBoolean stop = new AtomicBoolean(false);

	private GameLogic gameLogic;

	private Properties properties = new Properties();

	/**
	 * @param gameLogic
	 * @param properties
	 */
	public Bomberman(GameLogic gameLogic, Properties properties) {
		this.gameLogic = gameLogic;
		this.properties = properties;
	}

	public void renderEntities(Graphics2D g, int size, int start) {
		gameLogic.renderEntities(g, size, start);
	}

	public void addView(ObserverIf o) {
		observer.add(o);
		o.aktualisieren();
	}

	public void removeView(ObserverIf o) {
		observer.remove(o);
	}

	private void aktualisiereAlle() {
		for (ObserverIf o : observer) {
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

			loadImages();

			gameLogic.addUpgradeType(new BombCountUpgrade(0, 0, gameLogic));
			gameLogic.addUpgradeType(new BombRadiusUpgrade(0, 0, gameLogic));
			gameLogic.addUpgradeType(new BombTimerUpgrade(0, 0, gameLogic));
			gameLogic.addUpgradeType(new SpeedUpgrade(0, 0, gameLogic));

			Player pl = new Player(1, 1, gameLogic, 42);
			gameLogic.addPlayer(pl);

			int plLeft = Integer.parseInt(properties.getProperty("player1.left"));
			int plRight = Integer.parseInt(properties.getProperty("player1.right"));
			int plUp = Integer.parseInt(properties.getProperty("player1.up"));
			int plDown = Integer.parseInt(properties.getProperty("player1.down"));
			int plPickup = Integer.parseInt(properties.getProperty("player1.pickup"));
			int plPlaceBomb = Integer.parseInt(properties.getProperty("player1.placeBomb"));
			Controller c1 = new Controller(pl, plLeft, plRight, plUp, plDown, plPickup, plPlaceBomb);
			frame.addController(c1);

			Player pl2 = new Player(gameLogic.getWidth() - 2.0, gameLogic.getHeight() - 2.0, gameLogic, 0);
			gameLogic.addPlayer(pl2);

			int pl2Left = Integer.parseInt(properties.getProperty("player2.left"));
			int pl2Right = Integer.parseInt(properties.getProperty("player2.right"));
			int pl2Up = Integer.parseInt(properties.getProperty("player2.up"));
			int pl2Down = Integer.parseInt(properties.getProperty("player2.down"));
			int pl2Pickup = Integer.parseInt(properties.getProperty("player2.pickup"));
			int pl2PlaceBomb = Integer.parseInt(properties.getProperty("player2.placeBomb"));
			Controller c2 = new Controller(pl2, pl2Left, pl2Right, pl2Up, pl2Down, pl2Pickup, pl2PlaceBomb);
			frame.addController(c2);

			// fill the board with blocks
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
			// end of filling blocks

			// frame.startCountdown();
			gameLogic.setBomberman(this);
		});
	}

	private void loadImages() {

		BufferedImage image = null;

		try {
			image = ImageIO.read(new File("media/images/bomb.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gameLogic.setImages("bomb", image);

		try {
			image = ImageIO.read(new File("media/images/blockBroken.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gameLogic.setImages("brokenBlock", image);
		
		try {
		    image = ImageIO.read(new File("media/images/blockSolid.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		gameLogic.setImages("solidBlock", image);

		try {
			image = ImageIO.read(new File("media/images/explosionCentral.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gameLogic.setImages("explosionCentral", image);

		try {
			image = ImageIO.read(new File("media/images/explosionRight.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gameLogic.setImages("explosionRight", image);

		try {
			image = ImageIO.read(new File("media/images/explosionDown.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gameLogic.setImages("explosionDown", image);

		try {
			image = ImageIO.read(new File("media/images/explosionLeft.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gameLogic.setImages("explosionLeft", image);

		try {
			image = ImageIO.read(new File("media/images/explosionUp.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gameLogic.setImages("explosionUp", image);
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