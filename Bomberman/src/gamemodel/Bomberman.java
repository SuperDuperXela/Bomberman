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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.SwingUtilities;

import blocks.BrokenBlock;
import blocks.SolidBlock;
import upgrades.BombCountUpgrade;
import upgrades.BombRadiusUpgrade;
import upgrades.BombTimerUpgrade;
import upgrades.SpeedUpgrade;
import viewcontroller.Controller;
import viewcontroller.ObserverIf;
import viewcontroller.View;

public class Bomberman extends Thread {

	private List<ObserverIf> observer = new ArrayList<>();

	private AtomicBoolean stop = new AtomicBoolean(false);

	private AtomicBoolean waitingForInit = new AtomicBoolean(true);

	private GameLogic gameLogic;

	private Properties properties = new Properties();

	private Object osync = new Object();

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

	private void startBotThinking() {
		List<PlayerIf> bots = gameLogic.getBots();

		for (PlayerIf bot : bots) {
			gameLogic.startBot((BotPlayer) bot);
		}
	}

	private void init() {
		Bomberman b = this;

		SwingUtilities.invokeLater(() -> {

			View frame = new View(b, gameLogic);
			addView(frame);

			loadImages();
			loadSounds();

			// add Upgrade Types
			gameLogic.addUpgradeType(new BombCountUpgrade(0, 0, gameLogic));
			gameLogic.addUpgradeType(new BombRadiusUpgrade(0, 0, gameLogic));
			gameLogic.addUpgradeType(new BombTimerUpgrade(0, 0, gameLogic));
			gameLogic.addUpgradeType(new SpeedUpgrade(0, 0, gameLogic));

			int playerCount = Integer.parseInt(properties.getProperty("playercount"));
			int botPlayerCount = Integer.parseInt(properties.getProperty("botcount"));

			// player 1
			if (playerCount > botPlayerCount) {
				Player pl = new Player(1, 1, gameLogic, 1);
				gameLogic.addPlayer(pl);

				int plLeft = Integer.parseInt(properties.getProperty("player1.left"));
				int plRight = Integer.parseInt(properties.getProperty("player1.right"));
				int plUp = Integer.parseInt(properties.getProperty("player1.up"));
				int plDown = Integer.parseInt(properties.getProperty("player1.down"));
				int plPickup = Integer.parseInt(properties.getProperty("player1.pickup"));
				int plPlaceBomb = Integer.parseInt(properties.getProperty("player1.placeBomb"));
				Controller c1 = new Controller(pl, plLeft, plRight, plUp, plDown, plPickup, plPlaceBomb);
				frame.addController(c1);
			} else if (botPlayerCount >= 1) {
				BotPlayer pl1 = new BotPlayer(1, 1, gameLogic, 1);
				gameLogic.addBot(pl1);
			}

			// player 2
			if (playerCount - 1 > botPlayerCount) {
				Player pl2 = new Player(gameLogic.getWidth() - 2.0, gameLogic.getHeight() - 2.0, gameLogic, 2);
				gameLogic.addPlayer(pl2);

				int pl2Left = Integer.parseInt(properties.getProperty("player2.left"));
				int pl2Right = Integer.parseInt(properties.getProperty("player2.right"));
				int pl2Up = Integer.parseInt(properties.getProperty("player2.up"));
				int pl2Down = Integer.parseInt(properties.getProperty("player2.down"));
				int pl2Pickup = Integer.parseInt(properties.getProperty("player2.pickup"));
				int pl2PlaceBomb = Integer.parseInt(properties.getProperty("player2.placeBomb"));
				Controller c2 = new Controller(pl2, pl2Left, pl2Right, pl2Up, pl2Down, pl2Pickup, pl2PlaceBomb);
				frame.addController(c2);
			} else if (botPlayerCount >= 2) {
				BotPlayer pl2 = new BotPlayer(gameLogic.getWidth() - 2.0, gameLogic.getHeight() - 2.0, gameLogic, 2);
				gameLogic.addBot(pl2);
			}

			if (playerCount > 2) {

				// player 3
				if (playerCount - 2 > botPlayerCount) {
					Player pl3 = new Player(1, gameLogic.getHeight() - 2.0, gameLogic, 3);
					gameLogic.addPlayer(pl3);

					int pl3Left = Integer.parseInt(properties.getProperty("player3.left"));
					int pl3Right = Integer.parseInt(properties.getProperty("player3.right"));
					int pl3Up = Integer.parseInt(properties.getProperty("player3.up"));
					int pl3Down = Integer.parseInt(properties.getProperty("player3.down"));
					int pl3Pickup = Integer.parseInt(properties.getProperty("player3.pickup"));
					int pl3PlaceBomb = Integer.parseInt(properties.getProperty("player3.placeBomb"));
					Controller c3 = new Controller(pl3, pl3Left, pl3Right, pl3Up, pl3Down, pl3Pickup, pl3PlaceBomb);
					frame.addController(c3);
				} else if (botPlayerCount >= 3) {
					BotPlayer pl3 = new BotPlayer(1, gameLogic.getHeight() - 2.0, gameLogic, 3);
					gameLogic.addBot(pl3);
				}

				if (playerCount > 3) {
					// player 4
					if (playerCount - 3 > botPlayerCount) {
					Player pl4 = new Player(gameLogic.getWidth() - 2.0, 1, gameLogic, 4);
					gameLogic.addPlayer(pl4);

					int pl4Left = Integer.parseInt(properties.getProperty("player4.left"));
					int pl4Right = Integer.parseInt(properties.getProperty("player4.right"));
					int pl4Up = Integer.parseInt(properties.getProperty("player4.up"));
					int pl4Down = Integer.parseInt(properties.getProperty("player4.down"));
					int pl4Pickup = Integer.parseInt(properties.getProperty("player4.pickup"));
					int pl4PlaceBomb = Integer.parseInt(properties.getProperty("player4.placeBomb"));
					Controller c4 = new Controller(pl4, pl4Left, pl4Right, pl4Up, pl4Down, pl4Pickup, pl4PlaceBomb);
					frame.addController(c4);
					} else if (botPlayerCount >= 4) {
						BotPlayer pl4 = new BotPlayer(gameLogic.getWidth() - 2.0, 1, gameLogic, 4);
						gameLogic.addBot(pl4);
					}

				}
			}



			// debug code
			/*
			 * BombCountUpgrade u1 = new BombCountUpgrade(1, 1, gameLogic);
			 * BombRadiusUpgrade u2 = new BombRadiusUpgrade(2, 1, gameLogic);
			 * BombTimerUpgrade u3 = new BombTimerUpgrade(3, 1, gameLogic); SpeedUpgrade u4
			 * = new SpeedUpgrade(1, 2, gameLogic);
			 * 
			 * gameLogic.addUpgrade(u1); gameLogic.addUpgrade(u2); gameLogic.addUpgrade(u3);
			 * gameLogic.addUpgrade(u4);
			 */
			// debug code

			createBlocks();

			gameLogic.setBomberman(this);
			waitingForInit.set(false);
			synchronized (osync) {
				osync.notifyAll();
			}
		});
	}

