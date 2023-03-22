package mainmenu;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JoinRoomMenu {

	private static final int WINDOW_WIDTH = 900;
	private static final int WINDOW_HEIGHT = 600;

	public JoinRoomMenu() {
		JFrame frame = new JFrame("Join room");
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setLayout(null);

		JButton joinRoomButton = new JButton("Join!");
		joinRoomButton.setBounds(50, 50, 200, 100);
		joinRoomButton.addActionListener(e -> connect());

		frame.add(joinRoomButton);
		frame.setVisible(true);
	}

	private void connect() {
		Client client = new Client();
		client.startConnection("localhost", 5555); // "127.0.0.1"
		System.out.println(client.sendMessage("Ping"));
	}

}
