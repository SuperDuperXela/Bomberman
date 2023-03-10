package upgrades;

import gamemodel.EntityIf;
import gamemodel.GameLogic;
import players.PlayerIf;

public interface UpgradeIf extends EntityIf {

    void despawn();

    void newUpgrade(int x, int y, GameLogic gameLogic);

    void upgradePlayer(PlayerIf player);
}