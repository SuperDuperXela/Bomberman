package gamemodel;

import java.awt.Graphics2D;

import viewcontroller.Controller;

public class Spieler implements Entity {

	private double x;

	private double y;

	private int animationType = 0;

	private double speed;

	private int bombCount = 1;

	private int destroyedBlocks = 0;
	
	private int explosionRange = 1;

	private Controller controller;

	public Spieler(Controller controller) {
		this.controller = controller;
	}

	public void move(double x, double y) {

	}

	public void placeBomb() {

	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub

	}
}
