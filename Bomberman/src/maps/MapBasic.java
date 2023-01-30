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

}
