package gamemodel;

import java.util.List;

public class GameLogic {

	private List<PlayerIf> players;

	private List<AbstractBlock> blocks;
	
	private List<Bomb> bombs;
	
	private List<UpgradeIf> upgradeTypes;
	
	private List<UpgradeIf> upgrades;
	
	private Bomberman m;

	public GameLogic(Bomberman m) {
		this.m = m;
	}

	public void addPlayer(PlayerIf player) {
		
	}

	public void addBlock(AbstractBlock block) {

	}
	
	public void addUpgradeType(UpgradeIf upgrade) {
	    this.upgradeTypes.add(upgrade);
	}
	
	public void addUpgrade(UpgradeIf upgrade) {
	    this.upgrades.add(upgrade);
	}
	
	public void removeUpgrade(UpgradeIf upgrade) {
	    this.upgrades.remove(upgrade);
	}

	public void addBomb(Bomb bomb) {
	    this.bombs.add(bomb);
	}
	
	public void removeBomb(Bomb bomb) {
	    this.bombs.remove(bomb);
	}

	public void spawnUpgrade(int x, int y) {
	    upgradeTypes.get((int)Math.random() * upgradeTypes.size()).newUpgrade(x, y, this);
	    
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
	
	public List<UpgradeIf> getUpgradeTypes() {
	    return this.upgradeTypes;
	}
	
	public List<UpgradeIf> getUpgrades() {
	    return this.upgrades;
	}
	
}
