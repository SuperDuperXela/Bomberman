package gamemodel;

public abstract class AbstractEntity implements EntityIf {
	
    	private int x;
	
	private int y;
	
	private GameLogic gameLogic;
	
	public AbstractEntity(int x, int y, GameLogic gameLogic) {
		this.x = x;
		this.y = y;
		this.gameLogic = gameLogic;
	}
	
	public int getX() {
	    return this.x;
	}
	
	public int getY() {
	    return this.y;
	}
	
	public void setX(int x) {
	    this.x = x;
	}
	
	public void setY(int y) {
	    this.y = y;
	}

}
