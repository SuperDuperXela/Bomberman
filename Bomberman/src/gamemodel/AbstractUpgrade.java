package gamemodel;

public abstract class AbstractUpgrade extends AbstractEntity implements UpgradeIf {

    protected AbstractUpgrade(int x, int y, GameLogic gameLogic) {
	super(x, y, gameLogic);
    }
}