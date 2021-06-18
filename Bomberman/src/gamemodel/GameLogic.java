package gamemodel;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    private List<Player> players = new ArrayList<>();

    private List<SolidBlock> solidBlocks = new ArrayList<>();

    private List<BrokenBlock> brokenBlocks = new ArrayList<>();

    private List<Bomb> bombs = new ArrayList<>();

    private List<UpgradeIf> upgradeTypes = new ArrayList<>();

    private List<UpgradeIf> upgrades = new ArrayList<>();

    public GameLogic() {

    }

    public void addPlayer(Player player) {
	players.add(player);
    }

    public void addBrokenBlock(BrokenBlock block) {
	brokenBlocks.add(block);
    }

    public void removeBrokenBlock(BrokenBlock block) {
	brokenBlocks.remove(block);
    }

    public void addSolidBlock(SolidBlock block) {
	solidBlocks.add(block);
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

    public List<Player> getPlayers() {
	return players;
    }

    public void removePlayer(PlayerIf player) {
	players.remove(player);
    }

    public List<BrokenBlock> getBrokenBlocks() {
	return brokenBlocks;
    }

    public List<SolidBlock> getSolidBlocks() {
	return solidBlocks;
    }

    public List<AbstractBlock> getAllBlocks() {
	List<AbstractBlock> blocks = new ArrayList<>();
	blocks.addAll(solidBlocks);
	blocks.addAll(brokenBlocks);

	return blocks;
    }

    public List<Bomb> getBombs() {
	return bombs;
    }

    public List<UpgradeIf> getUpgrades() {
	return upgrades;
    }

}
