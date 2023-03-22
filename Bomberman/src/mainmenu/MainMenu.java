package mainmenu;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Bombs and Blocks");
		frame.setSize(385, 700);
		frame.setLayout(null);

		// TODO: coolen Schriftzug "Bombs and Blocks" oder so einfügen
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(10, 10, 350, 250);
		mainPanel.setLayout(new GridLayout(5, 1, 0, 10));

		// Von Alex hinzugefügt,
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		JButton quickPlayButton = new JButton("Quickplay");
		quickPlayButton.setEnabled(false);
		quickPlayButton.setToolTipText("Play a 1vs1 with standard rules!");

		JButton playButton = createButton("Play", "Lets you change options before starting!", e -> new PlayMenu());
		JButton joinRoomButton = createButton("Join", "Join an online room!", e -> new JoinRoomMenu());
		JButton createRoomButton = createButton("Create", "Create an online room!", e -> new CreateRoomMenu());
		JButton settingsButton = createButton("Settings", "", e -> new SettingsMenu());
		JButton creditsButton = createButton("Credits", "", e -> new CreditsMenu());
		JButton exitButton = createButton("Exit", "", e -> System.exit(0));

		JComponent[] components = { createRow(new JButton[] { quickPlayButton }), //
				createRow(new JButton[] { playButton }), //
				createRow(new JButton[] { joinRoomButton, createRoomButton }), //
				createRow(new JButton[] { settingsButton }), //
				createRow(new JButton[] { creditsButton, exitButton }) //
		};
		for (JComponent component : components) {
			mainPanel.add(component);
		}

		frame.add(mainPanel);
		frame.setVisible(true);

	}

	private static JButton createButton(String buttonText, String toolTip, ActionListener listener) {
		JButton button = new JButton(buttonText);
		button.setToolTipText(toolTip);
		button.addActionListener(listener);
		return button;
	}

	private static JPanel createRow(JButton[] components) {
		JPanel panel = new JPanel();

		if (components != null) {
			panel.setLayout(new GridLayout(1, components.length, 10, 0));
			for (JComponent component : components) {
				panel.add(component);
			}
		}
		return panel;
	}

}
