package gamemodel;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {

	private List<PlayerIf> players = new ArrayList<>();

	private List<AbstractBlock> blocks = new ArrayList<>();

	private List<Bomb> bombs = new ArrayList<>();

	private List<UpgradeIf> upgradeTypes = new ArrayList<>();

	private List<UpgradeIf> upgrades = new ArrayList<>();

	public GameLogic() {

	}

	public void addPlayer(PlayerIf player) {
		players.add(player);
	}

	public void addBlock(AbstractBlock block) {
		blocks.add(block);
	}

	public void removeBlock(AbstractBlock block) {
		blocks.remove(block);
	}

	public void addUpgradeType(UpgradeIf upgrade) {
		upgradeTypes.add(upgrade);
	}

	public void addUpgrade(UpgradeIf upgrade) {
		upgrades.add(upgrade);
	}

	public void removeUpgrade(UpgradeIf upgrade) {
		upgrades.remove(upgrade);
	}

	public void addBomb(Bomb bomb) {
		bombs.add(bomb);
	}

	public void removeBomb(Bomb bomb) {
		bombs.remove(bomb);
	}

	public void spawnUpgrade(int x, int y) {
		upgradeTypes.get((int) (Math.random() * upgradeTypes.size())).newUpgrade(x, y, this);

	}

	public List<PlayerIf> getPlayers() {
		return players;
	}
	
	public void removePlayer(PlayerIf player) {
		players.remove(player);
	}

	public List<AbstractBlock> getBlocks() {
		return blocks;
	}

	public List<Bomb> getBombs() {
		return bombs;
	}

	public List<UpgradeIf> getUpgrades() {
		return upgrades;
	}

}
