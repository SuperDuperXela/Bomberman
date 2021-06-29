package gamemodel;

import java.awt.Graphics2D;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameLogic {

    private List<Player> players = new ArrayList<>();

    private SolidBlock[][] solidBlocks;

    private BrokenBlock[][] brokenBlocks;

    private Bomb[][] bombs;

    private List<UpgradeIf> upgradeTypes = new ArrayList<>();

    private UpgradeIf[][] upgrades;

    private Set<EntityIf> entities = new HashSet<>();

    private int width;

    private int height;

    private Object osync = new Object();

    private SecureRandom random = new SecureRandom();

    public GameLogic(int width, int height) {
	this.width = width;
	this.height = height;
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
	synchronized (osync) {
	    entities.remove(bomb);
	    bombs[bomb.getX()][bomb.getY()] = null;
	}
    }

    public void spawnUpgrade(int x, int y) {
	upgradeTypes.get(random.nextInt(upgradeTypes.size() - 1)).newUpgrade(x, y, this);
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

    public Bomb[][] getBombs() {
	return bombs;
    }

    public UpgradeIf[][] getUpgrades() {
	return upgrades;
    }

    public Set<EntityIf> getEntities() {
	return entities;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }
    
    public void addExplosion(Explosion explosion) {
    	entities.add(explosion);
    }
    
    public void removeExplosion(Explosion explosion) {
    	entities.remove(explosion);
    }

    public void renderEntities(Graphics2D g, int size, int start) {
	for (EntityIf e : entities) {
	    e.render(g, size, start);
	}

	for (EntityIf e : players) {
	    e.render(g, size, start);
	}
    }
}