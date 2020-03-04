package logicLayer;


public class Unit
{
	private String name;
	private Stats stats;
	private int currentHp;
	private boolean hasMoved;
	private Owner owner;
	private Tile tile;
	
	public Unit(String name, Stats stats, Owner owner, Tile tile) {
		this.name = name;
		this.stats = new Stats(stats);
		this.owner = owner;
		this.tile = tile;
		this.hasMoved = false;
		this.currentHp = stats.getHp().getCurrentValue();
	}
	
	public Unit(Unit unit) {
		this.name = unit.getName();
		this.stats = new Stats(unit.getStats());
		this.hasMoved = false;
		this.owner = unit.getOwner();  //should share reference
		this.tile = tile; //should share reference?
	}
	
	public int getCurrentHp() {
		return currentHp;
	}
	
	public Stats getStats() {
		return stats;
	}
	
	public Owner getOwner() {
		return owner;
	}
	
	public boolean getHasMoved() {
		return hasMoved;
	}
	
	public void resetMove() {
		hasMoved = false;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String currentHpToString() {
		String stringVal = stats.getHp().getStatType() + ": " + currentHp + "/" + stats.getHp().getCurrentValue();
		return stringVal;
	}
	
	public void moveTo(Tile finalTile)
	{
		this.tile = tile;
		hasMoved = true;
	}
	
	public boolean isDead()
	{
		return (currentHp == 0);
	}

	public void takeDamage(int damageAmount)
	{
		currentHp = currentHp - damageAmount;
		if (currentHp < 0) {
			currentHp = 0;
		}
	}
	
	public Tile getTile()
	{
		return tile;
	}
	
	private boolean checkIfUnitCanMoveToPosition(Position position) {
		Position unitPosition = tile.getPos();
		int xDirectionDifference = Math.abs(unitPosition.getXPosition() - position.getXPosition());
		int yDirectionDifference = Math.abs(unitPosition.getYPosition() - position.getYPosition());
		int sumOfDifference = xDirectionDifference + yDirectionDifference;
		if (sumOfDifference <= stats.getMov().getCurrentValue()) {
			return true;
		}
		else {
			return false;
		}
	}
	
}

