package viewcontroller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gamemodel.Bomberman;

public class View implements Observer, WindowListener {

	private JFrame frame;

	private Zeichenfeld zeichenfeld;

	private Bomberman bomberman;

	private WindowListener w;

	/**
	 * @param bomberman
	 */
	public View(Bomberman bomberman) {
		this.bomberman = bomberman;
		w = this;
		createWindow(bomberman);
	}

	public void addController(ControllerIf controller) {
		SwingUtilities.invokeLater(() -> frame.addKeyListener(controller));
	}

	private void createWindow(Bomberman bomberman) {
		SwingUtilities.invokeLater(() -> {
			frame = new JFrame("EXTREMBombermanDXRemasterdHD&Knuckles");
			frame.setSize(1600, 900);
			zeichenfeld = new Zeichenfeld(bomberman);
			frame.setContentPane(zeichenfeld);
			frame.addWindowListener(w);
			frame.setVisible(true);
		});
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
		bomberman.kill();
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