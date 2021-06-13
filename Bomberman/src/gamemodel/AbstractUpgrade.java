package gamemodel;

public abstract class AbstractUpgrade extends AbstractEntity implements UpgradeIf  {
	
	public AbstractUpgrade(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
	}
	
	public abstract void Despawn();
	
	public int getX() {
	    return super.getX();
	}
	
	public int getY() {
	    return super.getY();
	}

}
