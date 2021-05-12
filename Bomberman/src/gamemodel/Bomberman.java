package gamemodel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import viewcontroller.Observer;

public class Bomberman extends Thread {

	private List<Entity> entities = new ArrayList<Entity>();

	private List<Observer> observer = new ArrayList<Observer>();

	private AtomicBoolean stop = new AtomicBoolean(false);
	
	

	public Bomberman() {
//		nur temporärer Code zum Testen
		int b = 79;
		int b1 = b + 1;
		for (int i = 0; i < 11; i++)
			for (int j = 0; j < 9; j++) {
				if (j % 2 == 1 && i % 2 == 1)
					addEntity(new TestBoxen(70 + i * b1, 70 + j * b1, b, b, Color.RED));
				else
					addEntity(new TestBoxen(70 + i * b1, 70 + j * b1, b, b));

			}
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public List<Entity> getEntities() {
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
