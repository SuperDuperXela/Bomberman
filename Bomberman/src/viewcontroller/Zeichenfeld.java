package viewcontroller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import gamemodel.EntityIf;
import gamemodel.TestBoxen;
import gamemodel.Bomberman;

public class Zeichenfeld extends JPanel {

	private Bomberman m;
	
	private int size = 80;
	
	private int start = 30; 

	public Zeichenfeld(Bomberman m) {
		super();
		this.m = m;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		int b = 79;
		for (int i = 0; i < 11; i++)
			for (int j = 0; j < 9; j++) {
				g.setColor(new Color(100, 100, 100));
				g.fillRect(start + i * size, start + j * size, size-1, size-1);
			}
		for (EntityIf e : m.getEntities()) {
			e.render((Graphics2D) g, size, start);
		}
	}
}
