package logicLayer;

public class Stats
{
	private int hp;
	private int def;
	private int mov;
	private int att;
	
	public Stats(int aHp, int aDef, int aMov, int anAtt) // Regular constructor
	{
		hp = aHp;
		def = aDef;
		mov = aMov;
		att = anAtt;
	}
	
	public Stats(Stats aStats) // Copy constructor
	{
		hp = aStats.hp;
		def = aStats.def;
		mov = aStats.mov;
		att = aStats.att;
	}
	
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
