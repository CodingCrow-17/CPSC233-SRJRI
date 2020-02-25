package logicLayer;


public class Unit
{
	String name;
	Stats stats;
	Owner owner;
	Tile tile;
	
	public Unit(String name, Stats stats, Owner owner, Tile tile) {
		this.name = name;
		this.stats = stats;
		this.owner = owner;
		this.tile = tile;
	}
	
	public void moveTo(Tile tile)
	{
		this.tile = tile;
	}
	public boolean isDead()
	{
		if (stats.getHp()==0) return true;
		return false;
	}
	public void takeDamage(int damageAmount)
	{
		int newHp = stats.getHp() - damageAmount;
		stats.setHp(newHp);
		if (stats.getHp() < 0) {
			stats.setHp(0);
		}
	}
	public Tile getTile()
	{
		return tile;
	}
	private boolean checkIfUnitCanMoveToPosition(Position position) {
		Position unitPosition = tile.getPos();
		int xDirectionDifference = Math.abs(unitPosition.getX() - position.getX());
		int yDirectionDifference = Math.abs(unitPosition.getY() - position.getY());
		int sumOfDifference = xDirectionDifference + yDirectionDifference;
		if (sumOfDifference <= stats.getMov()) {
			return true;
		}
		else {
			return false;
		}
	}
	
}

