package logicLayer;

public class Stats
{
	private int hp;
	private int def;
	private int mov;
	private int att;
	public int getHp()
	{
		return hp;
	}
	public int getDef()
	{
		return def;
	}
	public int getAtt()
	{
		return att;
	}
	public int getMov()
	{
		return mov;
	}
	public void setHp(int newHp)
	{
		hp=newHp;
	}
	public void setDef(int newDef)
	{
		def=newDef;
	}
	public void setAtt(int newAtt)
	{
		att=newAtt;
	}
	public void setMov(int newMov)
	{
		mov=newMov;
	}
}
