package userInterface;

import java.util.List;

import customExceptions.InvalidAttackException;
import customExceptions.InvalidInputException;
import customExceptions.InvalidMoveException;
import customExceptions.InvalidSelectException;
import logicLayer.EnemyAI;
import logicLayer.GameLogic;
import logicLayer.GameMap;
import logicLayer.Position;

public class UserLogicBridge {

	private GameLogic logic;
	
	public UserLogicBridge(GameMap gameMap) {
		logic = new GameLogic(gameMap);
	}
	
	public void interpretRegularInstruction(Instruction instruction) throws InvalidInputException{
		InstructionType type = instruction.getType();
		Position position = instruction.getPosition();
		switch (type) {
			case SELECT :
				logic.selectUnitAtPosition(position);
				break;
			case MOVE_TO :
				logic.moveSelectedUnitTo(position);
				break;
			case CANCEL :
				logic.deselectUnit();
				break;
			case WAIT :
				logic.haveSelectedUnitEndTurn();
				break;
			default :
				break;
		}
	}
	
	public void interpretLabelDisplayInstruction(Instruction instruction, InformationDisplayable informationDisplay) throws InvalidInputException {
		InstructionType type = instruction.getType();
		Position position = instruction.getPosition();
		switch (type) {
			case DISPLAY_UNIT_INFO :
				informationDisplay.displayUnitInfo(logic.getUnitAtPosition(position));
				break;
			case DISPLAY_ATTACK_FORECAST :
				informationDisplay.displayBattleForecastInfo(logic.forcastBattle(position));
				break;
			case ATTACK :
				attackOtherPosition(position, informationDisplay);
				break;
			default :
				break;
		}
	}
	
	public void interpretGridDisplayInstruction(Instruction instruction, GridDisplayable grid) {
		InstructionType type = instruction.getType();
		switch (type) {
			case FIND_MOVE_TILES :
				highlightMoveTiles(grid);
				break;
			case FIND_ATTACK_TILES :
				highlightAttackTiles(grid);
				break;
			case END_TURN :
				performEnemyTurn(grid);
				break;
			default :
				break;
		}
	}
	
	public boolean hasSelectedUnit() {
		return logic.hasSelectedUnit();
	}
	
	private void attackOtherPosition(Position position, InformationDisplayable informationDisplay) throws InvalidAttackException {
		informationDisplay.displayBattleResult(logic.performCombat(position));
		logic.haveSelectedUnitEndTurn();
	}
	
	private void highlightMoveTiles(GridDisplayable grid) {
		List<Position> positions = logic.findValidTileToMoveToPositions();
		grid.highlightMoveTiles(positions);
	}
	
	private void highlightAttackTiles(GridDisplayable grid) {
		List<Position> positions = logic.calculateValidTileToAttack();
		grid.highlightAttackTiles(positions);
	}
	
	private void performEnemyTurn(GridDisplayable grid) {
		logic.switchOwner();
		try {
			EnemyAI.runEnemyTurn(this.logic);
		} catch (InvalidSelectException e) {
		} catch (InvalidMoveException e) {
		} catch (InvalidAttackException e) {
			System.out.println("error 3");
			e.printStackTrace();
		}
		grid.refreshUnitPosition();
		logic.switchOwner();
	}
}
