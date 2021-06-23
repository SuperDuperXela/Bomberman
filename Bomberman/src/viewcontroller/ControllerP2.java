package viewcontroller;

import java.awt.event.KeyEvent;

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
		case KeyEvent.VK_A:
			player.setDirection(1);
			break;
		//w
		case KeyEvent.VK_W:
			player.setDirection(2);
			break;
		//d
		case KeyEvent.VK_D:
			player.setDirection(3);
			break;
		//s
		case KeyEvent.VK_S:
			player.setDirection(4);
			break;
		//leftshift
		case KeyEvent.VK_SHIFT:
			player.pickUpUpgrade();
			break;
		//space
		case KeyEvent.VK_SPACE:
			player.placeBomb();
			break;
		}
//		System.out.println(e.getKeyCode());

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:// left
			if (player.getDirection() == 1) {
				player.setDirection(0);
			}
			break;
		case KeyEvent.VK_W:// up
			if (player.getDirection() == 2) {
				player.setDirection(0);
			}
			break;
		case KeyEvent.VK_D:// right
			if (player.getDirection() == 3) {
				player.setDirection(0);
			}
			break;
		case KeyEvent.VK_S:// down
			if (player.getDirection() == 4) {
				player.setDirection(0);
			}
			break;
		}
	}

}
