package userInterface;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import logicLayer.OwnerType;
import logicLayer.Position;
import logicLayer.Tile;
import logicLayer.Unit;

public class Grid extends Group{
	
	private GridTile[][] gridTiles;
	private SelectionMarker selectionMarker;
	private List<UnitMarker> unitMarkers = new ArrayList<UnitMarker>();
	private int squareSideLength;
	private boolean enabled;
	
	private boolean hasHighlightedMoveTiles = false;
	
	public Grid(Tile[][] tiles, int height, int width) {
		super();
		gridTiles = new GridTile[tiles.length][tiles[0].length];
		squareSideLength = width/tiles.length;
		for(int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				GridTile gridTile = new GridTile(j*squareSideLength, i*squareSideLength, squareSideLength, squareSideLength, 
						Color.WHITESMOKE, Color.SKYBLUE, tiles[i][j]);
				this.getChildren().add(gridTile);
				gridTiles[i][j] = gridTile;
				if (tiles[i][j].hasUnit()) {
					int xCoordinate = j*squareSideLength + squareSideLength/2;
					int yCoordinate = i*squareSideLength + squareSideLength/2;
					int length = squareSideLength/4;
					UnitMarker unitMarker = new UnitMarker(xCoordinate,yCoordinate, length, length,tiles[i][j].getUnit());
					unitMarkers.add(unitMarker);
				}
			}
		}
		this.getChildren().addAll(unitMarkers);
		selectionMarker = new SelectionMarker(0,0,squareSideLength, squareSideLength, tiles[0].length, tiles.length);
		this.getChildren().add(selectionMarker);
		enable();
	}
	
	public void highlightMoveTiles(List<Position> positions){
		if (positions.isEmpty() == false) {
			hasHighlightedMoveTiles = true;
		}
		for (Position position : positions) {
			this.getGridTileAtPosition(position).changeToValidMoveColour();
		}
	}
	
	public void resetHightlight() {
		for(int i = 0; i < gridTiles.length; i++) {
			for (int j = 0; j < gridTiles[i].length; j++) {
				gridTiles[i][j].revertColour();
			}
		}
		hasHighlightedMoveTiles = false;
	}
	
	public boolean hasHighlightedMoveTiles() {
		return hasHighlightedMoveTiles;
	}
	
	public GridTile getGridTileAtPosition(Position position) {
		return getGridTileAtCoordinates(position.getXPosition(), position.getYPosition());
	}
	
	public GridTile getGridTileAtCoordinates(int x, int y) {
		return gridTiles[x][y];
	}
	
	public UnitMarker getUnitMarkerAtPostion(Position position) {
		UnitMarker unitMarker = null;
		for (UnitMarker marker : unitMarkers) {
			if (marker.getUnit().getTile().getPos().equals(position)) {
				unitMarker = marker;
				break;
			}
		}
		return unitMarker;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	public void enable() {
		selectionMarker.enableMarker();
		enabled = true;
	}
	public void disable() {
		selectionMarker.disableMarker();
		enabled = false;
	}
	
	public SelectionMarker getSelectionMarker() {
		return selectionMarker;
	}
	
}
