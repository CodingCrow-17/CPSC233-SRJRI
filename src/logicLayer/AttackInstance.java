package logicLayer;

import java.util.Random;

public class AttackInstance {
	
	private static final String NEW_LINE = "\r\n";

	private boolean hasRun = false;
	private boolean canAttack;
	private boolean didHit = false;
	private boolean didCrit = false;
	private int damageDone = 0;
	private String instanceRecord = "";
	
	private Unit attackingUnit;
	private Unit defensiveUnit;
	private AttackOrderType type;
	
	public AttackInstance(Unit attackingUnit, Unit defensiveUnit, AttackOrderType type) {
		this.attackingUnit = attackingUnit;
		this.defensiveUnit = defensiveUnit;
		canAttack = BattleForecaster.forecastCanAttack(this.attackingUnit, this.defensiveUnit);
		this.type = type;
	}
	
	public AttackOrderType getOrderType() {
		return type;
	}
	
	public Unit getAttackUnit() {
		return attackingUnit;
	}
	
	public Unit getDefendingUnit() {
		return defensiveUnit;
	}
	
	public String getInstanceRecord() {
		return this.instanceRecord;
	}
	
	private void createRecord() {
		StringBuilder sb = new StringBuilder("");
		sb.append(this.attackingUnit.getName());
		sb.append(" ");
		sb.append(AttackOrderType.getInSentence(type));
		sb.append(" ");
		sb.append(this.defensiveUnit.getName());
		sb.append(NEW_LINE);
		if (didCrit) {
			sb.append("and critted dealing: ");
			sb.append(this.damageDone);
			sb.append(NEW_LINE);
			sb.append(attackingUnit.getName());
			sb.append(": ");
			sb.append(attackingUnit.getCritLine());
		}
		else if (didHit) {
			sb.append("and hit, dealing: ");
			sb.append(this.damageDone);
		}
		else {
			sb.append("and missed!");
		}
		sb.append(NEW_LINE);
		if (this.defensiveUnit.isDead()) {
			sb.append(this.defensiveUnit.getName());
			sb.append(" ");
			sb.append("died!");
		}
		
		instanceRecord = sb.toString();
	}
	
	public void runAttack() {
		if (hasRun == false) {
			Random rand = new Random();
			if (rand.nextInt(100) +1 <= BattleForecaster.forecastAttackPercent(this.attackingUnit, this.defensiveUnit)) {
				didHit = true;
				if (rand.nextInt(100) +1 <= BattleForecaster.forecastCritPercent(this.attackingUnit, this.defensiveUnit)) {
					damageDone = BattleForecaster.forecastCritDamage(this.attackingUnit, this.defensiveUnit);
					didCrit = true;
				}
				else {
					damageDone = BattleForecaster.forecastDamage(this.attackingUnit, this.defensiveUnit);
					didCrit = false;
				}
			}
			else {
				damageDone = 0;
				didHit = false;
			}
			this.defensiveUnit.takeDamage(damageDone);
			createRecord();
		}
	}
	
}
