package userInterface;

import java.util.List;

import logicLayer.Position;

public interface GridDisplayable {
	
	public void highlightMoveTiles(List<Position> positions);
	public void highlightAttackTiles(List<Position> positions);
}
