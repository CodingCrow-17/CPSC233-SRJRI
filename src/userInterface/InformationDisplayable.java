package userInterface;

import logicLayer.BattleForecast;
import logicLayer.BattleInstance;
import logicLayer.Unit;

public interface InformationDisplayable {

	public void displayUnitInfo(Unit unit);
	public void displayBattleForecastInfo(BattleForecast forecast);
	public void displayBattleResult(BattleInstance instance);	
}
