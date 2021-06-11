package gamemodel;

public abstract class AbstractEntity implements EntityIf {
	
	private int x;
	private int y;
	
	public AbstractEntity(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
