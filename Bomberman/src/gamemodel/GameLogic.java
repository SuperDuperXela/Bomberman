package gamemodel;

import java.util.List;

public class GameLogic {

	private List<PlayerIf> players;

	private List<AbstractBlock> blocks;
	
	private List<Bomb> bombs;
	
	private Bomberman m;

	public GameLogic(Bomberman m) {
		this.m = m;
		
	}

	public void addPlayer(PlayerIf player) {
		
	}

	public void addBlock(AbstractBlock block) {

	}

	public void addBomb(Bomb bomb) {
	    this.bombs.add(bomb);
	}
	
	public void removeBomb(Bomb bomb) {
	    this.bombs.remove(bomb);
	}

	public void spawnUpgrade(UpgradeIf upgrade) {

	}
	
	public List<PlayerIf> getPlayers() {
	    return this.players;
	}
	
	public List<AbstractBlock> getBlocks() {
	    return this.blocks;
	}
	
	public List<Bomb> getBombs() {
	    return this.bombs;
	}
	
	
}
