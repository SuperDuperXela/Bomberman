package players;

import java.util.Map;

import gamemodel.EntityIf;

public interface PlayerIf extends EntityIf {

    void move(int x, int y);

    void placeBomb();

    double getBombCountDownTime();

    void setBombCountDownTime(double bombCountDownTime);

    int getBombRadius();

    void setBombRadius(int bombRadius);

    int getBombCount();

    void setBombCount(int bombCount);

    double getSpeed();

    void setSpeed(double speed);

    void giveBackBomb();

    void pickUpUpgrade();

    void takeDamage();

    int getLives();

    int getCurrentMaxBombs();

    void setCurrentMaxBombs(int currentMaxBombs);

    public int getDirection();

    public void setDirection(int direction);

    public Map<String, String> getPlayerInformation();

    public int getPlayerNumber();
}