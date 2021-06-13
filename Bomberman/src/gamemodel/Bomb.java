package gamemodel;

import java.awt.Graphics2D;

public class Bomb extends AbstractEntity implements EntityIf {

	private int time;

	private int radius;

	public Bomb(int x, int y, int radius, int time, GameLogic gameLogic) {
		super(x, y, gameLogic);
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
	public int getX() {
	    return super.getX();

	}

	@Override
	public int getY() {
	    return super.getY();
	}

}
