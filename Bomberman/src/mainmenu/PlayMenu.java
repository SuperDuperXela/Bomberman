package mainmenu;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gamemodel.Bomberman;
import gamemodel.GameLogic;

public class PlayMenu {

	private static final String HUMAN = "Human";
	private static final String BOT = "Bot";

	private static final String[] playerTypes = { HUMAN, BOT, "---" };
	private static final String[] botTypes = { "standing still", "evading" };
	private static final String[] teams = { "1", "2", "3", "4" };

	private static final String[] mapNames = { "Basic", "ZwoteMap" };

	private static final int WINDOW_WIDTH = 900;
	private static final int WINDOW_HEIGHT = 600;

	public PlayMenu() {

		Properties properties = new Properties();

		try (FileReader fileReader = new FileReader(new File("config.properties"))) {
			properties.load(fileReader);
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		JFrame frame = new JFrame("Play");
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setLayout(null);

		frame.add(createPlayerSelector(properties, 10, 10, 4));
		frame.add(createMapOptions(properties, WINDOW_WIDTH - 480, 10));

		JButton startButton = new JButton("Start");
		startButton.setBounds(WINDOW_WIDTH - 150, WINDOW_HEIGHT - 90, 120, 40);
		startButton.setVisible(true);
		startButton.addActionListener(e -> {

			int width = Integer.parseInt(properties.getProperty("playfield.width"));
			int height = Integer.parseInt(properties.getProperty("playfield.height"));
			GameLogic gameLogic = new GameLogic(width, height);

			Bomberman bomberman = new Bomberman(gameLogic, properties);
			bomberman.start();

		});
		frame.add(startButton);
		frame.setVisible(true);

	}

	/**
	 * @param frame
	 * @param properties
	 * @param xOffset
	 * @param yOffset
	 * @param maxPlayers Maximum number of players
	 */
	private JPanel createPlayerSelector(Properties properties, int xOffset, int yOffset, int maxPlayers) {

		JPanel panel = new JPanel();
		panel.setBounds(xOffset, yOffset, 300, (maxPlayers + 1) * 28);
		panel.setLayout(new GridLayout(maxPlayers + 1, 1, 0, 3));
		panel.setBorder(BorderFactory.createTitledBorder("Players"));

		JPanel subPanel = new JPanel(new GridLayout(1, 3));
		subPanel.add(new JLabel("Player type"));
		subPanel.add(new JLabel("Bot playstyle"));
		subPanel.add(new JLabel("Team"));

		panel.add(subPanel);

		for (int i = 1; i <= maxPlayers; i++) {
			panel.add(createPlayerSelectorRow(properties, i));
		}

		return panel;
	}

	private JPanel createPlayerSelectorRow(Properties properties, int playerNumber) {

		String playerNumberText = "player" + playerNumber;

		JPanel subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(1, 3, 5, 0));

		JComboBox<String> playerType = new JComboBox<>(playerTypes);
		JComboBox<String> difficulty = new JComboBox<>(botTypes);
		JComboBox<String> team = new JComboBox<>(teams);

		playerType.setSelectedItem(properties.getProperty(playerNumberText + ".type"));
		difficulty.setSelectedItem(properties.getProperty(playerNumberText + ".botType"));
		team.setSelectedItem(properties.getProperty(playerNumberText + ".team"));

		playerType.addActionListener(e -> {
			String selectedPlayerType = (String) playerType.getSelectedItem();
			difficulty.setVisible(selectedPlayerType.equals(BOT));
			team.setVisible(selectedPlayerType.equals(HUMAN) || selectedPlayerType.equals(BOT));
			properties.setProperty(playerNumberText + ".type", selectedPlayerType);
		});

		difficulty.addActionListener(
				e -> properties.setProperty(playerNumberText + ".botType", (String) difficulty.getSelectedItem()));

		team.addActionListener(
				e -> properties.setProperty(playerNumberText + ".team", (String) team.getSelectedItem()));

		subPanel.add(playerType);
		subPanel.add(difficulty);
		subPanel.add(team);

		String selectedPlayerType = (String) playerType.getSelectedItem();
		difficulty.setVisible(selectedPlayerType.equals(BOT));
		team.setVisible(selectedPlayerType.equals(HUMAN) || selectedPlayerType.equals(BOT));

		return subPanel;
	}

	private JPanel createMapOptions(Properties properties, int xOffset, int yOffset) {

		Map<String, BufferedImage> images = new HashMap<>();

		BufferedImage image = null;
		for (String mapName : mapNames) {
			try {
				image = ImageIO.read(new File("media/images/mapPreview" + mapName + ".png"));
				image = resizeImage(image, 390, 390);
				images.put(mapName, image);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		JPanel mapPanel = new JPanel();
		mapPanel.setBounds(xOffset, yOffset, 450, 450);
		mapPanel.setBorder(BorderFactory.createTitledBorder("Map"));

		// zeig ausgewählte Map als Vorschau

		JLabel mapPreview = new JLabel(new ImageIcon(images.get(properties.getProperty("mapName"))));

		mapPreview.setBounds(xOffset, yOffset, 400, 400);

		// jcombobox als Auswahl
		JComboBox<String> mapSelector = new JComboBox<>(mapNames);
		mapSelector.setSelectedItem(properties.getProperty("mapName"));
		mapSelector.addActionListener(e -> {
			String selectedMap = (String) mapSelector.getSelectedItem();
			mapPreview.setIcon(new ImageIcon(images.get(selectedMap)));
			properties.setProperty("mapName", selectedMap);
		});
		// zusatz Optionen zur Map evt
		mapPanel.add(mapPreview);
		mapPanel.add(mapSelector);
		return mapPanel;

	}

	private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight)
			throws IOException {
		BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = resizedImage.createGraphics();
		graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
		graphics2D.dispose();
		return resizedImage;
	}

}
