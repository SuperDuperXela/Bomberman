package maps;

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
				for (int i = round; i < width - round; i++) {
					// Hologramm bei (i|r) und width (- i | height - r)
					// Enum hologram/ Methode direkt aufrufen
					Hologram top = new Hologram(i, round, gameLogic, HologramTypes.ALERT);
					Hologram bottom = new Hologram(width - i - 1, height - round - 1, gameLogic, HologramTypes.ALERT);
					gameLogic.addHologram(top);
					gameLogic.addHologram(bottom);

					// Warten 1000ms
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						Thread.currentThread().interrupt();
					}
					top.remove();
					bottom.remove();
					// Platzieren
					gameLogic.removeBrokenBlockAt(i, round);
					gameLogic.removeBrokenBlockAt(width - i - 1, height - round - 1);
					gameLogic.addSolidBlock(new SolidBlock(i, round, gameLogic));
					gameLogic.addSolidBlock(new SolidBlock(width - i - 1, height - round - 1, gameLogic));

					// Spieler eliminieren
				}
			}
			round += 1;
		}

	}
}
