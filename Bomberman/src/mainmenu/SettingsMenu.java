package mainmenu;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

public class SettingsMenu {

	private LinkedHashMap<String, String> playerControllOptions = new LinkedHashMap<>();

	public SettingsMenu() {

		Properties properties = new Properties();

		try (FileReader fileReader = new FileReader(new File("config.properties"))) {
			properties.load(fileReader);

		} catch (IOException e2) {
			e2.printStackTrace();
		}
		// TODO combobox
		// add Options to playerControllOptions
		playerControllOptions.put("player%.placeBomb", "Bomb");
		playerControllOptions.put("player%.up", "Up");
		playerControllOptions.put("player%.pickup", "Pickup");
		playerControllOptions.put("player%.left", "Left");
		playerControllOptions.put("player%.down", "Down");
		playerControllOptions.put("player%.right", "Right");

		JFrame frame = new JFrame("Settings");
		frame.setSize(900, 700);
		frame.setLayout(null);
		frame.setVisible(true);

		addPlayerControlls(frame, properties, 10, 10, 1);
		addPlayerControlls(frame, properties, 10, 160, 2);
		addPlayerControlls(frame, properties, 10, 310, 3);
		addPlayerControlls(frame, properties, 10, 460, 4);

		// Start of Game Size
		JLabel playfieldWidthLabel = new JLabel("Playfield Width");
		playfieldWidthLabel.setBounds(400, 10, 120, 20);
		playfieldWidthLabel.setVisible(true);
		frame.add(playfieldWidthLabel);

		SpinnerModel widthModel = new SpinnerNumberModel(Integer.parseInt(properties.getProperty("playfield.width")), 3,
				21, 2);
		JSpinner widthSpinner = new JSpinner(widthModel);
		widthSpinner.setBounds(400, 30, 120, 30);
		widthSpinner.setVisible(true);
		((DefaultEditor) widthSpinner.getEditor()).getTextField().setEditable(false);
		frame.add(widthSpinner);

		JLabel playfieldHeightLabel = new JLabel("Playfield Height");
		playfieldHeightLabel.setBounds(400, 70, 120, 20);
		playfieldHeightLabel.setVisible(true);
		frame.add(playfieldHeightLabel);

		SpinnerModel heightModel = new SpinnerNumberModel(Integer.parseInt(properties.getProperty("playfield.height")),
				3, 19, 2);
		JSpinner heightSpinner = new JSpinner(heightModel);
		heightSpinner.setBounds(400, 90, 120, 30);
		heightSpinner.setVisible(true);
		((DefaultEditor) heightSpinner.getEditor()).getTextField().setEditable(false);
		frame.add(heightSpinner);
		// End of Game Size

		// Start of playercount
		JLabel playerCount = new JLabel("Playercount:");
		playerCount.setBounds(400, 130, 120, 20);
		playerCount.setVisible(true);
		frame.add(playerCount);

		SpinnerModel playerCountModel = new SpinnerNumberModel(Integer.parseInt(properties.getProperty("playercount")),
				2, 4, 1);
		JSpinner playerCountSpinner = new JSpinner(playerCountModel);
		playerCountSpinner.setBounds(400, 150, 120, 30);
		playerCountSpinner.setVisible(true);
		((DefaultEditor) playerCountSpinner.getEditor()).getTextField().setEditable(false);
		frame.add(playerCountSpinner);
		// End of playercount

		// Start of Botplayer
		JLabel botPlayerCount = new JLabel("Computerplayer:");
		botPlayerCount.setBounds(400, 180, 120, 20);
		botPlayerCount.setVisible(true);
		frame.add(botPlayerCount);

		SpinnerModel botPlayerModel = new SpinnerNumberModel(Integer.parseInt(properties.getProperty("botcount")), 0, 4,
				1);
		JSpinner botPlayerSpinner = new JSpinner(botPlayerModel);
		botPlayerSpinner.setBounds(400, 200, 120, 30);
		botPlayerSpinner.setVisible(true);
		((DefaultEditor) botPlayerSpinner.getEditor()).getTextField().setEditable(false);
		frame.add(botPlayerSpinner);
		// End of Botplayer
		// Start Error Message
		JLabel errorMessage = new JLabel("");
		errorMessage.setBounds(100, 610, 400, 30);
		errorMessage.setVisible(true);
		frame.add(errorMessage);

		// End Error Message
		// Save Button
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(10, 610, 80, 30);
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

		JPanel panel = new JPanel();
		panel.setBounds(xOffset, yOffset, 310, 140);
		panel.setLayout(new GridLayout(2, 3, 5, 10));

		TitledBorder title;
		title = BorderFactory.createTitledBorder("Spieler " + playerNumber);

		for (Map.Entry<String, String> entry : playerControllOptions.entrySet()) {

			JPanel subPanel = new JPanel();
			subPanel.setLayout(new GridLayout(2, 1));

			// Label
			JLabel label = new JLabel(entry.getValue());
			label.setVisible(true);
			subPanel.add(label);

			// Textfield
			JTextField textfield = new JTextField(KeyEvent.getKeyText(Integer
					.parseInt(properties.getProperty(entry.getKey().replace("%", Integer.toString(playerNumber))))));
			textfield.setVisible(true);
			textfield.setEditable(false);
			textfield.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (textfield.isFocusOwner()) {
						textfield.setText(KeyEvent.getKeyText(e.getKeyCode()) + "");
						properties.setProperty(entry.getKey().replace("%", Integer.toString(playerNumber)),
								e.getKeyCode() + "");
					}
				}
			});
			subPanel.add(textfield);

			panel.add(subPanel);
		}

		panel.setBorder(title);
		frame.add(panel);

	}

}