	private void loadImages() {

		String[] imageNames = { "bomb", "brokenBlock", "solidBlock", "explosionCentral", "explosionRight",
				"explosionLeft", "explosionUp", "explosionDown", "bombCountUpgrade", "bombTimerUpgrade", "speedUpgrade",
				"bombRadiusUpgrade", "fullHeart", "emptyHeart", "emptyBomb", "explosionIcon" };

		BufferedImage image = null;

		for (String imageName : imageNames) {
			try {
				image = ImageIO.read(new File("media/images/" + imageName + ".png"));
				gameLogic.setImages(imageName, image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void loadSounds() {
		String[] soundNames = { "death", "bombdrop", "explosion", "pickup", "start" };

		AudioInputStream inputStream = null;

		for (String soundName : soundNames) {
			try {
				inputStream = AudioSystem.getAudioInputStream(new File("media/sounds/" + soundName + ".wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(inputStream);
				gameLogic.setSound(soundName, clip);
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}
	}

	private void createBlocks() {
		// fill the board with blocks
		for (int i = 0; i < gameLogic.getWidth(); i++) {
			gameLogic.addSolidBlock(new SolidBlock(i, 0, gameLogic)); // top row
			gameLogic.addSolidBlock(new SolidBlock(i, gameLogic.getHeight() - 1, gameLogic)); // bottom row
			if (i > 3 && i < gameLogic.getWidth() - 4) {
				gameLogic.addBrokenBlock(new BrokenBlock(i, 1, gameLogic));
				gameLogic.addBrokenBlock(new BrokenBlock(i, gameLogic.getHeight() - 2, gameLogic));
			}
		}

		for (int i = 1; i < gameLogic.getHeight() - 1; i++) {
			gameLogic.addSolidBlock(new SolidBlock(0, i, gameLogic)); // left
			gameLogic.addSolidBlock(new SolidBlock(gameLogic.getWidth() - 1, i, gameLogic)); // right
			if (i > 3 && i < gameLogic.getHeight() - 4) {
				gameLogic.addBrokenBlock(new BrokenBlock(1, i, gameLogic));
				gameLogic.addBrokenBlock(new BrokenBlock(gameLogic.getWidth() - 2, i, gameLogic));
			}
		}

		for (int i = 2; i < gameLogic.getWidth() - 2; i++) {
			for (int j = 2; j < gameLogic.getHeight() - 2; j++) {
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
	}

	@Override
	public void run() {
		init();

		synchronized (osync) {
			while (waitingForInit.get()) {
				try {
					osync.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
		}

		startCountDown();

		startBotThinking();

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

	public void startCountDown() {
		for (ObserverIf o : observer) {
			o.startCountdown();
		}
	}
}