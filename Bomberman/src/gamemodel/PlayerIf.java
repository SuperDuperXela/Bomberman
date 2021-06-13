package gamemodel;

public interface PlayerIf extends EntityIf {

	void move(double x, double y);
	
	void placeBomb();
}
