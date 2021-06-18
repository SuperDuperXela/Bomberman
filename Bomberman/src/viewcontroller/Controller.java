package viewcontroller;

import java.awt.event.KeyEvent;

import gamemodel.PlayerIf;

public class Controller implements ControllerIf {

    private PlayerIf player;

    public Controller(PlayerIf player) {
	this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
	switch (e.getKeyCode()) {
	case 37:// left
	    player.setDirection(1);
	    break;
	case 38:// up
	    player.setDirection(2);
	    break;
	case 39:// right
	    player.setDirection(3);
	    break;
	case 40:// down
	    player.setDirection(4);
	    break;
	case 17:// right ctrl
	    player.pickUpUpgrade();
	    break;
	case 96:// numpad 0
	    player.placeBomb();
	    break;
	}

	System.out.println(e.getKeyCode());

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
