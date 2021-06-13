package gamemodel;

import java.awt.Graphics2D;

public class BombTimerUpgrade extends AbstractUpgrade {

	public BombTimerUpgrade(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Despawn() {
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

	@Override
	public void newUpgrade(int x, int y, GameLogic gameLogic) {
	    BombTimerUpgrade upgrade = new BombTimerUpgrade(x, y, gameLogic);
	    gameLogic.addUpgrade(upgrade);
	    
	}

}
