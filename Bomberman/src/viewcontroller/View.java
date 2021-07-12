package viewcontroller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import gamemodel.Bomberman;
import sounds.SoundPlayer;

public class View implements ObserverIf, WindowListener {

	private JFrame frame = new JFrame("EXTREMBombermanDXRemasterdHD&Knuckles");

	private Zeichenfeld zeichenfeld;

	private Bomberman bomberman;

	private WindowListener w;

	/**
	 * @param bomberman
	 */
	public View(Bomberman bomberman) {
		this.bomberman = bomberman;
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
	
	public void startCountdown() {
		try {
				JLabel countDown = new JLabel("3ssssssssssssssssssssssssssssssssssss");
				countDown.setBounds(1070, 60, 100, 10);
				countDown.setFont(new Font("Arial", Font.PLAIN, 32));
				countDown.setForeground(Color.BLUE);
				countDown.setVisible(true);
				frame.add(countDown);
				Thread.sleep(1000);
				countDown.setText("2ssssssssssssssssssssssss");
				Thread.sleep(1000);
				countDown.setText("1ssssssssssssssssssssssssss");
				Thread.sleep(1000);
				// countDown.setVisible(false);

				SoundPlayer.playStartSound();
			} catch (Exception e2) {
				e2.printStackTrace();
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