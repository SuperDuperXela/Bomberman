package gamemodel;

public abstract class AbstractUpgrade extends AbstractEntity implements UpgradeIf  {
    
    	private int x;
	
	private int y;
	
	private GameLogic gameLogic;
	
	public AbstractUpgrade(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
	}
	
	public abstract void Despawn();
	
	public int getX() {
	    return this.x;
	}
	
	public int getY() {
	    return this.y;
	}

}
