package output;

public class TwoDimensionalUnitDisplayable implements UnitDisplayable {

	DisplayObject idleDisplay;
	DisplayObject moveDisplay;
	DisplayObject attackDisplay;
	DisplayObject reactDisplay;
	
	@Override
	public DisplayObject getIdleDisplay() {
		return idleDisplay;
	}

	@Override
	public DisplayObject getMovDisplay() {
		return moveDisplay;
	}

	@Override
	public DisplayObject getAttackDisplay() {
		return attackDisplay;
	}

	@Override
	public DisplayObject getReactDisplay() {
		return reactDisplay;
	}

}
