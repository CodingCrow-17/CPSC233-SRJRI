package logicLayer;
import java.util.List;


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
	private int atk = 0;
	private int def = 0;
	
	
	//constructor
	public EnemyAI(Owner owner, GameMap gamemap, Position xposition, Position yposition, int movRange, int atk, int def) {
		if (owner.getType().equals(OwnerType.ENEMY)) {
			this.owner = gamelogic.getCurrentOwner();
			this.movRange = stat.getMov().getCurrentValue();
			this.atk = stat.getAtt().getCurrentValue();
			this.def = stat.getDef().getCurrentValue();
		}
		this.xposition = position.getXPosition();
		this.yposition = position.getYPosition();
		this.gamemap = gamemap;
	}
	
	//TODO: loop through all surrounding tiles and find units that are the player unit
	public boolean checkIfPlayersInMoveRange(Unit unit, Tile tile, GameMap gamemap, Owner owner) {
		for (){ 
			if (yposition >= 0 && yposition < gamemap.getTiles().length) {
				if (xposition >= 0 && xposition < gamemap.getTiles()[0].length) {
					if (tile.hasUnit()) {
						this.owner = unit.getOwner();
						if (owner.getType().equals(OwnerType.PLAYER)){
							players.add(owner);
							return true;
						}
					}
				}
			}
		}
			return false;
	}
	
	/*
	 * TODO:
	 * select a unit to manipulate, loop through all tiles valid to attack and if it has enemy, then attack.
	 * call 
	 * 
	 * complete method to calculate moveset if there are players in range.
	 */
	public boolean AtkDecision (BattleForecast bf, Stat stat) {
		if (players != null) {
			int thisatkstat = atk;
			int playerdefstat = players.stat.getDef().getCurrentValue();
			if (thisatkstat - playerdefstat > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}


