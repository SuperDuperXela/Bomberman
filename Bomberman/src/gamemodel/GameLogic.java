package gamemodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameLogic {

    private List<Player> players = new ArrayList<>();

    private SolidBlock[][] solidBlocks;

    private BrokenBlock[][] brokenBlocks;

    private Bomb[][] bombs;

    private List<UpgradeIf> upgradeTypes = new ArrayList<>();

    private UpgradeIf[][] upgrades;
    
    private List<EntityIf> entities = new ArrayList<>();
    
    public GameLogic(int width, int height) {
    	solidBlocks = new SolidBlock[width][height];
    	brokenBlocks = new BrokenBlock[width][height];
    	bombs = new Bomb[width][height];
    	upgrades = new UpgradeIf[width][height];
    }

    public void addPlayer(Player player) {
    	players.add(player);
    }
    
    public void removePlayer(PlayerIf player) {
    	players.remove(player);
    }

    public void addBrokenBlock(BrokenBlock block) {
    	entities.add(block);
    	brokenBlocks[block.getX()][block.getY()] = block;
    }

    public void removeBrokenBlock(BrokenBlock block) {
    	entities.remove(block);
    	brokenBlocks[block.getX()][block.getY()] = null;
    }

    public void addSolidBlock(SolidBlock block) {
    	entities.add(block);
    	solidBlocks[block.getX()][block.getY()] = block;
    }

    public void addUpgradeType(UpgradeIf upgrade) {
    	upgradeTypes.add(upgrade);
    }

    public void addUpgrade(UpgradeIf upgrade) {
    	entities.add(upgrade);
    	upgrades[upgrade.getX()][upgrade.getY()] = upgrade;
    }

    public void removeUpgrade(UpgradeIf upgrade) {
    	entities.remove(upgrade);
    	upgrades[upgrade.getX()][upgrade.getY()] = null;
    }

    public void addBomb(Bomb bomb) {
    	entities.add(bomb);
    	bombs[bomb.getX()][bomb.getY()] = bomb;
    }

    public void removeBomb(Bomb bomb) {
    	entities.remove(bomb);
    	bombs[bomb.getX()][bomb.getY()] = null;
    }

    public void spawnUpgrade(int x, int y) {
    	upgradeTypes.get((int) (Math.random() * upgradeTypes.size())).newUpgrade(x, y, this);

    }

    public List<Player> getPlayers() {
    	return players;
        }

    public BrokenBlock[][] getBrokenBlocks() {
    	return brokenBlocks;
    }

    public SolidBlock[][] getSolidBlocks() {
    	return solidBlocks;
    }

    /*public List<AbstractBlock> getAllBlocks() {
	List<AbstractBlock> blocks = new ArrayList<>();
	blocks.addAll(solidBlocks);
	blocks.addAll(brokenBlocks);

	return blocks;
    }*/

    public Bomb[][] getBombs() {
    	return bombs;
    }

    public UpgradeIf[][] getUpgrades() {
    	return upgrades;
    }
    
    public Iterator<EntityIf> getEntities() {
    	return entities.iterator();
    }
    

}
