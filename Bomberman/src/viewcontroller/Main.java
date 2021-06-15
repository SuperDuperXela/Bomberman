package viewcontroller;

import gamemodel.BombCountUpgrade;
import gamemodel.Bomberman;
import gamemodel.BrokenBlock;
import gamemodel.GameLogic;
import gamemodel.Player;
import gamemodel.SolidBlock;

public class Main {

    public static void main(String[] args) {

	GameLogic g = new GameLogic();

	Bomberman m = new Bomberman(g);
	m.start();

	View frame = new View(m);
	m.addView(frame);

	Controller c = new Controller(m);
	frame.addController(c);

	// test
	g.addUpgradeType(new BombCountUpgrade(0, 0, g));

	Player pl = new Player(c, 2, 2.9, g);
	g.addPlayer(pl);

	Player pl2 = new Player(c, 3, 2.9, g);
	g.addPlayer(pl2);

	pl.placeBomb();

	SolidBlock sb = new SolidBlock(3, 1, g);
	g.addBlock(sb);

	BrokenBlock bb = new BrokenBlock(1, 3, g);
	g.addBlock(bb);


	try {
	    Thread.sleep(5000);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	pl.move(-1, 0);
	pl.pickUpUpgrade();
    }

}
