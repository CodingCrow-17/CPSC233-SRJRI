package logicLayer;
import logicLayer.BattleForecast;
import java.util.Random;

public class BattleInstance {
	private BattleForecast bf;
	private int attDamageDone;
	private int defDamageDone;
	
	//Use these when outputting, so we can display messages like "miss", "no damage", and "critical!"
	private boolean hasHit = false;
	private boolean hasDoneDamage = false;
	private boolean critActivated = false;
	
	public BattleInstance(BattleForecast aBattleForecast) {
		this.bf = new BattleForecast(aBattleForecast);
		this.attDamageDone = calculateDamageDone(bf.getAttForecastDamage(),bf.getAttForecastPercent(),bf.getAttCritPercent());
		this.defDamageDone = calculateDamageDone(bf.getDefForecastDamage(),bf.getDefForecastPercent(),bf.getDefCritPercent());
	}
	
	public int getAttDamageDone() {
		return this.attDamageDone;
	}
	public int getDefDamageDone() {
		return this.defDamageDone;
	}
	public boolean getHasHit() {
		return this.hasHit;
	}
	public boolean getHasDoneDamage() {
		return this.hasDoneDamage;
	}
	public boolean getCritActivated() {
		return this.critActivated;
	}
	
	private int calculateDamageDone(int atk, int hit, int crit) {
		Random rand = new Random();
		if (rand.nextInt(100) + 1 <= hit) {
			this.hasHit = true;
			
			if (atk > 0) {
				this.hasDoneDamage = true;
			}
			
			if (rand.nextInt(100) + 1 <= crit) {
				this.critActivated = true;
				return atk * 3;
			}
			return atk;
		}
		return 0;
	}
}