package logicLayer;

import java.util.ArrayList;
import java.util.List;

public class BattleInstance {

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