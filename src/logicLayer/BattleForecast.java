package logicLayer;

public class BattleForecast {
	private Unit userUnit;
	private Unit enemyUnit;
	private int attForecastDamage;
	private int defForecastDamage;
	private int attForecastPercent;
	private int defForecastPercent;
	private int attCritPercent;
	private int defCritPercent;
	
	
	public BattleForecast(Unit aUserUnit, Unit aEnemyUnit) {
		setUserUnit(aUserUnit);
		setEnemyUnit(aEnemyUnit);
		this.attForecastDamage = calculateDamage(userUnit,enemyUnit);
		this.defForecastDamage = calculateDamage(enemyUnit,userUnit);
		this.attForecastPercent = calculateHitPercent(userUnit,enemyUnit);
		this.defForecastPercent = calculateHitPercent(enemyUnit,userUnit);
		this.attCritPercent = calculateCritPercent(userUnit,enemyUnit);
		this.defCritPercent = calculateCritPercent(enemyUnit,userUnit);
	}
	public BattleForecast(BattleForecast aBattleForecast) {
		setUserUnit(aBattleForecast.getUserUnit());
		setEnemyUnit(aBattleForecast.getEnemyUnit());
		this.attForecastDamage = aBattleForecast.getAttForecastDamage();
		this.defForecastDamage = aBattleForecast.getDefForecastDamage();
		this.attForecastPercent = aBattleForecast.getAttForecastPercent();
		this.defForecastPercent = aBattleForecast.getDefForecastPercent();
		this.attCritPercent = aBattleForecast.getAttCritPercent();
		this.defCritPercent = aBattleForecast.getDefCritPercent();
	}
	
	public Unit getUserUnit() {
		return new Unit(this.userUnit);
	}
	public Unit getEnemyUnit() {
		return new Unit(this.enemyUnit);
	}
	public void setUserUnit(Unit aUnit) {
		this.userUnit = new Unit(aUnit);
	}
	public void setEnemyUnit(Unit aUnit) {
		this.enemyUnit = new Unit(aUnit);
	}
	
	public int getAttForecastDamage() {
		return this.attForecastDamage;
	}
	public int getDefForecastDamage() {
		return this.defForecastDamage;
	}
	public int getAttForecastPercent() {
		return this.attForecastPercent;
	}
	public int getDefForecastPercent() {
		return this.defForecastPercent;
	}
	public int getAttCritPercent() {
		return this.attCritPercent;
	}
	public int getDefCritPercent() {
		return this.defCritPercent;
	}
	
	private int calculateDamage(Unit attUnit, Unit defUnit) {
		double GLOBALDEFENSEMULTIPLIER = defUnit.getTile().getTileType();//Same as below not calling it right
		int oneHit = attUnit.getStats().getAtt().getCurrentValue() - defUnit.getStats().getDef().getCurrentValue()*GLOBALDEFENSEMULTIPLIER;
		return oneHit;
	}
	
	private int calculateHitPercent(Unit attUnit, Unit defUnit, TileType type) {
		double GLOBALHITMULTIPLIER = attUnit.getTile().getTileType();//URGH how do you call this again
		 																	//I don't think I'm calling corrcetly
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
	public boolean willHitTwice(Unit attUnit, Unit defUnit, TileType type) {
		double GLOBALHITMULTIPLIER = attUnit.getTile().getTileType();//same as above not calling it right
		if (GLOBALHITMULTIPLIER*(attUnit.getStats().getSpd().getCurrentValue()) >= 5 + defUnit.getStats().getSpd().getCurrentValue()) {
			return true;
		}
		return false;
	}
}
