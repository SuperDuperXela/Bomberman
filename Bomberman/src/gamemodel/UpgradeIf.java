package gamemodel;

public interface UpgradeIf extends EntityIf {

    void despawn();

    void newUpgrade(int x, int y, GameLogic gameLogic);

    void upgradePlayer(PlayerIf player);
}