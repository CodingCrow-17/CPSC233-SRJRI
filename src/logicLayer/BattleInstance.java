package logicLayer;
import logicLayer.BattleForecast;

import java.util.ArrayList;
import java.util.List;
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
	public BattleInstance(BattleInstance aBattleInstance) {
		this.bf = new BattleForecast(aBattleInstance.getBattleForecast());
		this.attTurn = true;
		this.attDamageDone = calculateDamageDone(this.bf.getAttForecastDamage(),this.bf.getAttForecastPercent(),this.bf.getAttCritPercent());
		this.attTurn = false;
		this.defDamageDone = calculateDamageDone(this.bf.getDefForecastDamage(),this.bf.getDefForecastPercent(),this.bf.getDefCritPercent());
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

	// another way of doing things,
	
	private List<AttackInstance> attackInstances = new ArrayList<AttackInstance>();
	private Unit attackingUnit;
	private Unit defendingUnit;
	
	public BattleInstance(Unit attackingUnit, Unit defendingUnit) {
		this.attackingUnit = attackingUnit;
		this.defendingUnit = defendingUnit;

	}
	
	public void runBattle() {
		AttackInstance initialAttack = new AttackInstance(attackingUnit, defendingUnit, AttackOrderType.INITIAl);
		initialAttack.runAttack();
		attackInstances.add(initialAttack);
		if (defendingUnit.isDead() == false) {
			AttackInstance counterAttack = new AttackInstance(defendingUnit, attackingUnit, AttackOrderType.COUNTER);
			counterAttack.runAttack();
			attackInstances.add(counterAttack);
			if (attackingUnit.isDead() == false && BattleForecaster.forecastDoubleHit(attackingUnit, defendingUnit)) {
				AttackInstance followUpAttack = new AttackInstance(attackingUnit, defendingUnit, AttackOrderType.FOLLOW_UP);
				followUpAttack.runAttack();
				attackInstances.add(followUpAttack);
			}
			else if (attackingUnit.isDead() == false && BattleForecaster.forecastDoubleHit(defendingUnit, attackingUnit)) {
				AttackInstance followUpAttack = new AttackInstance(defendingUnit, attackingUnit, AttackOrderType.FOLLOW_UP);
				followUpAttack.runAttack();
				attackInstances.add(followUpAttack);
			}
		}
	}
	
	public List<AttackInstance> getAttackInstances(){
		return this.attackInstances;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		for (AttackInstance attackInstance : attackInstances) {
			sb.append(attackInstance.getInstanceRecord());
		}
		return sb.toString();
	}
}