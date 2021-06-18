package gamemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import viewcontroller.Controller;
import viewcontroller.Observer;
import viewcontroller.View;

public class Bomberman extends Thread {

    private List<EntityIf> entities = new ArrayList<>();

    private List<Observer> observer = new ArrayList<>();

    private AtomicBoolean stop = new AtomicBoolean(false);

    private GameLogic gameLogic;

    public Bomberman(GameLogic gameLogic) {
	this.gameLogic = gameLogic;
    }

//	public void addEntity(EntityIf e) {
//		entities.add(e);
//	}
//
    public List<EntityIf> getEntities() {
	entities.clear();
	entities.addAll(gameLogic.getBrokenBlocks());
	entities.addAll(gameLogic.getSolidBlocks());
	entities.addAll(gameLogic.getUpgrades());
	entities.addAll(gameLogic.getPlayers());
	entities.addAll(gameLogic.getBombs());
	return entities;

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
	View frame = new View(this);
	addView(frame);

	// test
	gameLogic.addUpgradeType(new BombCountUpgrade(0, 0, gameLogic));
	gameLogic.addUpgradeType(new BombRadiusUpgrade(0, 0, gameLogic));
	gameLogic.addUpgradeType(new BombTimerUpgrade(0, 0, gameLogic));
	gameLogic.addUpgradeType(new SpeedUpgrade(0, 0, gameLogic));

	Player pl = new Player(0, 0, gameLogic);
	gameLogic.addPlayer(pl);

	Controller c = new Controller(pl);
	frame.addController(c);

	Player pl2 = new Player(3, 2.9, gameLogic);
	gameLogic.addPlayer(pl2);

	BombRadiusUpgrade sp1 = new BombRadiusUpgrade(0, 0, gameLogic);
	gameLogic.addUpgrade(sp1);

	BombCountUpgrade sp2 = new BombCountUpgrade(1, 0, gameLogic);
	gameLogic.addUpgrade(sp2);

	BombCountUpgrade sp3 = new BombCountUpgrade(1, 1, gameLogic);
	gameLogic.addUpgrade(sp3);

	BombCountUpgrade sp4 = new BombCountUpgrade(0, 1, gameLogic);
	gameLogic.addUpgrade(sp4);

	BombCountUpgrade sp5 = new BombCountUpgrade(2, 1, gameLogic);
	gameLogic.addUpgrade(sp5);

	for (int i = 1; i < 10; i++) {
	    for (int j = 1; j < 8; j++) {
		if (i % 2 == 1 && j % 2 == 1) {
		    SolidBlock sb = new SolidBlock(i, j, gameLogic);
		    gameLogic.addSolidBlock(sb);
		} else {
		    BrokenBlock bb = new BrokenBlock(i, j, gameLogic);
		    gameLogic.addBrokenBlock(bb);
		}
	    }
	}
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
