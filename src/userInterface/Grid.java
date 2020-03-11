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
	private Group selectionMarker;
	private List<Group> unitMarkers = new ArrayList<Group>();
	private int squareSideLength;
	
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
		Rectangle selectionMarkerRepresentation = new Rectangle(0,0, squareSideLength, squareSideLength);
		selectionMarkerRepresentation.setFill(Color.TRANSPARENT);
		selectionMarkerRepresentation.setStrokeWidth(3);
		selectionMarkerRepresentation.setStroke(Color.GREENYELLOW);
		selectionMarker = new Group(selectionMarkerRepresentation);
		this.getChildren().add(selectionMarker);
	}
	
	public void moveSelectionUp() {
		if(selectionMarker.getTranslateY() == 0) {
			System.out.println("can't move");
		}
		else {
			selectionMarker.setTranslateY(selectionMarker.getTranslateY() - squareSideLength);
		}
	}
	
	public void moveSelectionDown() {
		if(selectionMarker.getTranslateY() == (squares.length-1)*squareSideLength) {
			System.out.println("can't move");
		}
		else {
			selectionMarker.setTranslateY(selectionMarker.getTranslateY() + squareSideLength);
		}
	}
	
	public void moveSelectionRight() {
		if(selectionMarker.getTranslateX() == (squares[0].length-1)*squareSideLength) {
			System.out.println("can't move");
		}
		else {
			selectionMarker.setTranslateX(selectionMarker.getTranslateX() + squareSideLength);
		}
	}
	
	public void moveSelectionLeft() {
		if(selectionMarker.getTranslateX() == 0) {
			System.out.println("can't move");
		}
		else {
			selectionMarker.setTranslateX(selectionMarker.getTranslateX() - squareSideLength);
		}
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
