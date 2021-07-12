package mainmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import gamemodel.Bomberman;
import gamemodel.GameLogic;

public class MainMenu {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Bombs and Blocks");
		frame.setSize(400, 700);
		frame.setVisible(true);
		frame.setLayout(null);
		
		//Von Alex hinzugefügt,
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton playButton = new JButton("Play");
		playButton.setBounds(10, 10, 350, 40);
		playButton.setVisible(true);
		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameLogic gameLogic = new GameLogic(13, 11);

				Bomberman bomberman = new Bomberman(gameLogic);
				bomberman.start();

			}

		});
		frame.add(playButton);

		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(10, 70, 350, 40);
		exitButton.setVisible(true);
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		frame.add(exitButton);
	}

}
