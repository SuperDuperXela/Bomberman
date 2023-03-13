package gamemodel;

public enum HologramTypes {
	ALERT("Alert"), //
	CAUTION("Caution"); //

	public final String hologramType;

	private HologramTypes(String hologramType) {
		this.hologramType = hologramType;
	}

	public String getHologramType() {
		return hologramType;
	}

}
