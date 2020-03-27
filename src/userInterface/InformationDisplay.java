package userInterface;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import logicLayer.BattleForecast;
import logicLayer.Unit;

public class InformationDisplay extends HBox {

	private static final String WHITESPACE = "\t";

	private static final String NEW_LINE = "\r\n";
	
	private Label primaryLabel;
	private Label secondaryLabel;
	
	public InformationDisplay() {
		super();
		primaryLabel = new Label();
		this.getChildren().add(primaryLabel);
		secondaryLabel = new Label();
		this.getChildren().add(secondaryLabel);
	}

	public void displayUnitInfo(Unit unit) {
		primaryLabel.setText(unit.toString());
	}
	
	public void displayBattleForecastInfo(BattleForecast forecast) {
		
		StringBuilder primaryBuilder = new StringBuilder("");
		primaryBuilder.append("COMBAT FORECAST");
		primaryBuilder.append(WHITESPACE);
		primaryBuilder.append(NEW_LINE);
		primaryBuilder.append(forecast.getUserUnit().getName());
		primaryBuilder.append(NEW_LINE);
		String userUnitAttackForecast = "Damage: " + String.valueOf(forecast.getAttForecastDamage());
		if (forecast.willHitTwice(forecast.getUserUnit(), forecast.getEnemyUnit())) {
			userUnitAttackForecast = userUnitAttackForecast + " *2";
		}
		primaryBuilder.append(userUnitAttackForecast);
		primaryBuilder.append(NEW_LINE);
		primaryBuilder.append("Hit chance: ");
		primaryBuilder.append(forecast.getAttForecastPercent());
		primaryBuilder.append('%');
		primaryBuilder.append(NEW_LINE);
		primaryBuilder.append("Crit chance: ");
		primaryBuilder.append(forecast.getAttCritPercent());
		primaryBuilder.append('%');
		
		StringBuilder secondaryBuilder = new StringBuilder("");
		secondaryBuilder.append(NEW_LINE);
		secondaryBuilder.append(forecast.getEnemyUnit().getName());
		secondaryBuilder.append(NEW_LINE);
		String enemyUnitAttackForecast = "Damage: " + String.valueOf(forecast.getDefForecastDamage());
		if (forecast.willHitTwice(forecast.getEnemyUnit(), forecast.getUserUnit())) {
			enemyUnitAttackForecast = enemyUnitAttackForecast + " *2";
		}
		secondaryBuilder.append(enemyUnitAttackForecast);
		secondaryBuilder.append(NEW_LINE);
		secondaryBuilder.append("Hit chance: ");
		secondaryBuilder.append(forecast.getDefForecastPercent());
		secondaryBuilder.append('%');
		secondaryBuilder.append(NEW_LINE);
		secondaryBuilder.append("Crit chance: ");
		secondaryBuilder.append(forecast.getDefCritPercent());
		secondaryBuilder.append('%');
		
		
		System.out.println(forecast.getUserUnit().getName() + ":");
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
		if (forecast.willHitTwice(forecast.getEnemyUnit(), forecast.getUserUnit())) {
			System.out.println("Atk: " + forecast.getDefForecastDamage() + " x2");
		}
		else {
			System.out.println("Atk: " + forecast.getDefForecastDamage());
		}
		System.out.println("Hit: " + forecast.getDefForecastPercent());
		System.out.println("Crit: " + forecast.getDefCritPercent());
		
		this.primaryLabel.setText(primaryBuilder.toString());
		this.secondaryLabel.setText(secondaryBuilder.toString());
	}
}
