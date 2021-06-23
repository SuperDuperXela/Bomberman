package viewcontroller;

import java.awt.event.KeyEvent;

import gamemodel.KeyInput;
import gamemodel.PlayerIf;

public class ControllerP1 implements ControllerIf {

	private PlayerIf player;

	public ControllerP1(PlayerIf player) {
		this.player = player;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		//left
		case 37:
			player.setDirection(1);
			break;
		//up
		case 38:
			player.setDirection(2);
			break;
		//right
		case 39:
			player.setDirection(3);
			break;
		//down
		case 40:
			player.setDirection(4);
			break;
		//rightctrl
		case 17:
			player.pickUpUpgrade();
			break;
		//numpad0
		case 96:
			player.placeBomb();
			break;
		}
		/*
		switch (e.getKeyCode()) {
		case KeyInput.ARROWLEFT.getKeyCode():
			player.setDirection(1);
			break;
		case KeyInput.ARROWUP.getKeyCode():
			player.setDirection(2);
			break;
		case KeyInput.ARROWRIGHT.getKeyCode():
			player.setDirection(3);
			break;
		case KeyInput.ARROWDOWN.getKeyCode():
			player.setDirection(4);
			break;
		case KeyInput.ARROWDOWN.getKeyCode():
			player.pickUpUpgrade();
			break;
		case KeyInput.NUMPAD0.getKeyCode():
			player.placeBomb();
			break;
		}
		*/
//		System.out.println(e.getKeyCode());

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 37:// left
			if (player.getDirection() == 1) {
				player.setDirection(0);
			}
			break;
		case 38:// up
			if (player.getDirection() == 2) {
				player.setDirection(0);
			}
			break;
		case 39:// right
			if (player.getDirection() == 3) {
				player.setDirection(0);
			}
			break;
		case 40:// down
			if (player.getDirection() == 4) {
				player.setDirection(0);
			}
			break;
		}
	}

}
