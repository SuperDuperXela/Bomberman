package viewcontroller;

import java.awt.event.KeyEvent;

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
		case KeyEvent.VK_LEFT:
			player.setDirection(1);
			break;
		case KeyEvent.VK_UP:
			player.setDirection(2);
			break;
		case KeyEvent.VK_RIGHT:
			player.setDirection(3);
			break;
		case KeyEvent.VK_DOWN:
			player.setDirection(4);
			break;
		case KeyEvent.VK_CONTROL:
			player.pickUpUpgrade();
			break;
		//numpad0
		case KeyEvent.VK_NUMPAD0:
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
		case KeyEvent.VK_LEFT:// left
			if (player.getDirection() == 1) {
				player.setDirection(0);
			}
			break;
		case KeyEvent.VK_UP:// up
			if (player.getDirection() == 2) {
				player.setDirection(0);
			}
			break;
		case KeyEvent.VK_RIGHT:// right
			if (player.getDirection() == 3) {
				player.setDirection(0);
			}
			break;
		case KeyEvent.VK_DOWN:// down
			if (player.getDirection() == 4) {
				player.setDirection(0);
			}
			break;
		}
	}

}
