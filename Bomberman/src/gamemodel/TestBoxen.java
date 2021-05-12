package gamemodel;

import java.awt.Color;
import java.awt.Graphics2D;

public class TestBoxen implements Entity {
	/*
	 * 
	 * nur eine tempor�re Klasse zum Testen
	 * 
	 * 
	 */
	private int x, y, width, height;
	private Color c;

	
	public TestBoxen(int x, int y, int width, int height, Color c) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.c = c;
	}

	public TestBoxen(int x, int y, int width, int height) {
		this(x, y, width, height, Color.BLACK);
	}
	
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(c);
		g.fillRect(x, y, width, height);
	}

}
