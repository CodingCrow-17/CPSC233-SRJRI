package logicLayer;

public class BattleForecast {
	private Unit userUnit;
	private Unit enemyUnit;
	
	private int[] forecastData;
	
	public BattleForecast(Unit aUserUnit, Unit aEnemyUnit) {
		this.userUnit = aUserUnit;
		this.enemyUnit = aEnemyUnit;
	}
	
	public Unit getUserUnit() {
		return new Unit(this.userUnit);
	}
	public Unit getEnemyUnit() {
		return new Unit(this.enemyUnit);
	}
	public int[] getForecastData() {
		int[] newFD = {};
		for (int i = 0; i < this.forecastData.length; i++) {
			newFD[i] = this.forecastData[i];
		}
		return newFD;
	}
	
	public int[] calculateForecast() {
		Tile userTile = this.userUnit.getTile();
		Tile enemyTile = this.enemyUnit.getTile();
		
		Unit userUnit = userTile.getUnit();
		Unit enemyUnit = enemyTile.getUnit();
		
		if (userTile.hasUnit() &&
			enemyTile.hasUnit()) {
			int attForecastDamage = calculateDamage(userUnit, enemyUnit);
			int defForecastDamage = calculateDamage(enemyUnit, userUnit);
			
			int attForecastPercent = calculateHitPercent(userUnit, enemyUnit);
			int defForecastPercent = calculateHitPercent(enemyUnit, userUnit);
			
			int attCritPercent = calculateCritPercent(userUnit, enemyUnit);
			int defCritPercent = calculateCritPercent(userUnit, enemyUnit);
			
			forecastData[0] = attForecastDamage;
			forecastData[1] = defForecastDamage;
			forecastData[2] = attForecastPercent;
			forecastData[3] = defForecastPercent;
			forecastData[4] = attCritPercent;
			forecastData[5] = defCritPercent;
		}
		return forecastData;
	}
	
	private int calculateDamage(Unit attUnit, Unit defUnit) {
		int oneHit = attUnit.getStats().getAtt().getCurrentValue() - defUnit.getStats().getDef().getCurrentValue();
		return oneHit;
	}
	
	private int calculateHitPercent(Unit attUnit, Unit defUnit) {
		double GLOBALHITMULTIPLIER = 1.2;
		int hitRate = (int) (100 * (attUnit.getStats().getDex().getCurrentValue()) / 
							(GLOBALHITMULTIPLIER * defUnit.getStats().getSpd().getCurrentValue()));
		if (hitRate > 100) {
			hitRate = 100;
		}
		return hitRate;
	}
		
	private int calculateCritPercent(Unit attUnit, Unit defUnit) {
		return 0;
	}
}
