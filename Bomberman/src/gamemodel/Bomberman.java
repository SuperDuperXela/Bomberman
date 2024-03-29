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

import maps.MapBasic;
import maps.MapIf;
import players.BotPlayer;
import players.Player;
import players.PlayerIf;
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

	private Integer[][] spawnpoints;

	private static final String PLAYER = "player";

	/**
	 * @param gameLogic
	 * @param properties
	 */
	public Bomberman(GameLogic gameLogic, Properties properties) {
		this.gameLogic = gameLogic;
		this.properties = properties;
		spawnpoints = new Integer[gameLogic.getWidth()][gameLogic.getHeight()];
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

			// start map and player creation
			createMap(properties.getProperty("mapName"));

			createPlayer(frame, 1);
			createPlayer(frame, 2);
			createPlayer(frame, 3);
			createPlayer(frame, 4);

			gameLogic.setBomberman(this);
			waitingForInit.set(false);
			synchronized (osync) {
				osync.notifyAll();
			}
		});
	}

	private void createMap(String mapName) {

		switch (mapName) {
		case "Basic":
			MapIf map = new MapBasic(gameLogic.getWidth(), gameLogic.getHeight(), gameLogic);
			map.createBlocks();
			spawnpoints = map.getSpawnpoints();
			map.startTimerPlayFieldReduction(180 * 1000L); // TODO: �ber Optionen �nderbar machen
			// eventuell sowas wie map start()
			break;
		case "MapNummeroZwo":
			break;
		default:
			break;
		}
	}

	private void createPlayer(View frame, int playerNumber) {

		switch (properties.getProperty(PLAYER + playerNumber + ".type")) {
		case "Human":
			Player player = new Player(spawnpoints[playerNumber - 1][0], spawnpoints[playerNumber - 1][1], gameLogic,
					playerNumber, Integer.parseInt(properties.getProperty(PLAYER + playerNumber + ".team")));

			frame.addController(
					new Controller(player, Integer.parseInt(properties.getProperty(PLAYER + playerNumber + ".left")),
							Integer.parseInt(properties.getProperty(PLAYER + playerNumber + ".right")),
							Integer.parseInt(properties.getProperty(PLAYER + playerNumber + ".up")),
							Integer.parseInt(properties.getProperty(PLAYER + playerNumber + ".down")),
							Integer.parseInt(properties.getProperty(PLAYER + playerNumber + ".pickup")),
							Integer.parseInt(properties.getProperty(PLAYER + playerNumber + ".placeBomb"))));
			gameLogic.addPlayer(player);
			break;

		case "Bot":
			gameLogic.addBot(new BotPlayer(spawnpoints[playerNumber - 1][0], spawnpoints[playerNumber - 1][1],
					gameLogic, playerNumber, Integer.parseInt(properties.getProperty(PLAYER + playerNumber + ".team")),
					properties.getProperty(PLAYER + playerNumber + ".botType")));
			break;
		default:
			break;
		}

	}

	private void loadImages() {

		String[] imageNames = { "bomb", "brokenBlock", "solidBlock", "explosionCentral", "explosionRight",
				"explosionLeft", "explosionUp", "explosionDown", "bombCountUpgrade", "bombTimerUpgrade", "speedUpgrade",
				"bombRadiusUpgrade", "fullHeart", "emptyHeart", "emptyBomb", "explosionIcon", "hologramAlert" };

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