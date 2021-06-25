package viewcontroller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import gamemodel.Bomberman;

public class Zeichenfeld extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 42;

    private transient Bomberman m;

    private int size = 70;

    private int start = 30;

    public Zeichenfeld(Bomberman m) {
	super();
	this.m = m;
    }

    @Override
    public void paintComponent(Graphics g) {
	g.clearRect(0, 0, this.getWidth(), this.getHeight());

	for (int i = 1; i < 12; i++)
	    for (int j = 1; j < 10; j++) {
		g.setColor(new Color(100, 100, 100));
		g.fillRect(start + i * size, start + j * size, size - 1, size - 1);
	    }

	m.renderEntities((Graphics2D) g, size, start);
    }
}