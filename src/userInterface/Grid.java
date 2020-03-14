package userInterface;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import logicLayer.OwnerType;
import logicLayer.Tile;
import logicLayer.Unit;

public class Grid extends Group{
	
	private Group[][] squares;
	private SelectionMarker selectionMarker;
	private List<Group> unitMarkers = new ArrayList<Group>();
	private int squareSideLength;
	
	private boolean enabled;
	
	public Grid(Tile[][] tiles, int height, int width) {
		super();
		squares = new Group[tiles.length][tiles[0].length];
		squareSideLength = width/tiles.length;
		for(int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				Rectangle square = new Rectangle(j*squareSideLength, i*squareSideLength, squareSideLength, squareSideLength);
				square.setFill(Color.ANTIQUEWHITE);
				square.setStrokeWidth(3);
				square.setStroke(Color.BLACK);
				Group group = new Group(square);
				this.getChildren().add(group);
				squares[i][j] = group;
				if (tiles[i][j].hasUnit()) {
					unitMarkers.add(drawUnit(i,j, tiles[i][j]));
				}
			}
		}
		selectionMarker = new SelectionMarker(0,0,squareSideLength, squareSideLength, tiles[0].length, tiles.length);
		this.getChildren().add(selectionMarker);
		enable();
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
	
	private Group drawUnit(int i, int j, Tile tile) {
		Ellipse unitMarker = new Ellipse(j*squareSideLength + squareSideLength/2,i*squareSideLength + squareSideLength/2,
				squareSideLength/4, squareSideLength/4);
		if (tile.getUnit().getOwner().getType().equals(OwnerType.PLAYER)) {
			unitMarker.setFill(Color.BLUE);
		}
		else {
			unitMarker.setFill(Color.RED);
		}
		Group group =new Group(unitMarker);
		this.getChildren().add(group);
		return group;
	}
	
}
