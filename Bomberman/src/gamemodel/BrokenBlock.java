package gamemodel;

import java.awt.Graphics2D;

public class BrokenBlock extends AbstractBlock {
    
    	GameLogic gameLogic;

	public BrokenBlock(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
		// TODO Auto-generated constructor stub
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
	
	public void destroy() {
	    //Chances that a Upgrade spawns
	    if(Math.random() >= 0.5) {
		this.gameLogic.spawnUpgrade(this.getX(), this.getY());
	    }
	    

	    //......
	}

}
