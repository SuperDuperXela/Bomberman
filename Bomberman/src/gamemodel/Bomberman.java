package gamemodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.SwingUtilities;

import viewcontroller.ControllerP1;
import viewcontroller.ControllerP2;
import viewcontroller.Observer;
import viewcontroller.View;

public class Bomberman extends Thread {

	private List<Observer> observer = new ArrayList<>();

	private AtomicBoolean stop = new AtomicBoolean(false);

	private GameLogic gameLogic;

	public Bomberman(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
	}
	
	public HashSet<EntityIf> getEntities() {
		return gameLogic.getEntities();
	}
	
	public List<Player> getPlayers() {
		return gameLogic.getPlayers();
	}

	public void addView(Observer o) {
		observer.add(o);
		o.aktualisieren();
	}

	public void removeView(Observer o) {
		observer.remove(o);
	}

	private void aktualisiereAlle() {
		for (Observer o : observer) {
			o.aktualisieren();
		}
	}

	private void refreshPlayerPosition() {
		List<Player> players = gameLogic.getPlayers();

		for (Player player : players) {
			switch (player.getDirection()) {
			case 1:
				player.move(-1, 0);
				break;
			case 2:
				player.move(0, -1);
				break;
			case 3:
				player.move(1, 0);
				break;
			case 4:
				player.move(0, 1);
				break;
			}
		}
	}

	private void innit() {
		Bomberman b = this;
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				View frame = new View(b);
				addView(frame);

				// test
				gameLogic.addUpgradeType(new BombCountUpgrade(0, 0, gameLogic));
				gameLogic.addUpgradeType(new BombRadiusUpgrade(0, 0, gameLogic));
				gameLogic.addUpgradeType(new BombTimerUpgrade(0, 0, gameLogic));
				gameLogic.addUpgradeType(new SpeedUpgrade(0, 0, gameLogic));

				Player pl = new Player(1, 1, gameLogic);
				gameLogic.addPlayer(pl);

				ControllerP1 c = new ControllerP1(pl);
				frame.addController(c);

				Player pl2 = new Player(gameLogic.getWidth() - 2, gameLogic.getHeight() - 2, gameLogic);
				gameLogic.addPlayer(pl2);
				
				ControllerP2 c2 = new ControllerP2(pl2);
				frame.addController(c2);

//				BombRadiusUpgrade sp1 = new BombRadiusUpgrade(0, 0, gameLogic);
//				gameLogic.addUpgrade(sp1);

//				BombCountUpgrade sp2 = new BombCountUpgrade(1, 0, gameLogic);
//				gameLogic.addUpgrade(sp2);

				BombCountUpgrade sp3 = new BombCountUpgrade(1, 1, gameLogic);
				gameLogic.addUpgrade(sp3);

//				BombCountUpgrade sp4 = new BombCountUpgrade(0, 1, gameLogic);
//				gameLogic.addUpgrade(sp4);

				BombCountUpgrade sp5 = new BombCountUpgrade(2, 1, gameLogic);
				gameLogic.addUpgrade(sp5);

				for (int i = 0; i < gameLogic.getWidth(); i++) {
					SolidBlock sboben = new SolidBlock(i, 0, gameLogic);
					gameLogic.addSolidBlock(sboben);
					SolidBlock sbunten = new SolidBlock(i, gameLogic.getHeight() - 1, gameLogic);
					gameLogic.addSolidBlock(sbunten);
				}
				
				for (int i = 1; i < gameLogic.getHeight() - 1; i++) {
					SolidBlock slinks = new SolidBlock(0, i, gameLogic);
					gameLogic.addSolidBlock(slinks);
					SolidBlock sbrechts = new SolidBlock(gameLogic.getWidth() - 1, i, gameLogic);
					gameLogic.addSolidBlock(sbrechts);
				}
				
				for (int i = 2; i < 11; i++) {
					for (int j = 2; j < 9; j++) {
						if (i % 2 == 0 && j % 2 == 0) {
							SolidBlock sb = new SolidBlock(i, j, gameLogic);
							gameLogic.addSolidBlock(sb);
						} else {
							BrokenBlock bb = new BrokenBlock(i, j, gameLogic);
							gameLogic.addBrokenBlock(bb);
						}
					}
				}
			}
		});
		
	}

	@Override
	public void run() {

		innit();

		try {
			while (stop.get() == false) {
				Thread.sleep(1000 / 60);
				aktualisiereAlle();
				refreshPlayerPosition();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);

	}

	/**
	 * Setzt {@code stop} auf {@code true} und beendet damit {@code run()} im Model.
	 * 
	 * @author Alex
	 */
	public void kill() {
		stop.set(true);
	}

}
