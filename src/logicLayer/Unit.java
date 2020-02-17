package logicLayer;


public class unit
{
	String name;
	Stats stuts;
	Owner owner;
	Pos position;
	public void moveTo(Pos pos)
	{
		position.x=pos.x;
		position.y=pos.y;
	}
	public boolean isDead()
	{
		if (stuts.hp==0) return true;
		return false;
	}
	public void takeDamage(int damageAmount)
	{
		stuts.hp-=takeDamae;
		if (stuts.hp<0) stuts.hp=0;
	}
	public Pos TileGotPos()
	{
		return position;
	}
}

