package logicLayer;

public class BattleForecaster {
	
	private static final double GLOBAL_EVASION_MULTIPLIER = 1.2;

	public static boolean forecastCanAttack(Unit initiating, Unit receiving) {
		return true;
	}
	
	public static int forecastDamage(Unit initiating, Unit receiving) {
		int damage = initiating.getStats().getAtt().getCurrentValue() - receiving.getStats().getDef().getCurrentValue();
		return damage;
	}
	
	public static int forecastCritDamage(Unit initiating, Unit receiving) {
		return forecastDamage(initiating, receiving) *3;
	}
	
	public static int forecastAttackPercent(Unit initiating, Unit receiving) { // calculation subject to change
		
		int hitRate = (int) (100 * (initiating.getStats().getDex().getCurrentValue()) / 
							(GLOBAL_EVASION_MULTIPLIER * receiving.getStats().getSpd().getCurrentValue()));
		if (hitRate > 100) {
			hitRate = 100;
		}
		return hitRate;
	}
	
	public static int forecastCritPercent(Unit initiating, Unit receiving) { // calculation subject to change
		return forecastAttackPercent(initiating, receiving)/10;
	}
	
	public static boolean forecastDoubleHit(Unit initiating, Unit receiving) {
		if (initiating.getStats().getSpd().getCurrentValue() >= 5 + receiving.getStats().getSpd().getCurrentValue()) {
			return true;
		}
		return false;
	}
	
}
