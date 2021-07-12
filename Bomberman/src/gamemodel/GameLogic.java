package gamemodel;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class GameLogic {

	private List<PlayerIf> players = new ArrayList<>();

	private SolidBlock[][] solidBlocks;

	private BrokenBlock[][] brokenBlocks;

	private Bomb[][] bombs;

	private List<UpgradeIf> upgradeTypes = new ArrayList<>();

	private UpgradeIf[][] upgrades;

	private Set<EntityIf> entities = new HashSet<>();

	private int width;

	private int height;

	private Object osync = new Object();

	private Bomberman bomberman;

	/**
	 * @param width
	 * @param height
	 */
	public GameLogic(int width, int height) {
		this.width = width;
		this.height = height;
		solidBlocks = new SolidBlock[width][height];
		brokenBlocks = new BrokenBlock[width][height];
		bombs = new Bomb[width][height];
		upgrades = new UpgradeIf[width][height];
	}

	public void addPlayer(PlayerIf player) {
		players.add(player);
	}

	public void removePlayer(PlayerIf player) {
		synchronized (osync) {
			players.remove(player);

			if (players.size() <= 1) {
				bomberman.kill();
			}
		}
	}

	public void addBrokenBlock(BrokenBlock block) {
		entities.add(block);
		brokenBlocks[block.getX()][block.getY()] = block;
	}

	public void removeBrokenBlock(BrokenBlock block) {
		synchronized (osync) {
			entities.remove(block);
			brokenBlocks[block.getX()][block.getY()] = null;
		}
	}

	public void addSolidBlock(SolidBlock block) {
		entities.add(block);
		solidBlocks[block.getX()][block.getY()] = block;
	}

	public void addUpgradeType(UpgradeIf upgrade) {
		upgradeTypes.add(upgrade);
	}

	public void addUpgrade(UpgradeIf upgrade) {
		synchronized (osync) {
			entities.add(upgrade);
			upgrades[upgrade.getX()][upgrade.getY()] = upgrade;
		}
	}

	public void removeUpgrade(UpgradeIf upgrade) {
		synchronized (osync) {
			entities.remove(upgrade);
			upgrades[upgrade.getX()][upgrade.getY()] = null;
		}
	}

	public void addBomb(Bomb bomb) {
		synchronized (osync) {
			entities.add(bomb);
			bombs[bomb.getX()][bomb.getY()] = bomb;
		}
	}

	public void removeBomb(Bomb bomb) {
		synchronized (osync) {
			entities.remove(bomb);
			bombs[bomb.getX()][bomb.getY()] = null;
		}
	}

	public void spawnUpgrade(int x, int y) {
		upgradeTypes.get(ThreadLocalRandom.current().nextInt(upgradeTypes.size())).newUpgrade(x, y, this);
	}

	public List<PlayerIf> getPlayers() {
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
		synchronized (osync) {
			entities.add(explosion);
		}
	}

	public void removeExplosion(Explosion explosion) {
		synchronized (osync) {
			entities.remove(explosion);
		}
	}

	public void renderEntities(Graphics2D g, int size, int start) {
		synchronized (osync) {
			for (EntityIf e : entities) {
				e.render(g, size, start);
			}

			for (EntityIf e : players) {
				e.render(g, size, start);
			}
		}
	}

	public void setBomberman(Bomberman bomberman) {
		this.bomberman = bomberman;
	}
}