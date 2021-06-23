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
	
	private List<Bomb> unexplodedBombs = new ArrayList<>();
	
	private List<PlayerIf> undamagedPlayers = new ArrayList<>();

	public Bomb(int x, int y, GameLogic gameLogic, Player player) {
		super(x, y, gameLogic);
		this.player = player;
	}

	public void explode() {
		if (gameLogic.getBombs()[getX()][getY()] != this) {
			return;
		}

		System.out.println("Exploded");

		this.gameLogic.removeBomb(this);
		player.giveBackBomb();

		explodeDirection(1, 0);
		explodeDirection(0, 1);
		explodeDirection(-1, 0);
		explodeDirection(0, -1);

		for (Bomb bomb : unexplodedBombs) {
			bomb.explode();

		}

		for (PlayerIf player : undamagedPlayers) {
			player.takeDamage();
		}
		
		/*
		rightSide: {
			// checking right side of bomb + the same field
			for (int i = 0; i <= player.getBombRadius(); i++) {
				for (SolidBlock block : gameLogic.getSolidBlocks()) {
					if (block.getX() == getX() + i && block.getY() == getY()) {
						break rightSide;
					}
				}

				for (BrokenBlock block : gameLogic.getBrokenBlocks()) {
					if (block.getX() == getX() + i && block.getY() == getY()) {
						block.destroy();
						break rightSide;
					}
				}

				for (Player player : gameLogic.getPlayers()) {
					if (player.getX() == getX() + i && player.getY() == getY()) {
						undamagedPlayers.add(player);
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
				for (SolidBlock block : gameLogic.getSolidBlocks()) {
					if (block.getX() == getX() - i && block.getY() == getY()) {
						break leftSide;
					}
				}

				for (BrokenBlock block : gameLogic.getBrokenBlocks()) {
					if (block.getX() == getX() - i && block.getY() == getY()) {
						block.destroy();
						break leftSide;
					}
				}

				for (Player player : gameLogic.getPlayers()) {
					if (player.getX() == getX() - i && player.getY() == getY()) {
						undamagedPlayers.add(player);
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
				for (SolidBlock block : gameLogic.getSolidBlocks()) {
					if (block.getX() == getX() && block.getY() == getY() - i) {
						break above;
					}
				}

				for (BrokenBlock block : gameLogic.getBrokenBlocks()) {
					if (block.getX() == getX() && block.getY() == getY() - i) {
						block.destroy();
						break above;
					}
				}

				for (Player player : gameLogic.getPlayers()) {
					if (player.getX() == getX() && player.getY() == getY() - i) {
						undamagedPlayers.add(player);
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
				for (SolidBlock block : gameLogic.getSolidBlocks()) {
					if (block.getX() == getX() && block.getY() == getY() + i) {
						break below;
					}
				}

				for (BrokenBlock block : gameLogic.getBrokenBlocks()) {
					if (block.getX() == getX() && block.getY() == getY() + i) {
						block.destroy();
						break below;
					}
				}

				for (Player player : gameLogic.getPlayers()) {
					if (player.getX() == getX() && player.getY() == getY() + i) {
						undamagedPlayers.add(player);
					}

				}

				for (Bomb bomb : gameLogic.getBombs()) {
					if (bomb.getX() == getX() && bomb.getY() == getY() + i) {
						unexplodedBombs.add(bomb);
					}
				}
			}
		}
	*/
		
	}
	
	public void explodeDirection(int xChange, int yChange) {
		for (int i = 0; i <= player.getBombRadius(); i++) {
			if (gameLogic.getSolidBlocks()[getX() + i * xChange][getY() + i * yChange] != null) {
				break;
			}
			if (gameLogic.getBrokenBlocks()[getX() + i * xChange][getY() + i * yChange] != null) {
				gameLogic.getBrokenBlocks()[getX() + i * xChange][getY() + i * yChange].destroy();
				break;
			}
			if (gameLogic.getBombs()[getX() + i * xChange][getY() + i * yChange] != null) {
				unexplodedBombs.add(gameLogic.getBombs()[getX() + i * xChange][getY() + i * yChange]);
			}
			for (Player player : gameLogic.getPlayers()) {
				if (player.getX() == getX() + i * xChange && player.getY() == getY() + i * yChange) {
					undamagedPlayers.add(player);
				}

			}
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
//		g.setFont(new Font("Ariel", 1, 16));
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
