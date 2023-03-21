package maps;

import java.util.Timer;
import java.util.TimerTask;

import blocks.SolidBlock;
import gamemodel.GameLogic;
import gamemodel.Hologram;
import gamemodel.HologramTypes;

public abstract class AbstractMap implements MapIf {

	protected int width;
	protected int height;
	protected Integer[][] spawnpoints;
	protected GameLogic gameLogic;
	protected boolean reducingPlayFieldSize = false;

	protected AbstractMap(int width, int height, GameLogic gameLogic) {
		this.width = width;
		this.height = height;
		this.gameLogic = gameLogic;
	}

	@Override
	public Integer[][] getSpawnpoints() {
		return spawnpoints;
	}

	@Override
	public void startTimerPlayFieldReduction(long delayMilliSeconds) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				startPlayFieldSizeReduction();
			}

		}, delayMilliSeconds);
	}

	@Override
	public void startPlayFieldSizeReduction() {
		if (!reducingPlayFieldSize) {
			Runnable runnable = () -> reducePlayFieldSize();
			new Thread(runnable).start();
			reducingPlayFieldSize = true;
		}
	}

	protected void reducePlayFieldSize() {
		boolean placeTopBottom = true;
		boolean placeRightLeft = true;
		int round = 1;

		while (placeTopBottom || placeRightLeft) {
			placeTopBottom = (height - 2 * round > 5);
			placeRightLeft = (width - 2 * round > 7);

			if (placeTopBottom) {
				reducePlayFieldSizeTopBottom(round);
			}
			if (placeRightLeft) {
				reducePlayFieldSizeRightLeft(round);
			}

			round += 1;
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}

	}

	private void reducePlayFieldSizeTopBottom(int round) {
		for (int i = round; i < width - round; i++) {
			// hologramm von oben links nach oben rechts
			if (gameLogic.getSolidBlockAt(i, round) == null) {
				Hologram top = new Hologram(i, round, gameLogic, HologramTypes.ALERT);
				gameLogic.addHologram(top);
				top.removeDelay(2000);
			}

			// hologramm von unten rechts nach unten links
			if (gameLogic.getSolidBlockAt(width - i - 1, height - round - 1) == null) {
				Hologram bot = new Hologram(width - i - 1, height - round - 1, gameLogic, HologramTypes.ALERT);
				gameLogic.addHologram(bot);
				bot.removeDelay(2000);
			}

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}

			gameLogic.removeBrokenBlockAt(i, round);
			gameLogic.removeBrokenBlockAt(width - i - 1, height - round - 1);
			gameLogic.addSolidBlock(new SolidBlock(i, round, gameLogic));
			gameLogic.addSolidBlock(new SolidBlock(width - i - 1, height - round - 1, gameLogic));

			// Spieler eliminieren
		}
	}

	private void reducePlayFieldSizeRightLeft(int round) {
		for (int i = round; i < height - round; i++) {
			// Hologramm von oben rechts nach unten rechts
			if (gameLogic.getSolidBlockAt(width - round - 1, i) == null) {
				Hologram right = new Hologram(width - round - 1, i, gameLogic, HologramTypes.ALERT);
				gameLogic.addHologram(right);
				right.removeDelay(2000);
			}

			// Hologramm von unten links nach oben links
			if (gameLogic.getSolidBlockAt(round, height - i - 1) == null) {
				Hologram left = new Hologram(round, height - i - 1, gameLogic, HologramTypes.ALERT);
				gameLogic.addHologram(left);
				left.removeDelay(2000);
			}

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
			// Platzieren
			gameLogic.removeBrokenBlockAt(width - round - 1, i);
			gameLogic.removeBrokenBlockAt(round, height - i - 1);
			gameLogic.addSolidBlock(new SolidBlock(width - round - 1, i, gameLogic));
			gameLogic.addSolidBlock(new SolidBlock(round, height - i - 1, gameLogic));

			// Spieler eliminieren
		}
	}
}
