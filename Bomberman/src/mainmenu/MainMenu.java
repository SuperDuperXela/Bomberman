package mainmenu;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;

import gamemodel.Bomberman;
import gamemodel.GameLogic;

public class MainMenu {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Bombs and Blocks");
		frame.setSize(400, 700);
		frame.setLayout(null);

		// Von Alex hinzugefügt,
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		JButton playButton = new JButton("Play");
		playButton.setBounds(10, 10, 350, 40);
		playButton.setVisible(true);
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
		frame.add(playButton);

		JButton settingsButton = new JButton("Settings");
		settingsButton.setBounds(10, 60, 350, 40);
		settingsButton.setVisible(true);
		settingsButton.addActionListener(e -> new SettingsMenu());
		frame.add(settingsButton);

		JButton creditsButton = new JButton("Credits");
		creditsButton.setBounds(10, 110, 350, 40);
		creditsButton.setVisible(true);
		creditsButton.addActionListener(e -> new CreditsMenu());
		frame.add(creditsButton);

		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(10, 160, 350, 40);
		exitButton.setVisible(true);
		exitButton.addActionListener(e -> System.exit(0));
		frame.add(exitButton);

		frame.setVisible(true);
	}

}
