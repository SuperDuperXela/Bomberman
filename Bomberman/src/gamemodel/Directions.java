package gamemodel;

public enum Directions {
	CENTRAL("Central"), //
	RIGHT("Right"), //
	DOWN("Down"), //
	LEFT("Left"), //
	UP("Up");

	public final String direction;

	private Directions(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}
}
