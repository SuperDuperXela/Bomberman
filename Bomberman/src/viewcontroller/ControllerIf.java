package viewcontroller;

import java.awt.event.KeyListener;

public interface ControllerIf extends KeyListener {

    public void setLeftKey(int key);

    public void setRightKey(int key);

    public void setUpKey(int key);

    public void setDownKey(int key);

    public void setPickUpKey(int key);

    public void setPlaceBombKey(int key);
}