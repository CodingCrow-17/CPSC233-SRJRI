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
	private SelectionMarker primarySelectionMarker;
	private SelectionMarker unitSelectionMarker;
	private List<UnitMarker> unitMarkers = new ArrayList<UnitMarker>();
	private int squareSideLength;
	private boolean enabled;
	
	private UnitCommandSelection unitCommandSelection;
	private MetaCommandSelection metaCommandSelection;
	
	private boolean hasHighlightedMoveTiles = false;
	private boolean hasHighlightedAttackTiles = false;
	
	public Grid(Tile[][] tiles, int height, int width) {
		super();
		gridTiles = new GridTile[tiles.length][tiles[0].length];
		squareSideLength = width/tiles.length;
		for(int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				GridTile gridTile = new GridTile(j*squareSideLength, i*squareSideLength, squareSideLength, squareSideLength, 
						Color.WHITESMOKE, Color.SKYBLUE, Color.MISTYROSE,tiles[i][j]);
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
		primarySelectionMarker = new SelectionMarker(0,0,squareSideLength, squareSideLength, tiles[0].length, tiles.length, Color.LIGHTBLUE);
		this.getChildren().add(primarySelectionMarker);
		unitSelectionMarker = new SelectionMarker(0,0,squareSideLength, squareSideLength, tiles[0].length, tiles.length, Color.LAVENDERBLUSH);
		unitSelectionMarker.disableMarker();
		this.getChildren().add(unitSelectionMarker);
		unitCommandSelection = new UnitCommandSelection(50, 80);
		metaCommandSelection = new MetaCommandSelection(50,40);
		unitCommandSelection.setVisible(false);
		metaCommandSelection.setVisible(false);
		this.getChildren().add(this.unitCommandSelection);
		this.getChildren().add(this.metaCommandSelection);
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
	
	public void highlightAttackTiles(List<Position> positions){
		if (positions.isEmpty() == false) {
			hasHighlightedAttackTiles = true;
		}
		for (Position position : positions) {
			this.getGridTileAtPosition(position).changeToValidAttackColour();
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

	public boolean hasHighlightedAttackTiles() {
		return hasHighlightedAttackTiles;
	}
	
	public GridTile getGridTileAtPosition(Position position) {
		return getGridTileAtCoordinates(position.getXPosition(), position.getYPosition());
	}
	
	public GridTile getGridTileAtCoordinates(int x, int y) {
		return gridTiles[x][y];
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	public void enable() {
		primarySelectionMarker.enableMarker();
		enabled = true;
	}
	public void disable() {
		primarySelectionMarker.disableMarker();
		enabled = false;
	}
	
	public UnitMarker findSelectedUnitMarker() {
		return findSelectedUnitMarker(primarySelectionMarker);
	}
	
	private UnitMarker findSelectedUnitMarker(SelectionMarker selectionMarker) {
		UnitMarker unitMarker = null;
		for (UnitMarker marker : unitMarkers) {
			if (marker.getUnit().getTile().getPos().equals(selectionMarker.getCurrentPosition().getInversePosition())) {
				unitMarker = marker;
				break;
			}
		}
		return unitMarker;
	}
	
	public void moveSelectedUnitMarker() {
		UnitMarker unitMarker = this.findSelectedUnitMarker(unitSelectionMarker);
		unitMarker.moveTo(this.primarySelectionMarker.getCurrentPosition());
		this.resetHightlight();
	}
	
	public void moveUnitSelectionMarker() {
		this.unitSelectionMarker.setCurrentPosition(this.primarySelectionMarker.getCurrentPosition());
		this.unitSelectionMarker.enableMarker();
	}
	
	public SelectionMarker getSelectionMarker() {
		return primarySelectionMarker;
	}

	public SelectionMarker getUnitSelectionMarker() {
		return this.unitSelectionMarker;
	}
	
	public UnitCommandSelection getUnitCommandSelection() {
		return this.unitCommandSelection;
	}
	
	public MetaCommandSelection getMetaMenu() {
		return this.metaCommandSelection;
	}
	
	public void displayCommandSelectionMenu() {
		display(this.unitCommandSelection);
	}
	
	public void hideCommandSelectionMenu() {
		hide(this.unitCommandSelection);
	}
	
	public void displayMetaCommandMenu() {
		display(this.metaCommandSelection);
	}
	
	public void hideMetaCommandMenu() {
		hide(this.metaCommandSelection);
	}
	
	private void display(CommandSelection selection) {
		selection.setTranslateX(this.primarySelectionMarker.getTranslateX());
		selection.setTranslateY(this.primarySelectionMarker.getTranslateY());
		selection.setVisible(true);
		selection.enable();
	}
	
	private void hide(CommandSelection selection) {
		selection.setVisible(false);
		selection.disable();
	}
}
