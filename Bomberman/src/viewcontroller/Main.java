package viewcontroller;

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

		// test
		g.addUpgradeType(new BombCountUpgrade(0, 0, g));
		g.addUpgradeType(new BombRadiusUpgrade(0, 0, g));
		g.addUpgradeType(new BombTimerUpgrade(0, 0, g));
		g.addUpgradeType(new SpeedUpgrade(0, 0, g));

		Player pl = new Player(4, 4, g);
		g.addPlayer(pl);

		Controller c = new Controller(pl);
		frame.addController(c);

		Player pl2 = new Player(3, 2.9, g);
		g.addPlayer(pl2);

		BombRadiusUpgrade sp1 = new BombRadiusUpgrade(0, 0, g);
		g.addUpgrade(sp1);

		BombCountUpgrade sp2 = new BombCountUpgrade(1, 0, g);
		g.addUpgrade(sp2);

		BombCountUpgrade sp3 = new BombCountUpgrade(1, 1, g);
		g.addUpgrade(sp3);

		BombCountUpgrade sp4 = new BombCountUpgrade(0, 1, g);
		g.addUpgrade(sp4);

		BombCountUpgrade sp5 = new BombCountUpgrade(2, 1, g);
		g.addUpgrade(sp5);

		for (int i = 1; i < 10; i++) {
			for (int j = 1; j < 8; j++) {
				if (i % 2 == 1 && j % 2 == 1) {
					SolidBlock sb = new SolidBlock(i, j, g);
					g.addBlock(sb);
				} else {
					BrokenBlock bb = new BrokenBlock(i, j, g);
					g.addBlock(bb);
				}
			}
		}

		try {
			Thread.sleep(000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
