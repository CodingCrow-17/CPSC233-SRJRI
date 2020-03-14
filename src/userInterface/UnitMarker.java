package userInterface;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import logicLayer.OwnerType;
import logicLayer.Unit;

public class UnitMarker extends Ellipse{

	private static final Color PLAYER_COLOUR = Color.BLUE;
	private static final Color ENEMY_COLOUR = Color.RED;
	
	private Unit unit;
	
	public UnitMarker(int xCoordinate, int yCoordinate, int width, int height, Unit unit) {
		super(xCoordinate, yCoordinate, width, height);
		this.unit = unit;
		if (unit.getOwner().getType().equals(OwnerType.PLAYER)){
			this.setFill(PLAYER_COLOUR);
		}
		else {
			this.setFill(ENEMY_COLOUR);
		}
	}
	
	public Unit getUnit() {
		return this.unit;
	}
	
}
