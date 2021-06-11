package gamemodel;

import java.util.List;

public class GameLogic {

	private List<PlayerIf> players;

	private List<AbstractBlock> blocks;
	
	private Bomberman m;

	public GameLogic(Bomberman m) {
		this.m = m;
	}

	public void addPlayer(PlayerIf player) {
		
	}

	public void addBlock(AbstractBlock block) {

	}

	public void spawnBomb(Bomb bomb) {

	}

	public void spawnUpgrade(UpgradeIf upgrade) {

	}
}
