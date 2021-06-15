package gamemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import viewcontroller.Observer;

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
	entities.addAll(gameLogic.getBlocks());
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

    @Override
    public void run() {
	try {
	    while (stop.get() == false) {
		Thread.sleep(1000 / 60);
		aktualisiereAlle();
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
