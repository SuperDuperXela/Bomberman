package gamemodel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends AbstractEntity implements EntityIf {

	private Player player;

	public Bomb(int x, int y, GameLogic gameLogic, Player player) {
		super(x, y, gameLogic);
		this.player = player;
	}

	public void explode() {
		if (!gameLogic.getBombs().contains(this)) {
			return;
		}

		System.out.println("Exploded");

		this.gameLogic.removeBomb(this);
		player.giveBackBomb();

		List<Bomb> unexplodedBombs = new ArrayList<>();
		List<PlayerIf> undamagedPlayers = new ArrayList<>();

		rightSide: {
			// checking right side of bomb + the same field
			for (int i = 0; i <= player.getBombRadius(); i++) {
				for (AbstractBlock block : gameLogic.getBlocks()) {
					if (block.getX() == getX() + i && block.getY() == getY()) {
						if (block instanceof BrokenBlock) {
							BrokenBlock newBlock = (BrokenBlock) block;
							newBlock.destroy();
						}
						break rightSide;
					}
				}

				for (PlayerIf player : gameLogic.getPlayers()) {
					if (player instanceof Player) {
						Player newPlayer = (Player) player;
						if (newPlayer.getX() == getX() + i && newPlayer.getY() == getY()) {
							undamagedPlayers.add(newPlayer);
						}
					}

				}

				for (Bomb bomb : gameLogic.getBombs()) {
					if (bomb.getX() == getX() + i && bomb.getY() == getY()) {
						unexplodedBombs.add(bomb);
					}
				}
			}
		}

		leftSide: {
			// checking left side of the bomb
			for (int i = 1; i <= player.getBombRadius(); i++) {
				for (AbstractBlock block : gameLogic.getBlocks()) {
					if (block.getX() == getX() - i && block.getY() == getY()) {
						if (block instanceof BrokenBlock) {
							BrokenBlock newBlock = (BrokenBlock) block;
							newBlock.destroy();
						}
						break leftSide;
					}
				}

				for (PlayerIf player : gameLogic.getPlayers()) {
					if (player instanceof Player) {
						Player newPlayer = (Player) player;
						if (newPlayer.getX() == getX() - i && newPlayer.getY() == getY()) {
							undamagedPlayers.add(newPlayer);
						}
					}

				}

				for (Bomb bomb : gameLogic.getBombs()) {
					if (bomb.getX() == getX() - i && bomb.getY() == getY()) {
						unexplodedBombs.add(bomb);
					}
				}
			}
		}

		above: {
			// checking above the bomb
			for (int i = 1; i <= player.getBombRadius(); i++) {
				for (AbstractBlock block : gameLogic.getBlocks()) {
					if (block.getX() == getX() && block.getY() == getY() - i) {
						if (block instanceof BrokenBlock) {
							BrokenBlock newBlock = (BrokenBlock) block;
							newBlock.destroy();
						}
						break above;
					}
				}

				for (PlayerIf player : gameLogic.getPlayers()) {
					if (player instanceof Player) {
						Player newPlayer = (Player) player;
						if (newPlayer.getX() == getX() && newPlayer.getY() == getY() - i) {
							undamagedPlayers.add(newPlayer);
						}
					}

				}

				for (Bomb bomb : gameLogic.getBombs()) {
					if (bomb.getX() == getX() && bomb.getY() == getY() - i) {
						unexplodedBombs.add(bomb);
					}
				}
			}
		}

		below: {
			// checking below the bomb
			for (int i = 1; i <= player.getBombRadius(); i++) {
				for (AbstractBlock block : gameLogic.getBlocks()) {
					if (block.getX() == getX() && block.getY() == getY() + i) {
						if (block instanceof BrokenBlock) {
							BrokenBlock newBlock = (BrokenBlock) block;
							newBlock.destroy();
						}
						break below;
					}
				}

				for (PlayerIf player : gameLogic.getPlayers()) {
					if (player instanceof Player) {
						Player newPlayer = (Player) player;
						if (newPlayer.getX() == getX() && newPlayer.getY() == getY() + i) {
							undamagedPlayers.add(newPlayer);
						}
					}

				}

				for (Bomb bomb : gameLogic.getBombs()) {
					if (bomb.getX() == getX() && bomb.getY() == getY() + i) {
						unexplodedBombs.add(bomb);
					}
				}
			}
		}

		for (Bomb bomb : unexplodedBombs) {
			bomb.explode();

		}

		for (PlayerIf player : undamagedPlayers) {
			player.takeDamage();
		}
	}

	public void startCountdown() {
		double countDownTime = player.getBombCountDownTime();

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				explode();

			}

		}, (long) countDownTime * 1000);

	}

	@Override
	public void render(Graphics2D g, int size, int start) {
		// TODO noch nicht fertig, nur zum Testen
		g.setColor(new Color(50, 50, 220));
		g.fillOval(start + getX() * size, start + getY() * size, size - 1, size - 1);
		g.setColor(new Color(255, 136, 0));
		g.drawOval(start + getX() * size, start + getY() * size, size - 1, size - 1);
		g.setFont(new Font("Ariel", 1, 16));
		g.drawString("Bomb", (start + getX() * size) + size / 4, (start + getY() * size) + size / 2);
	}

	@Override
	public int getX() {
		return super.getX();

	}

	@Override
	public int getY() {
		return super.getY();
	}

}
