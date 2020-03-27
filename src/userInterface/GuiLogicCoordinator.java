package userInterface;

import java.util.List;

import customExceptions.*;
import inputLayer.Instruction;
import inputLayer.InstructionType;
import javafx.scene.control.Label;
import logicLayer.GameLogic;
import logicLayer.GameMap;
import logicLayer.Position;

public class GuiLogicCoordinator {
	
	GameMap gameMap;
	GameLogic logic;
	
	public GuiLogicCoordinator(GameMap gameMap) {
		this.gameMap = gameMap;
		logic = new GameLogic(gameMap);
	}
	
	public void interpretRegularInstruction(Instruction instruction) throws InvalidInputException{
		InstructionType type = instruction.getType();
		Position position = instruction.getPosition();
		switch (type) {
			case SELECT :
				logic.selectUnitAtPosition(position);
				break;
			case MOVE :
				logic.moveSelectedUnitTo(position);
				break;
			case CANCEL :
				logic.deselectUnit();
				break;
			case ATTACK :
				attackOtherPosition(position);
				break;
			case WAIT :
				logic.haveSelectedUnitEndTurn();
				break;
			case END_TURN :
				performEnemyTurn();
				break;
			default :
				break;
		}
	}
	
	public void interpretLabelDisplayInstruction(Instruction instruction, InformationDisplay informationDisplay) throws InvalidInputException {
		InstructionType type = instruction.getType();
		Position position = instruction.getPosition();
		switch (type) {
			case DISPLAY_UNIT_INFO :
				informationDisplay.displayUnitInfo(logic.getUnitAtPosition(position));
				break;
			case DISPLAY_ATTACK_FORECAST :
				informationDisplay.displayBattleForecastInfo(logic.forcastBattle(position));
				break;
			default :
				break;
		}
	}
	
	public void interpretGridDisplayInstruction(Instruction instruction, Grid grid) {
		InstructionType type = instruction.getType();
		switch (type) {
			case FIND_MOVE_TILES :
				highlightMoveTiles(grid);
				break;
			case FIND_ATTACK_TILES :
				highlightAttackTiles(grid);
				break;
			default :
				break;
		}
	}
	
	public boolean hasSelectedUnit() {
		return logic.hasSelectedUnit();
	}
	
	private void attackOtherPosition(Position position) throws InvalidAttackException {
		logic.performCombat(position);
		logic.haveSelectedUnitEndTurn();
	}
	
	private void highlightMoveTiles(Grid grid) {
		List<Position> positions = logic.findValidTileToMoveToPositions();
		grid.highlightMoveTiles(positions);
	}
	
	private void highlightAttackTiles(Grid grid) {
		List<Position> positions = logic.calculateValidTileToAttack();
		grid.highlightAttackTiles(positions);
	}
	
	private void performEnemyTurn() {
		logic.switchOwner();
		//enemy logic goes here!
		logic.switchOwner();
	}
}
