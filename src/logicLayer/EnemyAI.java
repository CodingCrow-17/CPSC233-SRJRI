package logicLayer;
import java.util.List;

import customExceptions.InvalidAttackException;
import customExceptions.InvalidMoveException;
import customExceptions.InvalidSelectException;


/* Handles AI for enemy units and determines strategy for attack/defense. 
 * should take inputs from multiple classes to determine non-random AI strategy
 */
public class EnemyAI {

	private GameMap gamemap;
	private Tile[][] tile;
	private Owner owner;
	private Unit unit;
	private BattleForecast bf;
	private Position position;
	private GameLogic gamelogic;
	private Stats stat;
	private TileType tileType;
	
	//INSTANCE VARS
	private int xposition;
	private int yposition;
	private List<Owner> players;
	private int movRange = 0;

	
	
	//constructor
	public EnemyAI(Owner owner, GameMap gamemap, Position xposition, Position yposition) {
		if (owner.getType().equals(OwnerType.ENEMY)) {
			this.owner = gamelogic.getCurrentOwner();
			this.movRange = stat.getMov().getCurrentValue();
		}
		this.xposition = position.getXPosition();
		this.yposition = position.getYPosition();
		this.gamemap = gamemap;
	}
	
	//TODO: loop through all surrounding tiles and find units that are the player unit
	public void EnemyAtk(GameLogic gamelogic) throws InvalidSelectException, InvalidMoveException, InvalidAttackException{
		gamelogic.selectUnitAtPosition(position);
		gamelogic.findValidTileToMoveToPositions();
		for (int i = 0; i < gamelogic.findValidTileToMoveToPositions().size();) {
			gamelogic.moveSelectedUnitTo(gamelogic.findValidTileToMoveToPositions().get(i));
			gamelogic.calculateValidTileToAttack();
			if(gamelogic.calculateValidTileToAttack() != null) {
				gamelogic.performCombat(gamelogic.calculateValidTileToAttack().get(0));
				gamelogic.haveSelectedUnitEndTurn();
			} else {
				gamelogic.haveSelectedUnitEndTurn();
				i++;
			}
		}
	}
}


