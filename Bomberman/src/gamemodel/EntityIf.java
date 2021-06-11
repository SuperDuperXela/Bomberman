package gamemodel;

import java.awt.Graphics2D;

public interface EntityIf {
	/**
	 * Methode, die beschreiben soll, wie etwas gezeichnet werden soll. Name könnte
	 * man in "zeichnen" oder etwas besseres umbenennen.
	 * 
	 * @param g
	 */
	void render(Graphics2D g);
	
	void getX();
	
	void getY();

}
