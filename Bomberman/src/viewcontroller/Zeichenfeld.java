package viewcontroller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import gamemodel.Bomberman;
import gamemodel.PlayerIf;

public class Zeichenfeld extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8522049187808211577L;

	private transient Bomberman bomberman;

	private int size = 70;

	private int start = 30;

	public Zeichenfeld(Bomberman bomberman) {
		super();
		this.bomberman = bomberman;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());

		g.setColor(new Color(100, 100, 100));
		for (int i = 1; i < 12; i++) {
			for (int j = 1; j < 10; j++) {
				g.fillRect(start + i * size, start + j * size, size - 1, size - 1);
			}
		}
		bomberman.renderEntities((Graphics2D) g, size, start);

		List<PlayerIf> players = bomberman.getPlayers();

		for (int i = 0; i < players.size(); i++) {
			Map<String, String> playerInfo = players.get(i).getPlayerInformation();

			g.drawString("Player " + playerInfo.get("number"), 30 + start + 13 * size, 50 + 120 * i);
			g.drawString("Lives: " + playerInfo.get("lives"), 30 + start + 13 * size, 65 + 120 * i);
			g.drawString("Speed: " + playerInfo.get("speed"), 30 + start + 13 * size, 80 + 120 * i);
			g.drawString("Current Bombs: " + playerInfo.get("bombCountCurrent"), 30 + start + 13 * size, 95 + 120 * i);
			g.drawString("Max Bombs: " + playerInfo.get("bombCountMax"), 30 + start + 13 * size, 110 + 120 * i);
			g.drawString("Bomb Radius: " + playerInfo.get("bombRadius"), 30 + start + 13 * size, 125 + 120 * i);
			g.drawString("Bomb Timer: " + playerInfo.get("bombTimer"), 30 + start + 13 * size, 140 + 120 * i);
		}
	}
}