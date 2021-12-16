package viewcontroller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import gamemodel.Bomberman;
import gamemodel.GameLogic;
import gamemodel.PlayerIf;

public class Zeichenfeld extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8522049187808211577L;

	private transient Bomberman bomberman;

	private GameLogic gameLogic;

	private int size = 70;

	private int start = 30;

	private int width;

	private int height;

	private int scoreboardx;

	private int scoreboardy = 180;

	private int iconSize = 40;

	public Zeichenfeld(Bomberman bomberman, GameLogic gameLogic) {
		super();
		this.bomberman = bomberman;
		this.gameLogic = gameLogic;
		this.width = gameLogic.getWidth();
		this.height = gameLogic.getHeight();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());

		g.setColor(new Color(100, 100, 100));
		for (int i = 1; i < width; i++) {
			for (int j = 1; j < height; j++) {
				g.fillRect(start + i * size, start + j * size, size - 1, size - 1);
			}
		}
		bomberman.renderEntities((Graphics2D) g, size, start);

		List<PlayerIf> players = bomberman.getPlayers();

		scoreboardx = 2 * start + width * size;

		for (int i = 0; i < players.size(); i++) {
			Map<String, String> playerInfo = players.get(i).getPlayerInformation();

			g.drawString("Player " + playerInfo.get("number"), scoreboardx, 50 + scoreboardy * i);
//			g.drawString("Lives: " + playerInfo.get("lives"), scoreboardx, 65 + 120 * i);
//			g.drawString("Speed: " + playerInfo.get("speed"), scoreboardx, 80 + 120 * i);
//			g.drawString("Max Bombs: " + playerInfo.get("bombCountMax"), scoreboardx, 95 + 120 * i);
//			g.drawString("Current Bombs: " + playerInfo.get("bombCountCurrent"), scoreboardx, 110 + 120 * i);
//			g.drawString("Bomb Radius: " + playerInfo.get("bombRadius"), scoreboardx, 125 + 120 * i);
//			g.drawString("Bomb Timer: " + playerInfo.get("bombTimer"), scoreboardx, 140 + 120 * i);

			for (int lives = 0; lives < Integer.parseInt(playerInfo.get("lives")); lives++) {
				g.drawImage(gameLogic.getImages().get("fullHeart"), scoreboardx + lives * iconSize,
						65 + scoreboardy * i,
						iconSize, iconSize, null);
			}

			for (int emptylives = 2; emptylives > Integer.parseInt(playerInfo.get("lives")); emptylives--) {
				g.drawImage(gameLogic.getImages().get("emptyHeart"), scoreboardx + (emptylives - 1) * iconSize,
						65 + scoreboardy * i, 40, 40, null);
			}

			for (int bombs = 0; bombs < Integer.parseInt(playerInfo.get("bombCountCurrent")); bombs++) {
				g.drawImage(gameLogic.getImages().get("bomb"), scoreboardx + bombs * iconSize, 100 + scoreboardy * i,
						iconSize,
						iconSize, null);
			}

			for (int emptybombs = Integer.parseInt(playerInfo.get("bombCountMax")); emptybombs > Integer
					.parseInt(playerInfo.get("bombCountCurrent")); emptybombs--) {
				g.drawImage(gameLogic.getImages().get("emptyBomb"), scoreboardx + (emptybombs - 1) * iconSize,
						100 + scoreboardy * i, iconSize, iconSize, null);
			}
		}
	}
}