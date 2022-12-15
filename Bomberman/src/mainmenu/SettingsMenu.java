package mainmenu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class SettingsMenu {

	private LinkedHashMap<String, String> playerControllOptions = new LinkedHashMap<>();

	public SettingsMenu() {

		Properties properties = new Properties();

		try (FileReader fileReader = new FileReader(new File("config.properties"))) {
			properties.load(fileReader);

		} catch (IOException e2) {
			e2.printStackTrace();
		}

		// add Options to playerControllOptions
		playerControllOptions.put("player%.left", "Player % Left");
		playerControllOptions.put("player%.right", "Player % Right");
		playerControllOptions.put("player%.up", "Player % Up");
		playerControllOptions.put("player%.down", "Player % Down");
		playerControllOptions.put("player%.pickup", "Player % Pickup");
		playerControllOptions.put("player%.placeBomb", "Player % place Bomb");


		JFrame frame = new JFrame("Settings");
		frame.setSize(900, 700);
		frame.setLayout(null);
		frame.setVisible(true);

		addPlayerControlls(frame, properties, 10, 10, 1);
		addPlayerControlls(frame, properties, 150, 10, 2);

		// Start of Game Size
		JLabel playfieldWidthLabel = new JLabel("Playfield Width");
		playfieldWidthLabel.setBounds(310, 10, 120, 20);
		playfieldWidthLabel.setVisible(true);
		frame.add(playfieldWidthLabel);

		SpinnerModel widthModel = new SpinnerNumberModel(Integer.parseInt(properties.getProperty("playfield.width")), 3,
				21, 2);
		JSpinner widthSpinner = new JSpinner(widthModel);
		widthSpinner.setBounds(310, 30, 120, 30);
		widthSpinner.setVisible(true);
		((DefaultEditor) widthSpinner.getEditor()).getTextField().setEditable(false);
		frame.add(widthSpinner);

		JLabel playfieldHeightLabel = new JLabel("Playfield Height");
		playfieldHeightLabel.setBounds(310, 70, 120, 20);
		playfieldHeightLabel.setVisible(true);
		frame.add(playfieldHeightLabel);

		SpinnerModel heightModel = new SpinnerNumberModel(Integer.parseInt(properties.getProperty("playfield.height")),
				3, 19, 2);
		JSpinner heightSpinner = new JSpinner(heightModel);
		heightSpinner.setBounds(310, 90, 120, 30);
		heightSpinner.setVisible(true);
		((DefaultEditor) heightSpinner.getEditor()).getTextField().setEditable(false);
		frame.add(heightSpinner);
		// End of Game Size

		// Start of playercount
		JLabel playerCount = new JLabel("Playercount:");
		playerCount.setBounds(310, 130, 120, 20);
		playerCount.setVisible(true);
		frame.add(playerCount);

		SpinnerModel playerCountModel = new SpinnerNumberModel(Integer.parseInt(properties.getProperty("playercount")),
				2, 4, 1);
		JSpinner playerCountSpinner = new JSpinner(playerCountModel);
		playerCountSpinner.setBounds(310, 150, 120, 30);
		playerCountSpinner.setVisible(true);
		((DefaultEditor) playerCountSpinner.getEditor()).getTextField().setEditable(false);
		frame.add(playerCountSpinner);
		// End of playercount

		// Start of Botplayer
		JLabel botPlayerCount = new JLabel("Computerplayer:");
		botPlayerCount.setBounds(310, 180, 120, 20);
		botPlayerCount.setVisible(true);
		frame.add(botPlayerCount);

		SpinnerModel botPlayerModel = new SpinnerNumberModel(Integer.parseInt(properties.getProperty("botcount")), 0, 4,
				1);
		JSpinner botPlayerSpinner = new JSpinner(botPlayerModel);
		botPlayerSpinner.setBounds(310, 200, 120, 30);
		botPlayerSpinner.setVisible(true);
		((DefaultEditor) botPlayerSpinner.getEditor()).getTextField().setEditable(false);
		frame.add(botPlayerSpinner);
		// End of Botplayer
		// Start Error Message
		JLabel errorMessage = new JLabel("");
		errorMessage.setBounds(10, 370, 400, 30);
		errorMessage.setVisible(true);
		frame.add(errorMessage);

		// End Error Message
		// Save Button
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(10, 400, 100, 40);
		saveButton.setVisible(true);
		saveButton.addActionListener(e -> {
			try {
				errorMessage.setText("");
				if (Integer.parseInt(playerCountSpinner.getModel().getValue() + "") < Integer
						.parseInt(botPlayerSpinner.getModel().getValue() + "")) {
					errorMessage.setText("Number of players has to be higher or equal to computerplayer count.");

				} else {
					properties.setProperty("playfield.height", heightSpinner.getValue() + "");
					properties.setProperty("playfield.width", widthSpinner.getValue() + "");
					properties.setProperty("playercount", playerCountSpinner.getValue() + "");
					properties.setProperty("botcount", botPlayerSpinner.getValue() + "");
					properties.store(new FileOutputStream("config.properties"), null);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		frame.add(saveButton);
	}

	private void addPlayerControlls(JFrame frame, Properties properties, int xOffset, int yOffset, int playerNumber) {

		int extraY = 0;

		for (Map.Entry<String, String> entry : playerControllOptions.entrySet()) {
			// Label
			JLabel label = new JLabel(entry.getValue().replace("%", Integer.toString(playerNumber)));
			label.setBounds(xOffset, yOffset + 60 * extraY, 120, 20);
			label.setVisible(true);
			frame.add(label);

			// Textfield
			JTextField textfield = new JTextField(
					KeyEvent.getKeyText(
							Integer.parseInt(properties
									.getProperty(entry.getKey().replace("%", Integer.toString(playerNumber))))));
			textfield.setBounds(xOffset, yOffset + 20 + 60 * extraY, 120, 30);
			textfield.setVisible(true);
			textfield.setEditable(false);
			textfield.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (textfield.isFocusOwner()) {
						textfield.setText((char) e.getKeyCode() + "");
						properties.setProperty(entry.getKey(), e.getKeyCode() + "");
					}
				}
			});
			frame.add(textfield);

			extraY += 1;
		}



	}

}
