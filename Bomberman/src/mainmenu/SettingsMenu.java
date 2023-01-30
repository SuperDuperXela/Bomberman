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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		playerControllOptions.put("player%.placeBomb", "Bomb");
		playerControllOptions.put("player%.up", "Up");
		playerControllOptions.put("player%.pickup", "Pickup");
		playerControllOptions.put("player%.left", "Left");
		playerControllOptions.put("player%.down", "Down");
		playerControllOptions.put("player%.right", "Right");

		JFrame frame = new JFrame("Settings");
		frame.setSize(900, 700);
		frame.setLayout(null);

		frame.add(playerControllsPanel(properties, 10, 10, 4));

		// Start of Game Size
		JLabel playfieldWidthLabel = new JLabel("Playfield Width");
		playfieldWidthLabel.setBounds(400, 310, 120, 20);

		SpinnerModel widthModel = new SpinnerNumberModel(Integer.parseInt(properties.getProperty("playfield.width")), 3,
				21, 2);
		JSpinner widthSpinner = new JSpinner(widthModel);
		widthSpinner.setBounds(400, 330, 120, 30);
		((DefaultEditor) widthSpinner.getEditor()).getTextField().setEditable(false);

		JLabel playfieldHeightLabel = new JLabel("Playfield Height");
		playfieldHeightLabel.setBounds(400, 370, 120, 20);

		SpinnerModel heightModel = new SpinnerNumberModel(Integer.parseInt(properties.getProperty("playfield.height")),
				3, 19, 2);
		JSpinner heightSpinner = new JSpinner(heightModel);
		heightSpinner.setBounds(400, 390, 120, 30);
		((DefaultEditor) heightSpinner.getEditor()).getTextField().setEditable(false);

		// Save Button
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(10, 610, 80, 30);
		saveButton.addActionListener(e -> {
			try {
					properties.setProperty("playfield.height", heightSpinner.getValue() + "");
					properties.setProperty("playfield.width", widthSpinner.getValue() + "");
					properties.store(new FileOutputStream("config.properties"), null);

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		JComponent[] components = { playfieldWidthLabel, widthSpinner, playfieldHeightLabel, heightSpinner,
				saveButton };
		for (JComponent component : components) {
			frame.add(component);
		}

		frame.setVisible(true);
	}

	private JPanel playerControllsPanel(Properties properties, int xOffset, int yOffset, int maxPlayers) {

		JPanel panel = new JPanel();
		panel.setBounds(xOffset, yOffset, 310, 590);
		panel.setLayout(new GridLayout(maxPlayers, 1, 0, 5));

		for (int i = 1; i <= maxPlayers; i++) {
			panel.add(createPlayerControllsBlock(properties, i));
		}
		// refactored
		return panel;

	}

	private JPanel createPlayerControllsBlock(Properties properties, int playerNumber) {

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Player " + playerNumber));
		panel.setLayout(new GridLayout(2, 3, 5, 10));

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
		return panel;
	}
}
