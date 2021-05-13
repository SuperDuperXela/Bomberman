package gamemodel;

import java.awt.Graphics2D;

public class Block implements Entity {

	private int x;

	private int y;

	private boolean solid;

	public Block(int x, int y, boolean solid) {
		this.x = x;
		this.y = y;
		this.solid = solid;
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	/**
	 * Zerstört den Block, falls dieser nicht solide ist.
	 * 
	 * @author Alex
	 */
	public void destroy() {

	}

}
