package userInterface;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logicLayer.Tile;

public class GridTile extends Rectangle{
	
	private Color regularColour;
	private Color validMoveColour;
	private Tile tile;
	
	public GridTile(int xCoordinate, int yCoordinate, int width, int height, Color regularColour, Color validMoveColour, Tile tile) {
		super(xCoordinate, yCoordinate, width, height);
		this.regularColour = regularColour;
		this.validMoveColour = validMoveColour;
		this.setFill(regularColour);
		this.setStroke(Color.BLACK);
		this.setStrokeWidth(3);
		this.tile = tile;
	}
	
	public Tile getTile() {
		return this.tile;
	}
	
	public void changeToValidMoveColour() {
		this.setFill(validMoveColour);
		
	}
	
	public void revertColour() {
		this.setFill(regularColour);
	}
	
	public boolean isValidMoveTile() {
		return this.getFill().equals(this.validMoveColour);
	}
	
}
