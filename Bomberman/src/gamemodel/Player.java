package gamemodel;

import java.awt.Graphics2D;

import viewcontroller.Controller;

public class Player extends AbstractEntity implements PlayerIf {

	private int bombCount = 1;

	private double x;

	private double y;

	private int animationType = 0;

	private double speed;

	private int destroyedBlock = 0;

	private Controller controller;
	
	private GameLogic gameLogic;

	public Player(Controller controller, double x, double y, GameLogic gameLogic) {
		super((int)x, (int)y, gameLogic);
		this.controller = controller;
		this.x = x;
		this.y = y;
	}

	@Override
	public void move(double x, double y) {
	    this.x += x;
	    this.y += y;
	}

	/**
	 * bombCount-- timer ablaufen lassen bombCount++
	 */
	@Override
	public void placeBomb() {
	    Bomb test = new Bomb((int)this.x, (int)this.y, 2, 2, this.gameLogic);
	    test.startCountdown();
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getX() {
	    return (int) this.x;

	}

	@Override
	public int getY() {
	    return (int) this.y;
	}
}
