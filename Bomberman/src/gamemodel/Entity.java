package gamemodel;

import java.awt.Graphics2D;

public interface Entity {
	
	
	/**
	 * Methode, die beschreiben soll, wie etwas gezeichnet werden soll. Name könnte
	 * man in "zeichnen" oder etwas besseres umbenennen.
	 * 
	 * @param g
	 * @author Alex
	 */
	void render(Graphics2D g);

}
