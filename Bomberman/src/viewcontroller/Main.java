package viewcontroller;

import gamemodel.Bomb;
import gamemodel.BombCountUpgrade;
import gamemodel.BombRadiusUpgrade;
import gamemodel.BombTimerUpgrade;
import gamemodel.Bomberman;
import gamemodel.BrokenBlock;
import gamemodel.GameLogic;
import gamemodel.Player;
import gamemodel.SolidBlock;
import gamemodel.SpeedUpgrade;

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
		Player pl = new Player(c, 2, 2.9, g);
		g.addPlayer(pl);
		
		Player pl2 = new Player(c, 3, 2.9, g);
		g.addPlayer(pl2);
		
		pl.placeBomb();
		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		pl2.placeBomb();
		
//		Bomb bo = new Bomb(2, 3, g, pl);
//		g.addBomb(bo);
//		bo.explode();
		
		SolidBlock sb = new SolidBlock(3, 1, g);
		g.addBlock(sb);
		
		BrokenBlock bb = new BrokenBlock(1, 3, g);
		g.addBlock(bb);
		
		BombCountUpgrade bcu = new BombCountUpgrade(4, 2, g);
		g.addUpgrade(bcu);
		
		BombRadiusUpgrade bru = new BombRadiusUpgrade(5, 2, g);
		g.addUpgrade(bru);
		
		BombTimerUpgrade btu = new BombTimerUpgrade(6, 2, g);
		g.addUpgrade(btu);
		
		SpeedUpgrade su = new SpeedUpgrade(7, 2, g);
		g.addUpgrade(su);
		//
		Player test = new Player(c, 5, 5, g);
		
//		System.out.println(test.getX() + ", " + test.getY());
		
		test.move(1.5, 1.5);
		
//		System.out.println(test.getX() + ", " + test.getY());
		
		test.move(1.5, 1.5);
		
//		System.out.println(test.getX() + ", " + test.getY());
		
		BombRadiusUpgrade test2 = new BombRadiusUpgrade(2,2,g);
		
//		System.out.print(test2.getClass().getName());
	}

}
