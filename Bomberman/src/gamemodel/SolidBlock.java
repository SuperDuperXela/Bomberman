package gamemodel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SolidBlock extends AbstractBlock {

    /**
     * @param x
     * @param y
     * @param gameLogic
     */
    public SolidBlock(int x, int y, GameLogic gameLogic) {
	super(x, y, gameLogic);
    }

    @Override
    public void render(Graphics2D g, int size, int start) {
	
		// TODO noch nicht fertig, nur zum Testen
		g.setColor(new Color(40, 40, 40));
		g.fillRect(start + getX() * size, start + getY() * size, size - 1, size - 1);
	/*BufferedImage image = null;
	try {
	    image = ImageIO.read(new File("media/images/blockSolid.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	g.drawImage(image, start + getX() * size, start + getY() * size, start + (getX() + 1) * size,
		start + (getY() + 1) * size, 0, 0, 48, 48, null);
*/
    }
}