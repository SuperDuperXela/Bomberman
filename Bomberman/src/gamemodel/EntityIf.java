package gamemodel;

import java.awt.Graphics2D;

public interface EntityIf {
    /**
     * Methode, die beschreiben soll, wie etwas gerendert werden soll.
     * 
     * @param g
     * @param size
     * @param start
     */
    void render(Graphics2D g, int size, int start);

    int getX();

    int getY();
}