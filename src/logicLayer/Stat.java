package logicLayer;

import java.util.Random;

public class Stat
{
	private StatType type;
	private int currentValue=0;
	private double growthRate=0; // value between 0 and 1
	private int maxValue=0;
	
	public Stat(StatType type, int currentValue, double growthRate, int maxValue)
	{
		this.type=type;
		this.currentValue=currentValue;
		this.growthRate=growthRate;
		this.maxValue=maxValue;
	}
	
	public Stat(Stat preExistStat)
	{
		this.type=preExistStat.type;
		this.currentValue=preExistStat.currentValue;
		this.growthRate=preExistStat.growthRate;
		this.maxValue=preExistStat.maxValue;
	}
	
	public boolean levelUpStat() { // returns true if the stat leveled up in this call;
		Random generator =  new Random();
		double randomNum = generator.nextDouble();
		if (randomNum < growthRate && currentValue < maxValue) {
			currentValue++;
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setCurrentValue(int currentValue) {
		if (currentValue < maxValue) {
			this.currentValue = currentValue;
		}
	}
	
	public String toString() {
		String stringVal = StatType.toShortenedString(type) + ": " + currentValue;
		return stringVal;
	}
	
	public String getStatType()
	{
		return type.toString(type);
	}
	public int getCurrentValue(TileType type)
	{
		return currentValue ;
	}
	public double getGrowthRate()
	{
		return growthRate;
	}
	public int getMaxValue()
	{
		return maxValue;
	}
}
