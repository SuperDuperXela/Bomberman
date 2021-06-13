package gamemodel;

import java.awt.Graphics2D;

public interface EntityIf {
	/**
	 * Methode, die beschreiben soll, wie etwas gezeichnet werden soll.
	 * 
	 * @param g
	 */
	void render(Graphics2D g);
	
	int getX();
	
	int getY();

}
