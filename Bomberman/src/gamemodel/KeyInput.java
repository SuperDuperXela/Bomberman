package gamemodel;

public enum KeyInput {
	
	CONTROLRIGHT(17),
	ARROWLEFT(37),
	ARROWUP(38),
	ARROWRIGHT(39),
	ARROWDOWN(40),
	NUMPAD0(96);
	
	public final int keyCode;
	
    private KeyInput(int keyCode) {
        this.keyCode = keyCode;
    }

	public int getKeyCode() {
		return keyCode;
	}
	

}
