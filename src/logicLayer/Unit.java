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
}

