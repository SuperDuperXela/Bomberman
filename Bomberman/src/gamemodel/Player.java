package gamemodel;

import java.awt.Graphics2D;

import viewcontroller.Controller;

public class Player extends AbstractEntity implements PlayerIf {

	private int bombCount = 1;

	private double x;

	private double y;

	private int animationType = 0;

	private double speed;
	
	private int bombRadius = 1;
	
	private double bombCountdownTime = 3.0;

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
	    //replace the 2's with playerInformation about radius and time
	    Bomb bomb = new Bomb((int)this.x, (int)this.y, this.bombRadius, this.bombCountdownTime, this.gameLogic);
	    bomb.startCountdown();
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
	
	public void pickUpUpgrade() {
	    for (UpgradeIf upgrade : this.gameLogic.getUpgrades()) {
		//is player on same field as upgrade
		//yes -> which upgrade
		switch(upgrade.getClass().getName()) {
        		case "gamemodel.BombCountUpgrade":
        		    //upgradeBombCount()
        		    break;
        		//etc
		}
	    }
	}
}
