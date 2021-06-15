package viewcontroller;

import java.awt.event.KeyEvent;

import gamemodel.PlayerIf;

public class Controller implements ControllerIf {

	private PlayerIf player;
	
	private double boost = 3;

	public Controller(PlayerIf player) {
		this.player = player;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(player.getLives() == -1) {
			return;
		}
		if (boost > 1) {
			boost -= 0.5;
		}
		
		
//		System.out.println(e.getKeyCode());
		switch (e.getKeyCode()) {
		case 37:// left
			player.move(-1 * boost, 0);
			break;
		case 38:// up
			player.move(0, -1 * boost);
			break;
		case 39:// right
			player.move(1 * boost, 0);
			break;
		case 40:// down
			player.move(0, 1 * boost);
			break;
		case 17:// right ctrl
			player.pickUpUpgrade();
			break;
		case 96:// numpad 0
			player.placeBomb();
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		boost = 3;
	}

}
