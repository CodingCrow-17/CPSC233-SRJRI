package logicLayer;
import java.util.List;

import customExceptions.InvalidAttackException;
import customExceptions.InvalidMoveException;
import customExceptions.InvalidSelectException;


/* Handles AI for enemy units and determines strategy for attack/defense. 
 * should take inputs from multiple classes to determine non-random AI strategy
 */
public class EnemyAI {

	
	//TODO: loop through all surrounding tiles and find units that are the player unit
	public static void runEnemyTurn(GameLogic gameLogic) throws InvalidSelectException, InvalidMoveException, InvalidAttackException{
		List<Unit> units = gameLogic.getCurrentOwner().getUnits();
		for (Unit unit: units) {
			gameLogic.selectUnitAtPosition(unit.getTile().getPos().getInversePosition());
			for (int i = 0; i < gameLogic.findValidTileToMoveToPositions().size(); i++) {
				gameLogic.moveSelectedUnitTo(gameLogic.findValidTileToMoveToPositions().get(i).getInversePosition());
				gameLogic.calculateValidTileToAttack();
				if(gameLogic.calculateValidTileToAttack().isEmpty() == false) {
					gameLogic.performCombat(gameLogic.calculateValidTileToAttack().get(0).getInversePosition());
					break;
				}
			}
			gameLogic.haveSelectedUnitEndTurn();
		}
		
	}
}


