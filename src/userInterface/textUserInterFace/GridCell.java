package userInterface.textUserInterFace;

import logicLayer.Tile;

public class GridCell {
	
	private Tile tile;
	
	public GridCell(Tile tile) {
		this.tile = tile;
	}
	
	public Tile getTile() {
		return tile;
	}

}
