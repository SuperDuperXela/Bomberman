package gamemodel;

import java.awt.Graphics2D;

public class Bomb extends AbstractEntity implements EntityIf {

	private int x;

	private int y;

	private int time;

	private int radius;

	public Bomb(int x, int y, int radius, int time) {
		super(x, y);
		this.radius = radius;
		this.time = time;
	}

	public void explode() {
		
	}

	public void startCountdown() {

	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getX() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getY() {
		// TODO Auto-generated method stub

	}

}
