package viewcontroller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import gamemodel.Bomberman;
import gamemodel.GameLogic;

public class View implements ObserverIf, WindowListener {

	private JFrame frame = new JFrame("EXTREMBombermanDXRemasterdHD&Knuckles");

	private Zeichenfeld zeichenfeld;

	private Bomberman bomberman;

	private WindowListener w;

	private GameLogic gameLogic;

	/**
	 * @param bomberman
	 */
	public View(Bomberman bomberman, GameLogic gameLogic) {
		this.bomberman = bomberman;
		this.gameLogic = gameLogic;
		w = this;
		createWindow();
	}

	public void addController(ControllerIf controller) {
		SwingUtilities.invokeLater(() -> frame.addKeyListener(controller));
	}

	private void createWindow() {
		SwingUtilities.invokeLater(() -> {
			frame.setSize(1600, 900);
			zeichenfeld = new Zeichenfeld(bomberman);
			frame.add(zeichenfeld);
			frame.addWindowListener(w);
			frame.setVisible(true);
			frame.requestFocus();
		});
	}

	@Override
	public synchronized void startCountdown() {
		try {
			JLabel countDown = new JLabel("", SwingConstants.CENTER);
			countDown.setBounds(frame.getWidth() / 3 - 70, frame.getHeight() / 2 - 60, 100, 50);
			countDown.setFont(new Font("Arial", Font.PLAIN, 50));
			countDown.setForeground(Color.BLACK);
			countDown.setOpaque(true);
			countDown.setBackground(Color.WHITE);
			countDown.setVisible(true);
			frame.add(countDown);
			countDown.setText("Diesen Text sollte man nicht sehen.");
			Thread.sleep(50);
			countDown.setText("3");
			Thread.sleep(1000);
			countDown.setText("2");
			Thread.sleep(1000);
			countDown.setText("1");
			Thread.sleep(1000);

			gameLogic.getSoundPlayer().playStartSound();
			frame.remove(countDown);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	@Override
	public void aktualisieren() {
		SwingUtilities.invokeLater(() -> zeichenfeld.repaint());
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// this method is empty because it doesn't get used but has to be implemented by
		// WindowListener
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// this method is empty because it doesn't get used but has to be implemented by
		// WindowListener
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// this method is empty because it doesn't get used but has to be implemented by
		// WindowListener
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// this method is empty because it doesn't get used but has to be implemented by
		// WindowListener
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// this method is empty because it doesn't get used but has to be implemented by
		// WindowListener
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// this method is empty because it doesn't get used but has to be implemented by
		// WindowListener
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// this method is empty because it doesn't get used but has to be implemented by
		// WindowListener
	}
}