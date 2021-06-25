package gamemodel;

public abstract class AbstractUpgrade extends AbstractEntity implements UpgradeIf {

	public AbstractUpgrade(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
	}

	public abstract void despawn();

	public abstract void newUpgrade(int x, int y, GameLogic gameLogic);

	public int getX() {
		return super.getX();
	}

	public int getY() {
		return super.getY();
	}

	public abstract void upgradePlayer(PlayerIf player);

}
