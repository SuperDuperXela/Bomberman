package viewcontroller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gamemodel.Bomberman;

public class View implements Observer, WindowListener {

	private JFrame frame;

	private Zeichenfeld zeichenfeld;

	private Bomberman m;

	private ControllerIf c;

	private WindowListener w;

	public View(Bomberman m) {
		this.m = m;
		w = this;
		createWindow(m);
	}

	public void addController(ControllerIf c) {
		this.c = c;
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				frame.addKeyListener(c);

			}
		});

	}

	private void createWindow(Bomberman m) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				frame = new JFrame("EXTREMBombermanDXRemasterdHD&Knuckles");
				frame.setSize(1600, 900);
				zeichenfeld = new Zeichenfeld(m);
				frame.setContentPane(zeichenfeld);
				frame.addWindowListener(w);
				frame.setVisible(true);
			}

		});
	}

	@Override
	public void aktualisieren() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				zeichenfeld.repaint();

			}
		});

	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		m.kill();
	}

	@Override
	public void windowClosed(WindowEvent e) {
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
