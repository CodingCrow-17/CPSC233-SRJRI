package userInterface.textUserInterFace;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import logicLayer.BattleForecast;
import logicLayer.BattleInstance;
import logicLayer.GameMap;
import logicLayer.Position;
import logicLayer.Tile;
import userInterface.GridDisplayable;
import userInterface.graphicalUserInterface.customFxClasses.GridTile;
import userInterface.graphicalUserInterface.customFxClasses.UnitMarker;

public class GridPrinter implements GridDisplayable{

	final int CELL_WIDTH = 15;	
	final int CELL_HEIGHT = 4;
	
	private GridCell[][]gridCells;
	private List<GridUnit> gridUnits = new ArrayList<GridUnit>();
	private GridUnit selectedGridUnit;
	
	private boolean hasHighlightedAttackTiles = false;
	
	public GridPrinter(Tile[][] tiles) {
		this.gridCells = new GridCell[tiles.length][tiles[0].length];
		for(int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				GridCell gridCell = new GridCell(tiles[i][j]);
				this.gridCells[i][j] = gridCell;
				if (tiles[i][j].hasUnit()) {
					GridUnit gridUnit = new GridUnit(tiles[i][j].getUnit());
					gridUnits.add(gridUnit);
				}
			}
		}
	}
	
	public void selectGridUnitAt(Position position) {
		this.selectedGridUnit = getGridUnitAt(position.getXPosition(), position.getYPosition());
	}
	
	public GridUnit getSelectedGridUnit() {
		return this.selectedGridUnit;
	}
	
	public void deselectGridUnit() {
		this.selectedGridUnit = null;
	}
	
	public boolean getHasHighlightedAttackTiles() {
		return this.hasHighlightedAttackTiles;
	}
	
	public void removeDeadGridUnits() {
		List<GridUnit> toRemove = new ArrayList<GridUnit>();
		
		for (GridUnit gridUnit: gridUnits) {
			if (gridUnit.getUnit().isDead() == true) {
				toRemove.add(gridUnit);
			}
		}
		
		gridUnits.removeAll(toRemove);
	}
	
	public void printMap()
	{
		List<Position> positions = new ArrayList<Position>();
		printMap(positions);
		hasHighlightedAttackTiles = false;
	}

	@Override
	public void highlightMoveTiles(List<Position> positions) {
		printMap(positions);
		hasHighlightedAttackTiles = false;
	}
	@Override
	public void highlightAttackTiles(List<Position> positions) {
		if (positions.isEmpty()) {
			hasHighlightedAttackTiles = false;
		}
		else {
			hasHighlightedAttackTiles = true;
		}
		printMap(positions);
	}
	
	private void printMap(List<Position> highlightedPositions) {
		printColumnIndicators(gridCells[0]);
		// Loop through each row
		for (int rowInBoard = 0; rowInBoard<this.gridCells.length; rowInBoard++) 
		{ 
			// Loop through the height of a tile; each tile is CELL_HEIGHT spaces tall
			for (int rowOfText = 0; rowOfText<CELL_HEIGHT; rowOfText++) 
			{
				// Halfway through each row, print the row number
				if (rowOfText == CELL_HEIGHT / 2) {
					System.out.print(rowInBoard + " ");
				}
				else {
					System.out.print("  ");
				}
				
				// Loop through each column
				for (int columnInBoard = 0; columnInBoard<this.gridCells[rowInBoard].length; columnInBoard++) 
				{
					String content = "";
					if (rowOfText==0) 
					{
						if (coordinatesAreHighlighted(rowInBoard, columnInBoard, highlightedPositions)) {
							content = "#";
							content = padTextWithChar(content, '=');
						}
						else {
							content = "+";
							content = padTextWithChar(content, '-');
						}
					}
					else {
						if (coordinatesAreHighlighted(rowInBoard, columnInBoard, highlightedPositions)) {
							content = "I ";
						}
						else {
							content = "| ";
						}
						// On the first line of the tile, print tileType
						if (rowOfText == 1) {
							content = content+gridCells[rowInBoard][columnInBoard].getTile().getType().toString();
							content = padTextWithChar(content, ' ');
						}
						// On the 2nd line of the tile, print unit name if tile is occupied
						if(rowOfText==2 && this.getGridUnitAt(rowInBoard, columnInBoard)!= null) 
						{
							String unitName = this.getGridUnitAt(rowInBoard, columnInBoard).getUnit().getName();
							content = content + "name: " + unitName;
							content = padTextWithChar(content, ' ');
						}
						
						// On the 3rd line of the tile, print unitOwner if occupied;
						else if (rowOfText==3 && this.getGridUnitAt(rowInBoard, columnInBoard)!=null) 
						{
							String own = this.getGridUnitAt(rowInBoard, columnInBoard).getUnit().getOwner().getType().toString();
							content = content + own; 
							content = padTextWithChar(content, ' ');
						}
						else 
						{
							content = padTextWithChar(content, ' ');
						}
					}
					System.out.print(content);
				}
				System.out.println();
			}
		}
	}
	
	public void printCurrentTurnOwner(String currentOwner) {
		System.out.println("It is the "+ currentOwner + " turn!");
	}
	
	private boolean coordinatesAreHighlighted(int x, int y, List<Position> positions) {
		Position coordinatePosition = new Position(x,y);
		for (Position position: positions) {
			if (coordinatePosition.equals(position)) {
				return true;
			}
		}
		return false;
		
	}
	
	private String padTextWithChar(String text, char character) {
		for (int i = text.length(); i< CELL_WIDTH; i++) {
			text = text + character;
		}
		return text;
	}
	
	private GridUnit getGridUnitAt(int x, int y) {
		Position coordinatePosition = new Position(x,y); 
		for (GridUnit gridUnit: this.gridUnits) {
			if (gridUnit.getPosition().equals(coordinatePosition)) {
				return gridUnit;
			}
		}
		return null;
	}
	
	private void printColumnIndicators(GridCell[] columnList) {
		String content = " ";
		for (int column = 0; column < columnList.length; column++) {
			String indicator = "";
			for (int spaces = 0; spaces < CELL_WIDTH/2; spaces++) {
				indicator = indicator + " ";
			}
			indicator = indicator + column;
			indicator = padTextWithChar(indicator, ' ');
			content = content + indicator;
		}
		System.out.println(content);
	}

	@Override
	public void refreshUnitPosition() {
		for(GridUnit gridUnit: gridUnits) {
			gridUnit.resetPosition();
		}
	}
}
