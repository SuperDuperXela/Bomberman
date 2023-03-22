package mainmenu;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class CreateRoomMenu implements WindowListener {

	private static final int WINDOW_WIDTH = 900;
	private static final int WINDOW_HEIGHT = 600;

	private HostServer server;

	public CreateRoomMenu() {

		JFrame frame = new JFrame("Create room");
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setLayout(null);

		frame.setVisible(true);

		Runnable runnable = () -> {
			server = new HostServer();
			server.start(5555);
		};
		new Thread(runnable).start();

	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		server.stop();
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}
}
