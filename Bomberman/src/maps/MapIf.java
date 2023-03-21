package maps;

public interface MapIf {

	Integer[][] getSpawnpoints();

	void createBlocks();

	void startPlayFieldSizeReduction();

	void startTimerPlayFieldReduction(long delayMilliSeconds);
}
