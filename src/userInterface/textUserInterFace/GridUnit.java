package userInterface.textUserInterFace;

import logicLayer.Unit;
import logicLayer.Position;

public class GridUnit {

	private Unit unit;
	private Position position;
	
	public GridUnit(Unit unit) {
		this.unit = unit;
		this.position = new Position(unit.getTile().getPos());
	}
	
	public Unit getUnit() {
		return unit;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public void moveTo(Position position) {
		int xCoordinate = position.getYPosition();
		int yCoordinate = position.getXPosition();
		this.position.setXPosition(xCoordinate);
		this.position.setYPosition(yCoordinate);
	}
	
	public void resetPosition() {
		this.position = new Position(unit.getTile().getPos());
	}
}
