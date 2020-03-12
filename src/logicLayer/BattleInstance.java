package logicLayer;
import logicLayer.BattleForecast;
import java.util.Random;

public class BattleInstance {
	private BattleForecast bf;
	private int attDamageDone;
	private int defDamageDone;
	
	//Use these when outputting, so we can display messages like "miss", "no damage", and "critical!"
	private boolean attHasHit = false;
	private boolean defhasHit = false;
	private boolean attHasDoneDamage = false;
	private boolean attCritActivated = false;
	private boolean defHasDoneDamage = false;
	private boolean defCritActivated = false;
	
	private boolean attTurn = true;
	
	public BattleInstance(BattleForecast aBattleForecast) {
		this.bf = new BattleForecast(aBattleForecast);
		this.attTurn = true;
		this.attDamageDone = calculateDamageDone(bf.getAttForecastDamage(),bf.getAttForecastPercent(),bf.getAttCritPercent());
		this.attTurn = false;
		this.defDamageDone = calculateDamageDone(bf.getDefForecastDamage(),bf.getDefForecastPercent(),bf.getDefCritPercent());
	}
	
	public BattleForecast getBattleForecast() {
		return new BattleForecast(bf);
	}
	
	public int getAttDamageDone() {
		return this.attDamageDone;
	}
	public int getDefDamageDone() {
		return this.defDamageDone;
	}
	public boolean getAttHasHit() {
		return this.attHasHit;
	}
	public boolean getDefHasHit() {
		return this.defhasHit;
	}
	public boolean getAttHasDoneDamage() {
		return this.attHasDoneDamage;
	}
	public boolean getDefHasDoneDamage() {
		return this.defHasDoneDamage;
	}
	public boolean getAttCritActivated() {
		return this.attCritActivated;
	}
	public boolean getDefCritActivated() {
		return this.defCritActivated;
	}
	
	private int calculateDamageDone(int atk, int hit, int crit) {
		Random rand = new Random();
		if (rand.nextInt(100) + 1 <= hit) {
			if (attTurn) {
				this.attHasHit = true;
			}
			else {
				this.defhasHit = true;
			}
			
			if (atk > 0) {
				if (attTurn) {
					this.attHasDoneDamage = true;
				}
				else {
					this.defHasDoneDamage = true;
				}
			}
			
			if (rand.nextInt(100) + 1 <= crit) {
				if (attTurn) {
					this.attCritActivated = true;
				}
				else {
					this.defCritActivated = true;
				}
				return atk * 3;
			}
			return atk;
		}
		return 0;
	}
}