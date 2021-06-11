package viewcontroller;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import gamemodel.EntityIf;
import gamemodel.Bomberman;

public class Zeichenfeld extends JPanel {

	private Bomberman m;

	public Zeichenfeld(Bomberman m) {
		super();
		this.m = m;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		for (EntityIf e : m.getEntities()) {
			e.render((Graphics2D) g);
		}
	}
}
