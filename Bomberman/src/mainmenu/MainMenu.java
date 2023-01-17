package mainmenu;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gamemodel.Bomberman;
import gamemodel.GameLogic;

public class MainMenu {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Bombs and Blocks");
		frame.setSize(385, 700);
		frame.setLayout(null);

		// TODO: coolen Schriftzug "Bombs and Blocks" oder so einfügen

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 350, 250);
		panel.setLayout(new GridLayout(5, 1, 0, 10));

		// Von Alex hinzugefügt,
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		JButton quickPlayButton = new JButton("Quickplay");
		quickPlayButton.setEnabled(false);
		quickPlayButton.setToolTipText("Play a 1vs1 with standard rules!");

		JButton playButton = new JButton("Play");
		playButton.setToolTipText("Lets you change options before starting!");
		playButton.addActionListener(e -> {

			Properties properties = new Properties();

			try (FileReader fileReader = new FileReader(new File("config.properties"))) {
				properties.load(fileReader);

			} catch (IOException e2) {
				e2.printStackTrace();
			}

			int width = Integer.parseInt(properties.getProperty("playfield.width"));
			int height = Integer.parseInt(properties.getProperty("playfield.height"));
			GameLogic gameLogic = new GameLogic(width, height);

			Bomberman bomberman = new Bomberman(gameLogic, properties);
			bomberman.start();

		});

		JButton settingsButton = new JButton("Settings");
		settingsButton.addActionListener(e -> new SettingsMenu());

		JButton creditsButton = new JButton("Credits");
		creditsButton.addActionListener(e -> new CreditsMenu());

		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(e -> System.exit(0));

		JButton[] buttons = { quickPlayButton, playButton, settingsButton, creditsButton, exitButton };
		for (JButton button : buttons) {
			panel.add(button);
		}

		frame.add(panel);
		frame.setVisible(true);
	}

}
