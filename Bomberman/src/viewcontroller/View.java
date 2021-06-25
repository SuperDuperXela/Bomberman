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

    private WindowListener w;

    public View(Bomberman m) {
	this.m = m;
	w = this;
	createWindow(m);
    }

    public void addController(ControllerIf c) {
	SwingUtilities.invokeLater(() -> frame.addKeyListener(c));
    }

    private void createWindow(Bomberman m) {
	SwingUtilities.invokeLater(() -> {
	    frame = new JFrame("EXTREMBombermanDXRemasterdHD&Knuckles");
	    frame.setSize(1600, 900);
	    zeichenfeld = new Zeichenfeld(m);
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
	m.kill();
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