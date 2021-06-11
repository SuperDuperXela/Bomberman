package gamemodel;

public abstract class AbstractUpgrade extends AbstractEntity implements UpgradeIf  {

	private int x;
	
	private int y;
	
	public AbstractUpgrade(int x, int y) {
		super(x, y);
	}
	
	public abstract void Despawn();

}
