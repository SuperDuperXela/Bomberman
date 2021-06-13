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

	public Player(Controller controller, int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		this.controller = controller;
	}

	@Override
	public void move(double x, double y) {

	}

	/**
	 * bombCount-- timer ablaufen lassen bombCount++
	 */
	@Override
	public void placeBomb() {

	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub

	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub

	}
}
