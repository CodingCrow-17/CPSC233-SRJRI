package userInterface.textUserInterFace;

import logicLayer.BattleForecast;
import logicLayer.BattleInstance;
import logicLayer.Unit;
import userInterface.InformationDisplayable;

public class ExtraInfoTextDisplay implements InformationDisplayable{

	@Override
	public void displayUnitInfo(Unit unit) {
		System.out.println(unit.toString());
	}

	@Override
	public void displayBattleForecastInfo(BattleForecast forecast) {
		System.out.println(forecast.getUserUnit().getName() + ":");
		System.out.println(forecast.getUserUnit().currentHpToString());
		if (forecast.willHitTwice(forecast.getUserUnit(), forecast.getEnemyUnit())) {
			System.out.println("Atk: " + forecast.getAttForecastDamage() + " x2");
		}
		else {
			System.out.println("Atk: " + forecast.getAttForecastDamage());
		}
		System.out.println("Hit: " + forecast.getAttForecastPercent());
		System.out.println("Crit: " + forecast.getAttCritPercent());
		System.out.println("");
		
		System.out.println(forecast.getEnemyUnit().getName() + ":");
		System.out.println(forecast.getEnemyUnit().currentHpToString());
		if (forecast.willHitTwice(forecast.getEnemyUnit(), forecast.getUserUnit())) {
			System.out.println("Atk: " + forecast.getDefForecastDamage() + " x2");
		}
		else {
			System.out.println("Atk: " + forecast.getDefForecastDamage());
		}
		System.out.println("Hit: " + forecast.getDefForecastPercent());
		System.out.println("Crit: " + forecast.getDefCritPercent());
	}

	@Override
	public void displayBattleResult(BattleInstance instance) {
		System.out.println(instance.toString());
	}

}
