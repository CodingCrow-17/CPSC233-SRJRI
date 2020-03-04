package output;
import logicLayer.GameMap;

public interface output {
	// Look at methods in text output and then copy them plus their argument??
	//take a look at the input receiver interface to see how this is done.
	public void printMap(GameMap gameMap);
	public void printCurrentTurnOwner(String currentOwner);
}
