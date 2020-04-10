package logicLayer;

public class BattleForecaster {
	
	private static final double GLOBAL_EVASION_MULTIPLIER = 1.2;

	public static boolean forecastCanAttack(Unit initiating, Unit receiving) {
		return true;
	}
	
	public static int forecastDamage(Unit initiating, Unit receiving) {
		int baseAttack = initiating.getStats().getAtt().getCurrentValue();
		double attackBonus = TileType.getAttackBonus(initiating.getTile().getTileType());
		int baseDefense = receiving.getStats().getDef().getCurrentValue();
		double defenseBonus = TileType.getDefenseBonus(receiving.getTile().getTileType());//Same as below not calling it right
		int damage = (int) ((baseAttack*attackBonus) - (baseDefense*defenseBonus));
		return damage;
	}
	
	public static int forecastCritDamage(Unit initiating, Unit receiving) {
		return forecastDamage(initiating, receiving) *3;
	}
	
	public static int forecastHitRate(Unit initiating, Unit receiving) { // calculation subject to change
		
		int baseHitRate = (int) (initiating.getStats().getDex().getCurrentValue());
		double hitRateBonus = TileType.getHitChanceBonus(initiating.getTile().getTileType());
		int baseEvadeChance = (int)(GLOBAL_EVASION_MULTIPLIER * receiving.getStats().getSpd().getCurrentValue());
		double evadeChanceBonus = TileType.getEvadeRateBonus(receiving.getTile().getTileType());
		int hitRate = (int) (100*((baseHitRate*hitRateBonus)/(baseEvadeChance*evadeChanceBonus)));
		if (hitRate > 100) {
			hitRate = 100;
		}
		return hitRate;
	}
	
	public static int forecastCritPercent(Unit initiating, Unit receiving) { // calculation subject to change
		return forecastHitRate(initiating, receiving)/10;
	}
	
	public static boolean forecastDoubleHit(Unit initiating, Unit receiving) {
		if (initiating.getStats().getSpd().getCurrentValue() >= 5 + receiving.getStats().getSpd().getCurrentValue()) {
			return true;
		}
		return false;
	}
	
}
