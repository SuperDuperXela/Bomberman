package maps;

import blocks.BrokenBlock;
import blocks.SolidBlock;
import gamemodel.GameLogic;

public class MapBasic extends AbstractMap {

	Integer[][] spawnpointsBasic = { { 1, 1 }, { width - 2, height - 2 }, { width - 2, 1 }, { 1, height - 2 } };

	public MapBasic(int width, int height, GameLogic gameLogic) {
		super(width, height, gameLogic);
		spawnpoints = spawnpointsBasic.clone();
	}

	@Override
	public void createBlocks() {
		int width = gameLogic.getWidth();
		int height = gameLogic.getHeight();
		// fill the board with blocks
		for (int i = 0; i < width; i++) {
			gameLogic.addSolidBlock(new SolidBlock(i, 0, gameLogic)); // top row
			gameLogic.addSolidBlock(new SolidBlock(i, height - 1, gameLogic)); // bottom row
			if (i > 3 && i < width - 4) {
				gameLogic.addBrokenBlock(new BrokenBlock(i, 1, gameLogic));
				gameLogic.addBrokenBlock(new BrokenBlock(i, height - 2, gameLogic));
			}
		}

		for (int i = 1; i < height - 1; i++) {
			gameLogic.addSolidBlock(new SolidBlock(0, i, gameLogic)); // left
			gameLogic.addSolidBlock(new SolidBlock(width - 1, i, gameLogic)); // right
			if (i > 3 && i < height - 4) {
				gameLogic.addBrokenBlock(new BrokenBlock(1, i, gameLogic));
				gameLogic.addBrokenBlock(new BrokenBlock(width - 2, i, gameLogic));
			}
		}

		for (int i = 2; i < width - 2; i++) {
			for (int j = 2; j < height - 2; j++) {
				if (i % 2 == 0 && j % 2 == 0) {
					gameLogic.addSolidBlock(new SolidBlock(i, j, gameLogic));
				} else {
					gameLogic.addBrokenBlock(new BrokenBlock(i, j, gameLogic));
				}
			}
		}
		// end of filling blocks
	}

}
