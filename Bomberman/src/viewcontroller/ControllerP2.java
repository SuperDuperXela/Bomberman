package viewcontroller;

import java.awt.event.KeyEvent;

import gamemodel.KeyInput;
import gamemodel.PlayerIf;

public class ControllerP2 implements ControllerIf {

	private PlayerIf player;

	public ControllerP2(PlayerIf player) {
		this.player = player;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		//a
		case 65:
			player.setDirection(1);
			break;
		//w
		case 87:
			player.setDirection(2);
			break;
		//d
		case 68:
			player.setDirection(3);
			break;
		//s
		case 83:
			player.setDirection(4);
			break;
		//leftshift
		case 16:
			player.pickUpUpgrade();
			break;
		//space
		case 32:
			player.placeBomb();
			break;
		}
		System.out.println(e.getKeyCode());

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 65:// left
			if (player.getDirection() == 1) {
				player.setDirection(0);
			}
			break;
		case 87:// up
			if (player.getDirection() == 2) {
				player.setDirection(0);
			}
			break;
		case 68:// right
			if (player.getDirection() == 3) {
				player.setDirection(0);
			}
			break;
		case 83:// down
			if (player.getDirection() == 4) {
				player.setDirection(0);
			}
			break;
		}
	}

}
