package gamemodel;

public abstract class AbstractBlock extends AbstractEntity {
    
    	private int x;
    	
    	private int y;
    	
    	private GameLogic gameLogic;
	
	public AbstractBlock(int x, int y, GameLogic gameLogic) {
		super(x, y, gameLogic);
	}

}
