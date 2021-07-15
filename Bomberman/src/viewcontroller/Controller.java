package viewcontroller;

import java.awt.event.KeyEvent;

import gamemodel.PlayerIf;

public class Controller implements ControllerIf {

    private PlayerIf player;

    private int leftKey = KeyEvent.VK_A;

    private int rightKey = KeyEvent.VK_D;

    private int upKey = KeyEvent.VK_W;

    private int downKey = KeyEvent.VK_S;

    private int pickUpKey = KeyEvent.VK_SHIFT;

    private int placeBombKey = KeyEvent.VK_SPACE;

    /**
     * @param player
     */
    public Controller(PlayerIf player) {
	this.player = player;
    }

    public Controller(PlayerIf player, int leftKey, int rightKey, int upKey, int downKey, int pickUpKey,
	    int placeBombKey) {
	this.player = player;
	this.leftKey = leftKey;
	this.rightKey = rightKey;
	this.upKey = upKey;
	this.downKey = downKey;
	this.pickUpKey = pickUpKey;
	this.placeBombKey = placeBombKey;
    }

    @Override
    public void keyTyped(KeyEvent e) {
	// this method is empty because it doesn't get used but has to be implemented by
	// KeyListener
    }

    @Override
    public void keyPressed(KeyEvent e) {
	if (player.getLives() <= -1) {
	    return;
	}

	if (e.getKeyCode() == leftKey) {
	    player.setDirection(1);
	}

	if (e.getKeyCode() == rightKey) {
	    player.setDirection(3);
	}

	if (e.getKeyCode() == upKey) {
	    player.setDirection(2);
	}

	if (e.getKeyCode() == downKey) {
	    player.setDirection(4);
	}

	if (e.getKeyCode() == pickUpKey) {
	    player.pickUpUpgrade();
	}

	if (e.getKeyCode() == placeBombKey) {
	    player.placeBomb();
	}
    }

    @Override
    public void keyReleased(KeyEvent e) {
	if (player.getLives() <= -1) {
	    return;
	}

	if (e.getKeyCode() == leftKey && player.getDirection() == 1) {
	    player.setDirection(0);
	}

	if (e.getKeyCode() == rightKey && player.getDirection() == 3) {
	    player.setDirection(0);
	}

	if (e.getKeyCode() == upKey && player.getDirection() == 2) {
	    player.setDirection(0);
	}

	if (e.getKeyCode() == downKey && player.getDirection() == 4) {
	    player.setDirection(0);
	}
    }

    @Override
    public void setLeftKey(int key) {
	leftKey = key;
    }

    @Override
    public void setRightKey(int key) {
	rightKey = key;
    }

    @Override
    public void setUpKey(int key) {
	upKey = key;
    }

    @Override
    public void setDownKey(int key) {
	downKey = key;
    }

    @Override
    public void setPickUpKey(int key) {
	pickUpKey = key;
    }

    @Override
    public void setPlaceBombKey(int key) {
	placeBombKey = key;
    }

}
