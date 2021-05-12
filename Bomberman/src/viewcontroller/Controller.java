package viewcontroller;

import java.awt.event.KeyEvent;

import gamemodel.Bomberman;

public class Controller implements ControllerIf {

	private Bomberman m;

	public Controller(Bomberman m) {
		this.m = m;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
