package userInterface;

import javafx.scene.control.Label;
import logicLayer.BattleForecast;
import logicLayer.Unit;

public class InformationDisplay extends Label {

	public InformationDisplay() {
		super();
	}

	public InformationDisplay(String string) {
		super(string);
	}

	public void displayUnitInfo(Unit unit) {
		this.setText(unit.toString());
	}
	
	public void displayBattleForecastInfo(BattleForecast forecast) {
		
	}
	
}
