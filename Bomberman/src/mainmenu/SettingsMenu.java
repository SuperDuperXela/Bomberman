package mainmenu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
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

	public SettingsMenu() {

		Properties properties = new Properties();

		try (FileReader fileReader = new FileReader(new File("config.properties"))) {
			properties.load(fileReader);

		} catch (IOException e2) {
			e2.printStackTrace();
		}

		JFrame frame = new JFrame("Settings");
		frame.setSize(500, 700);
		frame.setLayout(null);
		frame.setVisible(true);

		// Start of Player 1
		JLabel player1LeftLabel = new JLabel("Player 1 Left");
		player1LeftLabel.setBounds(10, 10, 120, 20);
		player1LeftLabel.setVisible(true);
		frame.add(player1LeftLabel);

		JTextField player1LeftKey = new JTextField(
				KeyEvent.getKeyText(Integer.parseInt(properties.getProperty("player1.left"))));
		player1LeftKey.setBounds(10, 30, 120, 30);
		player1LeftKey.setVisible(true);
		player1LeftKey.setEditable(false);
		player1LeftKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (player1LeftKey.isFocusOwner()) {
					player1LeftKey.setText((char) e.getKeyCode() + "");
					properties.setProperty("player1.left", e.getKeyCode() + "");
				}
			}
		});
		frame.add(player1LeftKey);

		JLabel player1RightLabel = new JLabel("Player 1 Right");
		player1RightLabel.setBounds(10, 70, 120, 20);
		player1RightLabel.setVisible(true);
		frame.add(player1RightLabel);

		JTextField player1RightKey = new JTextField(
				KeyEvent.getKeyText(Integer.parseInt(properties.getProperty("player1.right"))));
		player1RightKey.setBounds(10, 90, 120, 30);
		player1RightKey.setVisible(true);
		player1RightKey.setEditable(false);
		player1RightKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (player1RightKey.isFocusOwner()) {
					player1RightKey.setText((char) e.getKeyCode() + "");
					properties.setProperty("player1.right", e.getKeyCode() + "");
				}
			}
		});
		frame.add(player1RightKey);

		JLabel player1UpLabel = new JLabel("Player 1 Up");
		player1UpLabel.setBounds(10, 130, 120, 20);
		player1UpLabel.setVisible(true);
		frame.add(player1UpLabel);

		JTextField player1UpKey = new JTextField(
				KeyEvent.getKeyText(Integer.parseInt(properties.getProperty("player1.up"))));
		player1UpKey.setBounds(10, 150, 120, 30);
		player1UpKey.setVisible(true);
		player1UpKey.setEditable(false);
		player1UpKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (player1UpKey.isFocusOwner()) {
					player1UpKey.setText((char) e.getKeyCode() + "");
					properties.setProperty("player1.up", e.getKeyCode() + "");
				}
			}
		});
		frame.add(player1UpKey);

		JLabel player1DownLabel = new JLabel("Player 1 Down");
		player1DownLabel.setBounds(10, 190, 120, 20);
		player1DownLabel.setVisible(true);
		frame.add(player1DownLabel);

		JTextField player1DownKey = new JTextField(
				KeyEvent.getKeyText(Integer.parseInt(properties.getProperty("player1.down"))));
		player1DownKey.setBounds(10, 210, 120, 30);
		player1DownKey.setVisible(true);
		player1DownKey.setEditable(false);
		player1DownKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (player1DownKey.isFocusOwner()) {
					player1DownKey.setText((char) e.getKeyCode() + "");
					properties.setProperty("player1.down", e.getKeyCode() + "");
				}
			}
		});
		frame.add(player1DownKey);

		JLabel player1PickupLabel = new JLabel("Player 1 Pickup");
		player1PickupLabel.setBounds(10, 250, 120, 20);
		player1PickupLabel.setVisible(true);
		frame.add(player1PickupLabel);

		JTextField player1PickupKey = new JTextField(
				KeyEvent.getKeyText(Integer.parseInt(properties.getProperty("player1.pickup"))));
		player1PickupKey.setBounds(10, 270, 120, 30);
		player1PickupKey.setVisible(true);
		player1PickupKey.setEditable(false);
		player1PickupKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (player1PickupKey.isFocusOwner()) {
					player1PickupKey.setText((char) e.getKeyCode() + "");
					properties.setProperty("player1.pickup", e.getKeyCode() + "");
				}
			}
		});
		frame.add(player1PickupKey);

		JLabel player1PlaceBombLabel = new JLabel("Player 1 place Bomb");
		player1PlaceBombLabel.setBounds(10, 310, 120, 20);
		player1PlaceBombLabel.setVisible(true);
		frame.add(player1PlaceBombLabel);

		JTextField player1PlaceBombKey = new JTextField(
				KeyEvent.getKeyText(Integer.parseInt(properties.getProperty("player1.placeBomb"))));
		player1PlaceBombKey.setBounds(10, 330, 120, 30);
		player1PlaceBombKey.setVisible(true);
		player1PlaceBombKey.setEditable(false);
		player1PlaceBombKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (player1PlaceBombKey.isFocusOwner()) {
					player1PlaceBombKey.setText((char) e.getKeyCode() + "");
					properties.setProperty("player1.placeBomb", e.getKeyCode() + "");
				}
			}
		});
		frame.add(player1PlaceBombKey);
		// End of Player 1

		// Start of Player 2
		JLabel player2LeftLabel = new JLabel("Player 2 Left");
		player2LeftLabel.setBounds(150, 10, 120, 20);
		player2LeftLabel.setVisible(true);
		frame.add(player2LeftLabel);

		JTextField player2LeftKey = new JTextField(
				KeyEvent.getKeyText(Integer.parseInt(properties.getProperty("player2.left"))));
		player2LeftKey.setBounds(150, 30, 120, 30);
		player2LeftKey.setVisible(true);
		player2LeftKey.setEditable(false);
		player2LeftKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (player2LeftKey.isFocusOwner()) {
					player2LeftKey.setText((char) e.getKeyCode() + "");
					properties.setProperty("player2.left", e.getKeyCode() + "");
				}
			}
		});
		frame.add(player2LeftKey);

		JLabel player2RightLabel = new JLabel("Player 2 Right");
		player2RightLabel.setBounds(150, 70, 120, 20);
		player2RightLabel.setVisible(true);
		frame.add(player2RightLabel);

		JTextField player2RightKey = new JTextField(
				KeyEvent.getKeyText(Integer.parseInt(properties.getProperty("player2.right"))));
		player2RightKey.setBounds(150, 90, 120, 30);
		player2RightKey.setVisible(true);
		player2RightKey.setEditable(false);
		player2RightKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (player2RightKey.isFocusOwner()) {
					player2RightKey.setText((char) e.getKeyCode() + "");
					properties.setProperty("player2.right", e.getKeyCode() + "");
				}
			}
		});
		frame.add(player2RightKey);

		JLabel player2UpLabel = new JLabel("Player 2 Up");
		player2UpLabel.setBounds(150, 130, 120, 20);
		player2UpLabel.setVisible(true);
		frame.add(player2UpLabel);

		JTextField player2UpKey = new JTextField(
				KeyEvent.getKeyText(Integer.parseInt(properties.getProperty("player2.up"))));
		player2UpKey.setBounds(150, 150, 120, 30);
		player2UpKey.setVisible(true);
		player2UpKey.setEditable(false);
		player2UpKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (player2UpKey.isFocusOwner()) {
					player2UpKey.setText((char) e.getKeyCode() + "");
					properties.setProperty("player2.up", e.getKeyCode() + "");
				}
			}
		});
		frame.add(player2UpKey);

		JLabel player2DownLabel = new JLabel("Player 2 Down");
		player2DownLabel.setBounds(150, 190, 120, 20);
		player2DownLabel.setVisible(true);
		frame.add(player2DownLabel);

		JTextField player2DownKey = new JTextField(
				KeyEvent.getKeyText(Integer.parseInt(properties.getProperty("player2.down"))));
		player2DownKey.setBounds(150, 210, 120, 30);
		player2DownKey.setVisible(true);
		player2DownKey.setEditable(false);
		player2DownKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (player2DownKey.isFocusOwner()) {
					player2DownKey.setText((char) e.getKeyCode() + "");
					properties.setProperty("player2.down", e.getKeyCode() + "");
				}
			}
		});
		frame.add(player2DownKey);

		JLabel player2PickupLabel = new JLabel("Player 2 Pickup");
		player2PickupLabel.setBounds(150, 250, 120, 20);
		player2PickupLabel.setVisible(true);
		frame.add(player2PickupLabel);

		JTextField player2PickupKey = new JTextField(
				KeyEvent.getKeyText(Integer.parseInt(properties.getProperty("player2.pickup"))));
		player2PickupKey.setBounds(150, 270, 120, 30);
		player2PickupKey.setVisible(true);
		player2PickupKey.setEditable(false);
		player2PickupKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (player2PickupKey.isFocusOwner()) {
					player2PickupKey.setText((char) e.getKeyCode() + "");
					properties.setProperty("player2.pickup", e.getKeyCode() + "");
				}
			}
		});
		frame.add(player2PickupKey);

		JLabel player2PlaceBombLabel = new JLabel("Player 2 place Bomb");
		player2PlaceBombLabel.setBounds(150, 310, 120, 20);
		player2PlaceBombLabel.setVisible(true);
		frame.add(player2PlaceBombLabel);

		JTextField player2PlaceBombKey = new JTextField(
				KeyEvent.getKeyText(Integer.parseInt(properties.getProperty("player2.placeBomb"))));
		player2PlaceBombKey.setBounds(150, 330, 120, 30);
		player2PlaceBombKey.setVisible(true);
		player2PlaceBombKey.setEditable(false);
		player2PlaceBombKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (player2PlaceBombKey.isFocusOwner()) {
					player2PlaceBombKey.setText((char) e.getKeyCode() + "");
					properties.setProperty("player2.placeBomb", e.getKeyCode() + "");
				}
			}
		});
		frame.add(player2PlaceBombKey);
		// End of Player 2

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

}
