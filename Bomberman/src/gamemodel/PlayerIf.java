package gamemodel;

public interface PlayerIf extends EntityIf {

    void move(double x, double y);

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

}
