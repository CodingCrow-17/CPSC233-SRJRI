package logicLayer;

public class Stats
{
	private Stat hp;
	private Stat att;
	private Stat spd;
	private Stat dex;
	private Stat def;
	private Stat mov;

	
	public Stats(Stat hp, Stat att, Stat spd, Stat dex, Stat def, Stat mov) {
		this.hp = hp;
		this.att  = att;
		this.spd = spd;
		this.dex = dex;
		this.def = def;
		this.mov = mov;
	}
	
	public Stats(Stats preExistingStats) {
		hp = new Stat(preExistingStats.getHp());
		att = new Stat(preExistingStats.getAtt());
		spd = new Stat(preExistingStats.getSpd());
		dex = new Stat(preExistingStats.getDex());
		def = new Stat(preExistingStats.getDef());
		mov = new Stat(preExistingStats.getMov());
	}
	
	public Stat getSpd()
	{
		return def;
	}
	public Stat getDex()
	{
		return att;
	}
	public Stat getHp()
	{
		return hp;
	}
	public Stat getDef()
	{
		return def;
	}
	public Stat getAtt()
	{
		return att;
	}
	public Stat getMov()
	{
		return mov;
	}
}
