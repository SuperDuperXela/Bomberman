package gamemodel;

import java.awt.Color;
import java.awt.Graphics2D;

public class TestBoxen implements EntityIf {
	/*
	 * 
	 * nur eine temporäre Klasse zum Testen
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
	public void render(Graphics2D g, int size, int start) {
		g.setColor(c);
		g.fillRect(x, y, width, height);
	}

	@Override
	public int getX() {
	    return x;
		
	}

	@Override
	public int getY() {
	    return y;
		
	}

}
