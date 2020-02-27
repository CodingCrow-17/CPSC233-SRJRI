package logicLayer;


public class Unit
{
	String name;
	Stats stats;
	Owner owner;
	Position position;
	
	public Unit(String name, Stats stats, Owner owner, Position position) {
		
	}
	
	public String getName()
	{
		return name;
	}
	
	public Stats getStats()
	{
		Stats newStats = new Stats(stats);
		return newStats;
	}
	
	
	public void moveTo(Position pos)
	{
		position = pos;
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
	public Position TileGotPos()
	{
		return position;
	}
}

