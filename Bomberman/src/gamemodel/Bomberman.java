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

	// solange Maps nicht existieren
	private Integer[] spawnpointsX = new Integer[4];

	private Integer[] spawnpointsY = new Integer[4];

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

			// start player creation
			int width = gameLogic.getWidth();
			int height = gameLogic.getHeight();

			spawnpointsX[0] = 1;
			spawnpointsX[1] = width - 2;
			spawnpointsX[2] = width - 2;
			spawnpointsX[3] = 1;

			spawnpointsY[0] = 1;
			spawnpointsY[1] = height - 2;
			spawnpointsY[2] = 1;
			spawnpointsY[3] = height - 2;

			createPlayer(frame, 1);
			createPlayer(frame, 2);
			createPlayer(frame, 3);
			createPlayer(frame, 4);

			//
			int playerCount = Integer.parseInt(properties.getProperty("playercount"));
			int botPlayerCount = Integer.parseInt(properties.getProperty("botcount"));


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

	private void createPlayer(View frame, int playerNumber) {

		switch (properties.getProperty("player" + playerNumber + ".type")) {
		case "Human":
			Player player = new Player(spawnpointsX[playerNumber - 1], spawnpointsY[playerNumber - 1], gameLogic,
					playerNumber);
			gameLogic.addPlayer(player);
			frame.addController(
					new Controller(player, Integer.parseInt(properties.getProperty("player" + playerNumber + ".left")),
							Integer.parseInt(properties.getProperty("player" + playerNumber + ".right")),
							Integer.parseInt(properties.getProperty("player" + playerNumber + ".up")),
							Integer.parseInt(properties.getProperty("player" + playerNumber + ".down")),
							Integer.parseInt(properties.getProperty("player" + playerNumber + ".pickup")),
							Integer.parseInt(properties.getProperty("player" + playerNumber + ".placeBomb"))));
			break;

		case "Bot":
			gameLogic.addBot(new BotPlayer(spawnpointsX[playerNumber - 1], spawnpointsY[playerNumber - 1], gameLogic,
					playerNumber));
			break;
		default:
			break;
		}

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