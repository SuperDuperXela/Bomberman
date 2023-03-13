package events;

import gamemodel.GameLogic;

public abstract class AbstractEvent implements EventIf {

	protected GameLogic gameLogic;

	protected AbstractEvent(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
	}

	@Override
	public void startEvent() {
	}

}
