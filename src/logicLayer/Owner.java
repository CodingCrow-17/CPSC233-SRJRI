package logicLayer;

import java.util.ArrayList;
import java.util.List;

public class Owner {
	private List<Unit> units;
	private OwnerType type;
	
	public Owner(OwnerType type) {
		this.type = type;
		units = new ArrayList<Unit>();
	}
	
	public Owner(List<Unit> units, OwnerType type) {
		this.units = units;
		this.type = type;
	}
	
	public OwnerType getType() {
		return type;
	}
	
	public List<Unit> getUnits(){
		return units;
	}
	
	public void addUnit(Unit unit) {
		units.add(unit); // We want this unit reference the original unit
	}
	
	public void removeUnit(Unit unit) {
		units.remove(unit);
	}
	
	public String toString() {
		String StringVal = "own: " + type;
		return StringVal;
	}
	
	public boolean checkIfAllUnitsMoved() {
		boolean allUnitsHaveMoved = true;
		for (Unit unit : units) {
			if (unit.getHasMoved() == false) {
				allUnitsHaveMoved = false;
				break;
			}
		}
		return allUnitsHaveMoved;
	}
	
	public void refreshAllUnits() {
		for (Unit unit : units) {
			unit.resetMove();
		}
	}
}
