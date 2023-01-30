package maps;

import gamemodel.GameLogic;

public abstract class AbstractMap implements MapIf {

	protected int width;
	protected int height;
	protected Integer[][] spawnpoints;
	protected GameLogic gameLogic;

	protected AbstractMap(int width, int height, GameLogic gameLogic) {
		this.width = width;
		this.height = height;
		this.gameLogic = gameLogic;
	}

	@Override
	public Integer[][] getSpawnpoints() {
		return spawnpoints;
	}
}
